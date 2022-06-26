TODO - Add a unit tests/build info if its easy to do

# Project description
This project creates jwt for clients and then verifies the token later on.

# Starting the server
TODO

# Endpoints
## GET /auth/health
### Sample curl
## GET /auth/get-token
### Sample curl
## GET /auth/validate-token
### Sample curl

# claims
- subject as user
- id is user
- issuer as root
- expiration of one hour

# secret - how to store them
- Hashicorp vault is simple to setup and use.
- This can also be passed when starting the server.

# Project tracker
- [x] generate tokens
    - [x] unit tests
    - user as subject
- [x] what claims to use? 
    - made a quick decision
- [x] verification of token
    - [x] unit tests
- [ ] tokenManager
    - [ ] tests
- [ ] end-point for generating token
- [ ] end-point for validating 
- [x] how to store the secret
    - [x] using AppConstants for now
    - [ ] use a vault
- [ ] use User without saving it in a db.
    - getToken sends username and we generate a UUID for it and use it
- [ ] add User to a database
    - getToken URI should validate a user Id and return token for it.

# Tests
Unit tests are a must. I will try to write them as much as possible.
I do not want to add integration test.


