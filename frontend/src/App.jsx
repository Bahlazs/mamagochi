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
            alert('User registered');
        } else {
            alert('Something went wrong');
        }
    }

    const login = async () => {
        const res = await fetch(`/user/login`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                email: formData.email,
                password: formData.password})
        })
        if (res.status === 200) {
            setUserName(formData.username);
        } else {
            alert('Login was not successful');
        }
    }

    const createGranny = async () => {
         const res = await fetch(`/granny/create-granny`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({name: formData.grannyName})
        })
        if (res.status !== 200) {
            alert('Something went wrong');
        }
    }

    async function visitGranny() {
        return await fetch(`/granny/visit-granny`).then((res) => {
            if (res.status === 200) {
                return res.json()
            } else {
                alert('Your granny is missing');
                const timeout = setTimeout(() => {
                    window.location.replace('http://localhost:5173');
                }, 3000);
                return () => clearTimeout(timeout);
            }
        });
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
            <Route path="/visit-granny" element={<GrannyPage visitGranny={visitGranny}/>} />
        </Routes>
      </Router>
      <Footer/>
    </div>
  )

}

export default App
