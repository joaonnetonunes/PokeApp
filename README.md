# PokeAPP

This Android project is a simple application fro fetching the pokeapi, using kotlin. It utilizes Kotlin Coroutines for asynchronous programming, Kotlin StateFlow for managing and propagating UI state changes, Hilt for dependency injection, and Retrofit for handling network requests.

## Architecture Overview

The MVI architecture pattern is a derivative of the Model-View-Controller (MVC) pattern. It aims to provide a clear separation of concerns by dividing the UI logic into three main components:

- **Model**: Represents the data and business logic of the application.
- **View**: Renders the UI and sends user events to the ViewModel.
- **Intent/ViewModel**: Receives user intents from the View, processes them along with the current state from the Model, and emits new state updates back to the View.

### Components

1. **Model**: Contains the data and business logic of the application. This includes data sources, repositories, and domain logic.
   
2. **View**: Renders the UI and listens for user interactions. It forwards user actions to the ViewModel and updates its state based on the emitted states.

3. **Intent/ViewModel**: Acts as an intermediary between the View and the Model. It receives user intents from the View, interacts with the Model to process those intents, and emits new state updates back to the View.

## Technologies Used

- **Kotlin**: Primary programming language for the project.
- **Kotlin Coroutines**: Used for asynchronous programming, simplifying code that executes asynchronously.
- **Kotlin StateFlow**: Manages and propagates UI state changes efficiently.
- **Hilt**: Provides a standard way to incorporate dependency injection into Android apps.
- **Retrofit**: A type-safe HTTP client for Android and Java.
