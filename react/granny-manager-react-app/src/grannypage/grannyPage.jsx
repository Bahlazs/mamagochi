import './grannyPage.css'
import {Container, Grid} from "@mui/material";
import Box from '@mui/material/Box';
import Stats from "../components/Stats.jsx";
import StatActionButton from "../components/StatActionButton.jsx";
import StatBar from "../components/StatBar.jsx";
import {useEffect, useState} from "react";

// function myGrid() {
//     return (
//         <Grid
//             container
//             spacing={0}
//             direction="column"
//             alignItems="center"
//             justifyContent="center"
//             style={{ minHeight: '100vh' }}
//         > </Grid>
//     )
// }

function GrannyPage() {
    const temporaryId = 0;

    const [health, setHealth] = useState(null);
    const [mood, setMood] = useState(null);
    const [environment, setEnvironment] = useState(null);

    const [initialDataController] = useState(new AbortController());

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
            setHealth(data.health.stringValueOfStat);
            setMood(data.mood.stringValueOfStat);
            setEnvironment(data.environment.stringValueOfStat);
        }

        setInitialStats();

    }, []);


    return (
        // <Grid item xs="true">

        <Grid container spacing={2} className='container'>
            <Grid className='granny-room' item xs={12}>
                <div>
                    <img/>
                </div>

            </Grid>

            <Grid item xs={4}>
                <span>Environment</span>
            </Grid>
            <Grid item xs={4}>
                <span>Health</span>
            </Grid>
            <Grid item xs={4}>
                <span>Mood</span>
            </Grid>

            <Grid item xs={4}>
                <StatBar />
            </Grid>
            <Grid item xs={4}>
              <StatBar />
            </Grid>
            <Grid item xs={4}>
              <StatBar />
            </Grid>

            <Grid item xs={4}>
                <StatActionButton apiLink={`http://localhost:8080/granny/clean-house/${temporaryId}`}
                                  jsonKey= "environment" setState={setEnvironment} actionText="Clean House"/>
            </Grid>
            <Grid item xs={4}>
                <StatActionButton apiLink={`http://localhost:8080/granny/feed-pie/${temporaryId}`}
                                  jsonKey= "health" setState={setHealth} actionText="Feed Pie"/>
            </Grid>
            <Grid item xs={4}>
                <StatActionButton apiLink={`http://localhost:8080/granny/play-mahjong/${temporaryId}`}
                                  jsonKey= "mood" setState={setMood} actionText="Play Mahjong"/>
            </Grid>
        </Grid>
        //*</Grid>*/
        // <Container maxWidth="sm">
        //
        //
        //
        //  </Container>

    )

}

export default GrannyPage


// <>
// {/*Navbar*/}
// {/*Background Image*/}
// <h1>Background Image Placeholder</h1>
// {/*Granny Image on top of it*/}
// <h2>Granny Image</h2>
// {/*Stat name constants*/}
// {/*Stat representation
//             Should we fetch all granny's stats here
//             Or should we fetch everything separately
//             */}
// <Stats/>
//
// </>