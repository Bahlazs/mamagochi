import grannyRoom from '../assets/stock-vector-vintage-interior-of-living-room-with-couch-armchair-clock-and-tv-on-stand-vector-cartoon-1922620874-transformed.jpeg'
import {Container} from "@mui/material";
import Box from '@mui/material/Box';


function BoxSx() {
    return (
        <Box
            sx={{
                width: 300,
                height: 300,
                backgroundImage: grannyRoom,
                '&:hover': {
                    backgroundColor: 'primary.main',
                    opacity: [0.9, 0.8, 0.7],
                },
            }}
        />
    );
}

function GrannyPage() {
    return (
        <Container maxWidth="sm">

            <BoxSx></BoxSx>

        </Container>

    )

}

export default GrannyPage