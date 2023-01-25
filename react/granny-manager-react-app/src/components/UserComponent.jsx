import * as React from 'react';
import {ReactSession} from 'react-client-session';
import {useState} from "react";
import {useEffect} from "react";



export default function UserComponent(setUsername, userName, loginBob, logoutBob) {


    if (userName === undefined) {
        return (
            <div>
                <button>Create Granny</button>
                <button onClick={() =>{loginBob(setUsername)}}>Login
                </button>
            </div>
        )
    } else {
        return (
            <div>
                <button>Profile</button>
                <button onClick={() =>{logoutBob(setUsername)}}>Logout
                </button>
            </div>
        )
    }


}
