import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import {useState} from "react";
import {createTheme, ThemeProvider} from '@mui/material/styles';
import {Link} from "react-router-dom";
import {HashLink} from "react-router-hash-link";

const pages = {
  features: 'Features',
  about: 'About'
};
const userPages = {
  visit: 'Visit Granny',
  logout: 'Logout'
}
const noUserPages = {
  create: 'Create Granny',
  login: 'Login'
}

const theme = createTheme({
  palette: {
    primary: {
      main: '#BF6330',
    },
  },
});

function ResponsiveAppBar({ userName, setOpen, logout }) {
  const [anchorElNav, setAnchorElNav] = useState(null);
  const [anchorElUser, setAnchorElUser] = useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return (
      <ThemeProvider theme={theme}>
      <AppBar position="sticky">
        <Container maxWidth="xl">
          <Toolbar disableGutters>
            {/*<AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />*/}
            <Typography
                variant="h6"
                noWrap
                component={Link}
                to="/"
                sx={{
                  mr: 2,
                  display: { xs: 'none', md: 'flex' },
                  fontFamily: 'inherit',
                  fontWeight: 700,
                  letterSpacing: '.3rem',
                  color: '#ded8b6',
                  textDecoration: 'none',
                }}
            >
              MAMAGOTCHI
            </Typography>

            <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
              <IconButton
                  size="large"
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  onClick={handleOpenNavMenu}
                  color='#ded8b6'
              >
                <MenuIcon />
              </IconButton>
              <Menu
                  id="menu-appbar"
                  anchorEl={anchorElNav}
                  anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'left',
                  }}
                  open={Boolean(anchorElNav)}
                  onClose={handleCloseNavMenu}
                  sx={{
                    display: { xs: 'block', md: 'none' },
                  }}
              >

                <MenuItem onClick={handleCloseNavMenu} component={HashLink} smooth to="/#features">
                  <Typography textAlign="center" sx={{color: 'black', fontFamily: 'inherit'}}>{pages.features}</Typography>
                </MenuItem>
                <MenuItem onClick={handleCloseNavMenu} component={HashLink} smooth to="/#about-us">
                <Typography textAlign="center" sx={{color: 'black', fontFamily: 'inherit'}}>{pages.about}</Typography>
                </MenuItem>
              </Menu>
            </Box>
            {/*<AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />*/}
            <Typography
                variant="h5"
                noWrap
                component={Link}
                to="/"
                sx={{
                  mr: 2,
                  display: { xs: 'flex', md: 'none' },
                  flexGrow: 1,
                  fontFamily: 'inherit',
                  fontWeight: 700,
                  letterSpacing: '.3rem',
                  color: '#ded8b6',
                  textDecoration: 'none',
                }}
            >
              MAMAGOTCHI
            </Typography>
            <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                  <Button
                      onClick={handleCloseNavMenu}
                      component={HashLink} smooth to="/#features"
                      sx={{ my: 2, color: '#ded8b6', display: 'block' }}
                  >
                    {pages.features}
                  </Button>
                <Button
                    onClick={handleCloseNavMenu}
                    component={HashLink} smooth to="/#about-us"
                    sx={{ my: 2, color: '#ded8b6', display: 'block' }}
                >
              {pages.about}
                </Button>
            </Box>

            <Box sx={{ flexGrow: 0 }}>
              <Tooltip title="Open settings">
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                  <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg" />
                </IconButton>
              </Tooltip>
              <Menu
                  sx={{ mt: '45px' }}
                  id="menu-appbar"
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
              >
                {userName?
                    <>
                  <MenuItem onClick={handleCloseUserMenu} component={Link} to="/visit-granny">
                    <Typography textAlign="center" sx={{fontFamily: 'inherit'}}>{userPages.visit}</Typography>
                  </MenuItem>
                <MenuItem onClick={ () => {
                  handleCloseUserMenu();
                  logout();
                  }
                } component={Link} to="/">
                  <Typography textAlign="center" sx={{fontFamily: 'inherit'}}>{userPages.logout}</Typography>
                </MenuItem> </>
                    :
                    <>
                  <MenuItem onClick={ () => {
                    handleCloseUserMenu();
                    setOpen(true);
                    }
                  }>
                  <Typography textAlign="center" sx={{fontFamily: 'inherit'}}>{noUserPages.create}</Typography>
                  </MenuItem>
                  <MenuItem onClick={handleCloseUserMenu}>
                  <Typography textAlign="center" sx={{fontFamily: 'inherit'}}>{noUserPages.login}</Typography>
                  </MenuItem> </>
                }
              </Menu>
            </Box>
          </Toolbar>
        </Container>
      </AppBar>
      </ThemeProvider>
  );
}
export default ResponsiveAppBar;