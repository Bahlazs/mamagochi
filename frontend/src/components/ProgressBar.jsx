// import * as React from 'react';
// import {styled} from '@mui/material/styles';
// import Box from '@mui/material/Box';
// import LinearProgress, {linearProgressClasses} from '@mui/material/LinearProgress';
//
// const ProgressBar = styled(LinearProgress)(({theme, color, text, value=33}) => {
//   const themeSpecs = ({
//     height: 30,
//     borderRadius: 5,
//     [`&.${linearProgressClasses.colorPrimary}`]: {
//       backgroundColor: theme.palette.grey[theme.palette.mode === 'light' ? 200 : 800],
//     },
//     [`& .${linearProgressClasses.bar}`]: {
//       borderRadius: 5,
//       backgroundColor: color,
//     },
//   })
//   return (
//       <Box sx={{flexGrow: 1}}>
//         <span id="pg-bar-text">{text}</span>
//         <ProgressBar variant="determinate" value={value}/>
//       </Box>
//   )
// })
//
//
// export default ProgressBar