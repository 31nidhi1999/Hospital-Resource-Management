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

export const getAllActiveAdmitedlist= async()=>{
        const response = await api.get("/admissions/active/list");
        return response.data;
}

export const getAllAdmissionByPatientId= async(id)=>{
        const response = await api.get(`/admissions/patient/${id}`);
        return response.data;
}

export const getAllAdmissionByDoctorId= async(id)=>{
        const response = await api.get(`/admissions/doctor/${id}`);
        return response.data;
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
       const res = await  api.get('/admissions/list');
       return res.data;
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

export const getTreatmentByPatientId = async(id)=>{
       const res = await  api.get(`/treatments/patient/${id}`);
       return res.data;
}

export const getTreatmentByDoctorId = async(id)=>{
       const res = await  api.get(`/treatments/doctor/${id}`);
       return res.data;
}