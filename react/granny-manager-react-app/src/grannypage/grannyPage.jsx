import grannyRoom from '../assets/livingroom.jpeg'
import {Container, Grid} from "@mui/material";
import Box from '@mui/material/Box';
import Stats from "../components/Stats.jsx";

function myGrid() {
    return (
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{ minHeight: '100vh' }}
        > </Grid>
    )
}

function GrannyPage() {
    return (
        // <Grid item xs="true">

        <Grid container spacing={2} sx={{paddingTop: "2vh"}}>
            <Grid
                item xs={12}
                sx={{
                    backgroundImage: `url(${grannyRoom})`,
                    backgroundPosition: `center`,
                    backgroundRepeat: `no-repeat`,
                    backgroundSize: `100%`,
                    height: "60vh",
                    width: "100vw",
                    marginLeft: "5vw",
                }}
            >
            </Grid>
            <Grid item xs={4}>
                <span>xs=4</span>
            </Grid>
            <Grid item xs={4}>
                <span>xs=4</span>
            </Grid>
            <Grid item xs={4}>
                <span>xs=4</span>
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