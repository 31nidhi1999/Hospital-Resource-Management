import React,{ createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom'
import api from "../api/axios"; 

const AuthContext = createContext()
export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const raw = localStorage.getItem('hrms_user')
        if (raw) setUser(JSON.parse(raw))
    }, [])

    const login = async (email, password) => {
        const res = await api.post('/login', { email, password })
        // expected response: { token, user: { id, name, role } }
        localStorage.setItem('token', res.data.token)
        localStorage.setItem('hrms_user', JSON.stringify(res.data.user))
        setUser(res.data.user)
        // redirect based on role
        if (res.data.user.role === 'ADMIN') navigate('/admin/dashboard')
        else if (res.data.user.role === 'DOCTOR') navigate('/doctor/dashboard')
        else if (res.data.user.role === 'PATIENT') navigate('/patient/dashboard')
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