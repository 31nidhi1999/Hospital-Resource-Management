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

function App() {

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar/>
      <div className="container mx-auto p-4">
          <Routes>
            <Route path="/login" element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>

            <Route path="/doctor/dashboard" element={<DoctorDashboard/>}/>
            <Route path="/doctor/schedule/:id" element={<DoctorSchedule/>}/>
            <Route path="/doctor/request-resource"/>

            /**Schedule */
            <Route path="/admin/dashboard" element={<AdminDashboard/>}/>            
            <Route path="/admin/staff-schedule" element={<StaffSchedule/>}/>
            <Route path="admin/admit" element={<AdmitPatient/>}/>
            <Route path="/admin/discharge" element={<DischargePatient/>}/>
            <Route path="/admin/approve-doctors" element={<ApproveDoctor/>}/>
          </Routes>
      </div>
    </div>
  )
}

export default App;
