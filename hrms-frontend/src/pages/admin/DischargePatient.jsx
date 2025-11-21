import { useEffect, useState } from "react";
import { getAllAdmitedlist } from "../../api/List";
import { dischargePatient } from "../../api/Patient";

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

      <table className="w-full border">
        <thead>
          <tr className="bg-gray-200">
            <th className="p-2 border">ID</th>
            <th className="p-2 border">Admission Date</th>
            <th className="p-2 border">Patient</th>
            <th className="p-2 border">Doctor</th>
            <th className="p-2 border">Status</th>
          </tr>
        </thead>

        <tbody>
          {admitedList.map((adm) => (
            <tr key={adm.id}>
              <td className="p-2 border">{adm.id}</td>
              <td className="p-2 border">{adm.admissionDate}</td>
              <td className="p-2 border">{adm.patientFirstName} {adm.patientLastName}</td>
              <td className="p-2 border">{adm.doctorFirstName} {adm.doctorLastName}</td>
              <td className="p-2 border">
                {adm.active === true ? (
                  <button
                    onClick={() => handleDischarge(adm.id)}
                    className="bg-red-500 text-white px-4 py-1 rounded"
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