import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import './home.css' // Correct relative path to import the CSS file

function Home() {
  const [user, setUser] = useState({ name: 'John Doe', email: 'john@example.com' }) // Example user details
  const [category, setCategory] = useState('')
  const navigate = useNavigate()

  const categories = ['Wedding', 'Food','Nature','Travel'] // Example categories

  const handleCategoryChange = (e) => {
    setCategory(e.target.value)
    if (e.target.value) {
      navigate(`/category/${e.target.value.toLowerCase()}`)
    }
  }

  return (
    <div className='home-container'>
      <header className='home-header'>
        <div className='logo'>MyAppLogo</div>
        <div className='categories-dropdown'>
          <div className='dropdown-container'>
            <select value={category} onChange={handleCategoryChange}>
              <option value=''>Select Category</option>
              {categories.map((cat) => (
                <option key={cat} value={cat}>
                  {cat}
                </option>
              ))}
            </select>
          </div>
        </div>
        <div className='user-details'>
          <p>{user.name}</p>
          <p>{user.email}</p>
        </div>
      </header>
      <main>
        <h2>Welcome to the Home Page</h2>
        {/* Additional content can be added here */}
      </main>
    </div>
  )
}

export default Home
