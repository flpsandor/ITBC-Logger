# ITBC-Logger
The final product for IT Bootcamp. Implementation of simple logger Rest API with Springboot, JPA, H2 database. 

Business Requirements, MVP (Minimum Viable Product)
## User
- The user can register
username, email, password
- The user can retrieve his "key"
Using username, email, and password
- The user can enter the log
message
log type (ERROR, WARNING,INFO)
created date
- User can search logs

## Admin
- There is an admin account (or admin key)
- Admin can see all users
- Admin can see the number of logs for each user
- The admin can change the user's password at his request

## Endpoints

1. Register
- HTTP Method: 'POST'
- Enpoint URL: '/api/clients/register'
- Request body:
```json
{
  "username":"string",
  "password":"string",
  "email":"string"
}
```
###Responses:
- 201 - Registered
- 400 - Bad Request
  - email must be valid
  - username at least 3 characters
  - password at least 8 characters and one letter and one number and one special
- 409 - Conflict
  - username already exist
  - email already exist



