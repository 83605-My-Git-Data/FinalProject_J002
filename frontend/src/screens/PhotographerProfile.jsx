import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { FaTrash } from 'react-icons/fa';
import { jwtDecode } from 'jwt-decode';
import { config } from '../services/config';

const PhotographerProfile = () => {
  const jwt = sessionStorage.getItem('token');
  const decodedToken = jwtDecode(jwt);
  const photographerId = decodedToken.id;
  const photographerName = decodedToken.Name;
  const photographerEmail = decodedToken.email;
  const photographerGender = decodedToken.gender;
  const photographerContactNo = decodedToken.contactno;

  const [profilePic, setProfilePic] = useState(null);
  const [name, setName] = useState(photographerName);
  const [email, setEmail] = useState(photographerEmail);
  const [gender, setGender] = useState(photographerGender);
  const [contact, setContact] = useState(photographerContactNo);
  const [bio, setBio] = useState('');
  const [experience, setExperience] = useState('BEGINNER');
  const [category, setCategory] = useState('');
  const [categories, setCategories] = useState([]);
  const [services, setServices] = useState('');
  const [price, setPrice] = useState('');
  const [photos, setPhotos] = useState([]);
  const [editingExperience, setEditingExperience] = useState(false);
  const [editingBio, setEditingBio] = useState(false);
  const [editingService, setEditingService] = useState(false);

  const navigate = useNavigate();

 


  useEffect(() => {
    const fetchProfileData = async () => {
      try {
        // Fetch profile photo
        const token = sessionStorage.getItem('token');
        const photoResponse = await axios.get(`${config.serverUrl}/photographer_profile/${photographerId}/profile-photo`, {
          headers: {
          Authorization: `Bearer ${token}`
          }});
        if (photoResponse.data) {
          setProfilePic(photoResponse.data);
        } else {
          setProfilePic(null);
        }

        // Fetch categories
        const categoriesResponse = await axios.get(`${config.serverUrl}/Categories`, {
          headers: {
          Authorization: `Bearer ${token}`
          }});
        setCategories(categoriesResponse.data);

        // Fetch bio
        const bioResponse = await axios.get(`${config.serverUrl}/photographer_profile/${photographerId}/Bio`,{
          headers: {
          Authorization: `Bearer ${token}`
          }});
        setBio(bioResponse.data);

        // Fetch experience
        const experienceResponse = await axios.get(`${config.serverUrl}/photographer_profile/${photographerId}/experience`,{
          headers: {
          Authorization: `Bearer ${token}`
          }});
        setExperience(experienceResponse.data);
      } catch (error) {
        console.error('Error fetching profile data:', error);
      }
    };

    fetchProfileData();
  }, [photographerId]);

  const handleProfilePicChange = (e) => {
    const token = sessionStorage.getItem('token');
    const file = e.target.files[0];
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    axios.post(`${config.serverUrl}/photographer_profile/uploadProfilePic/${photographerId}`, formData,{
      headers: {
      Authorization: `Bearer ${token}`
      }})
      .then(() => {
        setProfilePic(URL.createObjectURL(file));
        alert('Profile picture updated successfully!');
      })
      .catch(error => {
        console.error('Error uploading profile picture:', error);
        alert('Failed to update profile picture.');
      });
  };

  const handlePhotoChange = (e) => {
    const files = Array.from(e.target.files);
    const newPhotos = files.map(file => URL.createObjectURL(file));
    setPhotos(prevPhotos => [...prevPhotos, ...newPhotos]);
  };

  const handlePhotoDelete = (photoUrl) => {
    setPhotos(prevPhotos => prevPhotos.filter(photo => photo !== photoUrl));
  };

  const handleBioSave = () => {
    const token = sessionStorage.getItem('token');
    axios.post(`${config.serverUrl}/photographer_profile/updateBio/${photographerId}`, { bio },{
      headers: {
      Authorization: `Bearer ${token}`
      }})
      .then(() => {
        alert('Bio updated successfully!');
        setEditingBio(false);
      })
      .catch(error => {
        console.error('Error updating bio:', error);
        alert('Failed to update bio.');
      });
  };

  const handleExperienceSave = () => {
    const token = sessionStorage.getItem('token');
    axios.post(`${config.serverUrl}/photographer_profile/updateExperienceLevel/${photographerId}`, { experienceLevel: experience },{
      headers: {
      Authorization: `Bearer ${token}`
      }})
      .then(() => {
        alert('Experience level updated successfully!');
        setEditingExperience(false);
      })
      .catch(error => {
        console.error('Error updating experience level:', error);
        alert('Failed to update experience level.');
      });
  };

  const handleCategorySelection = (categoryId) => {
    const token = sessionStorage.getItem('token');
    axios.post(`${config.serverUrl}/photographer/addcategory`, {
      photographerId: photographerId,
      categoryId: categoryId
    },{
      headers: {
      Authorization: `Bearer ${token}`
      }})
    .then(() => {
      alert('Category added successfully!');
    })
    .catch(error => {
      console.error('Error adding category:', error);
      alert('Failed to add category.');
    });
  };

  const handleServiceSubmit = () => {
    const token = sessionStorage.getItem('token');
    axios.post(`${config.serverUrl}/services`, {
      photographerid: photographerId,
      categoryid: category,
      price: price,
      description: services
    },{
      headers: {
      Authorization: `Bearer ${token}`
      }})
    .then(() => {
      alert('Service added successfully!');
      setEditingService(false);
      setPrice('');
      setServices('');
    })
    .catch(error => {
      console.error('Error adding service:', error);
      alert('Failed to add service.');
    });
  };


  const addCategoryToPhotographer = () =>{
    const token = sessionStorage.getItem('token');
    axios.post(`${config.serverUrl}/photographer/addcategory`,{
      pid: photographerId,
      cid: category
    },{
      headers: {
      Authorization: `Bearer ${token}`
      }})
    .then(()=>{
      console.log("category added to photographer");
    })
  }

  const handleButtonClick = () => {
    addCategoryToPhotographer();

    setTimeout(()=>{
      handleServiceSubmit();

    },1000)
    
  };
  

  return (
    <div className="container my-4">
      <h2 className="text-center mb-4">Photographer Dashboard</h2>
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
          <form>
            <div className="mb-3">
              <label className="form-label">Name:</label>
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                className="form-control" disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Email:</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="form-control" disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Gender:</label>
              <input
                type="text"
                value={gender}
                onChange={(e) => setGender(e.target.value)}
                className="form-control" disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Contact No:</label>
              <input
                type="text"
                value={contact}
                onChange={(e) => setContact(e.target.value)}
                className="form-control" disabled
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Bio:</label>
              {editingBio ? (
                <>
                  <textarea
                    value={bio}
                    onChange={(e) => setBio(e.target.value)}
                    className="form-control"
                  ></textarea>
                  <button
                    type="button"
                    className="btn btn-primary mt-2"
                    onClick={handleBioSave}
                  >
                    Save
                  </button>
                </>
              ) : (
                <>
                  <p>{bio}</p>
                  <button
                    type="button"
                    className="btn btn-secondary mt-2"
                    onClick={() => setEditingBio(true)}
                  >
                    Edit Bio
                  </button>
                </>
              )}
            </div>
            <div className="mb-3">
              <label className="form-label">Experience Level:</label>
              {editingExperience ? (
                <>
                  <select
                    value={experience}
                    onChange={(e) => setExperience(e.target.value)}
                    className="form-select"
                  >
                    <option value="BEGINNER">BEGINNER</option>
                    <option value="INTERMEDIATE">INTERMEDIATE</option>
                    <option value="PROFESSIONAL">PROFESSIONAL</option>
                  </select>
                  <button
                    type="button"
                    className="btn btn-primary mt-2"
                    onClick={handleExperienceSave}
                  >
                    Save
                  </button>
                </>
              ) : (
                <>
                  <p>{experience}</p>
                  <button
                    type="button"
                    className="btn btn-secondary mt-2"
                    onClick={() => setEditingExperience(true)}
                  >
                    Edit Experience
                  </button>
                </>
              )}
            </div>
            <div className="mb-3">
              <label className="form-label">Add Service:</label>
              {editingService ? (
                <>
                  <div className="mb-3">
                    <label className="form-label">Category:</label>
                    <select
                      value={category}
                      onChange={(e) => setCategory(e.target.value)}
                      className="form-select"
                    >
                      {categories.map(cat => (
                        <option key={cat.id} value={cat.id}>{cat.categoryname}</option>
                      ))}
                    </select>
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
                  <div className="mb-3">
                    <label className="form-label">Description:</label>
                    <textarea
                      value={services}
                      onChange={(e) => setServices(e.target.value)}
                      className="form-control"
                    ></textarea>
                  </div>
                  <button
                    type="button"
                    className="btn btn-primary mt-2"
                    onClick={handleButtonClick}
                   
                  >
                    Save Service
                  </button>
                </>
              ) : (
                <>
                  <button
                    type="button"
                    className="btn btn-secondary mt-2"
                    onClick={() => setEditingService(true)}
                  >
                    Add Service
                  </button>
                </>
              )}
            </div>

            <button onClick={() => navigate('/AddPhotos')}>Next</button>

          
            
          </form>
        </div>
      </div>
    </div>
  );
};

