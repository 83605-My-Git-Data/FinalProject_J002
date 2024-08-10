import React from 'react';
import './New.css'; // Import custom CSS
import 'bootstrap/dist/css/bootstrap.min.css';

const photographers = [
    {
        name: 'John Doe',
        profilePhoto: 'profile1.jpg',
        price: '$150/hour',
        description: 'Some quick example text to build on the card title and make up the bulk of the card\'s content.',
        link: '#'
    },
    {
        name: 'Jane Smith',
        profilePhoto: 'profile2.jpg',
        price: '$200/hour',
        description: 'Another quick example text to provide more details about this photographer.',
        link: '#'
    },
    {
        name: 'Jane Smith',
        profilePhoto: 'profile2.jpg',
        price: '$200/hour',
        description: 'Another quick example text to provide more details about this photographer.',
        link: '#'
    },
    {
        name: 'Jane Smith',
        profilePhoto: 'profile2.jpg',
        price: '$200/hour',
        description: 'Another quick example text to provide more details about this photographer.',
        link: '#'
    },
    {
        name: 'Jane Smith',
        profilePhoto: 'profile2.jpg',
        price: '$200/hour',
        description: 'Another quick example text to provide more details about this photographer.',
        link: '#'
    },
    {
        name: 'Jane Smith',
        profilePhoto: 'profile2.jpg',
        price: '$200/hour',
        description: 'Another quick example text to provide more details about this photographer.',
        link: '#'
    },
    {
        name: 'Jane Smith',
        profilePhoto: 'profile2.jpg',
        price: '$200/hour',
        description: 'Another quick example text to provide more details about this photographer.',
        link: '#'
    }
    // Add more photographers as needed
];

const PhotographersList = () => {
    return (
        <div className="container mt-4">
            <div className="row">
                {photographers.map((photographer, index) => (
                    <div key={index} className="col-md-4 mb-4">
                        <div className="card transparent-card" style={{ width: '18rem' }}>
                            <img 
                                className="card-img-top" 
                                src={photographer.profilePhoto} 
                                alt={photographer.name} 
                            />
                            <div className="card-body">
                                <h5 className="card-title">{photographer.name}</h5>
                                <p className="card-text">{photographer.description}</p>
                                <p className="card-text"><strong>Price: {photographer.price}</strong></p>
                                <a href={photographer.link} className="btn btn-primary">View Profile</a>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default PhotographersList;
