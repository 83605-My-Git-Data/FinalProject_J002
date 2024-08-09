import React from 'react'
import { useNavigate } from 'react-router-dom'

function PhotographerCard({ photographer }) {
  const navigate = useNavigate()

  const handleMoreClick = () => {
    navigate(`/photographer/${photographer.id}/photos`)
  }

  return (
    <div className="photographer-card">
      <img src={photographer.photo} alt={photographer.name} className="photographer-photo" />
      <h3>{photographer.name}</h3>
      <p>Rate: ${photographer.rate}/hour</p>
      <button onClick={handleMoreClick}>More</button>
    </div>
  )
}

export default PhotographerCard
