import './App.css'
import GrannyPage from "./grannypage/grannyPage.jsx";
import NavBar from './components/NavBar.jsx'
import {BrowserRouter as Router,createBrowserRouter, RouterProvider, Routes, Route} from "react-router-dom";
import LandingPage from "./components/LandingPage.jsx";

import {useEffect, useState} from "react";
import {Footer} from "./components/Footer";


function App() {

    const [userName, setUserName] = useState(undefined);
    const [grannyCreated, setGrannyCreated] = useState(false)

    function logoutBob() {
        setUserName(undefined)
    }

    function loginBob() {
        setUserName("Bob")

    }

    useEffect( () => {
        createGranny()
    }, []);

    const createGranny = async () => {
         const res = await fetch(`http://localhost:8080/granny/create-granny`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({userId: 1, name: 'Mariska'})
        })
        if (res.status === 200) {
          setGrannyCreated(true)
        }
    }

  return (
    <div className="App">
      <Router>
      <NavBar userName = {userName} logoutBob={logoutBob} loginBob={loginBob} />
        <Routes>
            <Route path="/" element={<LandingPage userName={userName}/>}/>
            <Route path="/visit-granny" element={<GrannyPage grannyCreated={grannyCreated}/>}/>
        </Routes>
      </Router>
      <Footer/>
    </div>
  )

}

export default App
