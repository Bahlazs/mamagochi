import reactLogo from './assets/react.svg'
import './App.css'
import GrannyPage from "./grannypage/grannyPage.jsx";
import NavBar from './components/NavBar.jsx'
import {useEffect, useState} from "react";


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
        fetchData()
        // const createGranny = async () => {
        //     return await fetchData()
        // }
        // // const res = createGranny()
        // // console.log(res.json())
        // createGranny()
    }, [])
    //
    // useEffect(() => {
    //     const getTasks = async () => {
    //         const tasksFromServer = await fetchTasks()
    //         setTasks(tasksFromServer)
    //     }
    //     getTasks()
    // }, [])

    const fetchData = async () => {
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
            {/*<NavBar userName={userName} loginBob={loginBob} logoutBob={logoutBob}/>*/}
            <GrannyPage grannyCreated={grannyCreated}/>
        </div>
    )
}

export default App
