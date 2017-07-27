Contributor: Yanhan Lyu, Lucy Wu.

Phase_2:
Almost works as we expected except that we do not implement potato mine since we do not have eoungh time.


Brief Description:
We are going to implement a game similar with Plant vs. Zombie. The player can place different types of plants, each with their own unique offensive or defensive capabilities, around a house, in order to stop a horde of zombies from reaching the house of the residents. We will at least will include Sunflower, Peashooter, Wall-nut and Potato Mine, which are the main characters in the day-time scene. The playing field is divided into 5 horizontal lanes. A zombie will only move towards the player's house along one lane. Planting costs "sun", which can be gathered by planting Sunflowers. The plants can only attack or defend against zombies in the lane they are planted in. 

Why MVC:
We will be using the MVC design pattern to build the general structure of the program. 
Our Model contains all game state data. We will construct a class to hold the current game information, including the list of plants that have been planted, the list of zombies that appear in the current scene, the progress of the game. 


Core classes:
A player class, keeping track of the player’s “sun”, a list of plants that he can plant and a list of pants he has planted.
An Enemy class, keeping track of the speed and density of the whole group of zombies and game progress (if this is the last group of zombies). 
A plant class, keeping track of the plant’s position, price, health and power. It will have subclasses for every specific kind of plants.
A zombie class, keeping track of the zombies’ positions, remaining health, speed and power.
A tool class, which includes some helper tools. For example, the shovel can remove the plants immediately to make space for new plants.
Our view will include at least a home screen in which we could set difficulty level, the game view, and a menu bar that deals with using the money to buy new plants. The home screen will offer the user some buttons including set difficulty level and start new game. The default difficulty will be “Beginner”. 


The game view will have Zombies moving along the path and attacking the plants, the plants fighting against the zombies and the user can plant new plants in the available positions if they want. The view will show all plants and will receive a list of the Zombies and their positions at each time step, and then display each of them. At the bottom of the game view, we will display the progress of the game( if this is the last group of Zombies). At the top of the screen, we will include a menu bar that display how much “sun” you have, the plants that you can plant and the tools that you can use. These information all come from our model. When an affordable plant and an available cell are chosen,  the view will inform our controller to update information.
