import { myAxios } from "./helper";

export const signUp=(user)=>
{
    return myAxios
    .post('/signup', user)
    .then((Response)=> Response.json());
}