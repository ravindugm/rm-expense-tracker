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

2. Copy database script file to the running container
   ```
   docker cp rmexpensetracker_db.sql postgresdb:/
   ```
   
3. Login to the container
   ```
   docker container exec -it postgresdb bash
   ```

4. Run `SQL` inside the container
   ```
   psql -U postgres --file rmexpensetracker_db.sql
   ```