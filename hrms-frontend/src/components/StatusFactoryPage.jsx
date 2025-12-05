import UniversalStatusPage from "../pages/ResponsePage/UniversalStatusPage";

const STATUS_CONFIG = {
  success: {
    type: "success",
    message: "User Registered Successfully!",
    redirectTo: "/login",
  },

  exist: {
    type: "exists",
    message: "User Already Exists!",
    redirectTo: "/register",
  },

  error: {
    type: "error",
    message: "Something Went Wrong",
    redirectTo: "/admin/dashboard",
  },

  notfound: {
    type: "notfound",
    message: "Resource Not Found",
    redirectTo: "/admin/dashboard",
  },

  empty: {
    type: "empty",
    message: "No Records Found",
    redirectTo: "/admin/dashboard",
  },

  emptyDoctor: {
    type: "empty",
    message: "No Records Found",
    redirectTo: "/doctor/dashboard",
  },

  errorDoctor: {
    type: "error",
    message: "Something Went Wrong",
    redirectTo: "/doctor/dashboard",
  },

  errorPatient: {
    type: "error",
    message: "Something Went Wrong",
    redirectTo: "/patient/dashboard",
  },
  
  emptyPatient: {
    type: "empty",
    message: "No Records Found",
    redirectTo: "/patient/dashboard",
  },

  errorRegister: {
    type: "error",
    message: "Something Went Wrong",
    redirectTo: "/register",
  },

  successResource: {
  type: "success",
  message: "Resource Request Approved Successfully!",
  redirectTo: "/admin/dashboard",
},

existedResource: {
  type: "exists",
  message: "Resource Request Already Exists!",
  redirectTo: "/admin/dashboard",
},

successAdmission: {
  type: "success",
  message: "Patient Admitted Successfully!",
  redirectTo: "/admin/dashboard",
},

existedAdmission: {
  type: "exists",
  message: "Admission Record Already Exists!",
  redirectTo: "/admin/dashboard",
},

dischargeaExist: {
  type: "exists",
  message: "Discharge Record Already Exists!",
  redirectTo: "/admin/dashboard",
},

dischargeSucces: {
  type: "success",
  message: "Patient Discharged Successfully!",
  redirectTo: "/admin/dashboard",
},



};

export default function StatusPageFactory({ page }) {
  const config = STATUS_CONFIG[page];
  if (!config) return <div>Invalid status page</div>;

  return <UniversalStatusPage {...config} />;
}
