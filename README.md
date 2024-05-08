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

### AuthenticationCommandController
- POST /api/v1/auth/change-password: Change a user's password
- POST /api/v1/auth/logout: Logout a user
- POST /api/v1/auth/email-verification: Verify a user's email
- POST /api/v1/auth/send-forgot-password-email: Send a forgot password email to a user
- POST /api/v1/auth/forgot-password: Change a user's password using a forgot password request

### OAuth2Controller
- GET /oauth2/callback: Handle OAuth2 callback

### AuthenticationQueryController
- POST /api/v1/auth/login: Login a user
- POST /api/v1/auth/register: Register a new user
- POST /api/v1/auth/role-check: Check a user's role
- POST /api/v1/auth/validate-token: Validate a user's token
- POST /api/v1/auth/refresh-token: Refresh a user's token
- POST /api/v1/auth/resend-email-verification: Resend email verification to a user
- GET /api/v1/auth/generate-google-login-uri: Generate a Google login URI for a user
## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [FusionAuth](https://fusionauth.io/) - Used for user authentication and authorization
  
## License

This project is licensed under the Apache License - see the [LICENSE.txt](https://github.com/BurakKontas/manga-auth/blob/main/LICENSE.txt) file for details
