import api from "./axios";

export const updateDoctor = async (id, data) => {
  const res = await api.put(`/doctors/update/${id}`, data);
  return res.data;
};

export const updatePatient = async (id, data) => {
  const res = await api.put(`/patients/update/${id}`, data);
  return res.data;
};

export const updateAdmin = async (id, data) => {
  const res = await api.put(`/admins/update/${id}`, data);
  return res.data;
};