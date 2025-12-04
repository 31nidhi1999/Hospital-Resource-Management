import { useEffect, useState } from "react";
import { scheduleList } from "../../api/List";
import { formatDate } from "../../helper/formateDate";
import getShiftBadge from "../../helper/getShiftBadge";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function StaffSchedule() {
    const [schedules, setSchedules] = useState([]);
    const navigate = useNavigate();
    const [loading,setLoading] = useState(false);

    useEffect(() => {
        const loadSchedules = async () => {
            setLoading(true)
            try {
                const list = await scheduleList();
                if (!list || list.length === 0) {
                    return goToStatus(navigate, "empty");
                }
                setSchedules(list);
            } catch (err) {
                goToStatus(navigate, "error")
            } finally {
                setLoading(false);
            }
        };

        loadSchedules();
    }, []);
    if(loading) return <FullScreenLoader/>
    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-8">
            <h3 className="text-3xl font-bold text-indigo-800 mb-6">All Doctor Schedule</h3>
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden">
                <table className="w-full">
                    <thead className="bg-indigo-600 text-white">
                        <tr className="border-b"><th className="p-4 text-left">Doctor First Name</th><th className="p-4 text-left">Doctor Last Name</th><th className="p-4 text-left">Specialization</th><th className="p-4 text-left">Shift</th><th className="p-4 text-left">Date</th></tr>
                    </thead>
                    <tbody>
                        {schedules.map(s => (
                                <tr key={s.id} className="border-b hover:bg-indigo-50 transition">
                                    <td className="p-4 font-medium text-gray-700">{s.doctorFirstName}</td>
                                    <td className="p-4 font-medium text-gray-700">{s.doctorLastName}</td>
                                    <td className="p-4 font-medium text-gray-700">{s.specialization}</td>
                                    <td className="p-4">
                                        <span className={`px-3 py-1 text-sm font-semibold rounded-full ${getShiftBadge(s.shiftType)}`}>
                                            {s.shiftType}
                                        </span>
                                    </td>
                                    <td className="p-4 text-gray-700">{formatDate(s.startTime)}</td></tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}