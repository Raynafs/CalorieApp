# the calorie app 🔥🔨

This project is an Android app that enables users to check the nutritional value of various foods. 
Checking things like calories, protein, fat level etc.

## Dependencies

1. Jetpack Compose
2. Coroutines - For Concurrency and Asynchronous tasks
3. Ktor - For network requests
4. Hilt - For Dependency Injection
5. Room
6. Lint Checks 

## Architecture

The proposed architecture consists of 3 main modules;

### Data
1. Network Calls
2. Caching
3. The relevant data models


### Domain
1. The repository
2. Repository implementation
3. The relevant domain models.
4. Relevant Mappers

### Ui
1. View
2. ViewModels
3. Design system

Below is a picture describing the modularisation technique

![Modularisation](https://github.com/Raynafs/CalorieApp/assets/110402503/1e5d3f35-b92d-404e-a7eb-b560ab294640)


## Illustration


With internet

https://github.com/Raynafs/CalorieApp/assets/110402503/eb349676-f383-4f54-8c82-5de9b5cff91f





With No internet and no items found

https://github.com/Raynafs/CalorieApp/assets/110402503/4dade1ca-c39e-445a-8309-cd483e36c414




