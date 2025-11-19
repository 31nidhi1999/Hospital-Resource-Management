import { useState } from 'react'
import './App.css'
import Navbar from './components/Navbar'
import Login from './pages/auth/Login'
import { Route, Routes } from 'react-router-dom'
import Register from './pages/auth/Register'

function App() {

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar/>
      <div className="container mx-auto p-4">
          <Routes>
            <Route path="/login" element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
          </Routes>
      </div>
    </div>
  )
}

export default App
