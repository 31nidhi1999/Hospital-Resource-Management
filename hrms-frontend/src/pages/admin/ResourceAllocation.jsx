import { useEffect, useState } from "react";
import { getAllRequest } from "../../api/List";
import { approveRequest, rejectRequest } from "../../api/Resource";
import { goToStatus } from "../../utils/goToStatus";
import { useNavigate } from "react-router-dom";
import FullScreenLoader from "../../utils/FullScreenLoader";

export default function ResourceAllocation() {
    const [requests, setRequest] = useState([]);
    const navigate = useNavigate();
    const [loading,setLoading] = useState(false);

    const loadRequest = async () => {
        setLoading(true)
        try {
            const list = await getAllRequest();
            if(!list || list.length === 0){
                return goToStatus(navigate, "empty"); 
            }
            setRequest(list);
        } catch (err) {
            goToStatus(navigate,"error")
        }finally{
            setLoading(false);
        }
    };

    const handleApprove = async (id) => {
        try {
            await approveRequest(id);
            loadRequest(); 
        } catch (err) {
            alert("Error while approving resource request");
        }
    };

    const handleReject = async (id) => {
        try {
            await rejectRequest(id);
            loadRequest();
        } catch (err) {
            alert("Error while rejecting resource request");
        }
    };

    useEffect(() => {
        loadRequest();
    }, []);
    if(loading) return <FullScreenLoader/>
    return (
        <div className="max-w-4xl mx-auto bg-white p-6 rounded-xl shadow-lg">
            <h3 className="text-2xl font-semibold mb-5 text-gray-800">
                Resource Requests
            </h3>

            <div className="overflow-x-auto">
                <table className="min-w-full border border-gray-300 rounded-lg overflow-hidden">
                    <thead className="bg-gray-100 text-left">
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
                            <tr key={req.id} className="border hover:bg-gray-50">
                                <td className="p-3 border">{req.id}</td>

                                <td className="p-3 border">
                                    {req.doctorFirstName} {req.doctorLastName}
                                </td>

                                <td className="p-3 border">
                                    {req.patientFirstName} {req.patientLastName}
                                </td>

                                <td className="p-3 border">{req.resourceName}</td>

                                <td className="p-3 border text-center">
                                    <span
                                        className={`px-3 py-1 rounded-full text-xs font-semibold
                                            ${
                                                req.status === "APPROVED"
                                                    ? "bg-green-100 text-green-800"
                                                    : req.status === "REJECTED"
                                                    ? "bg-red-100 text-red-800"
                                                    : "bg-yellow-100 text-yellow-800"
                                            }
                                        `}
                                    >
                                        {req.status}
                                    </span>
                                </td>

                                <td className="p-3 border">
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
    );
}
