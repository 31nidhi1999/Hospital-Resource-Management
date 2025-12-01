import { useState } from "react";
import { useAuth } from "../../context/AuthContext";
import { Link } from "react-router-dom";
import {LogIn} from "lucide-react";

export default function Login() {

    const { login } = useAuth();
    const [form, setForm] = useState({ userName: "", password: "" });
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
           await login(form)
        } catch(err) {
            setError(err.message);
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center overflow-hidden">
            <div className="bg-white shadow-lg rounded-2xl p-8 w-full max-w-md">

                <h2 className="text-2xl font-semibold text-indigo-700 mb-6 text-center">Sign in to HRMS</h2>

                {error && (
                    <p className="text-red-600 bg-red-50 border border-red-200 p-2 rounded mb-3">
                        {error}
                    </p>
                )}

                <form className="space-y-5" onSubmit={handleSubmit}>

                    <input
                        type="email"
                        placeholder="Email"
                        className="w-full px-4 py-2 border rounded-lg bg-[#F7F9FC] focus:ring-2 focus:ring-indigo-200"
                        value={form.userName}
                        onChange={(e) => setForm({...form, userName: e.target.value})}
                        required
                    />

                    <input
                        type="password"
                        placeholder="Password"
                        className="w-full px-4 py-2 border rounded-lg bg-[#F7F9FC] focus:ring-2 focus:ring-indigo-200"
                        value={form.password}
                        onChange={(e) => setForm({...form, password: e.target.value})}
                        required
                    />

                    <button
                        type="submit"
                        className="w-full p-2 bg-green-600 text-white rounded flex items-center justify-center gap-2"
                    >
                        <LogIn size={18}/>Login
                    </button>

                </form>

                <p className="text-center text-sm mt-4 text-gray-600">
                    No account? <Link to="/register" className="text-indigo-600">Register</Link>
                </p>

            </div>
        </div>
    );
}
