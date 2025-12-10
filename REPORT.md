# Project Report

## Design Decisions

### Architecture Overview
We used an MVC structure to keep the model, UI, and controller logic separate and clean. Design patterns like Strategy, Factory, Observer, and Singleton helped make the system flexible and easy to maintain.

### API Integration Strategy
All API calls run asynchronously so the UI never freezes, and we handle errors and rate limits using retries and centralized error handling.

### Design Pattern Justifications
Strategy and Factory allow easy switching between story genres, Observer keeps the UI updated automatically, and Singleton ensures only one API service instance manages all requests.

## Challenges Faced

### Challenge 1: API Rate Limiting
**Problem:** We hit rate limits during testing.
**Solution:** We added retry logic and controlled request timing.
**Learning:** ...

### Challenge 2: Error Handling
**Problem:** Unexpected API failures caused crashes.
**Solution:** we built a unified error handler to manage and display errors safely.

## Testing Strategy
Describe your testing approach and coverage.

## API Usage & Costs
- Model used: gpt-3.5-turbo
- Estimated cost per request: $0.002
- Cost management strategies
    - Using a Low-Cost Model
    - Setting max_tokens Limit
    - Singleton Pattern for API Client
    - Asynchronous Execution
  
## AI/LLM Usage Disclosure
**Did you use AI tools (ChatGPT, Copilot, etc.)?**
- [] Yes - Describe how and what
- [x] No

If yes, explain:
- What AI tools you used
- What you asked them to do
- How you verified/modified the output
- What you learned from the process

## What We Learned
- OOP concept reinforced
- API integration insights
- Design pattern understanding

