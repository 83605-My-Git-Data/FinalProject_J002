import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { signUp } from '../services/user-service'

function Register() {
  const [Firstname, setFirstName] = useState('')
  const [Lastname, setLastName] = useState('')
  const [Email, setEmail] = useState('')
  const [Phoneno, setPhoneno] = useState('')
  const [Password, setPassword] = useState('')
  const [Gender, setGender] = useState('')
  const [Role, setRole] = useState('')

  const navigate = useNavigate()

  const onRegister = (role) => {
    if (Firstname.length === 0) {
      toast.error('Please enter first name')
    } else if (Lastname.length === 0) {
      toast.error('Please enter last name')
    } else if (Email.length === 0) {
      toast.error('Please enter email')
    } else if (Phoneno.length === 0) {
      toast.error('Please enter phone number')
    } else if (Password.length === 0) {
      toast.error('Please enter password')
    } else if (Gender.length === 0) {
      toast.error('Please select gender')
    } else if (Role.length === 0) {
      toast.error('Please select role')
    } else {
      toast.success(`Successfully registered as a new ${role}`)
      if (role === 'user') {
        navigate('/login')
      } else if (role === 'photographer') {
        navigate('/login')
      }
    }
    signUp().then((Response)=> {
      console.log(Response)
      console.log("success log");
    }).catch((error)=>{
      console.log(error)
      console.log("Error Log")

    });
  }

  return (
    <div>
      <h2 className='page-header'>Register</h2>
      <div className='row'>
        <div className='col'></div>
        <div className='col'>
          <div className='form'>
            <div className='mb-3'>
              <label htmlFor=''>First Name</label>
              <input
                onChange={(e) => setFirstName(e.target.value)}
                type='text'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Last Name</label>
              <input
                onChange={(e) => setLastName(e.target.value)}
                type='text'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Email</label>
              <input
                onChange={(e) => setEmail(e.target.value)}
                type='email'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Phone No</label>
              <input
                onChange={(e) => setPhoneno(e.target.value)}
                type='tel'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Password</label>
              <input
                onChange={(e) => setPassword(e.target.value)}
                type='password'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Gender</label>
              <select
                onChange={(e) => setGender(e.target.value)}
                className='form-control'
              >
                <option value=''>Select Gender</option>
                <option value='male'>Male</option>
                <option value='female'>Female</option>
                <option value='other'>Other</option>
              </select>
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Role</label>
              <select
                onChange={(e) => setRole(e.target.value)}
                className='form-control'
              >
                <option value=''>Select Role</option>
                <option value='user'>User</option>
                <option value='photographer'>Photographer</option>
              </select>
            </div>
            <div className='mb-3'>
              <div>
                Already have an account? <Link to='/login'>Login here</Link>
              </div>
              <button onClick={() => onRegister('user')} className='btn btn-success mt-2'>
                Sign up as User
              </button>
              <button onClick={() => onRegister('photographer')} className='btn btn-primary mt-2 ms-2'>
                Sign up as Photographer
              </button>
            </div>
          </div>
        </div>
        <div className='col'></div>
      </div>
    </div>
  )
}

export default Register
