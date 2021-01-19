Demonstration Video: https://www.youtube.com/watch?v=bOJnQrmtjJw&feature=youtu.be
This is a slightly older verson that has the extra tab "Set Up Colors", which was never implemented. "Set Up Colors" is missing from the final version of the project which can be found above (.jar file) and I did not want to remake the demonstration video.


Unfortunately, some of the code is actually missing and lost. The Maze.java file is an older version even though the MazeTest.java is the finished version. So the code in the repository will not compile into a completed project. Both files should have close to 1,000 lines of code. But the Maze.java file only has 370 lines.
However, the .jar file is still a playable game.

The text below is what the "Instructions" button shows in the "Help" menu:

Instructions:

This is a simple maze game that has a timer ticking down. It is not so much a maze
game but about rapidly pressing buttons quickly and efficiently to get through the maze. 
This game only consists of single games and there are no series involved.
A cricle is used as the item/object navigating through the maze and a colored square 
indicates the start and destination.

To move up: Press the up arrow key and/or the W key.

To move down: Press the right arrow key and/or the D key.

To move left: Press the left arrow key and/or the A key.

Button remapping is not supported. All games start at the upper left corner and generally end at the lower right corner.

The maze was generated using a depth-first search algorithm, so the paths mainly consists of long winded passagewas. FOr this reason, taking the wrong path is not necesasarily common, and the game is primarily about speed.

Settings Defintion

Size Level: The size level determines the width and height of the maze, which are generally the same. Therefore, the maze is shaped like a square. The width and height is equal to maze difficulty minus 10 (should be plus 10)

Time Level: The time level determines the amount of time left when starting a game. The time level depends on the difficulty/size of the maze if adjust time for length of maze (recommended) is turned off. If adjust time for length for length of maze (recommended) is turned on then, the length of the maze instead of the size of the maze determines the amount of time left.

Here are the relevant values for determining how much time is left beginning the maze (in seconds).

Level 1: length\*0.28

Level 2: length\*0.27

Level 3: length\*0.26

Level 4: length\*0.25

Level 5: length\*0.24

Level 6: length\*0.23

Level 7: length\*0.22

Level 8: length\*0.21

Level 9: length\*0.20

Level 10: length\*0.19

Level 11: length\*0.18

Level 12: length\*0.175

Level 13: length\*0.17

Level 14: length\*0.165

Level 15: length\*0.16

Level 16: length\*0.156

Level 17: length\*0.152

Level 18: length\*0.148

Level 19: length\*0.144

Level 20: length\*0.14

One second is added to the resulting values from the above equation. When adjust time is turned off, the same values are used, but the average length of the maze is used as the length.

The resulting value is multiplied by 1.1. If adjust for length is turned off, some mazes are very easy and some mazes may be impossible. Note that there are multiple places to change the difficulty settings. Both change the same thing, but the "Set up gameplay/difficulty" is the easiest way to do so.

Laser Beams: Laser beams were orginally to be added to the game, but they have been disabled.

Path fill: If path fill is turned on, every space that the circle has been to will be colored, which makes the maze easier as you can see which places have already been completed. There is a glitch where some spaces won't be colored when passing through it however.

Save settings for next time: The entire menu will be saved if this is turned on. IF this is turned off, the default settings wil be stored, which can't be altrered from the start menu.

Save statistics for next time: Statistics are normally saved every time the game is turned on and off, however, they can be deleted by unmarking the check box and exiting the game.

Show complete percentage: A percentage counter indicating what percentage of the maze is completed, along with a corresponding progress bar is included. This can be turned on and off by markign and unmarking the check box.The percentage counter will be not be incremented if circle goes to any wrong space or goes backward in the maze.

The space size and the wall size can also be changed. If the space size is set to very thin and the wall size is set to very thick, then the walls and spaces have the same width. However, it is difficulty to navigate the maze this way, so it is not recommended. The default settings have the spaces and walls set at normal.

Defining the statistics menu: The first of the statistics menu is labeled Wins/Losses and shows the typical wins and losses. Win percentage is defined as wins divided by total games. Losses are incurred when creating a new game or exiting he game while the timer is ticking down or when the timer hits 0.0. The average size and time difficulty when winning the game and the average size and time difficulty in games that are lost are also included. 

In the miscellaneous section, a few other limited number of statistics are available. The total number of directional button 
inputs is defined as spaces tried to move. The number of spaces that the circle actually moved is the spaces actually moved. Therefore, the spaces tried to move subtracting the spaces actually moved defines the number of directional inputs tht did not 
result in any movement, which is spaces tried to move without movement. The optimal number of spaces is defined as tbe minimum umber of directional inputs to reach the end of the maze and win the game. The actual number of spaces subtracting the optimal movement is therefore extra movement that is not needed. This can be revisiting spaces that were already visited or going the correct way, which is the statistic shown as spaces revisited or incorrect spaces.
Finally, the percentage optimal spaces of spaces tried to move is simply optimal number of spaces divided by spaces tried to move. This can be taken as the efficiency ratio of the player.

Other things: Be sure to pause the game through pressing Ctrl+P, which is the quickest method, to change any settings during the game, because the clock will be ticking down otherwise. The window on the screen is also centered, and cannot be moved from the center. In the help menu, a maze generator is included, which produces a 19* 19 size maze, equivalent to a level 9 size difficulty maze. 
This produces a gridpane of every single step that updates the maze when generating the maze through the depth-first search algorithm. This produces 441 images and the best way to remove the images is just to quit the game. Make sure a game is not ongoing when pressing the see maze generator item in the Help menu.
