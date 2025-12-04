import { useParams } from "react-router-dom";
import StatusPageFactory from "../components/StatusFactoryPage";

export default function StatusPageRoute() {
  const { page } = useParams();
  return <StatusPageFactory page={page} />;
}