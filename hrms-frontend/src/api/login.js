import api from './axios';

export const LoginUser =async (data)=>{
    const res = await api.post("/users/login",data);
    return res.data;

}