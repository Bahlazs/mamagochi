import './grannyPage.css'
import wholeGranny from '../assets/whole_granny_nobackground.png'
import {Container, Grid} from "@mui/material";
import StatActionButton from "../components/StatActionButton.jsx";
import {useEffect, useState} from "react";
import LinearProgress from '@mui/material/LinearProgress';
import {createTheme, ThemeProvider} from '@mui/material/styles';


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
      const grannyData = await fetch(`/granny/visit-granny/${temporaryId}`,
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

  const theme = createTheme({
    palette: {
      primary: {
        // Purple and green play nicely together.
        main: greenStat,
      },
    },
  });

  const theme2 = createTheme({
    palette: {
      primary: {
        // Purple and green play nicely together.
        main: redStat,
      },
    },
  });

  return (

      <Grid container spacing={2} className='container'>
        <Grid item xs={12}>MAMAGOTCHI</Grid>
        <Grid className='granny-room' item xs={12}>
          <img id="granny-in-room" src={wholeGranny}/>

        </Grid>

        <Grid className="stat-grid" item xs={4}>
          <ThemeProvider theme={theme2}>
            <div className="stat-grid">
              <span id="pg-bar-text">Environment</span>
              <LinearProgress variant="determinate" value={66} color: primary="true"
                              sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
            </div>
          </ThemeProvider>
        </Grid>
        <Grid className="stat-grid" item xs={4}>
          <ThemeProvider theme={theme2}>
            <div className="stat-grid">
              <span id="pg-bar-text">Health</span>
              <LinearProgress variant="determinate" value={66} color: primary="true"
                              sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
            </div>
          </ThemeProvider>
        </Grid>
        <Grid className="stat-grid" item xs={4}>
          <ThemeProvider theme={theme2}>
            <div className="stat-grid">
              <span id="pg-bar-text">Mood</span>
              <LinearProgress variant="determinate" value={66} color: primary="true"
                              sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
            </div>
          </ThemeProvider>
        </Grid>

        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`/granny/clean-house/${temporaryId}`}
                            jsonKey="environment" setState={setEnvironment} actionText="Clean House"/>
        </Grid>
        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`/granny/feed-pie/${temporaryId}`}
                            jsonKey="health" setState={setHealth} actionText="Feed Pie"/>
        </Grid>
        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`/granny/play-mahjong/${temporaryId}`}
                            jsonKey="mood" setState={setMood} actionText="Play Mahjong"/>
        </Grid>
      </Grid>

  )

}

export default GrannyPage
