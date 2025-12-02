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
import UpdateDoctor from './pages/doctor/UpdateDoctor'
import UpdateAdmin from './pages/admin/UpdateAdmin'
import UpdatePatient from './pages/patient/UpdatePatient'
import PatientDashboard from './pages/patient/PatientDashboard'

function App() {

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar/>
      <div className="container mx-auto p-4">
          <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register/>}/>
            <Route path="/forgot-password" element={<ForgotPassword/>}/>
            <Route path="/reset-password" element={<ResetPassword/>}/>

            <Route path="/patient/dashboard" element={<ProtectedRoute role="PATIENT"><PatientDashboard/></ProtectedRoute>}/>
            <Route path="/patient/update-patient-details" element={<ProtectedRoute role="PATIENT"><UpdatePatient/></ProtectedRoute>}/>

            <Route path="/doctor/dashboard" element={<ProtectedRoute role="DOCTOR"><DoctorDashboard/></ProtectedRoute>}/>
            <Route path="/doctor/schedule" element={<ProtectedRoute role="DOCTOR"><DoctorSchedule/></ProtectedRoute>}/>
            <Route path="/doctor/request-resource" element={<ProtectedRoute role="DOCTOR"><RaisedRequest/></ProtectedRoute>}/>
            <Route path="/doctor/update-doctor-details" element={<ProtectedRoute role="DOCTOR"><UpdateDoctor/></ProtectedRoute>}/>

            /**Schedule */
            <Route path="/admin/resource-requests" element={<ProtectedRoute role="ADMIN"><ResourceAllocation/></ProtectedRoute>}/>
            <Route path="/admin/dashboard" element={<ProtectedRoute role="ADMIN"><AdminDashboard/></ProtectedRoute>}/>            
            <Route path="/admin/staff-schedule" element={<ProtectedRoute  role="ADMIN"><StaffSchedule/></ProtectedRoute>}/>
            <Route path="/admin/admit" element={<ProtectedRoute role="ADMIN"><AdmitPatient/></ProtectedRoute>}/>
            <Route path="/admin/discharge" element={<ProtectedRoute  role="ADMIN"><DischargePatient/></ProtectedRoute>}/>
            <Route path="/admin/approve-doctors" element={<ProtectedRoute role="ADMIN"><ApproveDoctor/></ProtectedRoute>}/>
            <Route path="/admin/add-resource" element={<ProtectedRoute role="ADMIN"><AddResource/></ProtectedRoute>}/>
            <Route path="/admin/update-admin-details" element={<ProtectedRoute role="ADMIN"><UpdateAdmin/></ProtectedRoute>}/>
          </Routes>
      </div>
    </div>
  )
}

export default App;
