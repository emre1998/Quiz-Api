## QuizAPI - Spring Boot REST API for Quizzes

This project provides a Spring Boot RESTful API for managing quizzes, questions, users, scores, and authentication.

### Features

* Users can register with username, email, and password.
* Users can log in to receive an authentication token.
* The API allows retrieving all questions for a specific test identified by its ID.
* Users can submit their answers for a test and receive their score.
* Users can retrieve their score for a specific test.

### Technologies

* Spring Boot
* Spring Data JPA (Optional - for persistence)
* JWT (Optional - for authentication)

### API Documentation

The API uses JSON for request and response bodies.

#### Users

* **Register:**
    * **Method:** POST
    * **URL:** `/user/register`
    * **Request Body:**
        ```json
        {
          "username": "string",
          "email": "string",
          "password": "string"
        }
        ```
    * **Response:**
        * **Success:** 201 Created - "Kullanıcı kaydı başarılı!"
        * **Error:** 400 Bad Request - Error message (e.g., username already exists)
* **Login:**
    * **Method:** POST
    * **URL:** `/user/login`
    * **Request Body:**
        ```json
        {
          "username": "string",
          "password": "string"
        }
        ```
    * **Response:**
        * **Success:** 200 OK - "Bearer {token}" (replace `{token}` with the actual JWT token)
        * **Error:** 401 Unauthorized - "Kullanıcı girişi başarısız!" with error message

#### Tests

* **Get Questions by Test ID:**
    * **Method:** GET
    * **URL:** `/test/{testId}`
    * **Path Variable:**
        * `{testId}`: Long - The ID of the test
    * **Authorization:** Bearer token (required)
    * **Response:**
        * **Success:** 200 OK - List of `Question` objects (refer to Model section)
        * **Error:** 400 Bad Request - If no questions found for the test ID

#### Scores

* **Submit Answers and Get Score:**
    * **Method:** POST
    * **URL:** `/score/submit-answer`
    * **Headers:**
        * `Authorization: Bearer {token}` (required)
    * **Request Body:**
        ```json
        {
          "questionId" :"Long",
          "answerText" :"string"
        }
        ```
    * **Response:**
        * **Success:** 200 OK - Integer representing the score
* **Get Score by User ID and Test ID:**
    * **Method:** GET
    * **URL:** `/score`
    * **Query Parameters:**
        * `userId`: Long - The ID of the user
        * `testId`: Long - The ID of the test
    * **Authorization:** Bearer token (required)
    * **Response:**
        * **Success:** 200 OK - Integer representing the score
        * **Error:** 404 Not Found - If no score found for the user and test combination

### Models (Optional)

The API might utilize models (POJOs) to represent data entities. Here's an example structure (replace with your actual models):

* **Question:**
    * id (Long)
    * text (String)
    * ... (other question attributes)
* **User:**
    * id (Long)
    * username (String)
    * email (String)
    * password (String) (hashed and stored securely)
* **SubmitAnswerRequestDTO:** (if implemented)
    * testId (Long)
    * answers (List<Answer>) - List of answer objects

**Note:** This is a basic example. You might need to adjust the models based on your specific implementation.

### Dependencies (Optional)

The actual dependencies used in the project will depend on your chosen technology stack. Here are some potential dependencies:

* Spring Web
* Spring Security (for JWT)
* Spring Data JPA (for database persistence)
* Lombok (for boilerplate code reduction)

### Getting Started (Optional)

1. Clone this repository.
2. Install the required dependencies (refer to pom.xml or build instructions).
3. Configure your database connection details (if using persistence).
4. Run the application (refer to Spring Boot documentation).



