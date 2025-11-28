import { useState } from "react";
import { addResource } from "../../api/Resource";
import { CirclePlus } from "lucide-react";


export default function AddResource() {
  const [resource, setResource] = useState({
    resourceName: "",
    resourceType: "",
    totalQuantity: "",
  });

  const onChange = (e) => {
    setResource({
      ...resource,
      [e.target.name]: e.target.value,
    });
  };

  const submit = async (e) => {
    e.preventDefault();
    try {
      const res = await addResource(resource);
      alert("Resource Added Successfully!")
      setResource({ resourceName: "", resourceType: "", totalQuantity: "" });
    } catch (error) {
      alert("Error while adding resource");
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-50">
      <div className="w-full max-w-xl bg-white shadow-lg rounded-2xl p-8 border border-gray-100">
        <div className="flex items-center gap-3 mb-6">
          <div className="bg-indigo-100 p-3 rounded-xl">
            <CirclePlus className="text-indigo-700" size={26} />
          </div>
          <h2 className="text-2xl font-bold text-indigo-800">Add Resource</h2>
        </div>

        <form onSubmit={submit} className="space-y-4">

          <div>
            <label className="block mb-1 text-gray-600 font-medium">Resource Name</label>
            <input
              name="resourceName"
              value={resource.resourceName}
              onChange={onChange}
              placeholder="Enter resource name"
              className="w-full p-3 border bg-gray-50 rounded-lg focus:ring-2 focus:ring-blue-300 transition"
            />
          </div>

          <div>
            <label className="block mb-1 text-gray-600 font-medium">Resource Type</label>
            <select
              name="resourceType"
              value={resource.resourceType}
              onChange={onChange}
              className="w-full p-3 border bg-gray-50 rounded-lg focus:ring-2 focus:ring-blue-300 transition"
            >
              <option value="">-- Select --</option>
              <option value="BED">BED</option>
              <option value="VENTILATOR">VENTILATOR</option>
              <option value="EQUIPMENT">EQUIPMENT</option>
              <option value="ROOM">ROOM</option>
            </select>
          </div>

          <div>
            <label className="block mb-1 text-gray-600 font-medium">Total Quantity</label>
            <input
              name="totalQuantity"
              type="number"
              value={resource.totalQuantity}
              onChange={onChange}
              placeholder="Enter total quantity"
              className="w-full p-3 border bg-gray-50 rounded-lg focus:ring-2 focus:ring-blue-300 transition"
            />
          </div>

          <button
            type="submit"
            className="w-full p-3 bg-blue-600 text-white rounded-lg font-semibold hover:bg-blue-700 transition transform hover:scale-[1.01]"
          >
            Add Resource
          </button>
        </form>
      </div>
    </div>
  );
}
