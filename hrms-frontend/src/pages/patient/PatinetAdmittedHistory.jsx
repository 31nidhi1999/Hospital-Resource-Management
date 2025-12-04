import { getAllAdmissionByPatientId } from "../../api/List";
import { useState, useEffect } from "react";
import { getLoggedInUserId } from "../auth/auth";
import { formatDate } from "../../helper/formateDate";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";


export default function PatientAdmittedHistory() {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [AsdmissionList, setAsdmissionList] = useState([]);

    useEffect(() => {
        const loadAsdmissionList = async () => {
            setLoading(true)
            try {
                const id = getLoggedInUserId();
                const list = await getAllAdmissionByPatientId(id);
                if (!list || list.length === 0) {
                    return goToStatus(navigate, "emptyPatient");
                }
                setAsdmissionList(list);
            } catch (err) {
                goToStatus(navigate, "errorPatient")
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
                        <tr className="border-b"><th className="p-4 text-left">Doctor Name</th><th className="p-4 text-left">Admission Date</th><th className="p-4 text-left">Discharge Date</th></tr>
                    </thead>
                    <tbody>
                        {AsdmissionList.map(s => (
                                <tr key={s.id} className="border-b hover:bg-indigo-50 transition">
                                    <td className="p-4 font-medium text-gray-700">{s.doctorFirstName} {" "} {s.doctorLastName}</td>
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