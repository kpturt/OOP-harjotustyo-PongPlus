# Pong+

A very simple and technical approach to the classic Pong game with an additional twist.

- Game starts automatically and player controls their paddle with UP and DOWN arrows on their keyboard.
- Score counting is displayed in the console
(I know it's not ideal but you can keep count of the score in your head and take it as an additional challenge).
- Ball speed increases every five seconds and is reset on goal scored.
- Player's and CPU's paddle sizes change based on score.
- First to 3 points wins!

## Improvement ideas
- Create a main menu.
- Add score values graphically to the game screen.
- Implement better CPU handling and different difficulty settings.
  - sometimes CPU instantly loses to first ball toss or becomes unbeatable depending on paddle heights
  - this could be done by extending new CPU's from PlayerBase
- Update the game graphically such as adding a background, paddle and ball images.
- New game / return to main menu option after a game ends.
- Different game modes with new ball or paddle behaviours.
- Make the game campaign/rogue-like such as by implementing "select a card" for power ups and/or add different stages and bosses.

## Additional notice
This game was developed and tested on Windows 11. I ran into problems such as frame-rate stuttering and incorrect pixel calculations when I tested this on a Linux system (Fedora/Gnome). This problem might already be fixed with the addition of the .jar build of the game. 