import api from "./axios";

export const createTreatmnet = async(data)=>{
    const res = await api.post("/treatments/create", data);
    return res.data;
}