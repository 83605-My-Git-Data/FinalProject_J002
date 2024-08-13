import axios from "axios"
import { config } from './config'



export async function register(firstName,lastName,email,phoneNo,password,gender,role){


    try{
        const body = {firstName,lastName,email,phoneNo,password,gender,role}
        const response =  await axios.post(`${config.serverUrl}/signup`,body)


        return response.data;
    


    }
    catch (ex) {
        console.log(`exception: `, ex)
      }
    
      return null
    
}


export async function login(email, password) {
    const body = { email, password }
    try {
      const response = await axios.post(`${config.serverUrl}/login`, body)
      return response.data
    } catch (ex) {
      console.log(`exception: `, ex)
    }
  
    return null
   
  }

  export async function fetchCategories() {
    try {

      const response = await axios.get(`${config.serverUrl}/Categories`);
      console.log(response.data);
      return response.data; 
    } catch (error) {
      console.error('Error fetching categories:', error);
      return [];
    }
  }

  export async function fetchPhotographersByCategoryId(categoryId) {
    try {
      const response = await axios.get(`${config.serverUrl}/Categories/photographers?id=${categoryId}`);
      console.log(response.data)
     
      return response.data;
    } catch (error) {
      console.error('Error fetching photographers:', error);
      return [];
    }

  }
  export async function fetchPhotographerImages(photographerId, categoryId) {
    try {
      const response = await axios.get(`${config.serverUrl}/images/${photographerId}/${categoryId}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching photographer images:', error);
      return [];
    }
  }
  


  
//   export async function fetchCategories() {
//     try {
//         const token = sessionStorage.getItem('token');
//         console.log("Token from sessionStorage:", token); // Ensure this is not null or undefined

//         const response = await axios.get(`${config.serverUrl}/Categories`, {
//             headers: {
//                 Authorization: `Bearer ${token}`
//             }
//         });
//         console.log(response.data);
//         return response.data; // This should be an array of categories
//     } catch (error) {
//         console.error('Error fetching categories:', error);
//         return []; // Return an empty array in case of error
//     }
// }

