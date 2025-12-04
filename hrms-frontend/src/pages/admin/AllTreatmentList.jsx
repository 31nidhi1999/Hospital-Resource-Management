import { getAllTreatment } from "../../api/List";
import { useState, useEffect } from "react";
import { formatDate } from "../../helper/formateDate";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function AllTreatmentList(){
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [TreatmentList, setTreatmentList] = useState([]);

    useEffect(() => {
        const loadTreatmentList = async () => {
            setLoading(true)
            try {
                const list = await getAllTreatment();
                if (!list || list.length === 0) {
                    return goToStatus(navigate, "empty");
                }
                setTreatmentList(list);
            } catch (err) {
                goToStatus(navigate, "error")
            } finally {
                setLoading(false);
            }
        };

        loadTreatmentList();
    }, []);
    if (loading) return <FullScreenLoader />
    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-8">
            <h3 className="text-3xl font-bold text-indigo-800 mb-6">Treatment History</h3>
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden">
                <table className="w-full">
                    <thead className="bg-indigo-600 text-white">
                        <tr className="border-b"><th className="p-4 text-left">Doctor Name</th><th className="p-4 text-left">Patient Name</th><th className="p-4 text-left">Diagnosis</th><th className="p-4 text-left">Prescribed Medication</th><th className="p-4 text-left">Date</th></tr>
                    </thead>
                    <tbody>
                        {TreatmentList.map(s => (
                                <tr key={s.id} className="border-b hover:bg-indigo-50 transition">
                                    <td className="p-4 font-medium text-gray-700">{s.doctorFirstName} {" "} {s.doctorLastName}</td>
                                    <td className="p-4 font-medium text-gray-700">{s.patientFirstName} {" "} {s.patientLastName}</td>
                                    <td className="p-4 font-medium text-gray-700">{s.diagnosis}</td>
                                    <td className="p-4 font-medium text-gray-700">{s.prescribedMedication}</td>
                                    <td className="p-4 text-gray-700">{formatDate(s.createdAt)}</td></tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}