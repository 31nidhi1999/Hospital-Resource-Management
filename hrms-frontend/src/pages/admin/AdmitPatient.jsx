import { useEffect, useState } from "react";
import { getAllPatient } from "../../api/getAllPatient";
import { getAllDoctor } from "../../api/getAllDoctor";
import { admitPatient } from "../../api/admitPatient";

export default function AdmitPatient() {
    const [patients, setPatients] = useState([]);
    const [doctors, setDoctors] = useState([]);
    const [paylaod, setPayload] = useState({ patient_id: '', doctor_id: ' ' })

    const submit = async (e) => {
        e.preventDefault();
        try {
            const res = await admitPatient(paylaod)
            alert("Patient admitted Successfully!");
            console.log(res)
        } catch (err) {
            alert("Error while admiting patient");
        }
    }

    const laodPatient = async () => {
        try {
            const list = await getAllPatient();
            setPatients(list);
            console.log(list);
        } catch (err) {
            console.error("Error loading patients", err);
        }
    }

    const laodDoctor = async () => {
        try {
            const list = await getAllDoctor();
            setDoctors(list);
            console.log(list);
        } catch (err) {
            console.error("Error loading doctors", err);
        }
    }
    useEffect(() => {
        laodDoctor();
        laodPatient();
    }, []);

    return (
        <div className="max-w-lg bg-white p-6 rounded shadow">
            <h3 className="text-xl mb-3">Admit Patient</h3>
            <form onSubmit={submit} className="space-y-3">
                <select value={paylaod.patient_id} onChange={e => setPayload({ ...paylaod, patient_id: e.target.value })} className="w-full p-2 border rounded">
                    <option value="">Select Patient</option>
                    {patients.map(p =>
                        <option key={p.id} value={p.id}>{p.firstName} {p.lastName}</option>
                    )}
                </select>

                <select value={paylaod.doctor_id} onChange={(e) => setPayload({ ...paylaod, doctor_id: e.target.value })} className="w-full p-2 border rounded">
                    <option value="">Select Doctor</option>
                    {doctors.map(d =>
                        <option key={d.id} value={d.id}>{d.firstName} {d.lastName}</option>
                    )}
                </select>

                <button type="submit" className="w-full p-2 bg-green-600 text-white rounded">Admit</button>
            </form>
        </div>
    )
}