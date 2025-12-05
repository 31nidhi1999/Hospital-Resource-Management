import { useState, useEffect } from "react";
import { getAllActiveAdmitedlist, resourceList } from "../../api/List";
import { raisedRequest } from "../../api/Resource";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function RaisedRequest() {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [resources, setResources] = useState([]);
    const [admissions, setAdmissions] = useState([]);
     const [paylaod, setPayload] = useState({ patient_id: '', doctor_id: ' ', resource_id:' ',admission_id:' '})

    const submit = async (e) => {
        e.preventDefault();
        try {
            const res = await raisedRequest(paylaod)
            goToStatus(navigate, "successRequest");
        } catch (err) {
            goToStatus(navigate, "errorDoctor");
        }
    }

    const laodResources = async () => {
         setLoading(true);
        try {
            const list = await resourceList();
            if (!list || list.length === 0) {
                return goToStatus(navigate, "emptyDoctor");
            }
            setResources(list);
        } catch (err) {
            goToStatus(navigate, "errorDoctor");
        }finally{
            setLoading(false);
        }
    }

    const laodAdmission = async () => {
         setLoading(true);
        try {
            const list = await getAllActiveAdmitedlist();
            if (!list || list.length === 0) {
                return goToStatus(navigate, "emptyDoctor");
            }
            setAdmissions(list);
        } catch (err) {
            goToStatus(navigate, "errorDoctor");
        }
        finally{
            setLoading(false);
        }
    }
    useEffect(() => {
        laodAdmission();
        laodResources();
    }, []);
    if(loading) return <FullScreenLoader/>
    return (
        <div className="max-w-lg bg-white p-6 rounded shadow">
            <h3 className="text-xl mb-3">Raised Request</h3>
            <form onSubmit={submit} className="space-y-3">
                <select value={paylaod.patient_id} onChange={e => setPayload({ ...paylaod, patient_id: e.target.value })} className="w-full p-2 border rounded">
                    <option value="">Select Patient</option>
                    {admissions.map(p =>
                        <option key={p.patientId} value={p.patientId}>{p.patientFirstName} {p.patientLastName}</option>
                    )}
                </select>

                <select value={paylaod.doctor_id} onChange={(e) => setPayload({ ...paylaod, doctor_id: e.target.value })} className="w-full p-2 border rounded">
                    <option value="">Select Doctor</option>
                    {admissions.map(d =>
                        <option key={d.doctorId} value={d.doctorId}>{d.doctorFirstName} {d.doctorLastName}</option>
                    )}
                </select>

                <select value={paylaod.resource_id} onChange={(e) => setPayload({ ...paylaod, resource_id: e.target.value })} className="w-full p-2 border rounded">
                    <option value="">Select Resource</option>
                    {resources.map(r =>
                        <option key={r.id} value={r.id}>{r.resourceName}</option>
                    )}
                </select>

                <select value={paylaod.admission_id} onChange={(e) => setPayload({ ...paylaod, admission_id: e.target.value })} className="w-full p-2 border rounded">
                    <option value="">Select Admission</option>
                    {admissions.map(a =>
                        <option key={a.id} value={a.id}>{a.id}</option>
                    )}
                </select>

                <button type="submit" className="w-full p-2 bg-green-600 text-white rounded">Rasied Request</button>
            </form>
        </div>
    )
}    