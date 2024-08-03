import React from 'react'
import { useParams } from 'react-router-dom'

function PhotographerPhotos() {
  const { photographerId } = useParams()

  const photographers = {
    1: {
      name: 'Alice Smith',
      photos: ['alice1.jpg', 'alice2.jpg', 'alice3.jpg'],
    },
    2: {
      name: 'Bob Johnson',
      photos: ['bob1.jpg', 'bob2.jpg', 'bob3.jpg'],
    },
    3: {
      name: 'Charlie Brown',
      photos: ['charlie1.jpg', 'charlie2.jpg', 'charlie3.jpg'],
    },
    4: {
      name: 'Diana Prince',
      photos: ['diana1.jpg', 'diana2.jpg', 'diana3.jpg'],
    },
    // Add more photographers' photos
  }

  const photographer = photographers[photographerId] || { name: '', photos: [] }

  return (
    <div className="photographer-photos">
      <h2>{photographer.name}'s Photos</h2>
      <div className="photos-grid">
        {photographer.photos.map((photo, index) => (
          <img key={index} src={photo} alt={`Photo ${index + 1}`} className="photo" />
        ))}
      </div>
    </div>
  )
}

export default PhotographerPhotos
