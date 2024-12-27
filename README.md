## below are the api and its curl for postman execution

## Get a list of all user
http://localhost:8080/api/users/listUser

curl --location --request GET 'http://localhost:8080/api/users/listUser' \
--header 'Content-Type: application/json' \
--data 'http:'

## Get a user based on username

http://localhost:8080/api/users/user1

curl --location --request GET 'http://localhost:8080/api/users/user1' \
--header 'Content-Type: application/json' \
--data 'http:'


## create a new user
curl --location 'http://localhost:8080/api/users/create/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "user2",
"email": "user2@example.com",
"phoneNumber": "0987654329"
}'

## update a existing user

curl --location --request PUT 'http://localhost:8080/api/users/update/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "user2",
"email": "user2@example.com",
"phoneNumber": "09876"
}'

## delete a user by username

curl --location --request DELETE 'http://localhost:8080/api/users/user1' \
--header 'Content-Type: application/json' \
--data 'http:'
