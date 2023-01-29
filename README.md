## Todo API

---
### Build

-  clone from GitHub by running 'git clone https://github.com/MichaelBoyo/todoApi'
- locate the  folder in your terminal
- ensure you have maven and java 18 sdk or above  installed
- run 'mvn package' in your terminal or command line to build the project 
---

### Run
- run  'java -jar target/todoApi-0.0.1-SNAPSHOT.jar' in your terminal or command line to start the application
- the application will be started on http://localhost:8080

### Test
- visit http://localhost:8080 on your browser, and you should get "Welcome to TodoApi"
- to get data on all todos visit http://localhost:8080/api/v1/todo (ascending order by default based on date and time created)
- make a POST request to http://localhost:8080/api/v1/todo to add a new todo. required request body 
{ "title": "",
  "body": "" }
- make a GET request to http://localhost:8080/api/v1/todo?sort=desc to get todos in descending order.
  required request body
  { "droneId": 1}
- make a DELETE request to http://localhost:8080/api/v1/todo/{id} to delete a todo.
- make a PUT request to http://localhost:8080/api/v1/todo/{id} to update todo.
  required request body
  { "title": "",
  "body": "" }

