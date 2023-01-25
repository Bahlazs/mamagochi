import * as React from 'react';
import UserComponent from "./UserComponent.jsx";

function NavBar({userName, logoutBob, loginBob}) {

    return (
        <div className={"nav-bar"}>
            <div><button className="nav-bar-button" id={"about"}>About</button>
            <button className="nav-bar-button" id={"features"}>Features</button></div>
            <div id={"user-block"}><UserComponent userName={userName} loginBob={loginBob} logoutBob={logoutBob}/></div>
        </div>
    );
}

export default NavBar;