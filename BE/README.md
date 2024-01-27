# API mock demo Spring Boot + my SQL

# Summary demo project:
  1. Authentication and authorization
  2. Handler error, AOP logging, message
  3. Web design html, css, jquery...
  4. CRUD sample + swagger

## Project Architecture
<a href="#" target="blank">
    <img style="display: block; margin-left: auto; margin-right: auto;" src="./Architecture/ExceptionHandlerFlow.png"
alt="Project Architecture By Phúc Kute"/></a>
<a href="#" target="blank">
    <img style="display: block; margin-left: auto; margin-right: auto;" src="./Architecture/JwtFlow.png"
alt="Project Architecture By Phúc Kute"/></a>

## Technology Stack & Information system
1. Java version 21   
2. Spring boot 3.2.2 RELEASE 
   1. spring-boot-starter-web
   2. spring-boot-starter-data-jpa
   3. spring-boot-starter-security
   4. spring-boot-starter-validation
   5. spring-boot-starter-thymeleaf
   5. Spring AOP concepts..
3.  4.0.0
4. Mysql 
5. Docker / Docker Compose

## Step run project
1. Setup and run docker Mysql
```
 # cd {projectPath}/DockerConfig/MySql
 cd ./DockerConfig/MySql

 docker-compose up -d
```
2. Run and build project Spring boot to jar with docker
 ```
# build to jar at this local folder

mvn clean package
# build image PhucBE docker (--no-cache)
cd ../BE
docker build -t be_phuc_image .
docker run -p 8080:8080 -d be_phuc_image
```
3. Testing
   1. Open browser with url <a href="http://localhost:8080" target="blank">localhost:8080</a> to open website
   2. Check API documentation at <a href="http://localhost:8080/swagger-ui" target="blank">😱Not support Swagger for JakartaEE 😭😭😭</a>.

# Author
<h2 style="display: c">Hi 👋, I'm Phúc Kute</h2>
<h3 align="center">Start learning Developer at <a href="https://google.com" target="blank">phuckute.com</a></h3>
<h2 style="display: c">Thank you!</h4>