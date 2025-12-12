# AI Story Generator

## Team Members
- Jazmin Carlos 
- Cedar Hudgens 

## Demo Link
- Check in Demo: https://www.youtube.com/watch?v=kytr02STaDs
- Final Demo: https://www.youtube.com/watch?v=EDmgZXrFrrE

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
- [x] Fully functional GUI

## Design Patterns Used
1. **Strategy Pattern** - Multiple story generation strategies. 
   - Horror, Romance, Fantasy, SciFi
2. **Factory Pattern** - Centralizes the creation of StoryStrategy objects based on the user's selected genre (e.g., automatically creates a FantasyStrategy instance when 'Fantasy' is chosen).
3. **Observer Pattern** - Ui updates when story and/ or model changes
3. **Singleton Pattern** - Configuration manager or API rate limiter

## Setup Instructions
1. Get API key from Open API
2. Update "sample.config.properties" with the API key and API URL. 
3. Change name to "config.properties"

### Prerequisites
- Java 11 or higher
- Dependencies:
  - org.json (20231013)
  - okhttp 4.12.0
  - JUnit 4.13 (unit tests)
  - Mockito 5.21.0 (unit tests)
- OpenAI API key

### Installation (Intellij - Terminal)
1. Clone repository
2. Edit sample.config.properties as described above.
2. Go To main.java.TerminalTest
3. Run  the current file

### Installation (Intellij - GUI) 
1. Clone repository
2. Edit sample.config.properties as described above.
2. Go To main.java.Main
3. Run  the current file

