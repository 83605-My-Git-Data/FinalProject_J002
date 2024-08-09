  import React, { useState } from 'react';
  import { Link, useNavigate } from 'react-router-dom';
  import { toast } from 'react-toastify';
  import { login } from '../services/admin';
  import './Login.css'; // Import the CSS file
  import { jwtDecode } from 'jwt-decode'; // Corrected import statement

  function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isEmailEmpty, setEmailEmpty] = useState(false);
    const [isPasswordEmpty, setPasswordEmpty] = useState(false);

    const navigate = useNavigate();

    const onLogin = async () => {
      if (email.length === 0) {
        toast.error('Please enter email');
      } else if (password.length === 0) {
        toast.error('Please enter password');
      } else {
        try {
          const result = await login(email, password);

          if (result.jwt && result.email) {
            sessionStorage.setItem('name', result.email);
            sessionStorage.setItem('token', result.jwt);
            const decodedToken = jwtDecode(result.jwt);

            if (decodedToken.Role === "ROLE_PHOTOGRAPHER") {
              navigate('/home');
            } else if (decodedToken.Role === "ROLE_USER") {
              // Route to user page
              navigate('/home');
            }
          } else {
            toast.error('Login failed. Please try again.');
          }
        } catch (error) {
          console.error("Error during login:", error);
          toast.error('An error occurred during login.');
        }
      }
    };

    return (
      <div className="login-container">
        <div className="background-container"></div>
        <div className="form-container">
          <h2 className='page-header'>Login</h2>
          <div className='form'>
            <div className='mb-3'>
              <label htmlFor='email'>Email</label>
              <input
                id="email"
                onChange={(e) => {
                  if (e.target.value.length === 0) {
                    setEmailEmpty(true);
                  } else {
                    setEmailEmpty(false);
                  }
                  setEmail(e.target.value);
                }}
                type='email'
                className='form-control'
              />
              {isEmailEmpty && (
                <p style={{ color: 'red' }}>Email is mandatory</p>
              )}
            </div>
            <div className='mb-3'>
              <label htmlFor='password'>Password</label>
              <input
                id="password"
                onChange={(e) => {
                  if (e.target.value.length === 0) {
                    setPasswordEmpty(true);
                  } else {
                    setPasswordEmpty(false);
                  }
                  setPassword(e.target.value);
                }}
                type='password'
                className='form-control'
              />
              {isPasswordEmpty && (
                <p style={{ color: 'red' }}>Password is mandatory</p>
              )}
            </div>
            <div className='mb-3'>
              <div>
                Don't have an account? <Link to='/register'>Register here</Link>
              </div>
              <button onClick={onLogin} className='button button1'>
                Login
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }

  export default Login;
