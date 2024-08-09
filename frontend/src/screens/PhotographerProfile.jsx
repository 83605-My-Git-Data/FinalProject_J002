import React, { useState } from 'react';
import './PhotographerProfile.css';

const PhotographerProfile = () => {
  const [profilePic, setProfilePic] = useState(null);
  const [name] = useState('John Doe');
  const [email] = useState('john@example.com');
  const [gender] = useState('Male');
  const [contact] = useState('1234567890');
  const [bio, setBio] = useState('');
  const [experience, setExperience] = useState(1);
  const [category, setCategory] = useState('');
  const [services, setServices] = useState('');
  const [price, setPrice] = useState('');

  const handleProfilePicChange = (e) => {
    const file = e.target.files[0];
    setProfilePic(URL.createObjectURL(file));
  };

  const handleProfilePicClick = () => {
    document.getElementById('profilePicInput').click();
  };

  const handleProfilePicDelete = () => {
    setProfilePic(null);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle form submission logic here
    alert('Profile updated successfully!');
  };

  return (
    <div className="photographer-profile">
      <div className="profile-pic-section">
        <img
          src={profilePic || 'default-profile-pic.jpg'}
         
          className="profile-pic"
          onClick={handleProfilePicClick}
        />
        <input
          type="file"
          accept="image/*"
          id="profilePicInput"
          onChange={handleProfilePicChange}
          className="file-input"
          style={{ display: 'none' }}
        />
        <div className="profile-pic-buttons">
          <button onClick={handleProfilePicClick} className="edit-btn">
            Edit Photo
          </button>
          <button onClick={handleProfilePicDelete} className="delete-btn">
            Delete Photo
          </button>
        </div>
      </div>
      <div className="profile-details">
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Name:</label>
            <input type="text" value={name} disabled className="form-control" />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input type="email" value={email} disabled className="form-control" />
          </div>
          <div className="form-group">
            <label>Gender:</label>
            <input type="text" value={gender} disabled className="form-control" />
          </div>
          <div className="form-group">
            <label>Contact No:</label>
            <input type="text" value={contact} disabled className="form-control" />
          </div>
          <div className="form-group">
            <label>Bio:</label>
            <textarea
              value={bio}
              onChange={(e) => setBio(e.target.value)}
              className="form-control"
            ></textarea>
          </div>
          <div className="form-group">
            <label>Experience Level:</label>
            <select
              value={experience}
              onChange={(e) => setExperience(e.target.value)}
              className="form-control"
            >
              {[...Array(10).keys()].map((level) => (
                <option key={level + 1} value={level + 1}>
                  {level + 1}
                </option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label>Category:</label>
            <div className="category-options">
              {['Nature', 'Wedding', 'Travel', 'Food'].map((cat) => (
                <div key={cat} className="category-option">
                  <input
                    type="radio"
                    id={cat}
                    name="category"
                    value={cat}
                    checked={category === cat}
                    onChange={(e) => setCategory(e.target.value)}
                  />
                  <label htmlFor={cat}>{cat}</label>
                </div>
              ))}
            </div>
          </div>
          <div className="form-group">
            <label>Services:</label>
            <textarea
              value={services}
              onChange={(e) => setServices(e.target.value)}
              className="form-control"
            ></textarea>
          </div>
          <div className="form-group">
            <label>Price:</label>
            <input
              type="text"
              value={price}
              onChange={(e) => setPrice(e.target.value)}
              className="form-control"
            />
          </div>
          <button type="submit" className="btn btn-primary">
            Update Profile
          </button>
        </form>
      </div>
    </div>
  );
};

export default PhotographerProfile;