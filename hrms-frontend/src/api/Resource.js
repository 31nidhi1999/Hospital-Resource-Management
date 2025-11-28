import api from "./axios";

export const addResource = async (data) => {
    try {
        const res = await api.post("/resources/register", data);
        return res.data;
    } catch (err) {
        console.error("Adding resource  failed:", err);
        throw err;
    }
}

export const raisedRequest = async (data) => {
    try {
        const res = await api.post("/requests/create", data);
        return res.data;
    } catch (err) {
        console.error("Failed to raised request:", err);
        throw err;
    }
}

export const approveRequest = async (id) => {
    try {
        const res = await api.put(`/requests/id/${id}/approve`);
        return res.data;
    } catch (err) {
        console.error("Failed to raised request:", err);
        throw err;
    }
}

export const rejectRequest = async (id) => {
    try {
        const res = await api.put(`/requests/id/${id}/reject`);
        return res.data;
    } catch (err) {
        console.error("Failed to raised request:", err);
        throw err;
    }
}