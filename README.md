Voting API - Usage Guide and Features
Overview
The Voting API allows users to create candidates, cast votes, view vote counts, list all votes, and determine the candidate with the most votes. The API supports multiple concurrent users and is built using Spring Boot without a database, using local memory to store data.

Features Implemented
Enter a Candidate: Add a candidate with an initial vote count of 0.
Cast a Vote: Increment the vote count for a specific candidate.
Count Votes: Retrieve the current vote count for a specific candidate.
List All Votes: Display all candidates and their respective vote counts.
Get the Winner: Identify the candidate with the highest number of votes.
Concurrent Execution: The API supports multiple users voting simultaneously.
Input Validation: Ensures that votes are only cast for valid candidates.
Error Handling: Returns appropriate error messages for invalid inputs or actions.
Unit Testing: Includes unit tests for each endpoint to ensure correct functionality.
Getting Started
Requirements
Java 11 or higher
Maven
Postman (for testing API)
How to Run the Application
Clone the Repository:

bash
Copy code
git clone https://github.com/your-repository/voting-api.git
Navigate to the Project Directory:

bash
Copy code
cd voting-api
Build the Project:

Copy code
mvn clean install
Run the Spring Boot Application:

arduino
Copy code
mvn spring-boot:run
The application will start at http://localhost:8080.

API Endpoints
Enter a Candidate

URL: POST /api/entercandidate
Query Parameter: name (candidate's name)
Example: http://localhost:8080/api/entercandidate?name=ajay
Response: Candidate ajay added successfully.
Cast a Vote

URL: POST /api/castvote
Query Parameter: name (candidate's name)
Example: http://localhost:8080/api/castvote?name=ajay
Response: Vote cast for ajay. Current vote count: 1
Count Votes

URL: GET /api/countvote
Query Parameter: name (candidate's name)
Example: http://localhost:8080/api/countvote?name=ajay
Response: ajay has 1 votes.
List All Votes

URL: GET /api/listvote
Example: http://localhost:8080/api/listvote
Response:
json
Copy code
{
  "ajay": 1,
  "priya": 2
}
Get the Winner

URL: GET /api/getwinner
Example: http://localhost:8080/api/getwinner
Response: The winner is ajay
Testing the API
Use Postman to send HTTP requests to the API. Make sure your Spring Boot application is running before testing the endpoints.
