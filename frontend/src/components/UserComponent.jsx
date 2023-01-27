import * as React from 'react';
import {ReactSession} from 'react-client-session';
import {useState} from "react";
import {useEffect} from "react";




export default function UserComponent({userName, loginBob, logoutBob}) {


    if (userName === undefined) {
        return (
            <div>
                <button className="nav-bar-button">Create Granny</button>
                <button className="nav-bar-button" onClick={loginBob}>Login
                </button>
            </div>
        )
    } else {
        return (
            <div>
                <button className="nav-bar-button">Profile</button>
                <button className="nav-bar-button" onClick={logoutBob}>Logout
                </button>
            </div>
        )
    }


}
