import { useEffect, useState } from "react";
import { getAllRequest } from "../../api/List";
import { approveRequest, rejectRequest } from "../../api/Resource";

export default function ResourceAllocation() {
    const [requests, setRequest] = useState([]);

    const handleApprove = async (id) => {
        try {
            const res = await approveRequest(id);
            setRequest(res);
            console.log(res)
        } catch (err) {
            alert("Error while approval resource request");
        }
    }

    const handleReject = async (id) => {

        try {
            const res = await rejectRequest(id);
            setRequest(res);
            console.log(res)
        } catch (err) {
            alert("Error while rejecting resource request");
        }
    }

    const loadRequest = async () => {
        try {
            const list = await getAllRequest();
            setRequest(list);
            console.log(list);
        } catch (err) {
            console.error("Error loading doctors", err);
        }
    }

    useEffect(() => {
        loadRequest();
    },[])
    return (
        <div className="max-w-3xl mx-auto bg-white p-6 rounded shadow">
            <h3 className="text-xl font-semibold mb-4">Resource Requests</h3>

            {requests.length === 0 && (
                <p className="text-gray-600">No requests found</p>
            )}

            <div className="overflow-x-auto bg-white p-6 rounded shadow">
    <h3 className="text-xl font-semibold mb-4">Resource Requests</h3>

    <table className="min-w-full border border-gray-300 rounded">
        <thead className="bg-[#E5EAF5] text-left">
            <tr>
                <th className="p-3 border">ID</th>
                <th className="p-3 border">Doctor</th>
                <th className="p-3 border">Patient</th>
                <th className="p-3 border">Resource</th>
                <th className="p-3 border">Status</th>
                <th className="p-3 border text-center">Actions</th>
            </tr>
        </thead>

        <tbody>
            {requests.map((req) => (
                <tr key={req.id} className="border">
                    <td className="p-3 border">{req.id}</td>
                    <td className="p-3 border">{req.doctorFirstName} {req.doctorLastName}</td>
                    <td className="p-3 border">{req.patientFirstName} {req.patientLastName}</td>
                    <td className="p-3 border">{req.resourceName}</td>

                    <td className="p-3 border">
                        <span
                            className={`px-2 py-1 rounded text-sm
                                ${
                                    req.status === "APPROVED"
                                        ? "bg-green-200 text-green-800"
                                        : req.status === "REJECTED"
                                        ? "bg-red-200 text-red-800"
                                        : "bg-yellow-200 text-yellow-800"
                                }
                            `}
                        >
                            {req.status}
                        </span>
                    </td>

                    <td className="p-3 border text-center">
                        <div className="flex justify-center gap-2">
                            <button
                                className="px-3 py-1 bg-green-600 text-white rounded disabled:bg-gray-400"
                                disabled={req.status !== "PENDING"}
                                onClick={() => handleApprove(req.id)}
                            >
                                Approve
                            </button>

                            <button
                                className="px-3 py-1 bg-red-600 text-white rounded disabled:bg-gray-400"
                                disabled={req.status !== "PENDING"}
                                onClick={() => handleReject(req.id)}
                            >
                                Reject
                            </button>
                        </div>
                    </td>
                </tr>
            ))}
        </tbody>
    </table>
</div>

        </div>
    )
}