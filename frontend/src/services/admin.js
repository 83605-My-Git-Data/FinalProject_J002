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