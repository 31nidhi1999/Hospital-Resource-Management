import { useState } from "react";
import { registerAdmin, registerDoctor, registerPatient } from "../../api/registerUser";


export default function Register() {
    const [role, setRole] = useState('ADMIN');
    const [form, setForm] = useState({ firstName: '', lastName: '', email: '', password: '' })
    const [extra, setExtra] = useState({ specialization: '', licenseNumber: '', age: '', address: '' })
    const [msg, setMsg] = useState('')

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
                alert("Admin Registered Successfully!");
                console.log(res)
            } catch (err) {
                alert("Error registering admin");
            }
        }

        if (role === 'DOCTOR') {
            try {
                const res = await registerDoctor(payload)
                alert("Doctor Registered Successfully!");
                console.log(res)
            } catch (err) {
                alert("Error registering doctor");
            }
        }

        if (role === 'PATIENT') {
            try {
                const res = await registerPatient(payload)
                alert("Patient Registered Successfully!");
                console.log(res)
            } catch (err) {
                alert("Error registering Patient");
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
        <div className="max-w-2xl mx-auto bg-white p-6 rounded shadow">
            <h2 className="text-xl font-semibold mb-4">Register</h2>
            {msg && <div className="mb-3 text-green-700">{msg}</div>}
            <form onSubmit={submit} className="space-y-3">
                <div>
                    <label className="block mb-1">Role</label>
                    <select value={role} onChange={e => setRole(e.target.value)} className="p-2 border rounded">
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

                <button type="submit" className="w-full p-2 bg-green-600 text-white rounded">Register</button>
            </form>
        </div>
    )
}