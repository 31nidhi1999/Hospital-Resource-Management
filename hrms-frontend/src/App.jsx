import { useState } from 'react'
import './App.css'
import Navbar from './components/Navbar'
import Login from './pages/auth/Login'
import { Route, Routes } from 'react-router-dom'
import Register from './pages/auth/Register'
import DoctorDashboard from './pages/doctor/DoctorDashboard'
import StaffSchedule from './pages/admin/StaffSchedule'
import AdminDashboard from './pages/admin/AdminDashboard'
import AdmitPatient from './pages/admin/AdmitPatient'
import DischargePatient from './pages/admin/DischargePatient'
import ApproveDoctor from './pages/admin/ApproveDoctors'
import DoctorSchedule from './pages/doctor/DoctorSchedule'
import ProtectedRoute from './components/ProtectedRoute'
import AddResource from './pages/admin/AddResource'
import RaisedRequest from './pages/doctor/RaisedRequest'
import ResourceAllocation from './pages/admin/ResourceAllocation'
import ForgotPassword from './pages/auth/ForgetPassword'
import ResetPassword from './pages/auth/ResetPassword'

function App() {

  return (
    <div className="min-h-screen bg-gray-50">
      {/* <Navbar/> */}
      <div className="container mx-auto p-4">
          <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register/>}/>
            <Route path="/forgot-password" element={<ForgotPassword/>}/>
            <Route path="/reset-password" element={<ResetPassword/>}/>

            <Route path="/doctor/dashboard" element={<ProtectedRoute role="DOCTOR"><DoctorDashboard/></ProtectedRoute>}/>
            <Route path="/doctor/schedule/:id" element={<ProtectedRoute role="DOCTOR"><DoctorSchedule/></ProtectedRoute>}/>
            <Route path="/doctor/request-resource"/>

            /**Schedule */
            <Route path="/admin/dashboard" element={<ProtectedRoute role="ADMIN"><AdminDashboard/></ProtectedRoute>}/>            
            <Route path="/admin/staff-schedule" element={<ProtectedRoute  role="ADMIN"><StaffSchedule/></ProtectedRoute>}/>
            <Route path="admin/admit" element={<ProtectedRoute role="ADMIN"><AdmitPatient/></ProtectedRoute>}/>
            <Route path="/admin/discharge" element={<ProtectedRoute  role="ADMIN"><DischargePatient/></ProtectedRoute>}/>
            <Route path="/admin/approve-doctors" element={<ProtectedRoute role="ADMIN"><ApproveDoctor/></ProtectedRoute>}/>
          </Routes>
      </div>
    </div>
  )
}

export default App;
