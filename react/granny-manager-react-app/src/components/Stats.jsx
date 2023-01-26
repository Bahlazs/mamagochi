import {useEffect, useState} from "react";

function Stats(props) {
    const temporaryId = 0;

    const [health, setHealth] = useState(0);
    const [mood, setMood] = useState(0);
    const [environment, setEnvironment] = useState(0);
    useEffect(() => {
        async function fetchAllData() {
            // the backend will retrieve the id from the session? - not the frontend
            // but in this version here we're sending the id
            const grannyData = await fetch(`http://localhost:8080/granny/visit-granny/${temporaryId}`).then(res => res.json());
            console.log(grannyData);
            setHealth(grannyData.health.stringValueOfStat);
            setMood(grannyData.mood.stringValueOfStat);
            setEnvironment(grannyData.environment.stringValueOfStat);
        }

        fetchAllData();

        //     I'm not sure if we require a cleanup effect here?
    }, []);

    useEffect(() => {
        async function feedPie() {
            // We should send the update here to the backend - that the state updated
            const grannyData = await fetch(`http://localhost:8080/granny/feed-pie/${temporaryId}`);
            console.log(grannyData);
        }
        feedPie();
    }, [health])

    useEffect(() => {
        async function playMahjong() {
            // We should send the update here to the backend - that the state updated
            const grannyData = await fetch(`http://localhost:8080/granny/play-mahjong/${temporaryId}`);
            console.log(grannyData);
        }
        playMahjong();
    }, [mood])

    useEffect(() => {
        async function cleanHouse() {
            // We should send the update here to the backend - that the state updated
            const grannyData = await fetch(`http://localhost:8080/granny/clean-house/${temporaryId}`);
            console.log(grannyData);
        }
        cleanHouse();
    }, [environment])

    return (
        <table>
            <thead>
            <tr>
                <th>Health</th>
                <th>Mood</th>
                <th>Environment</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>{health}</td>
                <td>{mood}</td>
                <td>{environment}</td>
            </tr>
            <tr>
                <td>
                    {/*TODO: change the values back*/}
                    <button onClick={() => setHealth(0)}>Feed Pie</button>
                </td>
                <td>
                    <button onClick={() => setMood(0)}>Play Mahjong</button>
                </td>
                <td>
                    <button onClick={() => setEnvironment(0)}>Clean House</button>
                </td>
            </tr>
            </tbody>
        </table>
    )

}

export default Stats;


// TODO: 1. store the states in the frontend -> then it will send a number or something to the backend
// TODO: because then it will change before it gets stored in the backend
// TODO: 2. change all the activities to PUT request instead of GET request