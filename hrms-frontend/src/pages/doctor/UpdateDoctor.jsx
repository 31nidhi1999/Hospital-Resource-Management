import { useEffect, useState } from "react";
import { updateDoctor } from "../../api/UpdateUserDetails";
import { doctorDetails } from "../../api/fetchUserDetailById";
import { getLoggedInUserId } from "../auth/auth";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function UpdateDoctor({ doctor }) {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState({
  firstName: "",
  lastName: "",
  specialization: "",
  licenseNumber: "",
});

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
  e.preventDefault();

  try {
     const id = getLoggedInUserId();
    const res = await updateDoctor(id,form);
    goToStatus(navigate, "successUpdateDoctor");
  } catch (error) {
      goToStatus(navigate, "errorDoctor");
  }
};


  const fetchDoctorDetail = async () => {
    setLoading(true);
    try {
      const id = getLoggedInUserId();
      const res = await doctorDetails(id);
      setForm(res);
    } catch (error) {
      goToStatus(navigate, "errorDoctor");
      }finally{
        setLoading(false);
      }
  };

  useEffect(()=>{
    fetchDoctorDetail();
  },[])

  return (
    <div className="min-h-screen bg-blue-50 flex items-center justify-center p-4">
      <div className="bg-white/80 backdrop-blur-lg shadow-2xl rounded-2xl w-full max-w-md p-8 border border-white/40">
        <h2 className="text-2xl text-blue-700 font-bold mb-6 text-center">
          Update Doctor Details
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="firstName"
            value={form.firstName}
            onChange={handleChange}
            placeholder="First Name"
            className="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          />
          <input
            type="text"
            name="lastName"
            value={form.lastName}
            onChange={handleChange}
            placeholder="Last Name"
            className="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          />
          <input
            type="text"
            name="specialization"
            value={form.specialization}
            onChange={handleChange}
            placeholder="Specialization"
            disabled
            className="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          />
          <input
            type="text"
            name="licenseNumber"
            value={form.licenseNumber}
            onChange={handleChange}
            placeholder="License Number"
            disabled
            className="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          />

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-3 rounded-xl font-semibold hover:bg-blue-700 transition"
          >
            Update
          </button>
        </form>
      </div>
    </div>
  );
}
