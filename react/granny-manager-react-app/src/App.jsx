
import './App.css'
import NavBar from './components/NavBar.jsx'

import {useEffect, useState} from "react";







function App() {

    const [userName, setUserName] = useState(undefined);

    function logoutBob(){
        setUserName(undefined)
    }

    function loginBob(){
        setUserName("Bob")

    }

   

  return (
    <div className="App">
      <NavBar userName={userName} loginBob={loginBob} logoutBob={logoutBob}/>
    </div>
  )
}

export default App