export default PhotographerProfile;

























// import React, { useState, useEffect } from 'react';
// import axios from 'axios';
// import { Link, useNavigate } from 'react-router-dom';
// import { FaTrash } from 'react-icons/fa';
// import { jwtDecode } from 'jwt-decode';

// const PhotographerProfile = () => {
//   const jwt = sessionStorage.getItem('token');
//   const decodedToken = jwtDecode(jwt);
//   const photographerId = decodedToken.id;
//   const photographerName = decodedToken.Name;
//   const photographerEmail = decodedToken.email;
//   const photographerGender = decodedToken.gender;
//   const photographerContactNo = decodedToken.contactno;

//   const [profilePic, setProfilePic] = useState(null);
//   const [name, setName] = useState(photographerName);
//   const [email, setEmail] = useState(photographerEmail);
//   const [gender, setGender] = useState(photographerGender);
//   const [contact, setContact] = useState(photographerContactNo);
//   const [bio, setBio] = useState('');
//   const [experience, setExperience] = useState('BEGINNER');
//   const [category, setCategory] = useState('');
//   const [categories, setCategories] = useState([]);
//   const [services, setServices] = useState('');
//   const [price, setPrice] = useState('');
//   const [photos, setPhotos] = useState([]);
//   const [editingExperience, setEditingExperience] = useState(false);
//   const [editingBio, setEditingBio] = useState(false);
//   const [editingService, setEditingService] = useState(false);

//   const navigate = useNavigate();

  // useEffect(() => {
  //   const fetchProfileData = async () => {
  //     try {
  //       // Fetch profile photo
  //       const photoResponse = await axios.get(`http://localhost:8080/photographer_profile/${photographerId}/profile-photo`);
  //       if (photoResponse.data) {
  //         setProfilePic(photoResponse.data);
  //       } else {
  //         setProfilePic(null);
  //       }

  //       // Fetch categories
  //       const categoriesResponse = await axios.get('http://localhost:8080/Categories');
  //       setCategories(categoriesResponse.data);

  //       // Fetch bio
  //       const bioResponse = await axios.get(`http://localhost:8080/photographer_profile/${photographerId}/Bio`);
  //       setBio(bioResponse.data);

  //       // Fetch experience
  //       const experienceResponse = await axios.get(`http://localhost:8080/photographer_profile/${photographerId}/experience`);
  //       setExperience(experienceResponse.data);
  //     } catch (error) {
  //       console.error('Error fetching profile data:', error);
  //     }
  //   };

  //   fetchProfileData();
  // }, [photographerId]);

//   const handleProfilePicChange = (e) => {
//     const file = e.target.files[0];
//     if (!file) return;

//     const formData = new FormData();
//     formData.append('file', file);

//     axios.post(`http://localhost:8080/photographer_profile/uploadProfilePic/${photographerId}`, formData)
//       .then(() => {
//         setProfilePic(URL.createObjectURL(file));
//         alert('Profile picture updated successfully!');
//       })
//       .catch(error => {
//         console.error('Error uploading profile picture:', error);
//         alert('Failed to update profile picture.');
//       });
//   };

//   const handlePhotoChange = (e) => {
//     const files = Array.from(e.target.files);
//     const newPhotos = files.map(file => URL.createObjectURL(file));
//     setPhotos(prevPhotos => [...prevPhotos, ...newPhotos]);
//   };

//   const handlePhotoDelete = (photoUrl) => {
//     setPhotos(prevPhotos => prevPhotos.filter(photo => photo !== photoUrl));
//   };

//   const handleBioSave = () => {
//     axios.post(`http://localhost:8080/photographer_profile/updateBio/${photographerId}`, { bio })
//       .then(() => {
//         alert('Bio updated successfully!');
//         setEditingBio(false);
//       })
//       .catch(error => {
//         console.error('Error updating bio:', error);
//         alert('Failed to update bio.');
//       });
//   };

//   const handleExperienceSave = () => {
//     axios.post(`http://localhost:8080/photographer_profile/updateExperienceLevel/${photographerId}`, { experienceLevel: experience })
//       .then(() => {
//         alert('Experience level updated successfully!');
//         setEditingExperience(false);
//       })
//       .catch(error => {
//         console.error('Error updating experience level:', error);
//         alert('Failed to update experience level.');
//       });
//   };

