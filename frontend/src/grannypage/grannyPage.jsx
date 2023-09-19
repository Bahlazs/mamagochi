import './grannyPage.css'
import wholeGranny from '../assets/whole_granny_nobackground.png'
import {Grid} from "@mui/material";
import StatActionButton from "../components/StatActionButton.jsx";
import {useEffect, useState} from "react";
import LinearProgress from '@mui/material/LinearProgress';
import {createTheme, ThemeProvider} from '@mui/material/styles';


const greenStat = '#83ab7c'
const yellowStat = '#E3A924'
const redStat = '#BF6330'

const grannyTheme = createTheme({
    palette: {
        good: {
            // Purple and green play nicely together.
            main: greenStat
        },
        middle: {
            main: yellowStat
        },
        bad: {
            main: redStat
        }
    },
});

function GrannyPage({visitGranny}) {


    const [health, setHealth] = useState("HEALTHY");
    const [mood, setMood] = useState("HAPPY");
    const [environment, setEnvironment] = useState("TIDY");

    const [healthColor, setHealthColor] = useState("bad")
    const [moodColor, setMoodColor] = useState("bad")
    const [envColor, setEnvColor] = useState("bad")



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
        setHealth(granny.healthStat);
        setMood(granny.moodStat);
        setEnvironment(granny.environmentStat);
    }

    const setBarColors = () => {
        setHealthColor(pickColor(colorsForBars[health]))
        console.log(pickColor(colorsForBars[health]))
        setMoodColor(pickColor(colorsForBars[mood]))
        console.log(pickColor(colorsForBars[mood]))
        setEnvColor(pickColor(colorsForBars[environment]))
        console.log(pickColor(colorsForBars[environment]))
    }




    useEffect(() => {
        setBarColors()
    },[mood, health, environment])


    const pickColor = (stat) => {
        if (stat === 33) {
            return "bad";
        } else if (stat === 66) {
            return "middle";
        } else if (stat === 100) {
            return "good";
        }
    }

    return (

        <Grid container spacing={2} className='container'>
            {/*<Grid item xs={12}>MAMAGOTCHI</Grid>*/}
            <Grid className='granny-room' item xs={12}>
                <img id="granny-in-room" src={wholeGranny}/>

            </Grid>

            <Grid className="stat-grid" item xs={4}>
                <ThemeProvider theme={grannyTheme}>
                    <div className="stat-grid">
                        <span id="pg-bar-text">{environment}</span>
                        <LinearProgress variant="determinate" value={colorsForBars[environment]} color={envColor}
                                        sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
                    </div>
                </ThemeProvider>
            </Grid>
            <Grid className="stat-grid" item xs={4}>
                <ThemeProvider theme={grannyTheme}>
                    <div className="stat-grid">
                        <span id="pg-bar-text">{health}</span>
                        <LinearProgress variant="determinate" value={colorsForBars[health]} color={healthColor}
                                        sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
                    </div>
                </ThemeProvider>
            </Grid>
            <Grid className="stat-grid" item xs={4}>
                <ThemeProvider theme={grannyTheme}>
                    <div className="stat-grid">
                        <span id="pg-bar-text">{mood}</span>
                        <LinearProgress variant="determinate" value={colorsForBars[mood]} color={moodColor}
                                        sx={{width: 8 / 10, height: 30, borderRadius: 1}}/>
                    </div>
                </ThemeProvider>
            </Grid>

            <Grid className='btn-grid' item xs={4}>
                <StatActionButton apiLink={`/granny/clean-house`}
                                  jsonKey="environment" setState={setEnvironment} actionText="Clean House"/>
            </Grid>
            <Grid className='btn-grid' item xs={4}>
                <StatActionButton apiLink={`/granny/feed-granny`}
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