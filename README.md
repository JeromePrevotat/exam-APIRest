# exam-APIRest

# Project Architecture

-   Database Mysql
-   Docker Container

## Run Project

docker compose up --build app

## Access to DB

docker exec -it db bash

mysql -p

password: root
(very secure dont worry)

use taskdb;

## Execute HTTP Request from testRequests.http

### WIP

Serialize LocalDate currently

-   createdAt is being erased to null on HTTP POST/PUT
-   updatedAt is never updated
