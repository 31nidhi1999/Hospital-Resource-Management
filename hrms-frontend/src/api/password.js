import api from "./axios";

export const sendOtp = async (data) => {
  const res = await api.post("/users/forgot-password", data);
  return res.data;
};

export const verifyOtp = async (data) => {
  const res = await api.post("/users/verify-otp", data);
  return res.data;
};

export const resetPassword = async (data) => {
  const res = await api.post("/users/reset-password", data);
  return res.data;
};
