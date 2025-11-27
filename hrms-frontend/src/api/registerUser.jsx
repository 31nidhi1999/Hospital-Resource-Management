import api from "./axios";

export const registerAdmin = async(data)=>{
    try{
       const response = await api.post('/admins/register',data); 
       return response.data;
    }catch (error) {
    console.error("Admin registration failed:", error);
    throw error;
  }
}

export const registerDoctor = async(data)=>{
    try{
       const response = await api.post('/doctors/register',data); 
       return response.data;
    }catch (error) {
    console.error("Doctor registration failed:", error);
    throw error;
  }
}

export const registerPatient = async(data)=>{
    try{
       const response = await api.post('/patients/register',data); 
       return response.data;
    }catch (error) {
    console.error("Patient registration failed:", error);
    throw error;
  }
}