//   const handleCategorySelection = (categoryId) => {
//     axios.post('http://localhost:8080/photographer/addcategory', {
//       photographerId: photographerId,
//       categoryId: categoryId
//     })
//     .then(() => {
//       alert('Category added successfully!');
//     })
//     .catch(error => {
//       console.error('Error adding category:', error);
//       alert('Failed to add category.');
//     });
//   };

//   const handleServiceSubmit = () => {
//     axios.post('http://localhost:8080/services', {
//       photographerid: photographerId,
//       categoryid: category,
//       price: price,
//       description: services
//     })
//     .then(() => {
//       alert('Service added successfully!');
//       setEditingService(false);
//       setPrice('');
//       setServices('');
//     })
//     .catch(error => {
//       console.error('Error adding service:', error);
//       alert('Failed to add service.');
//     });
//   };

//   const addCategoryToPhotographer = () =>{
//     axios.post('http://localhost:8080/photographer/addcategory',{
//       pid: photographerId,
//       cid: category
//     })
//     .then(()=>{
//       console.log("category added to photographer");
//     })
//   }

//   const handleButtonClick = () => {
//     addCategoryToPhotographer();
//     handleServiceSubmit();
//   };

//   return (
//     <div className="container my-4">
//       <h2 className="text-center mb-4">Photographer Dashboard</h2>
//       <div className="row">
//         <div className="col-md-4 text-center">
//           <img
//             src={profilePic || 'default-profile-pic.jpg'}
//             className="img-fluid rounded-circle"
//             onClick={() => document.getElementById('profilePicInput').click()}
//             alt="Profile"
//           />
//           <input
//             type="file"
//             accept="image/*"
//             id="profilePicInput"
//             onChange={handleProfilePicChange}
//             className="d-none"
//           />
//           <div className="mt-2">
//             <button onClick={() => document.getElementById('profilePicInput').click()} className="btn btn-secondary me-2">
//               Edit Photo
//             </button>
//             <button onClick={() => setProfilePic(null)} className="btn btn-danger">
//               Delete Photo
//             </button>
//           </div>
//         </div>
//         <div className="col-md-8">
//           <form>
//             <div className="mb-3">
//               <label className="form-label">Name:</label>
//               <input
//                 type="text"
//                 value={name}
//                 onChange={(e) => setName(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Email:</label>
//               <input
//                 type="email"
//                 value={email}
//                 onChange={(e) => setEmail(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Gender:</label>
//               <input
//                 type="text"
//                 value={gender}
//                 onChange={(e) => setGender(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Contact No:</label>
//               <input
//                 type="text"
//                 value={contact}
//                 onChange={(e) => setContact(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Bio:</label>
//               {editingBio ? (
//                 <>
//                   <textarea
//                     value={bio}
//                     onChange={(e) => setBio(e.target.value)}
//                     className="form-control"
//                   ></textarea>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleBioSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{bio}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingBio(true)}
//                   >
//                     Edit Bio
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Experience Level:</label>
//               {editingExperience ? (
//                 <>
//                   <select
//                     value={experience}
//                     onChange={(e) => setExperience(e.target.value)}
//                     className="form-select"
//                   >
//                     <option value="BEGINNER">Beginner</option>
//                     <option value="INTERMEDIATE">Intermediate</option>
//                     <option value="EXPERT">Expert</option>
//                   </select>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleExperienceSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{experience}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingExperience(true)}
//                   >
//                     Edit Experience
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Add Services:</label>
//               {editingService ? (
//                 <>
//                   <div className="mb-3">
//                     <label className="form-label">Category:</label>
//                     <select
//                       value={category}
//                       onChange={(e) => setCategory(e.target.value)}
//                       className="form-select"
//                     >
//                       <option value="">Select Category</option>
//                       {categories.map(category => (
//                         <option key={category.id} value={category.id}>
//                           {category.categoryname}
//                         </option>
//                       ))}
//                     </select>
//                   </div>
//                   <div className="mb-3">
//                     <label className="form-label">Price:</label>
//                     <input
//                       type="number"
//                       value={price}
//                       onChange={(e) => setPrice(e.target.value)}
//                       className="form-control"
//                     />
//                   </div>
//                   <div className="mb-3">
//                     <label className="form-label">Description:</label>
//                     <textarea
//                       value={services}
//                       onChange={(e) => setServices(e.target.value)}
//                       className="form-control"
//                     ></textarea>
//                   </div>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleButtonClick}
//                   >
//                     Save Service
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingService(true)}
//                   >
//                     Add Service
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Upload Photos:</label>
//               <input
//                 type="file"
//                 multiple
//                 onChange={handlePhotoChange}
//                 className="form-control"
//               />
//               <div className="mt-3">
//                 {photos.map((photo, index) => (
//                   <div key={index} className="position-relative d-inline-block me-2 mb-2">
//                     <img src={photo} alt={`Uploaded ${index}`} className="img-thumbnail" style={{ width: '150px', height: '150px' }} />
//                     <button
//                       type="button"
//                       className="btn btn-danger position-absolute top-0 end-0 mt-1 me-1"
//                       onClick={() => handlePhotoDelete(photo)}
//                     >
//                       <FaTrash />
//                     </button>
//                   </div>
//                 ))}
//               </div>
//             </div>
//           </form>
//           <div className="mt-4">
//             <Link to="/photographerDashboard" className="btn btn-secondary">
//               Back to Dashboard
//             </Link>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default PhotographerProfile;























// aboveee useeffect
 // useEffect(() => {
  //   axios.get(`http://localhost:8080/photographer_profile/${photographerId}/profile-photo`)
  //     .then(response => {
  //       if (response.data) {
  //         setProfilePic(response.data);
  //       } else {
  //         setProfilePic(null);
  //       }
  //     })
  //     .catch(error => {
  //       console.error('Error fetching profile photo:', error);
  //     });

  //   axios.get('http://localhost:8080/Categories')
  //     .then(response => {
  //       setCategories(response.data);
  //     })
  //     .catch(error => {
  //       console.error('Error fetching categories:', error);
  //     });

      
  // }, [photographerId]);






// import React, { useState, useEffect } from 'react';
// import { FaTrash } from 'react-icons/fa';
// import axios from 'axios';
// import { jwtDecode } from 'jwt-decode'; // Make sure this import is correct

// const PhotographerProfile = () => {
//   const jwt = sessionStorage.getItem('token');
//   const decodedToken = jwtDecode(jwt);
//   const photographerId = decodedToken.id;
//   const photographerName = decodedToken.Name;
//   const photographerEmail = decodedToken.email;
//   const photographerGender = decodedToken.gender;
//   const photographerContactNo = decodedToken.contactno;

