import React,{ createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom'
import api from "../api/axios"; 
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext()
export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const raw = localStorage.getItem('hrms_user')
        if (raw) setUser(JSON.parse(raw))
    }, [])

    const login = async (data) => {
        const res = await api.post("/users/login",data);

        const token = res.data.jwt;
        if(!token) throw new Error("Token missing");

        const decode = jwtDecode(token);

        const userData = {
            id : decode.user_id,
            email : decode.sub,
            role : decode.authorities

        }
        localStorage.setItem('token', res.data.jwt)
        localStorage.setItem('hrms_user', JSON.stringify(userData))

        setUser(userData)
        console.log(user);
        if (userData.role === 'ADMIN') navigate('/admin/dashboard')
        else if (userData.role === 'DOCTOR') navigate('/doctor/dashboard')
        else if (userData.role === 'PATIENT') navigate('/patient/dashboard')
        else navigate("/");
    }

    const logout = () => {
        localStorage.removeItem('token')
        localStorage.removeItem('hrms_user')
        setUser(null)
        navigate('/login')
    }

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    )
}
export const useAuth = () => useContext(AuthContext);