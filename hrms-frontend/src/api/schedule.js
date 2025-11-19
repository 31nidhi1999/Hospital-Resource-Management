import api from "./axios";

export const scheduleList = async(data)=>{
    try{
       const res = await  api.get('/schedules');
       return res.data;
    }catch(error){
        console.error("Failed to fetch schedule:", error);
        throw error;
    }
}