//   const [profilePic, setProfilePic] = useState(null);
//   const [name, setName] = useState(photographerName);
//   const [email, setEmail] = useState(photographerEmail);
//   const [gender, setGender] = useState(photographerGender);
//   const [contact, setContact] = useState(photographerContactNo);
//   const [bio, setBio] = useState('');
//   const [experience, setExperience] = useState('BEGINNER');
//   const [category, setCategory] = useState('');
//   const [categories, setCategories] = useState([]);
//   const [services, setServices] = useState('');
//   const [price, setPrice] = useState('');
//   const [photos, setPhotos] = useState([]);
//   const [editingExperience, setEditingExperience] = useState(false);
//   const [editingBio, setEditingBio] = useState(false);
//   const [editingServices, setEditingServices] = useState(false);
//   const [showCategories, setShowCategories] = useState(false);
//   const [selectedCategory, setSelectedCategory] = useState(null);

//   useEffect(() => {
//     // Fetch the profile photo when the component mounts
//     axios.get(`http://localhost:8080/photographer_profile/${photographerId}/profile-photo`)
//       .then(response => {
//         if (response.data) {
//           setProfilePic(response.data); // Assuming response.data contains the URL or path to the profile photo
//         } else {
//           setProfilePic(null); // Set to null if no photo is found
//         }
//       })
//       .catch(error => {
//         console.error('Error fetching profile photo:', error);
//       });
      
//     // Fetch the categories when the component mounts
//     axios.get('http://localhost:8080/Categories')
//       .then(response => {
//         setCategories(response.data);
//       })
//       .catch(error => {
//         console.error('Error fetching categories:', error);
//       });
//   }, [photographerId]);

//   const handleProfilePicChange = (e) => {
//     const file = e.target.files[0];
//     if (!file) return;

//     const formData = new FormData();
//     formData.append('file', file);

//     axios.post(`http://localhost:8080/photographer_profile/uploadProfilePic/${photographerId}`, formData)
//       .then((response) => {
//         setProfilePic(URL.createObjectURL(file));
//         alert('Profile picture updated successfully!');
//       })
//       .catch(error => {
//         console.error('Error uploading profile picture:', error);
//         alert('Failed to update profile picture.');
//       });
//   };

//   const handlePhotoChange = (e) => {
//     const files = Array.from(e.target.files);
//     const newPhotos = files.map(file => URL.createObjectURL(file));
//     setPhotos(prevPhotos => [...prevPhotos, ...newPhotos]);
//   };

//   const handlePhotoDelete = (photoUrl) => {
//     setPhotos(prevPhotos => prevPhotos.filter(photo => photo !== photoUrl));
//   };

//   const handleBioSave = () => {
//     axios.post(`http://localhost:8080/photographer_profile/updateBio/${photographerId}`, { bio })
//       .then(response => {
//         alert('Bio updated successfully!');
//         setEditingBio(false);
//       })
//       .catch(error => {
//         console.error('Error updating bio:', error);
//         alert('Failed to update bio.');
//       });
//   };

//   const handleExperienceSave = () => {
//     axios.post(
//       `http://localhost:8080/photographer_profile/updateExperienceLevel/${photographerId}`,
//       { experienceLevel: experience },
//       {
//         headers: {
//           'Content-Type': 'application/json'
//         }
//       }
//     )
//     .then(response => {
//       alert('Experience level updated successfully!');
//       setEditingExperience(false);
//     })
//     .catch(error => {
//       console.error('Error updating experience level:', error);
//       alert('Failed to update experience level.');
//     });
//   };

//   const handleCategorySelection = (categoryId) => {
//     axios.post('http://localhost:8080/photographer/addcategory', {
//       pid: photographerId,
//       cid: categoryId
//     })
//       .then(response => {
//         alert('Category added successfully!');
//         setShowCategories(false);       // Hide the category selection modal or menu
//         setSelectedCategory(null);      // Clear the selected category
//       })
//       .catch(error => {
//         console.error('Error adding category:', error);
//         alert('Failed to add category.');
//       });
//   };
  

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     alert('Profile saved successfully!');
//   };

//   return (
//     <div className="container my-4">
//       <h2 className="text-center mb-4">Photographer Dashboard</h2>
//       <div className="row">
//         <div className="col-md-4 text-center">
//           <img
//             src={profilePic || 'default-profile-pic.jpg'}
//             className="img-fluid rounded-circle"
//             onClick={() => document.getElementById('profilePicInput').click()}
//             alt="Profile"
//           />
//           <input
//             type="file"
//             accept="image/*"
//             id="profilePicInput"
//             onChange={handleProfilePicChange}
//             className="d-none"
//           />
//           <div className="mt-2">
//             <button onClick={() => document.getElementById('profilePicInput').click()} className="btn btn-secondary me-2">
//               Edit Photo
//             </button>
//             <button onClick={() => setProfilePic(null)} className="btn btn-danger">
//               Delete Photo
//             </button>
//           </div>
//         </div>
//         <div className="col-md-8">
//           <form onSubmit={handleSubmit}>
//             <div className="mb-3">
//               <label className="form-label">Name:</label>
//               <input
//                 type="text"
//                 value={name}
//                 onChange={(e) => setName(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Email:</label>
//               <input
//                 type="email"
//                 value={email}
//                 onChange={(e) => setEmail(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Gender:</label>
//               <input
//                 type="text"
//                 value={gender}
//                 onChange={(e) => setGender(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Contact No:</label>
//               <input
//                 type="text"
//                 value={contact}
//                 onChange={(e) => setContact(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Bio:</label>
//               {editingBio ? (
//                 <>
//                   <textarea
//                     value={bio}
//                     onChange={(e) => setBio(e.target.value)}
//                     className="form-control"
//                   ></textarea>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleBioSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{bio}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingBio(true)}
//                   >
//                     Edit Bio
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Experience Level:</label>
//               {editingExperience ? (
//                 <>
//                   <select
//                     value={experience}
//                     onChange={(e) => setExperience(e.target.value)}
//                     className="form-select"
//                   >
//                     <option value="BEGINNER">BEGINNER</option>
//                     <option value="INTERMEDIATE">INTERMEDIATE</option>
//                     <option value="PROFESSIONAL">PROFESSIONAL</option>
//                   </select>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleExperienceSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{experience}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingExperience(true)}
//                   >
//                     Edit Experience Level
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Services:</label>
//               {editingServices ? (
//                 <>
//                   <textarea
//                     value={services}
//                     onChange={(e) => setServices(e.target.value)}
//                     className="form-control"
//                   ></textarea>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={() => setEditingServices(false)}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{services}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingServices(true)}
//                   >
//                     Edit Services
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Price:</label>
//               <input
//                 type="text"
//                 value={price}
//                 onChange={(e) => setPrice(e.target.value)}
//                 className="form-control"
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Photos:</label>
//               <input
//                 type="file"
//                 multiple
//                 accept="image/*"
//                 onChange={handlePhotoChange}
//                 className="form-control"
//               />
//               <div className="mt-2">
//                 {photos.map((photo, index) => (
//                   <div key={index} className="position-relative d-inline-block me-2">
//                     <img src={photo} alt={`Photo ${index}`} className="img-thumbnail" width="100" />
//                     <button
//                       type="button"
//                       className="btn btn-danger position-absolute top-0 end-0"
//                       onClick={() => handlePhotoDelete(photo)}
//                     >
//                       <FaTrash />
//                     </button>
//                   </div>
//                 ))}
//               </div>
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Categories:</label>
//               {showCategories ? (
//                 <>
//                   <div className="form-group">
//                     {categories.map((cat) => (
//                       <div key={cat.id} className="form-check">
//                         <input
//                           className="form-check-input"
//                           type="radio"
//                           name="category"
//                           value={cat.id}
//                           onChange={() => setSelectedCategory(cat.id)}
//                         />
//                         <label className="form-check-label">{cat.categoryname}</label>
//                       </div>
//                     ))}
//                     <button
//                       type="button"
//                       className="btn btn-primary mt-2"
//                       onClick={() => {
//                         if (selectedCategory) {
//                           handleCategorySelection(selectedCategory);
//                         } else {
//                           alert('Please select a category.');
//                         }
//                       }}
//                     >
//                       Add Category
//                     </button>
//                     <button
//                       type="button"
//                       className="btn btn-secondary mt-2"
//                       onClick={() => setShowCategories(false)}
//                     >
//                       Close
//                     </button>
//                   </div>
//                 </>
//               ) : (
//                 <button
//                   type="button"
//                   className="btn btn-primary"
//                   onClick={() => setShowCategories(true)}
//                 >
//                   Want to add category?
//                 </button>
//               )}
//             </div>
//             <button type="submit" className="btn btn-primary">
//               Save Profile
//             </button>
//           </form>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default PhotographerProfile;

















