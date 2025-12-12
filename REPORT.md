# Project Report

## Time Spent
For full completion of this project (from assignment #3 to this one) we have 
probably spent about 60 hours or so. 

## Design Decisions

### Architecture Overview
We used an MVC structure to keep the model, UI, and controller logic separate and clean. 
Design patterns like Strategy, Factory, Observer, and Singleton helped make the system flexible and easy to maintain.

### API Integration Strategy
All API calls run asynchronously so the UI never freezes, 
and we handle errors and rate limits using retries and centralized error handling.

### Design Pattern Justifications
**Strategy**: We needed different AI behaviors according to what genre the story should
be created with. 

**Factory**: allow easy switching between story genres, 

**Observer**: the UI is an observer of the story model, so each time
the model's story changes, the UI changes accordingly. While there is a manual setup
for this as well, the UI will always update.

**Singleton**: This is implemeneted in the API Client and service to ensure only one API service
instance manages all requests.

## OOP Four Pillars
### Encapsulation
  All of or classes have private instance variables that are only accessible an mutable by methods we determined. 
For example, the story model has all private fields that can only be set through the correct methods.

### Abstraction
  For our strategies, we created an abstract StoryStrategy class. From this class, all other strategies can be created
with the specified methods included. 

### Polymorphisim
  Also with our strategies, we are able to easily switch through them in the controller. The controller has a StoryStrategy
object that can be set to any one of the strategy classes. The rest of the code does not necessarily need to know which one 
was chosen, as all strategy classes have generateStory(). 

### Inheritance
Our model extends the class Observer. This gives it access to all the methods related to observer, including addObservers() and
notifyAll(). We are able to call these functions without needing to write them ourselves. 

## Challenges Faced

### Challenge 1: API Rate Limiting
**Problem:** We hit rate limits during testing.  

**Solution:** We added retry logic and controlled request timing. Additionally, 
we were able to upgrade API tiers in order to make sure we wouldn't run into this issue in the future.

**Learned:** This project very effectively taught us how to manage a live API. Interacting with the usage
console and creating and testing various API keys was helpful in simulating a real 
application.

### Challenge 2: Mockito Dependencies
**Problem:** Mockito was used in this project for creating a mock OpenAIService object.
Unfortunately, we often hit issues with the dependencies. While the dependency is included in the
pom.xml file, the import statements would still throw errors. Additionally, when we did get them to load, 
the mock of generateText() threw an additional error indicating that additional configuration information
needed to be added. 

**Solution:** To fix the Mockito import statements, invalidating the IntelliJ cache generally helped fix it. 
Unfortunately, this would need to be done every time a new import statement was needed. Also, for the configuration
error we were able to add the suggested change to pom.xml. However, searching online states that this doesn't
actually resolve the issue still. Ultimately, we ended up leaving it as is. The configuration change was not an error,
but rather just a notification. The mocked classes will still work.  

**Learned**: We were able to learn a lot more about how to troubleshoot dependency errors and how
to setup the pom.xml file with additional information.

## AI Usage
### API Usage & Costs
- Model used: gpt-3.5-turbo
- Estimated cost per request: $0.002
- Cost management strategies
    - Using a Low-Cost Model
    - Setting max_tokens Limit
    - Singleton Pattern for API Client
    - Asynchronous Execution
  
### AI/LLM Usage Disclosure
Gemini was used for general debugging help. However, no code was ever copied directly.
To offer some specific cases it was used for: debugging asyncronous calls to the service
to update the GUI and assistance with troubleshooting GUI layout. However, this was largely
to understand what was wrong so that we could review the appropriate documenation directly.

## Future Enhancements
- Implementation of Images
- Tagging options

