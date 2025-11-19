import { useState } from "react";
import { useAuth } from "../../context/AuthContext";

export default function Login(){
    const[email,setEmail] = useState('');
    const[password,setPassword] = useState('');
    const[loading,setLoading] = useState(false);
    const[error,setError] = useState('');
    const{login} = useAuth();

    const submit = async (e) =>{
        e.preventDefault();
        setLoading(true)
        setError('')
        try{
            await login(email,password)
        }catch(err){
            setError(err)
        }finally{setLoading(false)}
    }

    return(
        <div className="max-w-md mx-auto bg-white p-6 rounded shadow">
            <h2 className="text-xl font-semibold mb-4">Login</h2>
            {error && <div className="text-red-600 mb-3">{error}</div>}
            <form className="space-y-3" onSubmit={submit}>
                <input type="email" value={email} onChange={e=>setEmail(e.target.value)} placeholder="Email" className="w-full p-2 border rounded"/>
                 <input type="password" value={password} onChange={e=>setPassword(e.target.value)} placeholder="Password" className="w-full p-2 border rounded"/>
                 <button type="submit" disabled={loading} className="w-full p-2 bg-blue-600 text-white rounded">{loading ? "Loging ..." : "Login"}</button>
            </form>

        </div>
    );
}