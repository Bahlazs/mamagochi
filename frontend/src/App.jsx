import './App.css'
import {useEffect, useState} from "react";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import GrannyPage from "./grannypage/grannyPage.jsx";
import NavBar from './components/NavBar.jsx'
import LandingPage from "./components/LandingPage.jsx";
import {Footer} from "./components/Footer.jsx";
import {ModalForm} from "./components/ModalForm";


function App() {

    const [userName, setUserName] = useState(undefined);
    const [grannyCreated, setGrannyCreated] = useState(false);
    const [formData, setFormData] = useState(null);

    const handleFormSubmit = data => setFormData(data);

    useEffect(() => {
        // just to test formData
        console.log(formData);

        if (!formData) return;
        registerUser().then(r => console.log(r));
    }, [formData]);

    const registerUser = async () => {
        const res = await fetch(`/user/register`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                name: formData.username,
                email: formData.email,
                password: formData.password})
        })
        if (res.status === 200) {
            setUserName(formData.username);
        }
    }

    const createGranny = async (grannyName) => {
         const res = await fetch(`/granny/create-granny`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({userId: 1, name: grannyName})
        })
        if (res.status === 200) {
          setGrannyCreated(true);
        }
    }

    function logoutBob() {
        setUserName(undefined)
    }

    function loginBob() {
        setUserName("Bob")

    }

  return (
    <div className="App">
      <Router>
      <NavBar userName = {userName} logoutBob={logoutBob} loginBob={loginBob} />
        <ModalForm onFormSubmit={handleFormSubmit} />
        <Routes>
            <Route path="/" element={<LandingPage userName={userName}/>} />
            <Route path="/visit-granny" element={<GrannyPage grannyCreated={grannyCreated}/>} />
        </Routes>
      </Router>
      <Footer/>
    </div>
  )

}

export default App
