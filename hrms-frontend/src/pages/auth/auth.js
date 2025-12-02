export const getLoggedInUserId = () => {
  const user = JSON.parse(localStorage.getItem("hrms_user"));
  return user?.id || null;
};