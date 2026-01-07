# Carousell News

An Android application that displays a list of news articles, built using modern Android development practices.

## 📸 Screenshot

<img width="360" height="800" alt="image" src="https://github.com/user-attachments/assets/c63d8d82-9dbb-45eb-ab03-a0b31170e540" />

## ✨ Features

*   Fetches news articles from a remote API.
*   Displays articles in a vertically scrollable list.
*   Sort articles by **Recent** or **Popular**.
*   Handles loading, error, and empty states.
*   Follows Clean Architecture principles with MVVM.

## 🛠️ Built With

*   [Kotlin](https://kotlinlang.org/) - First-class and official programming language for Android development.
*   [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for building native UI.
*   [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous programming.
*   [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - For reactive state management.
*   [Hilt](https://dagger.dev/hilt/) - For dependency injection.
*   [Retrofit](https://square.github.io/retrofit/) - For networking.
*   [Coil](https://coil-kt.github.io/coil/) - For image loading.
*   [Material Components](https://material.io/develop/android/docs/getting-started) - For UI components.

## 🏛️ Architecture

The app follows the principles of **Clean Architecture** with a presentation layer, a domain layer, and a data layer.

*   **Presentation Layer**: The UI of the app, built with Jetpack Compose. The `NewsViewModel` provides the UI state to the `NewsListScreen` and handles user events.
*   **Domain Layer**: Contains the business logic of the app. It includes the `News` data model, the `NewsRepository` interface, and the `GetNews` and `SortNews` use cases.
*   **Data Layer**: Responsible for providing data to the domain layer. It includes the `NewsRepositoryImpl`, which fetches data from the `NewApi` (using Retrofit) and maps the DTOs to the domain model.

## 🚀 Setup

To build and run the project, you'll need to:

1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Let Gradle sync the dependencies.
4.  Run the app on an emulator or a physical device.
