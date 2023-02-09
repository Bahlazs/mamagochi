import * as React from 'react';
import {useState} from "react";
import {useEffect} from "react";




export default function UserComponent({userName, login, logout}) {


    if (userName === undefined) {
        return (
            <div>
                <button className="nav-bar-button">Create Granny</button>
                <button className="nav-bar-button" onClick={login}>Login
                </button>
            </div>
        )
    } else {
        return (
            <div>
                <button className="nav-bar-button">Profile</button>
                <button className="nav-bar-button" onClick={logout}>Logout
                </button>
            </div>
        )
    }


}
