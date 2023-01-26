import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import GrannyPage from "./grannypage/grannyPage.jsx";

function App() {
  const [count, setCount] = useState(0)

  return (
    <GrannyPage/>
  )
}

export default App
