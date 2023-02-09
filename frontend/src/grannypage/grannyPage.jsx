import './grannyPage.css'
import wholeGranny from '../assets/whole_granny_nobackground.png';
import {Grid} from "@mui/material";
import StatActionButton from "../components/StatActionButton.jsx";
import {useEffect, useState} from "react";
import LinearProgress from '@mui/material/LinearProgress';
import {createTheme, ThemeProvider} from '@mui/material/styles';


function GrannyPage({visitGranny}) {
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

    // const [initialDataController] = useState(new AbortController());

    const setBarColors = () => {
        setHealthColor(pickColor(health))
        setMoodColor(pickColor(mood))
        setEnvColor(pickColor(environment))
    }

    async function setInitialStats() {
        const data = await visitGranny();
        console.log(data)
        console.log(data.healthStat.statValue)
        //TODO figure out how to show granny stat
        setHealth(data.healthStat.statValue);
        setMood(data.moodStat.statValue);
        setEnvironment(data.environmentStat.statValue);
    }

    useEffect(() => {
        setInitialStats();
    },[]);

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
<<<<<<<<< Temporary merge branch 1

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
=========
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
>>>>>>>>> Temporary merge branch 2
        </Grid>

    )

}

export default GrannyPage
