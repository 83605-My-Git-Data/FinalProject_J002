import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

const PhotographerCategories = () => {
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [files, setFiles] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const token = sessionStorage.getItem('token');
                const decodedToken = jwtDecode(token);
                const photographerId = decodedToken.id;

                const response = await axios.get(`http://localhost:8080/photographer/categories/${photographerId}`);
                setCategories(response.data);
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };

        fetchCategories();
    }, []);

    const handleCategoryClick = (categoryId) => {
        setSelectedCategory(categoryId);
    };

    const handleFileChange = (event) => {
        setFiles(event.target.files);
    };

    const handleSubmit = async () => {
        if (!selectedCategory) return;

        try {
            const token = sessionStorage.getItem('token');
            const decodedToken = jwtDecode(token);
            const photographerId = decodedToken.id;

            const formData = new FormData();
            for (let i = 0; i < files.length; i++) {
                formData.append('files', files[i]);
            }

            await axios.post(`http://localhost:8080/images/${photographerId}/${selectedCategory}`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            alert('Photos uploaded successfully');
        } catch (error) {
            console.error('Error uploading photos:', error);
        }
    };

    return (
        <div>
            <h1>Photographer Categories</h1>
            {categories.map((category) => (
                <div key={category.id}>
                    <button onClick={() => handleCategoryClick(category.id)}>
                        {category.categoryname}
                    </button>
                </div>
            ))}

            {selectedCategory && (
                <div>
                    <h2>Add Photos to Category {selectedCategory}</h2>
                    <input type="file" multiple onChange={handleFileChange} />
                    <button onClick={handleSubmit}>Submit Photos</button>
                </div>
            )}
        </div>
    );
};

export default PhotographerCategories;
