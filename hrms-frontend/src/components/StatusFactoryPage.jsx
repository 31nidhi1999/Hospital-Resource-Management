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
  }

};

export default function StatusPageFactory({ page }) {
  const config = STATUS_CONFIG[page];
  if (!config) return <div>Invalid status page</div>;

  return <UniversalStatusPage {...config} />;
}
