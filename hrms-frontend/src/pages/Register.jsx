import { useState } from "react";

export default function Register(){
    const[role,setRole] = useState('PATIENT');
    const[form,setForm] = useState({name:'',email:'',password:''})
    const [extra, setExtra] = useState({ specialization: '', licenseNumber: '', age: '', address: '' })
    const [msg,setMsg] = useState('')

    const onChange = (e)=>setForm({...form,[e.target.name] : e.target.value})
    const onExtra =(e)=>setExtra({...extra,[e.target.name]:e.target.value})

}