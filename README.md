
# Hunger Games

Hunger Games is a Spigot 1.18.2 plugin trying to make you revive the first Hunger Games
gamemode you could find in the early 2010s, but with a modern version of Minecraft.

Hunger Games is a Minecraft game mode inspired by the Hunger Games book written by Suzanne Collins


## Features

- Config file for the game (arena size, time before pvp is active...)
- An arena system
- A kit system
- A feast system
- A popularity system that will reward you with gift from sponsor, like in the book


## FAQ

#### Can I use this plugin on my server ?

Yes, take times to give credits if you can !

#### Can I modify the plugin source code to use it ?

Yes of course, it is on github for this reason ;)


## Deployment

For using this plugin properly, you need to do 3 things.

1. First, you need to drag and drop the .jar file (you can found it in the Release section) in the plugins folder.
2. Create a start.bat file that launch your server and remove the map files, to regenerate a world each time you start a game.
3. Launch the server a first time to generate the config.yml file, to be able to customize your Hunger Games at your will. (You will find it in the plugin folder, in the HungerGames sub-folder).
You can copy-past this code :
````
@echo off
rmdir /s /q world
rmdir /s /q world_nether
rmdir /s /q world_the_end

java -Xms1G -Xmx1G -XX:+UseG1GC -jar spigot.jar nogui
pause
````

## Installation

I keep this sections for people who want to change the source code of the plugin.
If you want to add this plugin to your server, please see "Deployment" section.

Here are the steps :

1. clone this project in a folder
2. open the project with your IDE, it will generate a usable project with the pom.xml settings
    
## Authors

- [@Namularbre](https://github.com/Namularbre)

