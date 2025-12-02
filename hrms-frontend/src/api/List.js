import api from "./axios";

export const getAllPatient = async () => {
    try {
        const response = await api.get("/patients/list");
        return response.data;
    } catch (error) {
        console.error("Enable to fetch Patient from System:", error);
        throw error;
    }
}

export const getAllAdmitedlist= async()=>{
    try{
        const response = await api.get("/admissions/list");
        return response.data;
    }catch(err){
        console.error("Enable to fetch admitted list from System:", err);
        throw err;
    }
}

export const getAllDoctor = async () => {
    try {
        const response = await api.get('/doctors/list');
        return response.data;
    } catch (error) {
        console.error("Enable to fetch Doctor from System:", error);
        throw error;
    }
}

export const scheduleList = async(data)=>{
    try{
       const res = await  api.get('/schedules/list');
       return res.data;
    }catch(error){
        console.error("Failed to fetch schedule:", error);
        throw error;
    }
}

export const scheduleListById = async(id)=>{
    try{
       const res = await  api.get(`/schedules/id/${id}`);
       return res.data;
    }catch(error){
        console.error("Failed to fetch schedule:", error);
        throw error;
    }
}

export const resourceList = async()=>{
    try{
       const res = await  api.get('/resources/list');
       return res.data;
    }catch(error){
        console.error("Failed to fetch resource lsit:", error);
        throw error;
    }
}

export const getAllAdmission = async()=>{
    try{
       const res = await  api.get('/admissions/list');
       return res.data;
    }catch(error){
        console.error("Failed to fetch admission list:", error);
        throw error;
    }
}

export const getAllRequest = async()=>{
    try{
       const res = await  api.get('/requests/list');
       return res.data;
    }catch(error){
        console.error("Failed to fetch resource request list:", error);
        throw error;
    }
}

export const getAllTreatment = async()=>{
    try{
       const res = await  api.get('/treatments/list');
       return res.data;
    }catch(error){
        console.error("Failed to fetch treatment list:", error);
        throw error;
    }
}