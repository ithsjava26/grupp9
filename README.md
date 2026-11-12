# Fångdjupet

## Description

## How to run

## Module owners

- Module A (Combat): Monir
- Module B (Items and inventory): Nicholas
- Module C (Game engine, menu, stages, save/load): Yusur and Arben

## Module A: Combat

The combat package handles fighting between the hero and enemies.

- Combatants - Hero and Enemy: have HP, damage, armour and speed. The hero can equip a weapon and armour for bonus
  stats.
- Battle: plays one round. It decides who goes first, checks if each attack hits or misses, deals the damage and returns
  a list of what happened.
  It does not print anything, so the menu decides how to show it.
- TurnEvent: one entry in that list: who attacked, who was hit, how much damage, HP left and if it hit.

### Design pattern: Strategy

Each attack (Light, Normal, Heavy) is its own class that follows the AttackStrategy interface.

- Light Attack always hits, always goes first but deals less damage.
- Normal Attack almost always hits, is dependent on speed to hit first and does normal damage.
- Heavy Attack hits 60% of the time, is dependent on speed and does more damage.

Battle works with the AttackStrategy interface, so we can add new attacks for the hero without changing Battle.

### Testing

Battle takes a Random as input.
In tests, I give a fake Random that always hits or always misses, so the results are predictable.
The other classes in the package have their own tests too.

## Module B: Items
-for the item and inventory management section of our game I decided to take inspiration from rogue-like inventory management systems. The player character is only allowed to have 1 weapon and 1 armour, but can have multiple potions at a time. This allows for higher replayability through diversifying what loot is used from run to run

-Weapons, Armour & Potions are handled through sealed interfaces and records, ensuring data immutability

-Originally I had implemented variations of weapons, armour and potions directly through code (ItemRegistry contained a list of all weapons, armour & potions), however to cut down on the amount of code, clean it up and allow for altering of the games items I instead moved all items to an items.csv file which is then loaded into ItemRegistry 

-the LootGenerator then uses a factory/generator pattern randomly pulling generated items from ItemRegistry and dropping them to the player after each combat encounter. The dropped loot is then identified as either weapon or armour and handled either by handleWeaponDrop, or handleArmourDrop in Inventory.java

-as mentioned previously our game implements a rogue-like inventory management system, so handleWeaponDrop & handleArmourDrop prompt the player to either keep their currently equipped gear or drop it and equip the newly generated gear after each combat encounter

## Module C: Engine
The Game class manages the main game flow, including initializing the player, handling stage progression and determining when the game ends. The game progresses through multiple stages, with the player facing enemies in turn based combat. 

After each successful battle the game continues to the loot handling process, allowing the player to collect or equip items before progressing to the next stage. The game also provides feedback when the player advances to another stage, wins or loses.

The GameMenu class handles the concole based used interface. It displays options to start a new game, load a saved game or exit. A loop keeps the menu running until the player chooses to exit, while the readChoice() method handles numeric input, invalid input and end of input situations.

The menu and game engine are connected through the Game class. The SaveManager is passed to the game when starting or loading, allowing the save/load functioality to be integrated with the existing game flow. 

JUnit tests were implemted to verify menu choices, input validation menu looping and game behaviour. These tests help ensure that changes to the menu or game flow do not break existing functionality.

### Save and Load

The save and load system allows the player to save their progress and continue playing later without starting from the beginning.

The system uses two main classes:

- **GameSave:** A record that stores the players name, HP, current stage and if the game is completed.
- **SaveManager:** Handles saving the game data to a text file called `savegame.txt` and loading the data when needed.

The save and load system is connected to the `Game` and `GameMenu` classes.

After winning a battle the game automatically saves the players progress. When choosing **Load Game** in the main menu, the game loads the saved data and the player can continue from the same stage with the HP they had before.

JUnit tests are used to check that saving and loading works correctly. There is also a test for when the save file doesn't exist.

The system has also been tested by playing the game, saving progress and then restarting the game to load it again.


## Design patterns

- Strategy: the attacks in the combat package (see Module A)
## Git reflection
