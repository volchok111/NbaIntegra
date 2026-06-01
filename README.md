# NBA Integra

NBA Integra is a small Android app built with Kotlin and Jetpack Compose.

The app shows a list of NBA players with basic information such as first name, last name, position, and team. When the user scrolls to the end of the list, the app loads the next page of players. After selecting a player, the user can open a detail screen with more information. From the player detail screen, it is also possible to open the team detail screen.

The data is loaded from the [balldontlie API](https://app.balldontlie.io/).

## Tech stack

* Kotlin
* Jetpack Compose
* MVVM
* Clean Architecture approach
* Retrofit
* OkHttp
* Koin
* Paging 3
* Glide
* ktlint

## Project structure

The project is split into several modules:

* `app` — UI, navigation, ViewModels, application setup
* `domain` — domain models, repository interfaces, use cases
* `data` — API models, Retrofit API, repository implementations
* `di` — Koin modules for dependency injection

## API key setup

This project uses the `balldontlie` API, so an API key is required.

To run the app locally:

1. Create an account and get an API key from https://app.balldontlie.io/
2. Create a `local.properties` file in the root of the project
3. Add your API key:

```properties
BALLDONTLIE_API_KEY=your_api_key_here
```

**The real API key is not committed to the repository for security reasons and to demonstrate best practises for test task!!!**

You can also check `local.properties.example` to see the required property name.

## How to run

1. Clone the repository
2. Open the project in Android Studio
3. Add your API key to `local.properties`
4. Sync Gradle
5. Run the app on an emulator or a real device

## Code style

The project uses ktlint for code style checks.

Run check:

```bash
./gradlew ktlintCheck
```

Run auto-format:

```bash
./gradlew ktlintFormat
```

## Notes

This project was created as a test task. The main focus is on clean project structure, readable code, simple navigation, paginated loading, and correct API integration.
