
# Hunger Games

Hunger Games is a Spigot 1.18.2 plugin trying to make you revive the first Hunger Games
gamemode you could find in the early 2010s, but with a modern version of Minecraft.

Hunger Games is a Minecraft game mode inspired by the Hunger Games book written by Suzanne Collins


## Features

- **Lobby**: players wait at the spawn, choose a kit, and the game starts when enough players are connected.
  Hunger, time and weather are frozen until the game starts.
- **Kits**: each tribute chooses a kit (`/kits`, `/kit [name]` or the kit selector).
- **Grace period**: after the start, tributes can't be hurt until PvP is enabled.
- **Arena**: the vanilla world border shrinks during the game.
- **Feasts**: chests full of useful stuff spawn near the spawn point during the game.
- **Popularity & sponsors**: mining ores and killing mobs or players makes you popular.
  When you are famous enough, sponsors send you a gift with a parachute, like in the book.
- **Automatic restart**: the server stops 15 seconds after the victory, so the start script can reset the map
  and start a new game.
- Custom mechanics: TNT is ignited when placed, fire charges are launched as fireballs, saddles spawn a tamed horse,
  the compass points at the nearest tribute, shields can't be crafted, eating cake gives a random effect.
- The Nether and the End are disabled.
- Config file for the game (arena size, time before PvP...)


## Kits

Every kit also contains a compass.

| Kit      | Items                        |
|----------|------------------------------|
| Butcher  | Golden axe, 2 porkchops      |
| Career   | Stone sword, leather boots   |
| Finnick  | Trident, 20 cobwebs          |
| Gale     | 10 TNT, 1 cobweb             |
| Ghast    | 20 fire charges              |
| Horseman | Saddle, golden sword         |
| Katniss  | Bow, 20 spectral arrows      |
| Miner    | Iron pickaxe, 64 torches     |
| Peeta    | Cake, 3 bread                |
| None     | Nothing (default kit)        |


## FAQ

#### Can I use this plugin on my server ?

Yes, take times to give credits if you can !

#### Can I modify the plugin source code to use it ?

Yes of course, it is on github for this reason ;)


## Deployment

The plugin needs a Spigot (or Paper) 1.18.2 server, running with Java 17.

1. Drag and drop the .jar file (you can find it in the Release section) in the `plugins` folder.
2. Prepare the map: start the server once to generate the world, stop it, then copy the `world` folder
   to `world_template`. Remove the `playerdata` folder from `world_template`, otherwise the players get back
   the inventory and position saved in the template.
3. Create a `start.bat` file that resets the map and starts the server in a loop.
   The plugin stops the server at the end of each game, and the script starts a new one on a fresh map:
````
@echo off
:loop
rmdir /s /q world
rmdir /s /q world_nether
rmdir /s /q world_the_end
xcopy /e /i /q world_template world

java -Xms2G -Xmx3G -XX:+UseG1GC -jar spigot.jar nogui

echo Restarting in 3 seconds... (Ctrl+C to stop)
timeout /t 3
goto loop
````
4. Launch the server a first time to generate the `config.yml` file (in the `plugins/HungerGames` folder),
   to be able to customize your Hunger Games at your will.

The Nether and the End are not used: you can disable them with `allow-nether=false` in `server.properties`
and `allow-end: false` in `bukkit.yml`, so the server starts faster.


## Configuration

The plugin refuses to start if a value is invalid, with a message telling which one.
A missing key uses the default value.

| Key                             | Default | Description                                                    |
|---------------------------------|---------|----------------------------------------------------------------|
| `minimal_amount_of_player`      | 4       | Players needed to start the countdown (at least 1)             |
| `second_before_starting_game`   | 90      | Lobby countdown, in seconds                                    |
| `second_before_pvp`             | 90      | Grace period after the start, in seconds                       |
| `arena_start_radius`            | 2500.0  | Radius of the world border at the start                        |
| `arena_end_radius`              | 200.0   | Radius of the world border at the end (at most the start one)  |
| `arena_reducing_pvp`            | 10.0    | Blocks the border shrinks every 10 seconds                     |
| `max_item_in_feast`             | 15      | Maximum number of item stacks in a feast (at least 1)          |
| `number_of_feast`               | 3       | Number of feasts in a game (-1 for unlimited)                  |
| `seconds_between_feast`         | 500     | Time between two feasts, the first one comes after PvP starts  |
| `max_item_in_gift`              | 3       | Maximum number of item stacks in a gift (at least 1)           |
| `coal_ore_mining_popularity`    | 5       | Popularity for mining coal ore                                 |
| `iron_ore_mining_popularity`    | 50      | Popularity for mining iron ore                                 |
| `diamond_ore_mining_popularity` | 250     | Popularity for mining diamond ore                              |
| `monster_killing_popularity`    | 50      | Popularity for killing a monster                               |
| `animal_killing_popularity`     | 10      | Popularity for killing an animal                               |
| `player_killing_popularity`     | 500     | Popularity for killing a tribute                               |
| `tnt_fuse_time_seconds`         | 1.5     | Time before a placed TNT explodes                              |
| `horse_health`                  | 40.0    | Health of the horses spawned with a saddle (at most 1024)      |
| `horse_jump_strength`           | 1.0     | Jump strength of the horses (at most 2.0)                      |

A tribute receives a gift every 1000 popularity.


## Commands

| Command                  | Who       | Description                                                  |
|--------------------------|-----------|--------------------------------------------------------------|
| `/kits`                  | Everyone  | Show all the kits                                            |
| `/kit [name]`            | Tributes  | Select a kit in the lobby, or show your current kit          |
| `/setstate [state]`      | Operators | Force the game state: `not_started`, `starting`, `playing`, `ended` |
| `/state`                 | Operators | Show the game state                                          |
| `/gift`                  | Operators | Send yourself a sponsor gift                                 |
| `/feast`                 | Operators | Spawn a feast                                                |
| `/popularity [player]`   | Operators | Show your popularity, or the one of another tribute          |
| `/setpopularity [amount]`| Operators | Set your own popularity                                      |
| `/heal`                  | Operators | Heal yourself                                                |
| `/spawn`                 | Operators | Teleport to the middle of the arena                          |
| `/arenaradius [radius]`  | Operators | Show the arena radius, or set it                             |
| `/kitselector`           | Operators | Give yourself a kit selector                                 |

The operator commands are made for testing and debugging, and can only be used in game (not from the console).


## Installation

I keep this sections for people who want to change the source code of the plugin.
If you want to add this plugin to your server, please see "Deployment" section.

Here are the steps :

1. clone this project in a folder
2. open the project with your IDE, it will generate a usable project with the pom.xml settings
3. build the plugin with `mvn clean package` (in IntelliJ: Maven panel > Lifecycle > package).
   The plugin is `target/HungerGames-<version>.jar`, not the one starting with `original-`.

## Authors

- [@Namularbre](https://github.com/Namularbre)

