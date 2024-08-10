import { Route, Routes } from "react-router-dom"
import Login from './screens/Login'
import Register from './screens/Register'
import { ToastContainer } from 'react-toastify'
import Home from './screens/Home'
import Category from './screens/Category'
import PhotographerProfile from './screens/PhotographerProfile'
import PhotographerPhotos from './screens/PhotographerPhotos'
import Booking from './screens/Booking';
import BookingConfirm from './screens/BookingConfirm';
import Appointments from './screens/Appointments';
import Navbar from "./screens/Navbar";
import PhotographerGrid from './screens/PhotographerGrid';


function App() {
  return (
    <div className="container">
      <Routes>
        <Route path="" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/photographer-profile" element={<PhotographerProfile />} />
        <Route path="/category/:categoryId" element={<PhotographerGrid />} />
        <Route path="/category/:categoryName" element={<Category />} />
        <Route path="/photographer/:photographerId/photos" element={<PhotographerPhotos />} />
       
        {/* <Route path="/" element={<Booking/>} /> */}
        <Route path="/booking" element={<Booking />} />
         <Route path="/booking-confirmation" element={<BookingConfirm/>} ></Route>
         <Route path="/appointments" element={<Appointments />}> </Route>
         <Route path="/navbar" element={<Navbar/>}></Route>
         {/* Add new route */}
         
      </Routes>
       
      <ToastContainer />
    </div>
  )
}


export default App
