import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { fetchPhotographerImages } from '../services/admin'; 
import './PhotographerDetails.css';

const PhotographerDetails = () => {
  const { photographerId, categoryId } = useParams(); 
  const [images, setImages] = useState([]);
  const [photographerInfo, setPhotographerInfo] = useState(null);

  useEffect(() => {
    async function getImages() {
      console.log(photographerId + " " + categoryId);
      const data = await fetchPhotographerImages(photographerId, categoryId);
      setImages(data.map(item => item.image));
      setPhotographerInfo(data[0]?.photographer || null); // Assuming all photos have the same photographer info
    }

    getImages();
  }, [photographerId, categoryId]);

  if (!photographerInfo) return <div>Loading...</div>;

  return (
    <div className="photographer-details">
      <div className="photographer-info">
        <img 
          src={photographerInfo.profilePhoto} 
          alt={`${photographerInfo.register.firstName} ${photographerInfo.register.lastName}`} 
          className="photographer-profile-photo" 
        />
        <div className="photographer-bio">
          <h2>{`${photographerInfo.register.firstName} ${photographerInfo.register.lastName}`}</h2>
          <p>{photographerInfo.bio}</p>
        </div>
      </div>

      <h3>Photographer's Gallery</h3>
      <div className="photographer-photos-grid">
        {images.map((photoUrl, index) => (
          <div key={index} className="photographer-photo-container">
            <img 
              src={photoUrl} 
              alt={`Photo ${index + 1}`} 
              className="photographer-photo-detail" 
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default PhotographerDetails;
