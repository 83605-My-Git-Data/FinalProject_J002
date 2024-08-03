import React from 'react'
import { useParams } from 'react-router-dom'
import PhotographerCard from './PhotographerCard'

function Category() {
  const { categoryName } = useParams()

  const photographersData = {
    wedding: [
      {
        id: 1,
        name: 'Alice Smith',
        photo: 'alice_wedding.jpg',
        rate: 50,
      },
      {
        id: 2,
        name: 'Bob Johnson',
        photo: 'bob_wedding.jpg',
        rate: 70,
      },
      // Add more wedding photographers
    ],
    food: [
      {
        id: 3,
        name: 'Charlie Brown',
        photo: 'charlie_food.jpg',
        rate: 60,
      },
      {
        id: 4,
        name: 'Diana Prince',
        photo: 'diana_food.jpg',
        rate: 80,
      },
      // Add more food photographers
    ],
    // Add more categories and photographers
  }

  const photographers = photographersData[categoryName.toLowerCase()] || []

  return (
    <div>
      <h2>{categoryName.charAt(0).toUpperCase() + categoryName.slice(1)} Photography</h2>
      <div className="photographers-grid">
        {photographers.map((photographer) => (
          <PhotographerCard key={photographer.id} photographer={photographer} />
        ))}
      </div>
    </div>
  )
}

export default Category
