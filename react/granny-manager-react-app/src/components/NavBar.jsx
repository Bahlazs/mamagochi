import * as React from 'react';
import UserComponent from "./UserComponent.jsx";

function NavBar({userName, logoutBob, loginBob}) {

    function handleClick(selector){
        document.querySelector(selector).scrollIntoView({behavior: "smooth"})
    }

    return (
        <div className={"nav-bar"}>
            <div>
                <button onClick={()=>{
                    handleClick("#about-us")
                }} className="nav-bar-button" id={"about"}>About</button>
                <button onClick={()=>{
                    handleClick("#features")
                }} className="nav-bar-button" id={"features-button"}>Features</button>
            </div>
            <div id={"user-block"}><UserComponent userName={userName} loginBob={loginBob} logoutBob={logoutBob}/></div>
        </div>
    );
}

export default NavBar;