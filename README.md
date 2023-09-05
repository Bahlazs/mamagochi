# Mamagochi


## Table of contents
- [About](#about)
- [Technologies](#technologies)
- [Features](#features)
- [Setup guide](#setup-guide)
    - [Backend](#backend)
    - [Frontend](#frontend)
   
- [Future Plans](#future-plans)

## About

Mamagochi is a simple Tamagotchi-like application where a user can create and then take care of a virtual Grandmother. This was a school team project which I took over after graduating and continued to develop

## Technologies

- React
- MaterialUI
- Css
- Java
- Spring
- Vite
- Postgresql

## Features
- Manageable virtual Grandmother with 3 different stats

## Setup guide

First clone the project from my Github repository. After you cloned the project open the project folders with your IDE
of choice or if you dont have one open it in terminal. I recommend to open the **backend** and the **frontend** folders separately.

### Backend

#### Dependencies
- OpenJDK 17
- Maven

If you dnt have maven and OpenJDK on your computer first install OpenJDK after that you should install maven.
If you ar not familiar with the process how to do this follow these guides:

[OpedJDK install guide](https://openjdk.org/install/) <br>
[Maven install guide](https://www.javatpoint.com/how-to-install-maven)


After that open the **backend** folder in the **mamagochi** folder. If your IDE didn't configure the project or you opened the project in a terminal (cmd or powershell on windows) run the following command:

This will compile your code and start your server
```angular2html
mvn spring-boot:run
```


After starting the sever it should automatically connect to the data base.


### Frontend

#### Dependencies
- nvm
- npm

Open the **frontend** folder in the **mamagochi** folder with your IDE or in terminal. If you are using windows it's possible that nvm is not installed on your computer,
so you should install it first. <br>

If you are not familiar how to do this follow the guide in the following link:

[Link to node version manager installation guide](https://www.freecodecamp.org/news/nvm-for-windows-how-to-download-and-install-node-version-manager-in-windows-10/)

After that run the command bellow in a terminal.

```angular2html
npm install
```
This will install all the dependencies that you need to run the program.
After that run the code bellow to start the React development server.

```angular2html
npm run dev
```
You can check out the site on the link bellow:

http://localhost:5173/



## Future Plans
- Implement security features
- Friend and clan(nursing home) feature
- Multiplayer mini game


If you run into any problems contact me on nick.balazs18@gmail.com


