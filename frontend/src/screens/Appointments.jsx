import React from 'react';
import './Appointment.css';

const Appointments = () => {
  const bookingRequests = [
    { id: 1, clientName: 'John Doe', date: '2024-08-10', time: '10:00 AM', amount: '$100' },
    { id: 2, clientName: 'Jane Smith', date: '2024-08-11', time: '11:00 AM', amount: '$150' },
    { id: 3, clientName: 'Alice Johnson', date: '2024-08-12', time: '12:00 PM', amount: '$200' }
  ];

  return (
    <div className="appointments">
      <h2 className="heading">Appointments</h2>
      <div className="booking-requests">
        <h3>Booking Requests:</h3>
        {bookingRequests.map(req => (
          <div key={req.id} className="booking-request">
           <p>Client Name: {req.clientName}</p>
            <p>Date: {req.date}</p>
            <p>Time: {req.time}</p>
            <p>Amount: {req.amount}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Appointments;


