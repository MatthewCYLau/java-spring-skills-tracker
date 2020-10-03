# Java Spring Skills Tracker

## Run/Build Locally

- In project root directory, run `docker-compose up` to start a PostgreSQL database
- Then with Maven, select `dev` profile and run `maven clean install` followed by `maven spring-boot:run`
- Then, create two users in your local PostgreSQL database:
```bash
docker ps # obtain the PostgreSQL container id
docker exec -it <container id> bin/bash # enter PostgreSQL container shell
psql -U postgres # login to PostgreSQL with username postgres
\c skillsdb # connect to database with name skillsdb
CREATE EXTENSION "uuid-ossp"; # add the UUID extension
INSERT INTO users (user_id, username, password, is_admin) VALUES (uuid_generate_v4(), 'admin', 'password', TRUE); # creates the admin user
INSERT INTO users (user_id, username, password, is_admin) VALUES (uuid_generate_v4(), 'basic_user', 'password', FALSE); # creates the basic user
```

## Usage  

- Once app is running, authenticate user by making a POST request to `http://localhost:8080/login` with the following body:

```bash
{
    "username": "admin",
    "password": "password"
}
```

- From response header, copy the Authorization header bearer token i.e. `Bearer eyJhb...Eyw`

- Make a GET request to `http://localhost:8080/api/v1/profiles` with an Authorization header with the bearer token as value

- See Postman collection [here](https://www.getpostman.com/collections/b8d3e24049479e11bdbd)

## Deploy to AWS Elastic Beanstalk

- Create an AWS RDS PostgreSQL database. See official documentations [here](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/CHAP_GettingStarted.CreatingConnecting.PostgreSQL.html)
   - Make a note on database login username, and password; set database access to public
- Update `application-prod.yml` with database connection url and login credentials. For example:

```bash
app:
  datasource:
    jdbc-url: jdbc:postgresql://<AWS RDS Postgres resource URL>:5432/skillsdb
    username: <username>
    password: <password>
    pool-size: 30
```

- From project root directory, build the `.jar` file by running this command:
```bash
mvn clean install -Pprod
```

- On [AWS Elastic Beanstalk](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_Java.html), create a sample Java Corretto 11 app
- Find the `SNAPSHOT.jar` file from the `/target` directory, and upload onto the Elastic Beanstalk app
- On AWS console, navigate to the RDS database instance security group. Modify the inbound rule to allow traffic from the Elastic Beanstalk app environment security group
- You can now access the Elastic Beanstalk app environment

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)



