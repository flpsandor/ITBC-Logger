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
  Responses:
    - 201 - Registered
    - 400 - Bad Request
      - email must be valid
      - username at least 3 characters
      - password at least 8 characters and one letter and one number and one special
  - 409 - Conflict
      - username already exist
      - email already exist

2. Login
  - HTTP Method: 'POST'
  - Endpoint URL: '/api/clients/login'
  - Request body:
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```
    Responses:
      - 200 - OK
      ```json
      {
          "token":"string"
       }
      ```
      - 400 - Bad Request
        -username or password incorect

3. Create log
  - HTTP Method: 'POST'
  - Endpoint URL: '/api/logs/create'
  - Request body:
    ```json
    {
      "message":"string",
      "logType": 0
    }
    ```
  - Request headers:
     - 'Authorization' - token
  - Responses:
     - 201 - Created
     - 400 - Bad Request
       -Incorrect logType
     - 401 - Unauthorization
        -Incorrect token
     - 413 - Payload too large
        -Message should be less than 1024 character
4. Search Log
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/logs/search'
  - Request params:
    - 'dateFrom'
    - 'dateTo'
    - 'message'
    - 'logType'
  - Request headers:
    - 'Authorization' - token
  - Responses:
    - 200 - OK
      ```json
      [  
        {
          "message":"string",
          "logType":0,
          "createdDate":"date"
        }
      ]  
      ```
    - 400 - Bad request
      -Invalid dates
      -Invalid logType
    - 401 - Unauthorized
      -Incorrect token
5. Get all clients
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/clients'
  - Request headers:
    - 'Authorization' - token (Admin token)
  - Responses:
    - 200 - OK
      ```json
      [
        {
          "id":"uuid",
          "username":"string",
          "email":"string",
          "logCount":0
        }
      ]
      ```
   - 401 - Unauthorized
    - Incorrect token
   - 403 - Frobidden
     - Correct token, but not admin

6. Change client password
  - HTTP Methdo: 'PATCH'
  - Endpoint URL: '/api/clients/{id}/reset-password'
  - Request body:
    ```json
      {
        "password":"string"
      }
    ```
   - Request headers:
     -'Authorization' - token (Admin token)
   - Responses:
     - 204 - No content      
     - 401 - Unauthorized
      -Correct token, but not admin
     - 403 - Forbidden
      -Incorrect token

