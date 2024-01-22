# To-do API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

This application offers in a url (by default set as http://localhost:8080) data from a <a href=https://github.com/kdukoelho/todo-application-frontend>To-do Application</a>. Its includes users and tasks data. 

## Frameworks
The application uses the following frameworks:

- Spring Boot;
- Spring Boot Security;
- Java Persistence API (JPA);
- JSON Web Token (JWT);
- Lombok.

## Setting up
First, you need to install MySQL and open a connection at port 3306 on your localhost, if everything works fines, the application will create a database called "todo-application".

1. Clone this repository on your machine:
```bash
git clone https://github.com/kdukoelho/todo-application-api
```
2. Install the project dependencies:
```bash
mvn clean install
```
3. Run the application:
```bash
mvn spring-boot:run
```

## Authentication
To use the application it is necessary to log in to the session, by default, the application already has a admin account that has login and password set as "admin". 

We recommend that, when initializing the application, immediately change the password of the admin user.  Creating new users is only possible from an account with administrator privileges, so use the admin account to create your account, and then log out and use the new account. 

When the authentication completes, the application will return a bearer token, which will be responsible for authenticating in the others endpoints

### Roles
The application has two levels of privilege:

1. ADMIN: Permission to create, read, update and delete ANY data from database (tasks and users) .
2. USER: Permission to create, read, update, and delete ONLY your tasks and account.

## End-Points
The application's endpoints will be presented below, as well as, if necessary, the respective structure of their bodies. In URL's the elements between the braces is non-static variables. All the response data is send in a JSON format, and the request data, should be sent as JSON.

### Authentication
- Endpoint: SignIn
URL: http://localhost:8080/auth/login
Method: POST
Body: 
<code>{"username":  "admin",
"password":  "admin"}</code>

- Endpoint: SignUp
URL: http://localhost:8080/auth/register
Method: POST
Body:
<code>{"username":  "kdukoelho",
"password":  "123456789",
"role":  "USER"}</code>

### Users
- Endpoint: FindUsers
URL: http://localhost:8080/user
Method: GET
Response-Body:
<pre><code>[
	{
		"id":  "9ab911ff-7fc5-4f7b-9dfb-a28b01d351d7,
		"username":  "robertoKarlos",
		"passwordHash":  "$2a$10$vxSPCipS4QhZkgggNcaZsen4bpAsMAQk.POxGtJVH7nsKesm4yWr6"},
	{
		"id":  "d77a1c50-11a6-49e2-8e78-80e65ba85ce9",
		"username":  "kdukoelho",
		"passwordHash":  "$2a$10$wixQDbU7KdPHMxzr2gNhlOkpNc61YcsGUnAEalfy/pq/ApKBmitem"
	}
]</code></pre>

- Endpoint: FindUserById
URL: http://localhost:8080/user/{user_id}
Method: GET
Response-Body: 
<pre><code>{
	"id":  "d77a1c50-11a6-49e2-8e78-80e65ba85ce9",
	"username":  "kdukoelho",
	"passwordHash":  "$2a$10$wixQDbU7KdPHMxzr2gNhlOkpNc61YcsGUnAEalfy/pq/ApKBmitem"
}</code></pre>

- Endpoint: UpdateUser
URL: http://localhost:8080/user/{user_id}
Method: PUT
Body:
<pre><code>{
	"password": "new_password"
}</code></pre>

- Endpoint: DeleteUser
URL: http://localhost:8080/user/{user_id}
Method: DELETE
Body: -

### Tasks
- Endpoint: FindTasks
URL: http://localhost:8080/task
Method: GET
Response-Body:
<pre><code>[
	{
		"id":  "1",
		"name":  "Take garbage out",
		"description":  "",
		"state":  "DOING",
		"priority":  "MEDIUM"
	},
	{
		"id":  "2",
		"name":  "Clean house",
		"description":  "We need to clean the rooms and the kitchen",
		"state":  "TODO",
		"priority":  "LOW"
	}
]</code></pre>

- Endpoint: FindTaskById
URL: http://localhost:8080/task/{task_id}
Method: GET
Response-Body: 
<pre><code>{
	"id":  "2",
	"name":  "Clean house",
	"description":  "We need to clean the rooms and the kitchen",
	"state":  "TODO",
	"priority":  "LOW"
}</code></pre>

- Endpoint: FindTasksByUserId
URL: localhost:8080/task/user/{user_id}
Method: GET
Response-Body:
<pre><code>[
	{
		"id":  "2",
		"name":  "Clean house",
		"description":  "We need to clean the rooms and the kitchen",
		"state":  "TODO",
		"priority":  "LOW"
	},
	{
		"id":  "3",
		"name":  "Finish biology exercise",
		"description":  "Ecologic relationships exercise",
		"state":  "DONE",
		"priority":  "HIGH"
	}
]
</pre></code>

- Endpoint: DeleteTask
URL: http://localhost:8080/{user_id}/{task_id}
Method: DELETE
Body: -

