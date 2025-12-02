import api from "./axios";

export const doctorDetails = async (id) => {
  const res = await api.get(`/doctors/id/${id}`);
  return res.data;
};

export const patientDetails = async (id) => {
  const res = await api.get(`/patients/id/${id}`);
  return res.data;
};

export const adminDetails = async (id) => {
  const res = await api.get(`/admins/id/${id}`);
  return res.data;
};

