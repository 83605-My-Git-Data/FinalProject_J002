// src/screens/BookingConfirmation.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';
import './Booking.css';


function BookingConfirmation() {
  const [form, setForm] = useState({
    photographerName: '',
    date: '',
    time: '',
    amount: '',
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: value,
    });
  };

  const handleConfirm = () => {
    // Handle booking confirmation logic here
    console.log('Booking Confirmed:', form);
    navigate('/home'); // Navigate to home after confirmation
  };

  const handleGoBack = () => {
    navigate(-1); // Navigate back to the previous page
  };

  const handleCancel = () => {
    navigate('/'); // Navigate to the home page or any other desired route
  };

  return (
    <div className="booking-confirmation">
      <h1 style={{ textAlign: 'center' }}>Booking Confirmation</h1>
      <form>
        <div>
          <label>Customer Name:</label>
          <input
            type="text"
            name="photographerName"
            value={form.photographerName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Date:</label>
          <input
            type="date"
            name="date"
            value={form.date}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Time:</label>
          <input
            type="time"
            name="time"
            value={form.time}
            onChange={handleChange}
          />
        </div>
        <div>
        <label>Amount:</label>
          <input
            type="number"
            name="amount" // updated name attribute to 'amount'
            value={form.amount}
            onChange={handleChange}
          />
        </div>
        <div className="buttons">
          <button type="button" onClick={handleConfirm}>
            Yes, confirm my booking
          </button>
          <button type="button" onClick={handleGoBack}>
            Go Back
          </button>
          <button type="button" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}

export default BookingConfirmation;
