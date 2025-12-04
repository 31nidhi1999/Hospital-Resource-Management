import { useState, useEffect } from "react";
import { useAuth } from "../../context/AuthContext";
import { getAllPatient } from "../../api/List";
import { createTreatmnet } from "../../api/treatment";
import { getLoggedInUserId } from "../auth/auth";
import { useNavigate } from "react-router-dom";
import { goToStatus } from "../../utils/goToStatus";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function TreatmentPage() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const { user } = useAuth();
  const [patients, setPatients] = useState([]);
  const [form, setForm] = useState({
    diagnosis: "",
    prescribedMedication: "",
    patient_id: "",
    doctor_id: getLoggedInUserId()
  });

  useEffect(() => {
    const fetchPatients = async () => {
      setLoading(true)
      try {
        const list = await getAllPatient();
        if (!list || list.length === 0) {
          return goToStatus(navigate, "emptyDoctor");
        }
        setPatients(res);
      } catch (err) {
        goToStatus(navigate, "errorDoctor")
      } finally {
        setLoading(false);
      }
    };

    fetchPatients();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await createTreatmnet(form);
      alert("Treatment added successfully!");
      navigate("/doctor/dashboard")
    } catch (err) {
      console.error(err);
    }
  };
  if (loading) return <FullScreenLoader />
  return (
    <div className="min-h-screen p-10 bg-gradient-to-br from-indigo-50 to-blue-100">
      <h2 className="text-3xl font-bold text-indigo-800 mb-6">Add Treatment</h2>

      <form className="max-w-md bg-white p-6 rounded-2xl shadow space-y-4" onSubmit={handleSubmit}>

        <label className="block">
          <span className="text-gray-700">Select Patient</span>
          <select
            name="patient_id"
            value={form.patient_id}
            onChange={handleChange}
            required
            className="w-full mt-1 px-3 py-2 border rounded-lg focus:ring-2 focus:ring-indigo-200"
          >
            <option value="">--Select Patient--</option>
            {patients.map((p) => (
              <option key={p.id} value={p.id}>
                {p.firstName} {p.lastName}
              </option>
            ))}
          </select>
        </label>

        <label className="block">
          <span className="text-gray-700">Diagnosis</span>
          <input
            type="text"
            name="diagnosis"
            value={form.diagnosis}
            onChange={handleChange}
            required
            className="w-full mt-1 px-3 py-2 border rounded-lg focus:ring-2 focus:ring-indigo-200"
          />
        </label>

        <label className="block">
          <span className="text-gray-700">Prescribed Medication</span>
          <input
            type="text"
            name="prescribedMedication"
            value={form.prescribedMedication}
            onChange={handleChange}
            required
            className="w-full mt-1 px-3 py-2 border rounded-lg focus:ring-2 focus:ring-indigo-200"
          />
        </label>

        <button
          type="submit"
          className="w-full py-2 px-4 bg-indigo-600 text-white font-semibold rounded-lg hover:bg-indigo-700 transition"
        >
          Add Treatment
        </button>
      </form>
    </div>
  );
}
