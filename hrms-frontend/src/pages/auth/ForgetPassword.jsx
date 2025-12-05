import { useState } from "react";
import { sendOtp,verifyOtp} from "../../api/password";
import { useNavigate } from "react-router-dom";
import { goToStatus } from "../../utils/goToStatus";

export default function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [otpInputs, setOtpInputs] = useState(Array(6).fill(""));
  const [showOtp, setShowOtp] = useState(false);
  const navigate = useNavigate();

  const handleGenerateOtp = async () => {
    try {
        console.log(email);
      const res = await sendOtp({email});
      alert("OTP has been sent to your registered email address.");

      localStorage.setItem("fp_email", email);
      localStorage.setItem("fp_token", res.otpToken);

      setShowOtp(true);
    } catch (err) {
      if(err?.response?.status === 500){
        goToStatus(navigate,"mailSendingFailed");
        return;
      }
      goToStatus(navigate,"userNotFound");
    }
  };

  const handleOtpChange = (value, i) => {
    if (value.length > 1) return;

    const updated = [...otpInputs];
    updated[i] = value;
    setOtpInputs(updated);
  };

  const handleVerifyOtp = async () => {
    const otp = otpInputs.join("");

    try {
      const token = localStorage.getItem("fp_token");

      await verifyOtp({
        email,
        otp,
        otpToken: token
      });

      navigate("/reset-password");
    } catch (err) {
      goToStatus(navigate,"otpVerificationFailed");
    }
  };

  return (
    <div className="p-6 w-full max-w-md mx-auto">
      <h2 className="text-xl font-bold mb-4">Forgot Password</h2>

      {!showOtp && (
        <>
          <input
            type="email"
            className="w-full border p-2 rounded"
            placeholder="Enter Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <button
            onClick={handleGenerateOtp}
            className="w-full bg-blue-600 text-white py-2 mt-3 rounded"
          >
            Generate OTP
          </button>
        </>
      )}

      {showOtp && (
        <>
          <p className="mb-2 text-sm">Enter 6 digit OTP</p>

          <div className="flex gap-2 mb-4">
            {otpInputs.map((v, i) => (
              <input
                key={i}
                maxLength="1"
                className="w-10 h-10 border text-center text-lg rounded"
                value={v}
                onChange={(e) => handleOtpChange(e.target.value, i)}
              />
            ))}
          </div>

          <button
            onClick={handleVerifyOtp}
            className="w-full bg-green-600 text-white py-2 rounded"
          >
            Verify OTP
          </button>
        </>
      )}
    </div>
  );
}
