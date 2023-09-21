import './App.css'
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import GrannyPage from "./grannypage/grannyPage.jsx";
import LandingPage from "./components/LandingPage.jsx";
import {useEffect, useState} from "react";
import ResponsiveAppBar from "./components/AppBar.jsx";
import {Footer} from "./components/Footer.jsx";
import {RegisterModalForm} from "./components/RegisterModalForm.jsx";
import {LoginModalForm} from "./components/LoginModalForm.jsx";
import Cookies from "js-cookie"

function App() {

    const [userName, setUserName] = useState(undefined);
    const [registerFormData, setRegisterFormData] = useState(null);
    const [loginFormData, setLoginFormData] = useState(null)
    const [registerModalOpen, setRegisterModalOpen] = useState(false);
    const [loginModalOpen, setLoginModalOpen] = useState(false);
    const [Token, setToken] = useState(null)


    const handleRegisterFormSubmit = data => setRegisterFormData(data);
    const handleLoginFormSubmit = data => setLoginFormData(data)

    useEffect(() => {
        if (registerFormData) {
            registerUser();
        }
    }, [registerFormData]);

    useEffect(() => {
        if (loginFormData) {
            login();
            /*setToken(Cookies.get("jwt"))*/
        }
    }, [loginFormData]);

    const registerUser = async () => {
        const res = await fetch(`/auth/register`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                userName: registerFormData.username,
                email: registerFormData.email,
                password: registerFormData.password
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
                userName: loginFormData.username,
                password: loginFormData.password
            })
        })
        if (res.status === 200) {
            setUserName(loginFormData.username);
        } else {
            alert('Login was not successful');
        }
    }

    const createGranny = async () => {
        const res = await fetch(`/granny`, {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                grannyName: registerFormData.grannyName,
                userName: registerFormData.username
            })
        })
        if (res.status !== 200) {
            alert('Something went wrong');
        } else {
            return true;
        }
    }

    async function visitGranny() {
        return await fetch(`/granny` ,{
            method: 'GET',
            headers : {
                'Content-type': 'application/json',
                /*Authorization: `Bearer ${Token}`*/
            }
        }).then((res) => {
            if (res.status === 200) {
                console.log(Token)
                return res.json()
            } else {
                alert('Your granny is missing');
                console.log(Token)
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
                <ResponsiveAppBar userName={userName} setRegisterOpen={setRegisterModalOpen} setIsLoginOpen={setLoginModalOpen} logout={logout}/>
                <RegisterModalForm onFormSubmit={handleRegisterFormSubmit}
                                   open={registerModalOpen}
                                   setOpen={setRegisterModalOpen}/>
                <LoginModalForm onFormSubmit={handleLoginFormSubmit}
                                open={loginModalOpen}
                                setOpen={setLoginModalOpen}/>
                <Routes>
                    <Route path="/" element={<LandingPage userName={userName}/>}/>
                    <Route path="/visit-granny" element={<GrannyPage visitGranny={visitGranny} token={Token}/>}/>
                </Routes>
            </Router>
            <Footer/>
        </div>
    )

}

export default App
