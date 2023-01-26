
import './App.css'
import NavBar from './components/NavBar.jsx'
import React, {useState} from "react";
import {createBrowserRouter, Route, RouterProvider, Routes} from "react-router-dom";
import LandingPage from "./components/LandingPage.jsx";
import GrannyPage from "./components/GrannyPage.jsx";








function App() {
    const [userName, setUsername] = useState(undefined)

    const route = createBrowserRouter([{
        path: "/",
        element: <LandingPage userName={userName}/>
    }, {
        path: "/visit-granny",
        element: <GrannyPage/>
    }])

    function logoutBob(){
        setUsername(undefined)
    }

    function loginBob(){
        setUsername("Bob")

    }

  return (
    <div className="App">
      <NavBar userName = {userName} logoutBob={logoutBob} loginBob={loginBob} />
        <RouterProvider router={route}/>


        {/*<Routes>*/}
        {/*    <Route path="/" exact component={LandingPage}/>*/}
        {/*    <Route path="/visit-granny"  component={GrannyPage}/>*/}
        {/*</Routes>*/}

    </div>
  )
}

export default App
