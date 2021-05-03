import React, { useState } from 'react'
import imgPlaceholder from "../../assets/placeholder.jpeg"
import ImgUpload from './ImgUpload';
import UserApi from "../../api/UserApi"

export default function UserProfile({userData, getUserData}) {
    const [userForm, setUserForm]= useState({
        name: userData.name,
        email: userData.email,
        bio:userData.bio,
        imageUrl: userData.imageUrl,
    });
console.log("userDatafromUser", userData)
    const change= ({target:{name, value}}) =>{
        setUserForm({...userForm, 
        [name]: value,
        });
    }

    const updateUser= async (userData)=> {
        await UserApi.updateUser(userData)
        .then(response=>setUserForm(response.data))
        .catch(err => console.error(err))
        getUserData().then(responce => setUserForm(responce))
    }
    const handleSubmit = e => {
        e.preventDefault();
        updateUser(userForm);
    }
    console.log(userForm);

    
    return (
        <div className="profile">
            <form onSubmit={handleSubmit}>
                <div>
                <img className="img" src={userForm.imageUrl} alt=""/> <br/>
                <ImgUpload className="profile" uploadImg={change}/>
                <label htmlFor="name-input">Name:</label>
                <input type="text" id="name-input" name="name"
                value={userForm.name} onChange={change}/>
                </div>
                <div>
                <label htmlFor="email-input">Email:</label>
                <input type="text" id="email-input" name="email"
                value={userForm.email} onChange={change}/>
                </div>
                <div>
                <label htmlFor="bio-input">Bio:</label>
                <input type="text" id="bio-input" name="bio"
                value={userForm.bio} onChange={change}/>
                </div>
               <button type="submit">Save Changes </button>
            </form>
            
        </div>
    )
}
