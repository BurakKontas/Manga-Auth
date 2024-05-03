# Manga Auth API

Manga Auth API is a Spring Boot application that provides authentication and authorization services for Manga application.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- Gradle
- IntelliJ IDEA 2024.1.1 or any other IDE with similar capabilities

### Installing

1. Clone the repository
```bash
git clone https://github.com/BurakKontas/manga_auth.git
```
2. Navigate into the project directory
```bash
cd manga_auth
```
3. Install the dependencies
```bash
./gradlew build
```
4. Run the application
```bash
./gradlew bootRun
```

Please replace the placeholders with the actual information about your project.
## Usage

The API provides the following endpoints:

- POST /v1/auth/login: Login a user
- POST /v1/auth/register-user: Register a new user
- PUT /v1/auth/change-password: Change a user's password

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [FusionAuth](https://fusionauth.io/) - Used for user authentication and authorization

## Contributing

Please read [CONTRIBUTING.md](https://github.com/BurakKontas/manga_auth/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the Apache License - see the [LICENSE.md](https://github.com/BurakKontas/manga_auth/LICENSE.txt) file for details
