import { useEffect, useState } from "react";
import { getAllPatient, getAllDoctor } from "../../api/List";
import { admitPatient } from "../../api/Patient";
import { UserPlus } from "lucide-react";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function AdmitPatient() {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [patients, setPatients] = useState([]);
    const [doctors, setDoctors] = useState([]);
    const [paylaod, setPayload] = useState({ patient_id: '', doctor_id: ' ' })

    const submit = async (e) => {
        e.preventDefault();
        try {
            const res = await admitPatient(paylaod)
            alert("Patient admitted Successfully!");
            console.log(res)
        } catch (err) {
            alert("Error while admiting patient");
        }
    }

    const laodPatient = async () => {
        setLoading(true)
        try {
            const list = await getAllPatient();
            if (!list || list.length === 0) {
                return goToStatus(navigate, "empty");
            }
            setPatients(list);
            console.log(list);
        } catch (err) {
            goToStatus(navigate, "error")
        } finally {
            setLoading(false);
        }
    }

    const laodDoctor = async () => {
        try {
            const list = await getAllDoctor();
            setDoctors(list);
            console.log(list);
        } catch (err) {
            console.error("Error loading doctors", err);
        }
    }
    useEffect(() => {
        laodDoctor();
        laodPatient();
    }, []);
    if (loading) return <FullScreenLoader />
    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-6">
            <div className="w-full max-w-md bg-white shadow-2xl rounded-2xl p-8 border border-indigo-100">
                <div className="flex items-center gap-3 mb-6">
                    <div className="bg-indigo-100 p-3 rounded-xl">
                        <UserPlus className="text-indigo-700" size={26} />
                    </div>
                    <h2 className="text-2xl font-bold text-indigo-800">Admit Patient</h2>
                </div>

                <form onSubmit={submit} className="space-y-5">

                    <div>
                        <select value={paylaod.patient_id}
                            onChange={e => setPayload({ ...paylaod, patient_id: e.target.value })}
                            className="w-full p-3 border rounded-lg bg-gray-50 focus:ring-2 focus:ring-indigo-300 outline-none">
                            <option value="">Select Patient</option>
                            {patients.map(p =>
                                <option key={p.id} value={p.id}>{p.firstName} {p.lastName}</option>
                            )}
                        </select>
                    </div>

                    <div>
                        <select value={paylaod.doctor_id}
                            onChange={(e) => setPayload({ ...paylaod, doctor_id: e.target.value })}
                            className="w-full p-3 border rounded-lg bg-gray-50 focus:ring-2 focus:ring-indigo-300 outline-none">
                            <option value="">Select Doctor</option>
                            {doctors.map(d =>
                                <option key={d.id} value={d.id}>{d.firstName} {d.lastName}</option>
                            )}
                        </select>
                    </div>

                    <button type="submit" className="w-full p-2 bg-green-600 text-white rounded">Admit</button>
                </form>
            </div>
        </div>
    )
}