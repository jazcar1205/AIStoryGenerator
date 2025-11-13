# AI Story Generator

## Team Members
- Jazmin Carlos 
- Cedar Hudgens 

## Project Description
The Interactive Storytelling Application uses AI to create stories based on what the user writes or chooses. Users can pick a genre, make characters, and shape the world of their story. The app can build different types of stories and let users save or export them.

## Features Implemented
- [x] Feature 1
- [x] Feature 2
- [ ] Extra credit feature

## Design Patterns Used
1. **Strategy Pattern** - Mutiple story generation strategies. 
   - Horror, Romance, Fantasy, SciFi
2. **Factory Pattern** - Create different API clients
3. **Observer Pattern** - Ui updates when story and/ or model changes
3. **Singleton Pattern** - Configuration manager or API rate limiter
## Setup Instructions

### Prerequisites
- Java 11 or higher
- OpenAI API key

### Installation
1. Clone repository
2. Copy `config.properties.example` to `config.properties`
3. Add your OpenAI API key to `config.properties`
4. Run `Main.java`

### Dependencies
- org.json (version)
- JUnit 4.13

## Usage Guide
Step-by-step instructions with screenshots.

## API Usage & Costs
- Model used: gpt-3.5-turbo
- Estimated cost per request: $0.002
- Cost management strategies

## Known Issues
- Issue 1 and workaround
- Issue 2 and workaround

## Future Enhancements
- Feature we'd add with more time
