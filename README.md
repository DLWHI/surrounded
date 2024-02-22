# surrounded
A java game where you have to escape the pursuit

## Pre-requsites
- JDK 8
- Apache Maven
- Some hands and terminal (optional)

## Overview
The game consists of field and randomly placed walls. Player must reach randomly
placed escape cell while avoiding enemies. Player and enemies start at random location.
Each turn player must choose direction to move after which enemies will take
turn in response. Player loses when enemy can reach player on next move (in other
words, enemy must be one square away in vertical or horizontal direction).

If player hits the wall while trying to move then nothing happens and one can
go another way in order to avoid pursuit.

## Quickstart
Just `mvn compile package` and there should appear executable `.jar` file in
`target` directory. Run it with
```
java -jar path/to/jar --wallsCount=10 --enemiesCount=2 --size=10 --profile=production
```
Where:
- `wallsCount` - number of walls on the field
- `enemiesCount` - number of enemies on the field
- `size` - field size (NxN square)
- `profile` - game configuration (see below)

This archive is pretty self contained, so it should run basically from any directory

## Profiles
Game supports custom options (also called as "profiles"). These options
can be configured via configuration files located in `config` directory. Initially
there is only `dev` and `producion` profiles, but you can add your own by creating `application-%PROFILE%.properties` file in `config` directory. These files
may have following contents:
- `char.%OBJECT%` - sets corresponding object's render symbol
- `color.%OBJECT%` - sets corresponding object's render color. Supports all ANSI
colors.
- `key.%KEY%` - binds key to specified action. Action list:
    - `move_up/move_down/move_right/move_left` - move in direction
    - `exit` - exit game
    - `accept` - equivalent to "yes"
    - `decline` - equivalent to "no"
    - `fortfeit` - fortfeits the game
- `clearFrame` - specifies whether terminal should be clear after every move.
