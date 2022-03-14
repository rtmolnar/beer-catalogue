
# beer-catalogue
JAVA BACK END DEVELOPER TEST

**Considerations**
Due to the lack of time I couldn't implement everything I wanted. The project lacks error treatment, logging, a much more test coverage and a more elaborated postman script but again, this is what I had time to implement. I hope you like it :)

---

**To be aware of**
There is a postman collection in the project in the folder `resources/postman-collection`, it can be used to test the application.

Please note that the postman collection doesn't have pre-request scripts so you will have to manually copy and past the bearer token into the requests header that needs it. Please add a bearer token authentication and paste there the return of the authentication endpoint.

---

**How to run**
First of all the project needs to built:

    mvn clean install
Them there are two possibilities for running the project:

1. run it with maven `mvn spring-boot:run -Dspring-boot.run.fork=false`
2. run it with docker:
   In the root of the project execute the following command to build the docker image:
   `docker build -t beer/catalogue .`
   once it's built it's possible to run it with the following command:
   `docker run -p 8080:8080 beer/catalogue`

Both ways will let the application accessible by `http:localhos:8080`

---

List of features requested:

- [x] Consider including any type of API documentation.
- [x] CRUD of beer HTTP RESTFUL
- [x] CRUD manufacturer
- [x] ADMIN users can authenticate and edit any information of any manufacturer or beer
- [ ] Manufacturer can authenticate and edit their own info but not another manufacturer info
- [x] Deployable to a Docker container
- [x] Anonymous users can only retrieve beers information
- [x] Add pagination to the collection endpoints
- [x] Users should be able to search by any  beer attribute
- [x] Fulfill missing information from other sources
- [ ] Include a picture for each beer and allow uploading a file for a beer

**Consider including any type of API documentation.**
This project has a Swagger page where all the requests can be checked and tested. Also there is a postman collection in the resource folder that can be used for testing the requests, the only required action for that is an environment with the variable `{{api-url}}`
If the project is running on local the swagger url is: http://localhost:8080/swagger-ui/index.html#/

---

**CRUD of beer HTTP RESTFUL**
The crud of both beer and manufacturer is made using Spring `RestController`, `Services` and `JpaRepository`. For most endpoints there were no logic, just the little boiler plate code that one has to write for getting things working with Spring Boot.

---

**ADMIN users can authenticate and edit any information**
The project has the springSecurity enabled so the user can only perform post, put and delete request if logged in. The get requests are allowed for not logged in requests.
Since the application is stateless the decided approach was the JWT bearer token that has to be sent within any request that requires authentication.

---

**Manufacturer can authenticate and edit their own info but not another manufacturer info **
Unfortunately this is something I couldn't manage to make work, i tried with the `@PreAuthorize` and `@PostAuthorize`but for some reason all logged users could still make the request.

---

**Deployable to a Docker container**
TO BE WRITTEN

---

**Anonymous users can only retrieve beers information**
Inside the project `SecurityConfigurations` only the get endpoints are authorized without any authentication, this can be seen in the class `com.haufe.test.beer.catalogue.security.SecurityConfigurations.java`

---

**Add pagination to the collection endpoints**
All the endpoints that return a list of results have the Spring `Page` implemented so the results can be sorted by any parameter with the querystring `INSERT HERE THE SORT EXAMPLE OF THE URL`as the type of sort can also be sent

---

**Users should be able to search by any  beer attribute**
Users can search a beer by any attribute. This is done using the jpa `Specification`.
the endpoint and the possible attributes are:

    beers/attributes?name=Duvel&fabricationDate=2011-09-01&type=BLOND&GRADUATION=5.5&description=fruity&manufacturer=Duvel
All the search criteria follows the `or` policy which means that when the user search by multiple attributes, if the beer matches with any of them, then it will be part of the result list.

---

**Fulfill missing information from other sources**
If any result has been found in the endpoint `beers/attributes` and **only** in this endpoint, then a new request will be triggered to the [punk api](https://punkapi.com/documentation/v2) in order to fetch the desired info. This is done using the Spring `Feign Client`

**Include a picture for each beer and allow uploading a file for a beer**
Still pending, unfortunately I still didn't have time to implement this functionality.  

