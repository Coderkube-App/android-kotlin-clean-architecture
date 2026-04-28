# Android Clean Architecture Project

A modern Android application built using Kotlin and Clean Architecture principles. This project demonstrates a scalable and maintainable structure using MVVM, Hilt (Dependency Injection), and REST API integration.

---

## Tech Stack

* Language: Kotlin
* UI: XML (Activities / Fragments)
* Architecture: Clean Architecture + MVVM
* Dependency Injection: Hilt (Dagger)
* Networking: Retrofit + OkHttp
* Asynchronous: Kotlin Coroutines + Flow
* State Management: StateFlow / LiveData

---

## Project Structure

```id="proj-structure-xml"
app/src/main/java/com/project/

├── core/                  
├── data/                  
├── domain/                
├── presentation/          
├── di/                    
└── MainActivity.kt
```

---

## Layer Breakdown

### Core Layer

Contains reusable components used across the app:

* Network configuration (Retrofit, interceptors)
* DataStore / Preferences
* Utility classes and extensions
* Constants

---

### Data Layer

Responsible for handling all data operations:

* API calls (remote)
* DTO models
* Repository implementations
* Data mapping (DTO to Domain)

---

### Domain Layer

Pure business logic with no Android dependencies:

* Domain models (entities)
* Repository interfaces
* UseCases (application logic)

---

### Presentation Layer

Handles UI and state:

* Activities / Fragments (XML-based UI)
* ViewModels (MVVM)
* UI state management using StateFlow / LiveData
* Reusable UI components

---

### Dependency Injection

Provides dependencies using Hilt:

* NetworkModule
* RepositoryModule
* UseCaseModule

---

## Data Flow

```id="data-flow-xml"
UI (Activity / Fragment)
   ↓
ViewModel
   ↓
UseCase
   ↓
Repository (Domain Interface)
   ↓
Repository Implementation (Data Layer)
   ↓
Remote API
```

---

## Features

* Clean Architecture implementation
* MVVM pattern with StateFlow / LiveData
* REST API integration
* Repository pattern
* Dependency Injection using Hilt
* Scalable and maintainable structure
* Structured error handling

---

## Setup Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-repo.git
   ```

2. Open the project in Android Studio

3. Sync Gradle

4. Run the application on an emulator or physical device

---

## Best Practices

* Follows SOLID principles
* Clear separation of concerns
* Unidirectional data flow
* Immutable UI state
* Clean and readable code structure

---

## Scalability

This project follows a single-module clean architecture approach.
It can be extended into a multi-module architecture for larger applications:

```id="multi-module-xml"
:app
:core
:data
:domain
:feature-*
```

---

## Contributing

Contributions are welcome. You can fork the repository and submit a pull request.

---

## License

This project is licensed under the Apache License 2.0.
