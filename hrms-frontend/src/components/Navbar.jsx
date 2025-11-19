import { useAuth } from "../context/AuthContext";
import { Link } from "react-router-dom";

export default function Navbar() {
    const { user, logout } = useAuth();
    return (
        <nav className="bg-white shadow">
            <div className="container mx-auto px-4 py-3 flex items-center justify-between">
                <div className="font-bold">HRMS</div>
                <div className="space-x-4">
                    {!user && (
                        <>
                            <Link to="/login" className="text-sm">Login</Link>
                            <Link to="/register" className="text-sm">Register</Link>
                        </>
                    )}


                    {user && (
                        <>
                            <span className="text-sm">{user.name} ({user.role})</span>
                            <button onClick={logout} className="ml-2 px-3 py-1 bg-red-500 text-white rounded text-sm">Logout</button>
                        </>
                    )}
                </div>
            </div>
        </nav>
    )
}