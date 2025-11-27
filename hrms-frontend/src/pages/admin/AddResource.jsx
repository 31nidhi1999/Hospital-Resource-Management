import { useState } from "react";
import { addResource } from "../../api/Resource";


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
    <div className="max-w-2xl mx-auto bg-white p-6 rounded shadow">
      <h2 className="text-xl font-semibold mb-4">Add Resource</h2>

      <form onSubmit={submit} className="space-y-4">
    
        <div>
          <label className="block mb-1">Resource Name</label>
          <input
            name="resourceName"
            value={resource.resourceName}
            onChange={onChange}
            placeholder="Enter resource name"
            className="w-full p-2 border rounded"
          />
        </div>

        <div>
          <label className="block mb-1">Select Resource Type</label>
          <select
            name="resourceType"
            value={resource.resourceType}
            onChange={onChange}
            className="w-full p-2 border rounded"
          >
            <option value="">-- Select --</option>
            <option value="BED">BED</option>
            <option value="VENTILATOR">VENTILATOR</option>
            <option value="EQUIPMENT">EQUIPMENT</option>
            <option value="ROOM">ROOM</option>
          </select>
        </div>

        <div>
          <label className="block mb-1">Total Quantity</label>
          <input
            name="totalQuantity"
            type="number"
            value={resource.totalQuantity}
            onChange={onChange}
            placeholder="Enter total quantity"
            className="w-full p-2 border rounded"
          />
        </div>

        <button
          type="submit"
          className="w-full p-2 bg-green-600 text-white rounded"
        >
          Add Resource
        </button>
      </form>
    </div>
  );
}