// import React, { useState, useEffect } from 'react';
// import { FaTrash } from 'react-icons/fa';
// import axios from 'axios';
// import { jwtDecode } from 'jwt-decode'; // Make sure this import is correct

// const PhotographerProfile = () => {
//   const jwt = sessionStorage.getItem('token');
//   const decodedToken = jwtDecode(jwt);
//   const photographerId = decodedToken.id;
//   const photographerName = decodedToken.Name;
//   const photographerEmail = decodedToken.email;
//   const photographerGender = decodedToken.gender;
//   const photographerContactNo = decodedToken.contactno;

//   const [profilePic, setProfilePic] = useState(null);
//   const [name, setName] = useState(photographerName);
//   const [email, setEmail] = useState(photographerEmail);
//   const [gender, setGender] = useState(photographerGender);
//   const [contact, setContact] = useState(photographerContactNo);
//   const [bio, setBio] = useState('');
//   const [experience, setExperience] = useState('BEGINNER');
//   const [category, setCategory] = useState('');
//   const [services, setServices] = useState('');
//   const [price, setPrice] = useState('');
//   const [photos, setPhotos] = useState([]);
//   const [editingExperience, setEditingExperience] = useState(false);
//   const [editingBio, setEditingBio] = useState(false);
//   const [editingServices, setEditingServices] = useState(false);

//   useEffect(() => {
//     // Fetch the profile photo when the component mounts
//     axios.get(`http://localhost:8080/photographer_profile/${photographerId}/profile-photo`)
//       .then(response => {
//         if (response.data) {
//           setProfilePic(response.data); // Assuming response.data contains the URL or path to the profile photo
//         } else {
//           setProfilePic(null); // Set to null if no photo is found
//         }
//       })
//       .catch(error => {
//         console.error('Error fetching profile photo:', error);
//       });
//   }, [photographerId]); // Dependency array to re-run when photographerId changes

//   const handleProfilePicChange = (e) => {
//     const file = e.target.files[0];
//     if (!file) return;

//     const formData = new FormData();
//     formData.append('file', file);

//     axios.post(`http://localhost:8080/photographer_profile/uploadProfilePic/${photographerId}`, formData)
//       .then((response) => {
//         setProfilePic(URL.createObjectURL(file));
//         alert('Profile picture updated successfully!');
//       })
//       .catch(error => {
//         console.error('Error uploading profile picture:', error);
//         alert('Failed to update profile picture.');
//       });
//   };

//   const handlePhotoChange = (e) => {
//     const files = Array.from(e.target.files);
//     const newPhotos = files.map(file => URL.createObjectURL(file));
//     setPhotos(prevPhotos => [...prevPhotos, ...newPhotos]);
//   };

//   const handlePhotoDelete = (photoUrl) => {
//     setPhotos(prevPhotos => prevPhotos.filter(photo => photo !== photoUrl));
//   };

//   const handleBioSave = () => {
//     axios.post(`http://localhost:8080/photographer_profile/updateBio/${photographerId}`, { bio })
//       .then(response => {
//         alert('Bio updated successfully!');
//         setEditingBio(false);
//       })
//       .catch(error => {
//         console.error('Error updating bio:', error);
//         alert('Failed to update bio.');
//       });
//   };

//   const handleExperienceSave = () => {
//     axios.post(
//       `http://localhost:8080/photographer_profile/updateExperienceLevel/${photographerId}`,
//       { experienceLevel: experience },
//       {
//         headers: {
//           'Content-Type': 'application/json'
//         }
//       }
//     )
//     .then(response => {
//       alert('Experience level updated successfully!');
//       setEditingExperience(false);
//     })
//     .catch(error => {
//       console.error('Error updating experience level:', error);
//       alert('Failed to update experience level.');
//     });
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     alert('Profile saved successfully!');
//   };

