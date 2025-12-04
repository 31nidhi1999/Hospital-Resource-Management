import { Loader2 } from "lucide-react";

export default function FullScreenLoader(){
    return (
    <div className="h-screen flex items-center justify-center bg-gray-50">
      <Loader2 className="animate-spin text-blue-600" size={60} />
    </div>
    );
}