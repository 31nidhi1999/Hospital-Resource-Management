import { useState } from 'react'
import './App.css'
import Navbar from './components/Navbar'
import Login from './pages/Login'
import { Route, Routes } from 'react-router-dom'

function App() {

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar/>
      <div className="container mx-auto p-4">
          <Routes>
            <Route path="/login" element={<Login/>}/>
          </Routes>
      </div>
    </div>
  )
}

export default App
