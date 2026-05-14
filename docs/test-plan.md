# Test Plan – Coin Collection Game  
Author: Thilini Amarasekara  
Student ID: 5109653  

This document outlines the functionality, usability, and compliance testing performed on the Coin Collection Game.  
A total of 12 tests were executed to verify that the game behaves correctly, is easy to use, and meets basic accessibility standards.

---

## 1. Functionality Tests

| Test ID | Test Scenario                    | Expected Result                                       | Actual Result                                                   | Pass/Fail |
|---------|----------------------------------|-------------------------------------------------------|-----------------------------------------------------------------|-----------|
| FT01    | Player movement using arrow keys | Player moves smoothly in all directions               | Player moved correctly in all directions                        |    Pass   |
| FT02    | Player collects a coin           | Coin disappears and score increases by 1              | Coin disappeared and score updated                              |    Pass   |
| FT03    | Player collides with enemy       | Health decreases by 1 and player becomes invulnerable | Health reduced and player flashed grey                          |    Pass   |
| FT04    | All coins collected              | Game enters GAME_WON state                            | “Congratulations! You collected all coins!” displayed correctly |    Pass   |
| FT05    | Player health reaches zero       | Game enters GAME_OVER state                           | GAME_OVER displayed correctly                                   |    Pass   |

---

## 2. Usability Tests

| Test ID | Test Scenario                                | Expected Result                              | Actual Result                             | Pass/Fail |
|---------|----------------------------------------------|----------------------------------------------|-------------------------------------------|-----------|
| UT01    | Interface readability during gameplay        | Health and coin count should be easy to read | Text was clear and readable               |    Pass   |
| UT02    | Player understands how to start the game     | “Press SPACE to start” message is visible    | Message displayed clearly                 |    Pass   |
| UT03    | Restarting after GAME_OVER                   | Pressing SPACE resets game correctly         | Game reset and returned to INITIALISING   |    Pass   |
| UT04    | Player receives visual feedback when damaged | Player should flash or change colour         | Player turned grey during invulnerability |    Pass   |
| UT05    | Coins and enemies are visually distinct      | Player can easily identify objects           | Colours were clear and distinguishable    |    Pass   |

---

## 3. Compliance Tests

| Test ID | Test Scenario                              | Expected Result                                             | Actual Result                                               | Pass/Fail|
|---------|--------------------------------------------|-------------------------------------------------------------|-------------------------------------------------------------|----------|
| CT01    | Colour accessibility for colourblind users | Game should not rely solely on colour; shapes should differ | Enemies = squares, coins = circles, player = rounded square |    Pass  |
| CT02    | Unicode hearts display correctly           | Hearts should appear as ♡ symbols                           | Hearts displayed correctly on screen                        |    Pass  |

---

## 4. Evaluation Summary

A brief test plan was completed to assess the functionality, usability, and compliance of the game application.  
The purpose of this testing was to confirm that the game behaves as expected, is straightforward for players to use, and meets basic accessibility standards.

### Functionality Testing  
These tests verified core game mechanics, including movement, scoring, collision detection, and game state transitions.  
All functionality tests passed, confirming that the game logic works reliably during normal gameplay.

### Usability Testing  
Usability tests focused on clarity, readability, and ease of interaction.  
All tests passed, showing that the game is simple to understand and provides clear visual feedback.

### Compliance Testing  
Compliance tests ensured accessibility and correct rendering of Unicode symbols.  
Both tests passed successfully.

### Test Results  
No major issues were found.  
Minor improvements were made during development, including increasing invulnerability duration and improving text visibility.  
After adjustments, all tests were re-run and passed.

---

## 5. Conclusion

The game meets the expected standards for functionality, usability, and accessibility.  
All tests passed successfully, and the application is stable and ready for use.
