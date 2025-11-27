import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { Hospital } from "lucide-react";

export default function Navbar() {
    const { user, logout } = useAuth();

    return (
        <nav className="bg-white shadow-md">
            <div className="container mx-auto px-6 py-4 flex items-center justify-between">
                
                <div className="flex items-center gap-3">
                    <Hospital className="h-8 w-8 text-indigo-600" />
                    <span className="text-2xl font-semibold text-indigo-700 tracking-wide">
                        HRMS
                    </span>
                </div>

                <div className="flex items-center gap-6">

                    {user && (
                        <>
                            <span className="text-gray-600 text-sm">
                                {user.name} ({user.role})
                            </span>
                            <button
                                onClick={logout}
                                className="px-3 py-1 bg-red-500 text-white rounded-lg text-sm hover:bg-red-600"
                            >
                                Logout
                            </button>
                        </>
                    )}

                </div>
            </div>
        </nav>
    );
}