//   return (
//     <div className="container my-4">
//       <h2 className="text-center mb-4">Photographer Dashboard</h2>
//       <div className="row">
//         <div className="col-md-4 text-center">
//           <img
//             src={profilePic || 'default-profile-pic.jpg'}
//             className="img-fluid rounded-circle"
//             onClick={() => document.getElementById('profilePicInput').click()}
//             alt="Profile"
//           />
//           <input
//             type="file"
//             accept="image/*"
//             id="profilePicInput"
//             onChange={handleProfilePicChange}
//             className="d-none"
//           />
//           <div className="mt-2">
//             <button onClick={() => document.getElementById('profilePicInput').click()} className="btn btn-secondary me-2">
//               Edit Photo
//             </button>
//             <button onClick={() => setProfilePic(null)} className="btn btn-danger">
//               Delete Photo
//             </button>
//           </div>
//         </div>
//         <div className="col-md-8">
//           <form onSubmit={handleSubmit}>
//             <div className="mb-3">
//               <label className="form-label">Name:</label>
//               <input
//                 type="text"
//                 value={name}
//                 onChange={(e) => setName(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Email:</label>
//               <input
//                 type="email"
//                 value={email}
//                 onChange={(e) => setEmail(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Gender:</label>
//               <input
//                 type="text"
//                 value={gender}
//                 onChange={(e) => setGender(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Contact No:</label>
//               <input
//                 type="text"
//                 value={contact}
//                 onChange={(e) => setContact(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Bio:</label>
//               {editingBio ? (
//                 <>
//                   <textarea
//                     value={bio}
//                     onChange={(e) => setBio(e.target.value)}
//                     className="form-control"
//                   ></textarea>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleBioSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{bio}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingBio(true)}
//                   >
//                     Edit Bio
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Experience Level:</label>
//               {editingExperience ? (
//                 <>
//                   <select
//                     value={experience}
//                     onChange={(e) => setExperience(e.target.value)}
//                     className="form-select"
//                   >
//                     <option value="BEGINNER">BEGINNER</option>
//                     <option value="INTERMEDIATE">INTERMEDIATE</option>
//                     <option value="PROFESSIONAL">PROFESSIONAL</option>
//                   </select>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleExperienceSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{experience}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingExperience(true)}
//                   >
//                     Edit Experience
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Category:</label>
//               <div className="d-flex flex-wrap gap-2">
//                 {['Nature', 'Wedding', 'Travel', 'Food', 'Other'].map((cat) => (
//                   <div key={cat} className="form-check">
//                     <input
//                       type="radio"
//                       id={cat}
//                       name="category"
//                       value={cat}
//                       checked={category === cat}
//                       onChange={(e) => setCategory(e.target.value)}
//                       className="form-check-input"
//                     />
//                     <label htmlFor={cat} className="form-check-label">
//                       {cat}
//                     </label>
//                   </div>
//                 ))}
//               </div>
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Services Offered:</label>
//               {editingServices ? (
//                 <>
//                   <textarea
//                     value={services}
//                     onChange={(e) => setServices(e.target.value)}
//                     className="form-control"
//                   ></textarea>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={() => setEditingServices(false)}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{services}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingServices(true)}
//                   >
//                     Edit Services
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Price:</label>
//               <input
//                 type="text"
//                 value={price}
//                 onChange={(e) => setPrice(e.target.value)}
//                 className="form-control"
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Upload Photos:</label>
//               <input
//                 type="file"
//                 multiple
//                 accept="image/*"
//                 onChange={handlePhotoChange}
//                 className="form-control"
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Uploaded Photos:</label>
//               <div className="d-flex flex-wrap gap-2">
//                 {photos.map((photoUrl, index) => (
//                   <div key={index} className="position-relative">
//                     <img src={photoUrl} alt={`Photo ${index + 1}`} className="img-thumbnail" />
//                     <FaTrash
//                       className="position-absolute top-0 end-0 mt-2 me-2 text-danger"
//                       style={{ cursor: 'pointer' }}
//                       onClick={() => handlePhotoDelete(photoUrl)}
//                     />
//                   </div>
//                 ))}
//               </div>
//             </div>
//             <button type="submit" className="btn btn-primary">Save Profile</button>
//           </form>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default PhotographerProfile;



















// import React, { useState } from 'react';
// import { FaTrash } from 'react-icons/fa';
// import axios from 'axios';
// import { jwtDecode } from 'jwt-decode'; // Updated import statement

// const PhotographerProfile = () => {
//   const jwt = sessionStorage.getItem('token');
//   const decodedToken = jwtDecode(jwt);
//   const photographerId = decodedToken.id;
//   const photographerName = decodedToken.Name;
//   const photographerEmail = decodedToken.email;
//   const photographerGender = decodedToken.gender;
//   const photographerContactNo = decodedToken.contactno;

//   const [profilePic, setProfilePic] = useState(null);
//   const [name, setName] = useState(photographerName);
//   const [email, setEmail] = useState(photographerEmail);
//   const [gender, setGender] = useState(photographerGender);
//   const [contact, setContact] = useState(photographerContactNo);
//   const [bio, setBio] = useState('');
//   const [experience, setExperience] = useState('BEGINNER');
//   const [category, setCategory] = useState('');
//   const [services, setServices] = useState('');
//   const [price, setPrice] = useState('');
//   const [photos, setPhotos] = useState([]);
//   const [editingExperience, setEditingExperience] = useState(false);
//   const [editingBio, setEditingBio] = useState(false);
//   const [editingServices, setEditingServices] = useState(false);

//   const handleProfilePicChange = (e) => {
//     const file = e.target.files[0];
//     if (!file) return;

//     const formData = new FormData();
//     formData.append('file', file);

//     axios.post(`http://localhost:8080/photographer_profile/uploadProfilePic/${photographerId}`, formData)
//       .then((response) => {
//         setProfilePic(URL.createObjectURL(file));
//         alert('Profile picture updated successfully!');
//       })
//       .catch(error => {
//         console.error('Error uploading profile picture:', error);
//         alert('Failed to update profile picture.');
//       });
//   };

//   const handlePhotoChange = (e) => {
//     const files = Array.from(e.target.files);
//     const newPhotos = files.map(file => URL.createObjectURL(file));
//     setPhotos(prevPhotos => [...prevPhotos, ...newPhotos]);
//   };

//   const handlePhotoDelete = (photoUrl) => {
//     setPhotos(prevPhotos => prevPhotos.filter(photo => photo !== photoUrl));
//   };

//   const handleBioSave = () => {
//     axios.post(`http://localhost:8080/photographer_profile/updateBio/${photographerId}`, { bio })
//       .then(response => {
//         alert('Bio updated successfully!');
//         setEditingBio(false);
//       })
//       .catch(error => {
//         console.error('Error updating bio:', error);
//         alert('Failed to update bio.');
//       });
//   };

//   const handleExperienceSave = () => {
//     axios.post(
//       `http://localhost:8080/photographer_profile/updateExperienceLevel/${photographerId}`,
//       { experienceLevel: experience },
//       {
//         headers: {
//           'Content-Type': 'application/json'
//         }
//       }
//     )
//     .then(response => {
//       alert('Experience level updated successfully!');
//       setEditingExperience(false);
//     })
//     .catch(error => {
//       console.error('Error updating experience level:', error);
//       alert('Failed to update experience level.');
//     });
//   };
  

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     alert('Profile saved successfully!');
//   };

