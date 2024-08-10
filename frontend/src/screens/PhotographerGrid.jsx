import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import {  fetchPhotographersByCategoryId } from '../services/admin'; 
import './PhotographerGrid.css'

const PhotographerGrid = () => {
  const { categoryId } = useParams(); 
  const [photographers, setPhotographers] = useState([]);
  console.log(categoryId);
  useEffect(() => {
    async function getPhotographers() {
      const data = await fetchPhotographersByCategoryId(categoryId);

      console.log("insideee");
      
      setPhotographers(data);
    }

    getPhotographers();
    
  }, []);
  return (
    <div className="photographer-grid">
      {photographers.map((photographer) => (
        
        <div key={photographer.id} className="photographer-card">
          <img src={photographer.profilePhoto
} alt={photographer.fullName} />


          <h3>{photographer.fullName}</h3>
          <p>{photographer.price}</p>
        </div>
      ))}
    </div>
  );
};

export default PhotographerGrid;