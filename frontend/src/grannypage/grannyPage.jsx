import './grannyPage.css'
import wholeGranny from '../assets/whole_granny_nobackground.png'
import {Container, Grid} from "@mui/material";
import Box from '@mui/material/Box';
import Stats from "../components/Stats.jsx";
import StatActionButton from "../components/StatActionButton.jsx";
import StatBar from "../components/StatBar.jsx";
import {useEffect, useState} from "react";


function GrannyPage({grannyCreated}) {
  const temporaryId = 1;

  const greenStat = '#83ab7c'
  const yellowStat = '#E3A924'
  const redStat = '#BF6330'

  const [health, setHealth] = useState(null);
  const [mood, setMood] = useState(null);
  const [environment, setEnvironment] = useState(null);

  const [healthColor, setHealthColor] = useState(null)
  const [moodColor, setMoodColor] = useState(null)
  const [envColor, setEnvColor] = useState(null)

  const [initialDataController] = useState(new AbortController());

  const setBarColors = () => {
    setHealthColor(pickColor(health))
    setMoodColor(pickColor(mood))
    setEnvColor(pickColor(environment))
  }

  useEffect(() => {
    async function fetchAllData() {
      // the backend will retrieve the id from the session - not the frontend
      // but in this version here we're sending the id
      const grannyData = await fetch(`http://localhost:8080/granny/visit-granny/${temporaryId}`,
          {signal: initialDataController.signal}).then(res => res.json());
      return grannyData;
    }

    async function setInitialStats() {
      const data = await fetchAllData();
      setHealth(data.health.stat);
      setMood(data.mood.stat);
      setEnvironment(data.environment.stat);
    }

    setInitialStats();

  }, [grannyCreated]);

  useEffect(setBarColors, [mood, health, environment])

  const pickColor = (stat) => {
    if (stat === 0) {
      return redStat;
    } else if (stat === 1) {
      return yellowStat;
    } else if (stat === 2) {
      return greenStat;
    }
  }

  return (

      <Grid container spacing={2} className='container'>
        <Grid item xs={12}>MAMAGOTCHI</Grid>
        <Grid className='granny-room' item xs={12}>
          <img id="granny-in-room" src={wholeGranny}/>

        </Grid>

        <Grid item xs={4}>
          <StatBar text='Environment' color={envColor}/>
        </Grid>
        <Grid item xs={4}>
          <StatBar text='Health' color={healthColor}/>
        </Grid>
        <Grid item xs={4}>
          <StatBar text='Mood' color={moodColor}/>
        </Grid>

        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`http://localhost:8080/granny/clean-house/${temporaryId}`}
                            jsonKey="environment" setState={setEnvironment} actionText="Clean House"/>
        </Grid>
        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`http://localhost:8080/granny/feed-pie/${temporaryId}`}
                            jsonKey="health" setState={setHealth} actionText="Feed Pie"/>
        </Grid>
        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`http://localhost:8080/granny/play-mahjong/${temporaryId}`}
                            jsonKey="mood" setState={setMood} actionText="Play Mahjong"/>
        </Grid>
      </Grid>

  )

}

export default GrannyPage
