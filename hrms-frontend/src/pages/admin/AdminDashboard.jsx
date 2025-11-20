export default function AdminDashboard() {
    return (
        <div>
            <h2 className="text-2xl font-semibold mb-4">ADMIN DASHBOARD</h2>
            <div className="grid grid-cols-2 gap-4 max-w-xl">
                <Link to="/admin/resource-requests" className="p-4 bg-white rounded shadow">Resource Requests</Link>
                <Link to="/admin/staff-schedule" className="p-4 bg-white rounded shadow">Staff Schedule</Link>
                <Link to="/admin/admit" className="p-4 bg-white rounded shadow">Admit Patient</Link>
                <Link to="/admin/discharge" className="p-4 bg-white rounded shadow">Discharge Patient</Link>
                <Link to="/admin/approve-doctors" className="p-4 bg-white rounded shadow">Approve Doctors</Link>
            </div>
        </div>
    )
}