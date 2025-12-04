import { useEffect } from "react";
import {
  CheckCircle,
  XCircle,
  AlertTriangle,
  UserX,
  ShieldAlert,
  FileX,
  Info,
} from "lucide-react";
import { useNavigate } from "react-router-dom";

export default function UniversalStatusPage({
  type = "success",
  message = "",
  redirectTo = "/",
  duration = 2500,
  listLength = null,
}) {
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => navigate(redirectTo), duration);
    return () => clearTimeout(timer);
  }, [navigate, redirectTo, duration]);

  // Icons for different cases
  const icons = {
    success: <CheckCircle size={90} className="text-green-600 animate-bounce" />,
    error: <XCircle size={90} className="text-red-600 animate-bounce" />,
    warning: <AlertTriangle size={90} className="text-yellow-600 animate-bounce" />,
    empty: <FileX size={90} className="text-gray-500 animate-pulse" />,
    notfound: <UserX size={90} className="text-gray-700 animate-bounce" />,
    exists: <ShieldAlert size={90} className="text-red-600 animate-pulse" />,
    info: <Info size={90} className="text-blue-600 animate-bounce" />,
  };

  // Background colors
  const colors = {
    success: "bg-green-50",
    error: "bg-red-50",
    warning: "bg-yellow-50",
    empty: "bg-gray-100",
    notfound: "bg-gray-50",
    exists: "bg-red-50",
    info: "bg-blue-50",
  };

  // Handle list-based empty case
  let finalMessage = message;
  if (listLength === 0) {
    finalMessage = "No Records Found";
    type = "empty";
  }

  return (
    <div className={`h-screen flex flex-col items-center justify-center ${colors[type]}`}>
      {icons[type]}
      
      <h1 className="text-3xl font-bold mt-4 text-gray-800">
        {finalMessage}
      </h1>

      <p className="text-gray-600 mt-2">
        Redirecting...
      </p>

      <p className="text-sm text-gray-400 mt-6">Please wait a moment…</p>
    </div>
  );
}
