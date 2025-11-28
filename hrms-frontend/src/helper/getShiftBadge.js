export default function getShiftBadge(shift) {
        switch (shift) {
            case "MORNING":
                return "bg-green-100 text-green-700";
            case "AFTERNOON":
                return "bg-yellow-100 text-yellow-700";
            case "NIGHT":
                return "bg-indigo-100 text-indigo-700";
            default:
                return "bg-gray-100 text-gray-700";
        }
    };