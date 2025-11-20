import api from "./axios";

export const getAllAdmitedlist= async()=>{
    try{
        const response = await api.get("/admissions");
        return response.data;
    }catch(err){
        console.error("Enable to fetch admitted list from System:", err);
        throw err;
    }
}