import UniversalStatusPage from "../pages/ResponsePage/UniversalStatusPage";

export const ExistedUser = () => {
  return (
    <UniversalStatusPage
      type="exists"
      message="User Already Exists!"
      redirectTo="/register"
    />

  );
};

export const SuccessUserRegisteredPage = () => {
  return (
    <UniversalStatusPage
      type="success"
      message="User Registered Successfully!"
      redirectTo="/login"
    />
  );
};

export const SomethingWentWrong = () => {
  return (
    <UniversalStatusPage
      type="notfound"

      message="Resource Not Found"
      redirectTo="/admin/dashboard"
    />
  );
};

