import api from "./axios";

export const getAllPatient = async () => {
    try {
        const response = await api.get('/patients/list');
        return response.data;
    } catch (error) {
        console.error("Enable to fetch Patient from System:", error);
        throw error;
    }
}

export const getAllAdmitedlist= async()=>{
    try{
        const response = await api.get("/admissions");
        return response.data;
    }catch(err){
        console.error("Enable to fetch admitted list from System:", err);
        throw err;
    }
}

export const getAllDoctor = async () => {
    try {
        const response = await api.get('/doctors');
        return response.data;
    } catch (error) {
        console.error("Enable to fetch Doctor from System:", error);
        throw error;
    }
}

export const scheduleList = async(data)=>{
    try{
       const res = await  api.get('/schedules');
       return res.data;
    }catch(error){
        console.error("Failed to fetch schedule:", error);
        throw error;
    }
}

export const scheduleListById = async(id)=>{
    try{
       const res = await  api.get(`/schedules/${id}`);
       return res.data;
    }catch(error){
        console.error("Failed to fetch schedule:", error);
        throw error;
    }
}