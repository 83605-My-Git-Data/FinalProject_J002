import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { register } from '../services/admin';
import './Register.css'; // Import the CSS file

function Register() {
  const [Firstname, setFirstName] = useState('');
  const [Lastname, setLastName] = useState('');
  const [Email, setEmail] = useState('');
  const [Phoneno, setPhoneno] = useState('');
  const [Password, setPassword] = useState('');
  const [Gender, setGender] = useState('');

  const navigate = useNavigate();

  const onRegister = async (role) => {
    if (!Firstname || !Lastname || !Email || !Phoneno || !Password || !Gender) {
      toast.error('Please fill in all fields');
      return;
    }

    try {
      const result = await register(Firstname, Lastname, Email, Phoneno, Password, Gender, role);
      if (result['message'] === 'registered successfully') {
        toast.success('Successfully registered a new user');
        navigate('/login');
      } else {
        toast.error(result['error']);
      }
    } catch (error) {
      console.error("Error during registration:", error);
      toast.error('An error occurred during registration.');
    }
  };

  return (
    <div className="register-container">
      <div className="background-container"></div>
      <div className="form-container">
        <h2 className='page-header'>Register</h2>
        <div className='form'>
          {/* Form fields for registration */}
          <div className='mb-3'>
            <label htmlFor='firstname'>First Name</label>
            <input
              id="firstname"
              onChange={(e) => setFirstName(e.target.value)}
              type='text'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <label htmlFor='lastname'>Last Name</label>
            <input
              id="lastname"
              onChange={(e) => setLastName(e.target.value)}
              type='text'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <label htmlFor='email'>Email</label>
            <input
              id="email"
              onChange={(e) => setEmail(e.target.value)}
              type='email'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <label htmlFor='phoneno'>Phone No</label>
            <input
              id="phoneno"
              onChange={(e) => setPhoneno(e.target.value)}
              type='tel'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <label htmlFor='password'>Password</label>
            <input
              id="password"
              onChange={(e) => setPassword(e.target.value)}
              type='password'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <label htmlFor='gender'>Gender</label>
            <select
              id="gender"
              onChange={(e) => setGender(e.target.value)}
              className='form-control'
            >
              <option value=''>Select Gender</option>
              <option value='MALE'>Male</option>
              <option value='FEMALE'>Female</option>
              <option value='OTHER'>Other</option>
            </select>
          </div>
          <div className='mb-3'>
            <div>
              Already have an account? <Link to='/login'>Login here</Link>
            </div>
            <button onClick={() => onRegister('ROLE_USER')} className='button'>
            
              Sign up as User
            </button>
            <button onClick={() => onRegister('ROLE_PHOTOGRAPHER')} className='button'>
              Sign up as Photographer
            </button>


          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
