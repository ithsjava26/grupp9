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

## Design patterns

## Git reflection
