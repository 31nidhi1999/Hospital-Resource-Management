import { Link } from "react-router-dom"
import { ClipboardList,CalendarCheck,UserPlus,UserMinus,UserCog,PlusCircle,UserPen,History   } from "lucide-react"

export default function AdminDashboard() {
    return (
        <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 p-10">
            <h2 className="text-4xl font-bold text-indigo-800 mb-8 tracking-wide">ADMIN DASHBOARD</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 gap-6">
                
                <Link to="/admin/resource-requests"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <ClipboardList size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Resource Requests
                    </span>
                </Link>

                <Link to="/admin/staff-schedule"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <CalendarCheck size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Staff Schedule
                    </span>
                </Link>

                <Link to="/admin/admit"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <UserPlus size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Admit Patient
                    </span>
                </Link>

                <Link to="/admin/discharge"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <UserMinus size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Discharge Patient
                    </span>
                </Link>

                <Link to="/admin/doctors"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <UserCog size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                       Approve Doctors
                    </span>
                </Link>

                 <Link to="/admin/add-resource"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <PlusCircle size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Add Resource
                    </span>
                </Link>

                <Link to="/admin/update-admin-details"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <UserPen size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Update Admin Deatils
                    </span>
                </Link>

                <Link to="/admin/treatment-history"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <History size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Treatment List
                    </span>
                </Link>

                <Link to="/admin/admission-history"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <History size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Admission History
                    </span>
                </Link>
            </div>
        </div>
    )
}