### 1. 프로젝트 개발 환경

- JDK 1.8
- nginx 1.21.3
- Spring boot 2.5.5
- gradle 7.2
- MySQL 8.0.26
- Intellij IDEA Ultimate 2020.3.1
- Vue 2
- VsCode 1.60.2
- Jenkins 2.303.1
- Docker 20.10.8

### 2. build & deploy

- FrontEnd
  1. cd frontend
  2. npm install
  3. run build
  4. docker build -t nginx:0.1 -f FrontDockerfile .
  5. docker run -d --name nginx -p 80:80 -p 443:443 nginx:0.1
- BackEnd
  1. cd backend
  2. docker build -t back:0.1 -f BackDockerfile .
  3. docker run -d --name back -p 8443:8443 back:0.1

### 3. 외부 서비스

- KaKao 지도 API
  https://apis.map.kakao.com/web/guide/

### 4. DataBase 
[dumps](AndoDumps.sql)
```
# database properties
spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
spring.data.web.pageable.one-indexed-parameters=true
spring.datasource.url=jdbc:mysql://j5a305.p.ssafy.io:3306/andoDB?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.username=root
spring.datasource.hikari.password=ando1234
```

### 5. 시연 시나리오