//   return (
//     <div className="container my-4">
//       <h2 className="text-center mb-4">Photographer Dashboard</h2>
//       <div className="row">
//         <div className="col-md-4 text-center">
//           <img
//             src={profilePic || 'default-profile-pic.jpg'}
//             className="img-fluid rounded-circle"
//             onClick={() => document.getElementById('profilePicInput').click()}
//             alt="Profile"
//           />
//           <input
//             type="file"
//             accept="image/*"
//             id="profilePicInput"
//             onChange={handleProfilePicChange}
//             className="d-none"
//           />
//           <div className="mt-2">
//             <button onClick={() => document.getElementById('profilePicInput').click()} className="btn btn-secondary me-2">
//               Edit Photo
//             </button>
//             <button onClick={() => setProfilePic(null)} className="btn btn-danger">
//               Delete Photo
//             </button>
//           </div>
//         </div>
//         <div className="col-md-8">
//           <form onSubmit={handleSubmit}>
//             <div className="mb-3">
//               <label className="form-label">Name:</label>
//               <input
//                 type="text"
//                 value={name}
//                 onChange={(e) => setName(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Email:</label>
//               <input
//                 type="email"
//                 value={email}
//                 onChange={(e) => setEmail(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Gender:</label>
//               <input
//                 type="text"
//                 value={gender}
//                 onChange={(e) => setGender(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Contact No:</label>
//               <input
//                 type="text"
//                 value={contact}
//                 onChange={(e) => setContact(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Bio:</label>
//               {editingBio ? (
//                 <>
//                   <textarea
//                     value={bio}
//                     onChange={(e) => setBio(e.target.value)}
//                     className="form-control"
//                   ></textarea>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleBioSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{bio}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingBio(true)}
//                   >
//                     Edit Bio
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Experience Level:</label>
//               {editingExperience ? (
//                 <>
//                   <select
//                     value={experience}
//                     onChange={(e) => setExperience(e.target.value)}
//                     className="form-select"
//                   >
//                     <option value="BEGINNER">BEGINNER</option>
//                     <option value="INTERMEDIATE">INTERMEDIATE</option>
//                     <option value="PROFESSIONAL">PROFESSIONAL</option>
//                   </select>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={handleExperienceSave}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{experience}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingExperience(true)}
//                   >
//                     Edit Experience
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Category:</label>
//               <div className="d-flex flex-wrap gap-2">
//                 {['Nature', 'Wedding', 'Travel', 'Food', 'Other'].map((cat) => (
//                   <div key={cat} className="form-check">
//                     <input
//                       type="radio"
//                       id={cat}
//                       name="category"
//                       value={cat}
//                       checked={category === cat}
//                       onChange={(e) => setCategory(e.target.value)}
//                       className="form-check-input"
//                     />
//                     <label htmlFor={cat} className="form-check-label">
//                       {cat}
//                     </label>
//                   </div>
//                 ))}
//                 {category === 'Other' && (
//                   <input
//                     type="text"
//                     placeholder="Type your category"
//                     value={category}
//                     onChange={(e) => setCategory(e.target.value)}
//                     className="form-control mt-2"
//                   />
//                 )}
//               </div>
//             </div>
//             {category && (
//               <>
//                 <div className="mb-3">
//                   <label className="form-label">Services:</label>
//                   {editingServices ? (
//                     <>
//                       <textarea
//                         value={services}
//                         onChange={(e) => setServices(e.target.value)}
//                         className="form-control"
//                       ></textarea>
//                       <button
//                         type="button"
//                         className="btn btn-primary mt-2"
//                         onClick={() => setEditingServices(false)}
//                       >
//                         Save
//                       </button>
//                     </>
//                   ) : (
//                     <>
//                       <p>{services}</p>
//                       <button
//                         type="button"
//                         className="btn btn-secondary mt-2"
//                         onClick={() => setEditingServices(true)}
//                       >
//                         Edit Services
//                       </button>
//                     </>
//                   )}
//                 </div>
//                 <div className="mb-3">
//                   <label className="form-label">Price:</label>
//                   <input
//                     type="number"
//                     value={price}
//                     onChange={(e) => setPrice(e.target.value)}
//                     className="form-control"
//                   />
//                 </div>
//                 <div className="mb-3">
//                   <label className="form-label">Photos:</label>
//                   <input
//                     type="file"
//                     accept="image/*"
//                     multiple
//                     onChange={handlePhotoChange}
//                     className="form-control"
//                   />
//                   <div className="mt-3">
//                     {photos.map((photoUrl, index) => (
//                       <div key={index} className="position-relative d-inline-block me-2">
//                         <img src={photoUrl} alt={`Photo ${index + 1}`} className="img-thumbnail" />
//                         <button
//                           type="button"
//                           className="btn btn-danger position-absolute top-0 end-0"
//                           onClick={() => handlePhotoDelete(photoUrl)}
//                         >
//                           <FaTrash />
//                         </button>
//                       </div>
//                     ))}
//                   </div>
//                 </div>
//               </>
//             )}
//             <button type="submit" className="btn btn-primary mt-3">
//               Save Profile
//             </button>
//           </form>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default PhotographerProfile;























// doing good here
// import React, { useState } from 'react';
// import { FaTrash } from 'react-icons/fa';
// import axios from 'axios';
// import { jwtDecode } from 'jwt-decode';


// const PhotographerProfile = () => {



//   const jwt =  sessionStorage.getItem('token');
//   const decodedToken = jwtDecode(jwt);
//   const photographerId = decodedToken.id;
//   const photographerName = decodedToken.Name;
//   const photographerEmail = decodedToken.email;
//   const photographerGender = decodedToken.gender;
//   const photographerContactNo = decodedToken.contactno;


//   const [profilePic, setProfilePic] = useState(null);
//   const [name, setName] = useState(photographerName);
//   const [email, setEmail] = useState(photographerEmail);
//   const [gender, setGender] = useState(photographerGender);
//   const [contact, setContact] = useState(photographerContactNo);
//   const [bio, setBio] = useState('');
//   const [experience, setExperience] = useState('BEGINNER');
//   const [category, setCategory] = useState('');
//   const [services, setServices] = useState('');
//   const [price, setPrice] = useState('');
//   const [photos, setPhotos] = useState([]);
//   const [editingExperience, setEditingExperience] = useState(false);
//   const [editingServices, setEditingServices] = useState(false);



//   const handleProfilePicChange = (e) => {
//     const file = e.target.files[0];
//     if (!file) return;
  
//     const formData = new FormData();
//     formData.append('file', file);
  
