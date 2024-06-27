# Java sample application.
## Tech stack
* Java 21
* SpringBoot 3
* Spring Secutity
* KeyCloak
* Docker compose

## Installation
### Keycloak

1. Start the keycloak service in the docker-compose.yml file
2. Browse to http://localhost:9091 and login the "admin" credentials.
2. Create a realm called "eapp" with default options.
2. Create a client "eapplogin" in the realm "eapp"
   1. Valid redirect URIs: http://localhost:8080/*
   2. Web origins: http://localhost:8080
   3. Valid post logout: http://localhost:8080/*
3. Go to "Client scopes" menu and set "microprofile-jwt" as "Default"
4. Go to "Realm roles" and create the role "appuser"
5. Go to "Users" menu and create the user "eappuser", assign he role "appuser" and set credentials.
6. You should be ready to go.