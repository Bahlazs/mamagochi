import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import GrannyPage from "./grannypage/grannyPage.jsx";


function App() {
    const [count, setCount] = useState(0)

    return (
        <div className="App">
            <GrannyPage></GrannyPage>
        </div>
    )
}

export default App
