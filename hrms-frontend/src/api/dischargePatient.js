import api from "./axios";

export const dischargePatient = async (id) => {
    try {
        const res = await api.delete(`/admissions/${id}`);
        return res.data;
    } catch (err) {
        console.error("Not able to discharge patient:", err);
        throw err;
    }
}