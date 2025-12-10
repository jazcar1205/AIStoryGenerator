# AI Story Generator

## Team Members
- Jazmin Carlos 
- Cedar Hudgens 

## Demo Link
- Check in https://www.youtube.com/watch?v=kytr02STaDs
- 

## Project Description
The Interactive Storytelling Application uses AI to create stories based on what the user writes or chooses. Users can pick a genre, make characters, and shape the world of their story. The app can build different types of stories and let users save or export them.

## Features Implemented
- [x] Genre selection (Horror, Romance, Fantasy, SciFi)
- [x] Save / export stories
- [x] Length Selection (Short, Medium, Long)
- [x] Complexity selection (Child friendly, Average, Difficult)
- [x] Pace selection (Slow, Normal, Fast)
- [x] Perspective selection (1st or 3rd)
- [x] Added Prompts for Setting, Tone, Time Period
- [x] Ability to ask for specific characters

## Design Patterns Used
1. **Strategy Pattern** - Multiple story generation strategies. 
   - Horror, Romance, Fantasy, SciFi
2. **Factory Pattern** - Centralizes the creation of StoryStrategy objects based on the user's selected genre (e.g., automatically creates a FantasyStrategy instance when 'Fantasy' is chosen).
3. **Observer Pattern** - Ui updates when story and/ or model changes
3. **Singleton Pattern** - Configuration manager or API rate limiter

## Setup Instructions
1. Get API key from [website]
2. export API_KEY="your-key"   # or set in config.properties
3. 
### Prerequisites
- Java 11 or higher
- Dependencies:
  - org.json (20231013)
  - JUnit 4.13 (unit tests)
- OpenAI API key

### Installation (Intellij - Terminal)
1. Clone repository
2. Go To main.java.TerminalTest
3. Run  the current file

### Installation (Intellij - GUI) 
1. Clone repository
2. Go To main.java.Main
3. Run  the current file

### Dependencies
- org.json (20231013)
- JUnit 4.13


## Known Issues
- The use of the API Client and connecting to the controller : Code Has been Fixed 
- Silent Failure in Terminal: Rerun the program and make sure the API Key is correct

## Future Enhancements
- Implementation of Images
- More Customization to the Story
  - Style
  - Setting
  - Tags
