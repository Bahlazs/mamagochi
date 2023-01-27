import {useEffect, useState} from "react";

function Stats(props) {
    const temporaryId = 0;

    const [health, setHealth] = useState(null);
    const [mood, setMood] = useState(null);
    const [environment, setEnvironment] = useState(null);

    const [initialDataController] = useState(new AbortController());

    useEffect(() => {
        async function fetchAllData() {
            // the backend will retrieve the id from the session? - not the frontend
            // but in this version here we're sending the id
            const grannyData = await fetch(`/granny/visit-granny/${temporaryId}`,
                {signal: initialDataController.signal}).then(res => res.json());
            console.log(grannyData);
            // TODO: separate fetch and set part of the method
            //
            setHealth(grannyData.health.stringValueOfStat);
            setMood(grannyData.mood.stringValueOfStat);
            setEnvironment(grannyData.environment.stringValueOfStat);
        }
        fetchAllData();

        return () => initialDataController.abort();
    }, []);

    // TODO: one reusable component for action buttons - useEffect - on click state change

    useEffect(() => {
        async function feedPie() {
            // We should send the update here to the backend - that the state updated
            const grannyData = await fetch(`/granny/feed-pie/${temporaryId}`);
        }
        feedPie();
    }, [health])

    useEffect(() => {
        async function playMahjong() {
            // We should send the update here to the backend - that the state updated
            const grannyData = await fetch(`/granny/play-mahjong/${temporaryId}`);
        }
        playMahjong();
    }, [mood])

    useEffect(() => {
        async function cleanHouse() {
            // We should send the update here to the backend - that the state updated
            const grannyData = await fetch(`/granny/clean-house/${temporaryId}`);
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


// TODO: 1. store the states in the frontend -> increase them when possible with setState -> send a PUT request with the state value in the RequestBody
// TODO: representation will change right away
// TODO: OR on button click, not change frontend - send PUT request with empty RequestBody - manually trigger rerender - and fetch the data again that shows the updated values
// TODO: 2. change all the activities to PUT request instead of GET request
