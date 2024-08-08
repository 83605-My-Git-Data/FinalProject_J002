import React from 'react';
import { Navbar as BootstrapNavbar, Nav, NavDropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { FiUser } from 'react-icons/fi';
import './Navbar.css'; 

const CustomNavbar = ({ categories }) => {
  console.log('Categories in Navbar:', categories);
  return (
    <BootstrapNavbar bg="dark" variant="dark" expand="lg" fixed="top" className="custom-navbar">
      <BootstrapNavbar.Brand as={Link} to="/">
        <img src="/favicon.ico" alt="ClickGenius Logo" className="d-inline-block align-top logo" />
        <span className="brand-name">ClickGenius</span>
      </BootstrapNavbar.Brand>
      <BootstrapNavbar.Toggle aria-controls="basic-navbar-nav" />
      <BootstrapNavbar.Collapse id="basic-navbar-nav">
      <Nav className="mr-auto">
          <Nav.Link as={Link} to="/booking-confirmation">My Bookings</Nav.Link>
          <NavDropdown title="Category">
            {categories.map((cat) => (
              <NavDropdown.Item key={cat.id} as={Link} to={`/category/${cat.categoryname}`}>
                {cat.categoryname}
              </NavDropdown.Item>
            ))}
          </NavDropdown>
        </Nav>
        <Nav className="ml-auto">
          <NavDropdown title={<FiUser />} id="profile-nav-dropdown" alignRight>
            <NavDropdown.Item as={Link} to="/settings">Settings</NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/edit-profile">Edit</NavDropdown.Item>
          </NavDropdown>
        </Nav>
      </BootstrapNavbar.Collapse>
    </BootstrapNavbar>
  );
};

export default CustomNavbar;
