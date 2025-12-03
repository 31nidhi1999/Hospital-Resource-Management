import api from "./axios";

export const registerAdmin = async(data)=>{
       const response = await api.post('/admins/register',data); 
       return response.data;
}

export const registerDoctor = async(data)=>{
       const response = await api.post('/doctors/register',data); 
       return response.data;
}

export const registerPatient = async(data)=>{
       const response = await api.post('/patients/register',data); 
       return response.data;
}
