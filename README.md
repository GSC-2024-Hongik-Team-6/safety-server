# safety-server
GDSC Solution Challenge Server Repository

## Technology stack
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/86ebc910-acd4-4259-9f3f-590c11da4a02 width="120" height="60"/>
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/9d61a40d-5527-4da6-a900-d4991f1c0a92 width="100" height="60"/>
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/40be89d1-c6da-4fad-acc6-e2ab4b036929 width="120" height="60"/>
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/14bb572e-6a61-474c-b9eb-f5badd8f81f5 width="90" height="90"/>
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/b9d85257-a40f-4301-9222-96331765828e width="120" height="60"/>
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/7b557174-dedb-4422-819f-3b66581e143e2 width="120" height="60"/>
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/5722bc9b-b0bb-4a17-b73f-e0bdfba2d850 width="120" height="60"/>
<img src=https://github.com/GSC-2024-Hongik-Team-6/safety-server/assets/106096303/9c3b8df5-6611-4ecd-8f55-2838f01d53e0 width="120" height="60"/>
</br>

## 🖹DB setting
#### Local MySQL ~ Google Cloud MySQL integration
Set Local MySQL </br>
Set application.yml file in Springboot</br>
Network authorization in security through Google Cloud MySQL
```
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url : ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        use_sql_comments: true
        hbm2ddl:
          auto: update
        default_batch_fetch_size: 1000
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: https://www.googleapis.com/service_accounts/v1/jwk/securetoken%40system.gserviceaccount.com
          issuer-uri: https://securetoken.google.com/${FIREBASE_APP_NAME}

logging.level:
  org.hibernate.SQL: debug
```
* ```${DB_URL}``` should be Public IP address of Google Cloud MySQL
* ```${DB_USERNAME}``` should be Local MySQL's userName
* ```${DB_PASSWORD}``` should be Local MySQL's passWord

## 🛳Server Deployment Process
####   CI using GithubActions </br>

Local : DockerImage Making

1. jar build : ```gradle build```

2. image creation : ```docker build -t userAccountName/repositoryName ./```

3. push to Docker hub : using Docker Desktop

* ```userAccountName/repositoryName``` is from Docker hub repository

#### Server : Deploy through Google Cloud VM instance

1. docker login : ```docker login```
2. pull from Docker hub : ```docker pull userAccountName/repositoryName```
3. query DockerImage : ```docker images```
4. rename DockerImage : ```docker tag userAccountName/repositoryName DockerImageRenaming```
5. make docker-compose.yml : put ```DockerImageRenaming``` text in docker-compose.yml
6. run Docker Compose : ```docker-compose up```
