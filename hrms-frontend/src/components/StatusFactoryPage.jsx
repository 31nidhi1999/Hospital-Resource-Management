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

successResource: {
  type: "success",
  message: "Resource added successfully!",
  redirectTo: "/admin/dashboard",
},

existedResource: {
  type: "exists",
  message: "Resource already exists!",
  redirectTo: "/admin/dashboard",
},

successRequest: {
  type: "success",
  message: "Resource request raised successfully!",
  redirectTo: "/doctor/dashboard"
},

successTreatment: {
  type: "success",
  message: "Treatment added successfully!",
  redirectTo: "/doctor/dashboard"
},

successUpdateAdmin: {
  type: "success",
  message: "User details updated successfully!",
  redirectTo: "/admin/dashboard"
},

successUpdateDoctor: {
  type: "success",
  message: "User details updated successfully!",
  redirectTo: "/doctor/dashboard"
},

successUpdatePatient: {
  type: "success",
  message: "User details updated successfully!",
  redirectTo: "/patient/dashboard"
},

};

export default function StatusPageFactory({ page }) {
  const config = STATUS_CONFIG[page];
  if (!config) return <div>Invalid status page</div>;

  return <UniversalStatusPage {...config} />;
}
