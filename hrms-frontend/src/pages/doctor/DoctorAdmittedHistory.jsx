import { useState, useEffect } from "react";
import { getAllAdmissionByDoctorId } from "../../api/List";
import { getLoggedInUserId } from "../auth/auth";
import { formatDate } from "../../helper/formateDate";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function DoctorAdmittedHistory(){
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [AsdmissionList, setAsdmissionList] = useState([]);

    useEffect(() => {
        const loadAsdmissionList = async () => {
            setLoading(true)
            try {
                const id = getLoggedInUserId();
                const list = await getAllAdmissionByDoctorId(id);
                if (!list || list.length === 0) {
                    return goToStatus(navigate, "emptyDoctor");
                }
                setAsdmissionList(list);
            } catch (err) {
                goToStatus(navigate, "errorDoctor")
            } finally {
                setLoading(false);
            }
        };

        loadAsdmissionList();
    }, []);
    if(loading) return <FullScreenLoader/>
    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-8">
            <h3 className="text-3xl font-bold text-indigo-800 mb-6">Addmission History</h3>
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden">
                <table className="w-full">
                    <thead className="bg-indigo-600 text-white">
                        <tr className="border-b"><th className="p-4 text-left">Patient Name</th><th className="p-4 text-left">Admission Date</th><th className="p-4 text-left">Discharge Date</th></tr>
                    </thead>
                    <tbody>
                        {AsdmissionList.map(s => (
                                <tr key={s.id} className="border-b hover:bg-indigo-50 transition">
                                    <td className="p-4 font-medium text-gray-700">{s.patientFirstName} {" "} {s.patientLastName}</td>
                                    <td className="p-4 text-gray-700">{formatDate(s.admissionDate)}</td>
                                    <td className="p-4 text-gray-700">{formatDate(s.dischargeDate)}</td></tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}