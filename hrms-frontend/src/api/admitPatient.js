import api from "./axios";

export const admitPatient = async (data) => {
    try {
        const res = await api.post("/admissions",data);
        return res.data;
    } catch (err) {
        console.error("Admint Patient failed:", err);
        throw err;
    }
}