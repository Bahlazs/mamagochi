import * as React from 'react';
import UserComponent from "./UserComponent.jsx";
import {Link} from "react-router-dom";

function NavBar({userName, logout, login}) {

    function handleClick(selector){
        document.querySelector(selector).scrollIntoView({behavior: "smooth"})
    }

    return (
        <div className={"nav-bar"}>
            <div>
                <Link to="/">
                    <button className="nav-bar-button" id="home">Home</button>
                </Link>
                <button onClick={()=>{
                    handleClick("#about-us")
                }} className="nav-bar-button" id={"about"}>About</button>
                <button onClick={()=>{
                    handleClick("#features")
                }} className="nav-bar-button" id={"features-button"}>Features</button>
            </div>
            <div id={"user-block"}><UserComponent userName={userName} login={login} logout={logout}/></div>
        </div>
    );
}

export default NavBar;