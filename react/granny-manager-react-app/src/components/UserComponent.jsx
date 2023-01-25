import * as React from 'react';
import {ReactSession} from 'react-client-session';
import {useState} from "react";
import {useEffect} from "react";



export default function UserComponent({loginBob, logoutBob, userName}) {


    if (userName === undefined) {
        return (
            <div>
                <button>Create Granny</button>
                <button onClick={loginBob}>Login
                </button>
            </div>
        )
    } else {
        return (
            <div>
                <button>Profile</button>
                <button onClick={logoutBob}>Logout
                </button>
            </div>
        )
    }


}
