
import './App.css'
import NavBar from './components/NavBar.jsx'
import {useState} from "react";







function App() {
    const [userName, setUsername] = useState(undefined)

    function logoutBob(){
        setUsername(undefined)
    }

    function loginBob(){
        setUsername("Bob")

    }



  return (
    <div className="App">
      <NavBar userName = {userName} logoutBob={logoutBob} loginBob={loginBob}/>
    </div>
  )
}

export default App
