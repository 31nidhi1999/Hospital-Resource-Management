import { useEffect, useState } from "react";
import { getAllAdmitedlist } from "../../api/List";
import { dischargePatient } from "../../api/Patient";
import { formatDate } from "../../helper/formateDate";

export default function DischargePatient() {
    const [admitedList, setAdmitedList] = useState([]);

    const handleDischarge = async(id)=>{
        try{
            const data = await dischargePatient(id);
        }catch{
            alert("Error while discharing patient");
        }
    }

    const loadList = async () => {
        try {
            const list = await getAllAdmitedlist();
            setAdmitedList(list);
        } catch (err) {
            console.error("Error loading admisiion list", err);
        }
    };

    useEffect(() => {
        loadList();
    },[]);

    return (
    <div className="p-6">
      <h1 className="text-2xl font-semibold mb-4">Patient Discharge</h1>

      <table className="w-full border rounded-lg overflow-hidden shadow">
        <thead>
          <tr className="bg-indigo-100 text-indigo-900">
            <th className="p-3 border">ID</th>
            <th className="p-3 border">Admission Date</th>
            <th className="p-3 border">Discharge Date</th>
            <th className="p-3 border">Patient</th>
            <th className="p-3 border">Doctor</th>
            <th className="p-3 border">Status</th>
          </tr>
        </thead>

        <tbody>
          {admitedList.map((adm) => (
            <tr key={adm.id} className="hover:bg-indigo-50">
              <td className="p-3 border">{adm.id}</td>
              <td className="p-3 border">{formatDate(adm.admissionDate)}</td>
              <td className="p-3 border">{formatDate(adm.dischargeDate)}</td>
              <td className="p-3 border">{adm.patientFirstName} {adm.patientLastName}</td>
              <td className="p-3 border">{adm.doctorFirstName} {adm.doctorLastName}</td>
              <td className="p-3 border">
                {adm.active === true ? (
                  <button
                    onClick={() => handleDischarge(adm.id)}
                    className="bg-red-500 text-white px-4 py-1 rounded shadow hover:bg-red-700"
                  >
                    Discharge
                  </button>
                ) : (
                  <span className="text-gray-500 italic">
                    Already Discharged
                  </span>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}