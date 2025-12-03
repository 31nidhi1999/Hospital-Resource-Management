import { Link } from "react-router-dom";
import { UserPen,History } from "lucide-react";

export default function PatientDashboard(){
   return(
     <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 p-10">
            <h2 className="text-4xl font-bold text-indigo-800 mb-8 tracking-wide">PATIENT DASHBOARD</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 gap-6">

                <Link to="/patient/update-patient-details"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <UserPen size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Update Patient Deatils
                    </span>
                </Link>

                 <Link to="/patient/treatment-history"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <History size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Treatment List
                    </span>
                </Link>

                <Link to="/patient/admission-history"
                    className="p-6 bg-white/70 backdrop-blur-xl border border-white rounded-2xl shadow hover:shadow-lg hover:-translate-y-1 transition-all flex items-center gap-4">
                    <History size={32} className="text-indigo-600" />
                    <span className="text-lg font-semibold text-gray-800">
                        Admission List
                    </span>
                </Link>
            </div>
        </div>
   )
}