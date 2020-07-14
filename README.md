# Java Spring Security Boilerplate

A boilerplate reference project on Java Spring Security with JWT users authentications

Inspired by [this](https://www.youtube.com/watch?v=her_7pa0vrg) YouTube tutorial by [AmigosCode](https://amigoscode.com/)

## Run/Build Locally

With Maven, run `maven clean install` followed by `maven spring-boot:run`

## Database

Users data currently stored via in-memory database in `FakeApplicationUserDaoService`

## Usage  

- Once app is running, make a POST request to `http://localhost:8080/login` with the following body:

```bash
{
    "username": "usertwo",
    "password": "password"
}
```

- From response header, copy the Authorization header bearer token i.e. `Bearer eyJhb...Eyw`

- Make a GET request to `http://localhost:8080/management/api/v1/students` with an Authorization header with the bearer token as value to retrieve profile details


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)



