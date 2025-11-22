# AI Story Generator

## Team Members
- Jazmin Carlos 
- Cedar Hudgens 

## Demo Link
https://www.youtube.com/watch?v=kytr02STaDs

## Project Description
The Interactive Storytelling Application uses AI to create stories based on what the user writes or chooses. Users can pick a genre, make characters, and shape the world of their story. The app can build different types of stories and let users save or export them.

## Features Implemented
- [x] Genre selection (Horror, Romance, Fantasy, SciFi)
- [x] Save / export stories
- [x] Length Selection (Short, Medium, Long)
- [x] Complexity selection (Child friendly, Average, Difficult)

## Design Patterns Used
1. **Strategy Pattern** - Multiple story generation strategies. 
   - Horror, Romance, Fantasy, SciFi
2. **Factory Pattern** - Centralizes the creation of StoryStrategy objects based on the user's selected genre (e.g., automatically creates a FantasyStrategy instance when 'Fantasy' is chosen).
3. **Observer Pattern** - Ui updates when story and/ or model changes
3. **Singleton Pattern** - Configuration manager or API rate limiter
## Setup Instructions

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

## Usage Guide
Step-by-step instructions with screenshots.

## API Usage & Costs
- Model used: gpt-3.5-turbo
- Estimated cost per request: $0.002
- Cost management strategies
  - Using a Low-Cost Model
  - Setting max_tokens Limit
  - Singleton Pattern for API Client
  - Asynchronous Execution

## Known Issues
- The use of the API Client and connecting to the controller : Code Has been Fixed 
- Silent Failure in Terminal: Rerun the program and make sure the API Key is correct

## Future Enhancements
- Implementation of Images
- More Customization to the Story
  - Style
  - Setting
  - Tags
