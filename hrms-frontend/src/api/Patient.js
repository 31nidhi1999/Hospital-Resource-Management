import api from "./axios";

export const admitPatient = async (data) => {
    try {
        const res = await api.post("/admissions/create",data);
        return res.data;
    } catch (err) {
        console.error("Admint Patient failed:", err);
        throw err;
    }
}

export const dischargePatient = async (id) => {
    try {
        const res = await api.delete(`/admissions/${id}`);
        return res.data;
    } catch (err) {
        console.error("Not able to discharge patient:", err);
        throw err;
    }
}