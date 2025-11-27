import { Link } from "react-router-dom";

export default function DoctorDashboard(){
    return(
        <div>
           <h2 className="text-2xl font-semibold mb-4">Doctor Dashboard</h2>
           <div className="grid grid-cols-2 gap-4 max-w-xl">
                <Link to="/doctor/request-resource" className="p-4 bg-white rounded shadow">Raise Resource Request</Link>
                <Link to="/doctor/schedule" className="p-4 bg-white rounded shadow">Doctor Schedule</Link>
            </div>
        </div>
    )
}