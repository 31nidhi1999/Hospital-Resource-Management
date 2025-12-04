import { useState } from "react";
import { registerAdmin, registerDoctor, registerPatient } from "../../api/registerUser";
import { User } from "lucide-react";
import { Link, useNavigate } from "react-router-dom";
import { goToStatus } from "../../utils/goToStatus";

export default function Register() {
    const [role, setRole] = useState('ADMIN');
    const [form, setForm] = useState({ firstName: '', lastName: '', email: '', password: '' })
    const [extra, setExtra] = useState({ specialization: '', licenseNumber: '', age: '', address: '' })
    const [msg, setMsg] = useState('')
    const navigate = useNavigate();
    
    const onChange = (e) => setForm({ ...form, [e.target.name]: e.target.value })
    const onExtra = (e) => setExtra({ ...extra, [e.target.name]: e.target.value })

    const payload = {
        ...form,
        ...extra
    }

    const submit = async (e) => {
        e.preventDefault();

        if (role === 'ADMIN') {
            try {
                const res = await registerAdmin(form)
                goToStatus(navigate, "success");
            } catch (err) {
                if(err?.response?.status === 500){
                    goToStatus(navigate, "exist");
                    return;
                }
                goToStatus(navigate, "errorRegister");
            }
        }

        if (role === 'DOCTOR') {
            try {
                const res = await registerDoctor(payload)
                goToStatus(navigate, "success");
            } catch (err) {
                if(err?.response?.status === 500){
                    goToStatus(navigate, "exist");
                    return;
                }
                goToStatus(navigate, "errorRegister");
            }
        }

        if (role === 'PATIENT') {
            try {
                const res = await registerPatient(payload)
                goToStatus(navigate, "success");
            } catch (err) {
                console.log(err?.response?.status)
                if(err?.response?.status === 500){
                    goToStatus(navigate, "exist");
                    return;
                }
                goToStatus(navigate, "errorRegister");
            }
        }

        setForm({
            firstName: "",
            lastName: "",
            email: "",
            password: "",
        });

        setExtra({
            specialization: "",
            licenseNumber: "",
            age: "",
            address: "",
        });
    }

    return (
        <div className="min-h-screen flex items-center justify-center bg-[#F7F9FC]">
            <div className="bg-white shadow-xl rounded-2xl p-8 w-full max-w-lg">
                <h2 className="text-2xl font-semibold text-indigo-700 mb-6 text-center">Register</h2>
                {msg && <div className="mb-3 text-green-700">{msg}</div>}
                <form onSubmit={submit} className="space-y-4">
                    <div>
                        <label className="block mb-1">Role</label>
                        <select value={role} onChange={e => setRole(e.target.value)} className="w-full p-2 border rounded-lg bg-gray-50">
                            <option value="DOCTOR">Doctor</option>
                            <option value="PATIENT">Patient</option>
                            <option value="ADMIN">Admin</option>
                        </select>
                    </div>
                    <input name="firstName" value={form.firstName} onChange={onChange} placeholder="First Name" className="w-full p-2 border rounded" />
                    <input name="lastName" value={form.lastName} onChange={onChange} placeholder="Last Name" className="w-full p-2 border rounded" />
                    <input name="email" value={form.email} onChange={onChange} placeholder="Email" className="w-full p-2 border rounded" />
                    <input name="password" value={form.password} onChange={onChange} placeholder="Password" className="w-full p-2 border rounded" />

                    {role === 'DOCTOR' && (
                        <div className="space-y-2">
                            <input name="specialization" value={extra.specialization} onChange={onExtra} placeholder="Specialization" className="w-full p-2 border rounded" />
                            <input name="licenseNumber" value={extra.licenseNumber} onChange={onExtra} placeholder="licenseNumber" className="w-full p-2 border rounded" />
                        </div>
                    )}

                    {role === 'PATIENT' && (
                        <div className="space-y-2">
                            <input name="age" value={extra.age} onChange={onExtra} placeholder="age" className="w-full p-2 border rounded" />
                            <input name="address" value={extra.address} onChange={onExtra} placeholder="address" className="w-full p-2 border rounded" />
                        </div>
                    )}

                    <button type="submit" className="w-full p-2 bg-green-600 text-white rounded flex items-center justify-center gap-2"><User size={18} />Register</button>
                </form>

                <p className="text-center text-sm mt-4 text-gray-600">
                    Already have an account?
                    <Link to="/login" className="text-indigo-600 font-semibold ml-1">
                        Login here
                    </Link>
                </p>
            </div>
        </div>
    )
}