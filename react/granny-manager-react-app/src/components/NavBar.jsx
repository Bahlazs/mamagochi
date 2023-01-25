import * as React from 'react';
import UserComponent from "./UserComponent.jsx";

function NavBar(setUsername, userName, logoutBob, loginBob) {

    return (
        <div className={"nav-bar"}>
            <button id={"about"}>About</button>
            <button id={"features"}>Features</button>
            <div id={"user-block"}><UserComponent setUsername={setUsername} userName={userName} loginBob={loginBob} logoutBob={logoutBob}/></div>
        </div>
    );
}

export default NavBar;