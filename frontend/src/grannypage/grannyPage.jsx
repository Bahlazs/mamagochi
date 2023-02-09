import './grannyPage.css'
import wholeGranny from '../assets/whole_granny_nobackground.png'
import {Grid} from "@mui/material";
import StatActionButton from "../components/StatActionButton.jsx";
import StatBar from "../components/StatBar.jsx";
import {useEffect, useState} from "react";


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
        setHealth(data.health.stat);
        setMood(data.mood.stat);
        setEnvironment(data.environment.stat);
    }

    useEffect(() => {
        setInitialStats();
    });

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
