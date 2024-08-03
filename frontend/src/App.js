import { Route, Routes } from "react-router-dom"
import Login from './screens/Login'
import Register from './screens/Register'
import { ToastContainer } from 'react-toastify'
import Home from './screens/Home'
import Category from './screens/Category'
import PhotographerProfile from './screens/PhotographerProfile'
import PhotographerPhotos from './screens/PhotographerPhotos'

function App() {
  return (
    <div className="container">
      <Routes>
        <Route path="" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/photographer-profile" element={<PhotographerProfile />} />
        <Route path="/category/:categoryName" element={<Category />} />
        <Route path="/photographer/:photographerId/photos" element={<PhotographerPhotos />} />
      </Routes>
      <ToastContainer />
    </div>
  )
}

export default App
