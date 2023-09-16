import './App.css'
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import GrannyPage from "./grannypage/grannyPage.jsx";
import LandingPage from "./components/LandingPage.jsx";
import {useEffect, useState} from "react";
import ResponsiveAppBar from "./components/AppBar.jsx";
import {Footer} from "./components/Footer.jsx";
import {ModalForm} from "./components/ModalForm";

function App() {

    const [userName, setUserName] = useState(undefined);
    const [formData, setFormData] = useState(null);
    const [open, setOpen] = useState(false);

    const handleFormSubmit = data => setFormData(data);

    useEffect(() => {
        if (!formData) return;
        registerUser();
    }, [formData]);

    const registerUser = async () => {
        const res = await fetch(`/auth/register`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                userName: formData.username,
                email: formData.email,
                password: formData.password
            })
        });
        if (res.status === 200) {
            alert('User registered');
            await createGranny();
        } else {
            alert('Something went wrong');
        }
    }

    const login = async () => {
        const res = await fetch(`/auth/login`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                userName: formData.name,
                password: formData.password
            })
        })
        if (res.status === 200) {
            setUserName(formData.username);
        } else {
            alert('Login was not successful');
        }
    }

    const createGranny = async () => {
        const res = await fetch(`/granny`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                grannyName: formData.grannyName,
                userName: formData.username
            })
        })
        if (res.status !== 200) {
            alert('Something went wrong');
        }
        else {
            return true;
        }
    }

    async function visitGranny() {
        return await fetch(`/granny`).then((res) => {
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

    async function logout() {
        const res = await fetch(`/user/logout`);
        setUserName(undefined);
    }

  return (
    <div className="App">
      <Router>
        <ResponsiveAppBar userName={userName} setOpen={setOpen} logout={logout}/>
        <ModalForm onFormSubmit={handleFormSubmit} open={open} setOpen={setOpen}/>
        <Routes>
            <Route path="/" element={<LandingPage userName={userName}/>}/>
            <Route path="/visit-granny" element={<GrannyPage visitGranny={visitGranny}/>}/>
        </Routes>
      </Router>
      <Footer/>
    </div>
  )

}

export default App
