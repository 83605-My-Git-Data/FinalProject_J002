import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaTrash } from 'react-icons/fa';
import {useLocation} from 'react-router-dom';

const PhotographerProfile = () => {
  const [profilePic, setProfilePic] = useState(null);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [gender, setGender] = useState('');
  const [contact, setContact] = useState('');
  const [bio, setBio] = useState('');
  const [experience, setExperience] = useState('BEGINNER');
  const [category, setCategory] = useState('');
  const [services, setServices] = useState('');
  const [price, setPrice] = useState('');
  const [photos, setPhotos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  // const [description,setDescription] = useState("");

  const location = useLocation();

  const photographerId = location.state.id; // Hardcoded ID, replace with dynamic ID as needed

  console.log("state " + JSON.stringify(location.state))
  useEffect(() => {
    if (photographerId) {
      setLoading(true);
      axios.get(`http://localhost:8080/photographer_profile/${photographerId}/photographerdetails`)
        .then(response => {
          console.log(response.data);
          const {
             profilePhoto, name, image, bio, description, price, phoneNumber            // profilePhoto, name, image, bio, price, phoneNumber
          } = response.data;
  
          setProfilePic(profilePhoto);
          setName(name);
          setPhotos(image || []); // Use image array from the response
          setBio(bio);
          setPrice(price);
          setContact(phoneNumber);
          setGender(location.state.gender);
          // setDescription(description);
          setEmail(location.state.sub);
          // Note: Description is not used in the form fields, adjust if needed
        })
        .catch(error => {
          console.error('Error fetching profile details:', error);
          setError('Failed to load profile data.');
        })
        .finally(() => setLoading(false));
    } else {
      setError('No photographer ID provided.');
      setLoading(false);
    }
  }, [photographerId]);
  

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  const handleProfilePicChange = (e) => {
    const file = e.target.files[0];
    const formData = new FormData();
    formData.append('file', file);

    axios.post(`http://localhost:8080/photographer_profile/uploadProfilePic/${photographerId}`, formData)
      .then(() => {
        setProfilePic(URL.createObjectURL(file));
        alert('Profile picture uploaded successfully!');
      })
      .catch(error => console.error('Error uploading profile picture:', error));
  };

  const handlePhotoChange = (e) => {
    const files = Array.from(e.target.files);
    const formData = new FormData();
    files.forEach(file => formData.append('photos', file));

    axios.post(`http://localhost:8080/photographer_profile/uploadPhotos/${photographerId}`, formData)
      .then(() => {
        const newPhotos = files.map(file => URL.createObjectURL(file));
        setPhotos(prevPhotos => [...prevPhotos, ...newPhotos]);
      })
      .catch(error => console.error('Error uploading photos:', error));
  };

  const handlePhotoDelete = (photoUrl) => {
    setPhotos(prevPhotos => prevPhotos.filter(photo => photo !== photoUrl));
    // Optionally, send a request to delete the photo from the server
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const profileData = {
      bio,
      experience,
      category,
      services,
      price
    };

    axios.post(`http://localhost:8080/photographer_profile/updateBio/${photographerId}`, profileData)
      .then(() => alert('Profile updated successfully!'))
      .catch(error => console.error('Error updating profile:', error));
  };

  return (
    <div className="container my-4">
      <div className="row">
        <div className="col-md-4 text-center">
          <img
            src={profilePic || 'default-profile-pic.jpg'}
            className="img-fluid rounded-circle"
            onClick={() => document.getElementById('profilePicInput').click()}
            alt="Profile"
          />
          <input
            type="file"
            accept="image/*"
            id="profilePicInput"
            onChange={handleProfilePicChange}
            className="d-none"
          />
          <div className="mt-2">
            <button onClick={() => document.getElementById('profilePicInput').click()} className="btn btn-secondary me-2">
              Edit Photo
            </button>
            <button onClick={() => setProfilePic(null)} className="btn btn-danger">
              Delete Photo
            </button>
          </div>
        </div>
        <div className="col-md-8">
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label className="form-label">Name:</label>
              <input
                type="text"
                value={name}
                className="form-control"
                disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Email:</label>
              <input
                type="email"
                value={email}
                className="form-control"
                disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Gender:</label>
              <input
                type="text"
                value={gender}
                className="form-control"
                disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Contact No:</label>
              <input
                type="text"
                value={contact}
                className="form-control"
                disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Bio:</label>
              <textarea
                value={bio}
                onChange={(e) => setBio(e.target.value)}
                className="form-control"
              ></textarea>
            </div>
            <div className="mb-3">
              <label className="form-label">Experience Level:</label>
              <select
                value={experience}
                onChange={(e) => setExperience(e.target.value)}
                className="form-select"
              >
                <option value="BEGINNER">BEGINNER</option>
                <option value="INTERMEDIATE">INTERMEDIATE</option>
                <option value="PROFESSIONAL">PROFESSIONAL</option>
              </select>
            </div>
            <div className="mb-3">
              <label className="form-label">Category:</label>
              <div className="d-flex flex-wrap gap-2">
                {['Nature', 'Wedding', 'Travel', 'Food', 'Other'].map((cat) => (
                  <div key={cat} className="form-check">
                    <input
                      type="radio"
                      id={cat}
                      name="category"
                      value={cat}
                      checked={category === cat}
                      onChange={(e) => setCategory(e.target.value)}
                      className="form-check-input"
                    />
                    <label htmlFor={cat} className="form-check-label">
                      {cat}
                    </label>
                  </div>
                ))}
                {category === 'Other' && (
                  <input
                    type="text"
                    placeholder="Type your category"
                    value={category}
                    onChange={(e) => setCategory(e.target.value)}
                    className="form-control mt-2"
                  />
                )}
              </div>
            </div>
            {category && (
              <>
                <div className="mb-3">
                  <label className="form-label">Services:</label>
                  <textarea
                    value={services}
                    onChange={(e) => setServices(e.target.value)}
                    className="form-control"
                  ></textarea>
                </div>
                <div className="mb-3">
                  <label className="form-label">Price:</label>
                  <input
                    type="text"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                    className="form-control"
                  />
                </div>
              </>
            )}
            <div className="mb-3">
              <label className="form-label">Upload Photos:</label>
              <button
                type="button"
                onClick={() => document.getElementById('photosInput').click()}
                className="btn btn-secondary"
              >
                Choose Photos
              </button>
              <input
                type="file"
                accept="image/*"
                multiple
                id="photosInput"
                onChange={handlePhotoChange}
                className="d-none"
              />
              <div className="mt-3 d-flex flex-wrap gap-2">
                {photos.map((photo, index) => (
                  <div key={index} className="position-relative">
                    <img
                      src={photo}
                      alt={`Photo ${index + 1}`}
                      className="img-fluid"
                      style={{ width: '100px', height: '100px' }}
                    />
                    <button
                      type="button"
                      className="btn btn-danger position-absolute top-0 end-0"
                      onClick={() => handlePhotoDelete(photo)}
                    >
                      <FaTrash />
                    </button>
                  </div>
                ))}
              </div>
            </div>
            <button type="submit"  className="btn btn-primary">Save Profile</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default PhotographerProfile;
