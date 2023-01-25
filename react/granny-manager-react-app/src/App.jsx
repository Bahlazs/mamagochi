
import './App.css'
import NavBar from './components/NavBar.jsx'
import { ReactSession } from 'react-client-session';
import {useState} from "react";



function loginBob(setUserName){
    ReactSession.set("username", "Bob");
    setUserName(ReactSession.get("username"))
}



function App() {
    const [userName, setUsername] = useState(ReactSession.get("username"));

    function logoutBob(){
        sessionStorage.removeItem('username');
        setUserName(ReactSession.get("username"))
    }


  return (
    <div className="App">
      <NavBar setUserName={setUsername} userName = {userName} logoutBob={logoutBob} loginBob={loginBob}/>
    </div>
  )
}

export default App
