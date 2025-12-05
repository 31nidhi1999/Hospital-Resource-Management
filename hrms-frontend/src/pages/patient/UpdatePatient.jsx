import { useState,useEffect } from "react";
import { patientDetails } from "../../api/fetchUserDetailById";
import { updatePatient } from "../../api/UpdateUserDetails";
import { getLoggedInUserId } from "../auth/auth";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function UpdatePatient({ patient }) {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState({
    firstName:  "",
    lastName: "",
    age: "",
    address: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit =async (e) => {
    e.preventDefault();
    try {
        const id = getLoggedInUserId(); 
        const res = await updatePatient(id,form);
        goToStatus(navigate, "successUpdatePatient");
      } catch (error) {
          goToStatus(navigate, "errorPatient");
      }
  };

  const fetchPatientDetail = async () => {
        try {
          const id = getLoggedInUserId(); 
          const res = await patientDetails(id);
          console.log(res)
          setForm(res);
        } catch (error) {
          goToStatus(navigate, "errorPatient");
        }
      };
    
      useEffect(()=>{
          fetchPatientDetail();
        },[])

  return (
    <div className="min-h-screen bg-blue-50 flex items-center justify-center p-4">
      <div className="bg-white/80 backdrop-blur-lg shadow-2xl rounded-2xl w-full max-w-md p-8 border border-white/40">
        <h2 className="text-2xl text-blue-700 font-bold mb-6 text-center">
          Update Patient Details
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
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
            placeholder="Email"
            className="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          />

          <input
            type="text"
            name="address"
            value={form.address}
            onChange={handleChange}
            placeholder="Address"
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
