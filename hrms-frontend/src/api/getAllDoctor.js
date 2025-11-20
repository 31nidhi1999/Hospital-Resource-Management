import api from "./axios";

export const getAllDoctor = async () => {
    try {
        const response = await api.get('/doctors');
        return response.data;
    } catch (error) {
        console.error("Enable to fetch Doctor from System:", error);
        throw error;
    }
}