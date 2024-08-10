import React, { createContext, useState } from 'react';

export const BookingContext = createContext();

export const BookingProvider = ({ children }) => {
  const [bookingRequests, setBookingRequests] = useState([]);

  const addBookingRequest = (request) => {
    setBookingRequests((prevRequests) => [...prevRequests, request]);
  };

  return (
    <BookingContext.Provider value={{ bookingRequests, addBookingRequest }}>
      {children}
    </BookingContext.Provider>
  );
};
