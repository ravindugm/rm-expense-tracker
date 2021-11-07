# rm-expense-tracker
REST project for tracking expenses

### Prerequisite
- JDK 11
- Maven 3
- Postgres 
- Docker-CE 19+

### Initial Setup
1. Run `Postgres` docker container
    ```
   docker container run --name postgresdb -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres
   ```
   - Container name: `postgresdb`
   - Admin password: `admin`
   - Port: `5432`
   - Docker image name: `postgres`