import { useEffect, useState } from "react";
import api from "../../api/axios";
import { scheduleList } from "../../api/schedule";

export default function StaffSchedule() {
    const [schedules, setSchedules] = useState([]);

    useEffect(() => {
        const loadSchedules = async () => {
            try {
                const list = await scheduleList();
                setSchedules(list);
                console.log(list);
            } catch (err) {
                console.error("Error loading schedules", err);
            }
        };

        loadSchedules();
    }, []);

    return (
        <div>
            <h3 className="text-xl mb-3">Staff Schedule</h3>
            <div className="bg-white rounded shadow">
                <table className="w-full">
                    <thead className="text-left p-2">
                        <tr className="border-b"><th className="p-2">Doctor First Name</th><th className="p-2">Doctor Last Name</th><th className="p-2">Shift</th><th className="p-2">Date</th></tr>
                    </thead>
                    <tbody>
                        {schedules.map(s => (
                            <tr key={s.id} className="border-b"><td className="p-2">{s.doctorFirstName}</td><td className="p-2">{s.doctorLastName}</td><td className="p-2">{s.shiftType}</td><td className="p-2">{s.startTime}</td></tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}