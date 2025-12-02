import { Link } from "react-router-dom";
import { ClipboardList,CalendarCheck,UserPen } from "lucide-react";

export default function DoctorDashboard(){
    return(
        <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 p-10">
           <h2 className="text-4xl font-bold text-indigo-800 mb-8 tracking-wide">Doctor Dashboard</h2>
           <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                <Link to="/doctor/request-resource"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <ClipboardList size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Raise Resource Request
                    </span>
                </Link>

                <Link to="/doctor/schedule"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <CalendarCheck size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Doctor Schedule
                    </span>
                </Link>

                <Link to="/doctor/update-doctor-details"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <UserPen size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Update Deatils
                    </span>
                </Link>

                <Link to="/doctor/create-treatment"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <UserPen size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Register Treatment
                    </span>
                </Link>
            </div>
        </div>
    )
}