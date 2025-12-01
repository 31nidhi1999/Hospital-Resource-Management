import { useState, useEffect } from "react";
import { resetPassword } from "../../api/password";
import { useNavigate } from "react-router-dom";

export default function ResetPassword() {
  const [newPassword, setNewPassword] = useState("");
  const email = localStorage.getItem("fp_email");
  const otpToken = localStorage.getItem("fp_token");

  const navigate = useNavigate();

  const handleReset = async () => {
    try {
      await resetPassword({
        email,
        newPassword,
        otpToken
      });

      alert("Password updated successfully");
      localStorage.removeItem("fp_email");
      localStorage.removeItem("fp_token");

      navigate("/login");
    } catch (err) {
      alert("Failed to reset password");
    }
  };

  return (
    <div className="p-6 w-full max-w-md mx-auto">
      <h2 className="text-xl font-bold mb-4">Reset Password</h2>

      <input
        type="password"
        className="w-full border p-2 rounded"
        placeholder="Enter New Password"
        value={newPassword}
        onChange={(e) => setNewPassword(e.target.value)}
      />

      <button
        onClick={handleReset}
        className="w-full bg-blue-600 text-white py-2 mt-3 rounded"
      >
        Update Password
      </button>
    </div>
  );
}
