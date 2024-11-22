## Hello

- this is my documentation on the implementation of "The Backpack Problem"
- Initially, I solved this problem in one class and I used HashMap for holding the initial data and the results
- However, to showcase my familiarity with other technologies, I used Spring Boot, JPA ORM SQL, Spring REST and MVC, JUNIT

## Goals
- My main goals have been: correctness, efficiency, design, and clarity
- Also, I hope I made it easy for next developers to easily maintain this code
- Correct Java package organization can make the program a lot more readable
- Unit tests not only prove the correctness of the algorithm, it can also be a great documentation on how the program works
- I used Sonar to make sure I fixed issues as they came up
- Finally, I followed these other standard Java practices:
-
- Standard project structure,
- Readability over Reusability
- Standard Code Formatting
- Strict the number of parameters
- Avoid hard coding values

## Assumptions
- I am assuming that the initial database has an item that weighs one. This way, all the given weights will always be used

## How to run
- You have to have docker environment running on your machine. In my Windows environment, I ran Docker Desktop before I ran my program.
- I needed Docker because I am using Mysql Database to store my initial data
- Spring Boot automatically created compose.yaml which made sure that Mysql Database was running
- In any IDE or command line, you have to run the main Java method in BackPackApplication class
- Run the GUI application by running http://localhost:7000/
- I used Postman to test the REST endpoints