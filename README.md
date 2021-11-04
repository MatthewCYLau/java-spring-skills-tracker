# Java Spring Skills Tracker

A reference project to deploy a Java Spring app onto AWS Elastic Beanstalk

An app to track users, their skillz and achievements

## Run/Build Locally

- In project root directory, run `docker-compose up` to start a PostgreSQL database
- Then with Maven, select `dev` profile and run `maven clean install` followed by `maven spring-boot:run`

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

## Deploy to GCP Cloud Run

```
docker build -t gcr.io/<your_gcp_project_id>/java-spring-skills-tracker .
docker push gcr.io/<your_gcp_project_id>/java-spring-skills-tracker:latest
gcloud run deploy --image=gcr.io/<>/java-spring-skills-tracker:latest --update-env-vars POSTGRESQL_URL=jdbc:postgresql://<CLOUD-SQL-PUBLIC-IP>:5432/skillsdb,POSTGRESQL_USERNAME=postgres
```

- Then, on Cloud Run console, update `POSTGRESQL_PASSWORD`, and `JWT_SECRET` as secured environment variables. Re-deploy Cloud Run service

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
