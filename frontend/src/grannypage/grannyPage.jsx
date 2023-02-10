import './grannyPage.css'
import wholeGranny from '../assets/whole_granny_nobackground.png'
import {capitalize, Container, Grid} from "@mui/material";
import StatActionButton from "../components/StatActionButton.jsx";
import {useEffect, useState} from "react";
import LinearProgress from '@mui/material/LinearProgress';
import {createTheme, ThemeProvider} from '@mui/material/styles';


function GrannyPage({visitGranny}) {


  const greenStat = '#83ab7c'
  const yellowStat = '#E3A924'
  const redStat = '#BF6330'

  const [health, setHealth] = useState(null);
  const [mood, setMood] = useState(null);
  const [environment, setEnvironment] = useState(null);

  const colorTheme = createTheme({
    palette: {
      primary: {
        // Purple and green play nicely together.
        main: greenStat,
      },
      secondary:{
        main: yellowStat
      },
      third:{
        main: redStat
      }
    },
  });



  const [healthColor, setHealthColor] = useState(null)
  const [moodColor, setMoodColor] = useState(null)
  const [envColor, setEnvColor] = useState(null)



  const colorsForBars = {
    "IN_RUINS": 33,
    "MESSY": 66,
    "TIDY": 100,
    "GRUMPY": 33,
    "BORED": 66,
    "HAPPY": 100,
    "SICK": 33,
    "WEAK": 66,
    "HEALTHY": 100,
  }
  useEffect(() => {
    setInitialStats();
  }, []);

  async function setInitialStats() {
    const granny = await visitGranny();
    console.log(granny)
    console.log(colorsForBars[granny.healthStat])
    //TODO figure out how to show granny stat
    setHealth(granny.healthStat);
    setMood(granny.moodStat);
    setEnvironment(granny.environmentStat);
  }

  const setBarColors = () => {
    setHealthColor(pickColor(colorsForBars[health]))
    setMoodColor(pickColor(colorsForBars[mood]))
    setEnvColor(pickColor(colorsForBars[environment]))

  }



  useEffect(setBarColors, [mood, health, environment])




  const pickColor = (stat) => {
    if (stat === 33) {
      return "third";
    } else if (stat === 66) {
      return "secondary";
    } else if (stat === 100) {
      return "primary";
    }
  }

  return (

      <Grid container spacing={2} className='container'>
        {/*<Grid item xs={12}>MAMAGOTCHI</Grid>*/}
        <Grid className='granny-room' item xs={12}>
          <img id="granny-in-room" src={wholeGranny}/>

        </Grid>

        <Grid className="stat-grid" item xs={4}>
          <ThemeProvider theme={colorTheme}>
            <div className="stat-grid">
              <span id="pg-bar-text">{environment}</span>
              <LinearProgress variant="determinate" value={colorsForBars[environment]} color={"primary"}
                              sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
            </div>
          </ThemeProvider>
        </Grid>
        <Grid className="stat-grid" item xs={4}>
          <ThemeProvider theme={colorTheme}>
            <div className="stat-grid">
              <span id="pg-bar-text">{health}</span>
              <LinearProgress id="pg1" variant="determinate" value={colorsForBars[health]} color={"primary"}
                              sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
            </div>
          </ThemeProvider>
        </Grid>
        <Grid className="stat-grid" item xs={4}>
          <ThemeProvider theme={colorTheme}>
            <div className="stat-grid">
              <span id="pg-bar-text">{mood}</span>
              <LinearProgress variant="determinate" value={colorsForBars[mood]} color={"primary"}
                              sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
            </div>
          </ThemeProvider>
        </Grid>

        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`/granny/clean-house`}
                            jsonKey="environment" setState={setEnvironment} actionText="Clean House"/>
        </Grid>
        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`/granny/feed-pie`}
                            jsonKey="health" setState={setHealth} actionText="Feed Pie"/>
        </Grid>
        <Grid className='btn-grid' item xs={4}>
          <StatActionButton apiLink={`/granny/play-mahjong`}
                            jsonKey="mood" setState={setMood} actionText="Play Mahjong"/>
        </Grid>
      </Grid>

  )

}

export default GrannyPage