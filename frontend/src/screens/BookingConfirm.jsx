// src/screens/BookingDetails.jsx
import React, { useState } from 'react';

const BookingDetails = () => {
  const [status, setStatus] = useState('Pending'); // Change to 'Rejected' to see the rejected status

  const booking = {
    photographerName: 'John Doe',
    date: '2024-08-04',
    time: '14:00',
    contactNo: '+1-234-567-8901',
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>Your Booking Details</h1>
      <div>
        <p><strong>Photographer Name:</strong> {booking.photographerName}</p>
        <p><strong>Date:</strong> {booking.date}</p>
        <p><strong>Time:</strong> {booking.time}</p>
        <p><strong>Contact No:</strong> {booking.contactNo}</p>
        <p><strong>Status:</strong> {status}</p>
      </div>
    </div>
  );
};

export default BookingDetails;
