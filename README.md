<h1 align="center"> To-do API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
</h1>

<p align="center"> This application offers in an url (by default set as http://localhost:8080) data from a <a href=https://github.com/kdukoelho/todo-application-frontend>To-do Application</a>. Its includes users and tasks data. This project follows the architecture of REST API's.
</p>

##  💻  Technologies
The application uses the following frameworks:

- Spring Boot;
- Spring Boot Security;
- Java Persistence API (JPA);
- JSON Web Token (JWT);
- Lombok.

## 🚀 Setting up
### Prerequisites
1. You need to install MySQL and open a connection at port 3306 on your localhost, if everything works fines, the application will create a database called "todo-application";
2. Maven as path variable, or you can run the application directly on IDE.

### Cloning
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
To use the application it is necessary to log in to the session, by default, the application already has an admin account that has login and password set as "admin". 

We recommend that, when initializing the application, immediately change the password of the admin user.  Creating new users is only possible from an account with administrator privileges, so use the admin account to create your account, and then log out and use the new account. 

When the authentication completes, the application will return a bearer token, which will be responsible for authenticating in the others endpoints

### Roles
The application has two levels of privilege:

1. ADMIN: Permission to create, read, update and delete ANY data from database (tasks and users) .
2. USER: Permission to create, read, update, and delete ONLY your tasks and account.

## API Endpoints
The application's endpoints will be presented below, as well as, if necessary, the respective structure of their bodies. All the response data is send in a JSON format, and the request data, should be sent as JSON.

### Authentication
| Route                          | Description                             |
|--------------------------------|-----------------------------------------|
| <kbd>POST /auth/login</kbd>    | Authenticate user and retrieves a token |
| <kbd>POST /auth/register</kbd> | Register a user (roles: USER & ADMIN)   |

#### POST /auth/login

##### REQUEST
```json
{
	"username":  "admin",
	"password":  "admin"
}
```
##### RESPONSE
```json
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0b2RvLWFwaSIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzA1OTQ3OTk4fQ.z3OQXYcsV4_Z_7NmOwJWuZVS-U2pRJAd25tBynH3bXU"
}
```

#### POST /auth/register

##### REQUEST
```json
{
	"username": "kdukoelho",
	"password": "123456789",
	"role": "USER"
}
```

### Users
| Route                        | Description                                                                          |
|------------------------------|--------------------------------------------------------------------------------------|
| <kbd>GET /user</kbd>         | Retrieves all users information                                                      |
| <kbd>GET /user/{id}</kbd>    | Retrieves information about the user that have the specified id                      |
| <kbd>PUT /user/{id}</kbd>    | Update the password (unique non-unique field) of the user that have the specified id |
| <kbd>DELETE /user/{id}</kdb> | Delete the user that has the specified id                                            |

#### GET /user 

##### RESPONSE 
```json
[
	{
		"id":  "9ab911ff-7fc5-4f7b-9dfb-a28b01d351d7",
		"username":  "robertoKarlos",
		"passwordHash":  "$2a$10$vxSPCipS4QhZkgggNcaZsen4bpAsMAQk.POxGtJVH7nsKesm4yWr6"},
	{
		"id":  "d77a1c50-11a6-49e2-8e78-80e65ba85ce9",
		"username":  "kdukoelho",
		"passwordHash":  "$2a$10$wixQDbU7KdPHMxzr2gNhlOkpNc61YcsGUnAEalfy/pq/ApKBmitem"
	}
]
```

#### GET /user/{user_id}
##### RESPONSE
```json
{
	"id":  "d77a1c50-11a6-49e2-8e78-80e65ba85ce9",
	"username":  "kdukoelho",
	"passwordHash":  "$2a$10$wixQDbU7KdPHMxzr2gNhlOkpNc61YcsGUnAEalfy/pq/ApKBmitem"
}
```

#### PUT /user/{user_id}
##### REQUEST
```json
{
    "password": "new_password",
    "role": "USER"
}
```


### Tasks
| Route                               | Description                                                                 |
|-------------------------------------|-----------------------------------------------------------------------------|
| <kbd>GET /task</kbd>                | Retrieves all tasks information                                             |
| <kbd>GET /task/{task_id}</kbd>      | Retrieves information about the task that have the specified id             |
| <kbd>GET /task/user/{user_id}</kbd> | Retrieves all tasks information that are related with the specified user id |
| <kbd>POST /task</kbd>               | Create a new task                                                           |
| <kbd>DELETE /task/{task_id}</kbd>   | Deletes the task that has the specified id                                  |

#### GET /task
##### RESPONSE
```json
[
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
]
```
#### GET /task/{task_id}
##### RESPONSE
```json
{
	"id":  "2",
	"name":  "Clean house",
	"description":  "We need to clean the rooms and the kitchen",
	"state":  "TODO",
	"priority":  "LOW"
}
```
#### GET /task/user/{user_id}
##### RESPONSE
```json
[
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
```

#### POST /task
##### REQUEST
```json
{
	"name":  "Math Exercises",
	"description":  "Differential calculus exercises",
	"user_id":  "2",
	"state":  "DOING",
	"priority":  "HIGH"
}
```