//     axios.post(`http://localhost:8080/photographer_profile/uploadProfilePic/${photographerId}`, formData)
//       .then((response) => {
//         // Assuming the server responds with the updated profile picture URL
//         setProfilePic(URL.createObjectURL(file));
//         alert('Profile picture updated successfully!');
//       })
//       .catch(error => {
//         console.error('Error uploading profile picture:', error);
//         alert('Failed to update profile picture.');
//       });
//   };
  

//   const handlePhotoChange = (e) => {
//     const files = Array.from(e.target.files);
//     const newPhotos = files.map(file => URL.createObjectURL(file));
//     setPhotos(prevPhotos => [...prevPhotos, ...newPhotos]);
//   };

//   const handlePhotoDelete = (photoUrl) => {
//     setPhotos(prevPhotos => prevPhotos.filter(photo => photo !== photoUrl));
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     alert('Profile saved successfully!');
//   };

//   return (
//     <div className="container my-4">
//       <h2 className="text-center mb-4">Photographer Dashboard</h2>
//       <div className="row">
//         <div className="col-md-4 text-center">
//           <img
//             src={profilePic || 'default-profile-pic.jpg'}
//             className="img-fluid rounded-circle"
//             onClick={() => document.getElementById('profilePicInput').click()}
//             alt="Profile"
//           />
//           <input
//             type="file"
//             accept="image/*"
//             id="profilePicInput"
//             onChange={handleProfilePicChange}
//             className="d-none"
//           />
//           <div className="mt-2">
//             <button onClick={() => document.getElementById('profilePicInput').click()} className="btn btn-secondary me-2">
//               Edit Photo
//             </button>
//             <button onClick={() => setProfilePic(null)} className="btn btn-danger">
//               Delete Photo
//             </button>
//           </div>
//         </div>
//         <div className="col-md-8">
//           <form onSubmit={handleSubmit}>
//             <div className="mb-3">
//               <label className="form-label">Name:</label>
//               <input
//                 type="text"
//                 value={name}
//                 onChange={(e) => setName(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Email:</label>
//               <input
//                 type="email"
//                 value={email}
//                 onChange={(e) => setEmail(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Gender:</label>
//               <input
//                 type="text"
//                 value={gender}
//                 onChange={(e) => setGender(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Contact No:</label>
//               <input
//                 type="text"
//                 value={contact}
//                 onChange={(e) => setContact(e.target.value)}
//                 className="form-control" disabled
//               />
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Bio:</label>
//               <textarea
//                 value={bio}
//                 onChange={(e) => setBio(e.target.value)}
//                 className="form-control"
//               ></textarea>
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Experience Level:</label>
//               {editingExperience ? (
//                 <>
//                   <select
//                     value={experience}
//                     onChange={(e) => setExperience(e.target.value)}
//                     className="form-select"
//                   >
//                     <option value="BEGINNER">BEGINNER</option>
//                     <option value="INTERMEDIATE">INTERMEDIATE</option>
//                     <option value="PROFESSIONAL">PROFESSIONAL</option>
//                   </select>
//                   <button
//                     type="button"
//                     className="btn btn-primary mt-2"
//                     onClick={() => setEditingExperience(false)}
//                   >
//                     Save
//                   </button>
//                 </>
//               ) : (
//                 <>
//                   <p>{experience}</p>
//                   <button
//                     type="button"
//                     className="btn btn-secondary mt-2"
//                     onClick={() => setEditingExperience(true)}
//                   >
//                     Edit Experience
//                   </button>
//                 </>
//               )}
//             </div>
//             <div className="mb-3">
//               <label className="form-label">Category:</label>
//               <div className="d-flex flex-wrap gap-2">
//                 {['Nature', 'Wedding', 'Travel', 'Food', 'Other'].map((cat) => (
//                   <div key={cat} className="form-check">
//                     <input
//                       type="radio"
//                       id={cat}
//                       name="category"
//                       value={cat}
//                       checked={category === cat}
//                       onChange={(e) => setCategory(e.target.value)}
//                       className="form-check-input"
//                     />
//                     <label htmlFor={cat} className="form-check-label">
//                       {cat}
//                     </label>
//                   </div>
//                 ))}
//                 {category === 'Other' && (
//                   <input
//                     type="text"
//                     placeholder="Type your category"
//                     value={category}
//                     onChange={(e) => setCategory(e.target.value)}
//                     className="form-control mt-2"
//                   />
//                 )}
//               </div>
//             </div>
//             {category && (
//               <>
//                 <div className="mb-3">
//                   <label className="form-label">Services:</label>
//                   {editingServices ? (
//                     <>
//                       <textarea
//                         value={services}
//                         onChange={(e) => setServices(e.target.value)}
//                         className="form-control"
//                       ></textarea>
//                       <button
//                         type="button"
//                         className="btn btn-primary mt-2"
//                         onClick={() => setEditingServices(false)}
//                       >
//                         Save
//                       </button>
//                     </>
//                   ) : (
//                     <>
//                       <p>{services}</p>
//                       <button
//                         type="button"
//                         className="btn btn-secondary mt-2"
//                         onClick={() => setEditingServices(true)}
//                       >
//                         Edit Services
//                       </button>
//                     </>
//                   )}
//                 </div>
//                 <div className="mb-3">
//                   <label className="form-label">Price:</label>
//                   <input
//                     type="text"
//                     value={price}
//                     onChange={(e) => setPrice(e.target.value)}
//                     className="form-control"
//                   />
//                 </div>
//               </>
//             )}
//             <div className="mb-3">
//               <label className="form-label">Upload Photos:</label>
//               <button
//                 type="button"
//                 onClick={() => document.getElementById('photosInput').click()}
//                 className="btn btn-secondary"
//               >
//                 Choose Photos
//               </button>
//               <input
//                 type="file"
//                 accept="image/*"
//                 multiple
//                 id="photosInput"
//                 onChange={handlePhotoChange}
//                 className="d-none"
//               />
//               <div className="mt-3 d-flex flex-wrap gap-2">
//                 {photos.map((photo, index) => (
//                   <div key={index} className="position-relative">
//                     <img
//                       src={photo}
//                       alt={`Photo ${index + 1}`}
//                       className="img-fluid"
//                       style={{ width: '100px', height: '100px' }}
//                     />
//                     <button
//                       type="button"
//                       className="btn btn-danger position-absolute top-0 end-0"
//                       onClick={() => handlePhotoDelete(photo)}
//                     >
//                       <FaTrash />
//                     </button>
//                   </div>
//                 ))}
//               </div>
//             </div>
//             <button type="submit" className="btn btn-primary">Save Profile</button>
//           </form>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default PhotographerProfile;

