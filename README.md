# Fångdjupet

## Description

## How to run

## Module owners

- Module A (Combat): Monir
- Module B (Items and inventory): Nicholas
- Module C (Game engine, menu, stages, save/load): Yusur and Arben

## Module A: Combat

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


## Design patterns

## Git reflection
