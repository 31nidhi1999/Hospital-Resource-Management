import api from "./axios";

export const getAllPatient = async () => {
    try {
        const response = await api.get('/patients');
        return response.data;
    } catch (error) {
        console.error("Enable to fetch Patient from System:", error);
        throw error;
    }
}