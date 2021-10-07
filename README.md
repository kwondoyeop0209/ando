![logo](https://i.imgur.com/g05qEfJ.png)


# 🌎 안도

![web](https://img.shields.io/badge/platform-web-yellow) ![framework](https://img.shields.io/badge/framework-SpringBoot-9cf) ![library](https://img.shields.io/badge/libray-Vue-orange) ![db](https://img.shields.io/badge/database-MySQL-inactive)

> 서울시 범죄 현황 및 패턴 분석과 환경 요소에 기반한 범죄 안전 지대 분석 서비스

## 👨‍👩‍👧‍👦 팀원
 🧡 **권도엽** ([@kwondoyeop0209](https://github.com/kwondoyeop0209))  
 💛 **박형민** ([@cofls](https://github.com/cofls))  
 💚 **박채린** ([@npnppn](https://github.com/npnppn))  
 💙 **양혜선** ([@Yanghyesun](https://github.com/Yanghyesun))  
 💜 **정은진** ([@jejin009](https://github.com/jejin009))  

## 🎥 프로젝트 개요
### 진행 기간
- 2021.08.23 ~ 2021.10.08

### 주제
- 지역별(서울시) 범죄 분석과 공간 특성에 기반한 범죄 취약지 예측/안전지대 제안

### 목표
1. 웹과 Hadoop 기술을 접목하여 범죄 빅데이터 시각화 서비스를 구현한다.
2. 범죄 현황 및 안전 지수 제공을 통해 시민의 범죄대응역량을 강화한다.
3. 자치구별 범죄 현황을 다양한 차트로 시각화하여 한눈에 쉽게 볼 수 있도록 제공한다.
4. 지도에서 원하는 행정동을 클릭하면 안전 지수 및 환경 요소 현황을 파악한다.
5. CPTED를 기반으로 서울시의 공간 특성(CCTV, 보안등, 경찰서 등)을 분석하여 우리만의 안전 지수를 제공한다.

### 프로젝트 RULE
1. Jira를 사용하여 1주일 단위의 스프린트를 진행하고 프로젝트를 관리한다.
2. 매일 오전과 오후에 스크럼 미팅을 실시하고 Mattermost에 기록한다.
3. 서비스 배포 환경으로는 아마존 EC2와 Docker 컨테이너를 사용한다.
4. 정한 git commit 규칙을 준수하여 프로젝트 형상 관리를 한다. (ex. 깃모지 feat/fix/docs 설명)
5. Git Convention, Jira Convention, Code Convention 을 준수하여 개발을 진행한다.


### 와이어프레임
![wireframe](https://i.imgur.com/VrD64P7.png)



<br>

## 🍀 핵심기능
1) 서울시의 전체적인 범죄 통계 시각화
2) 자치구별로 범죄율, 검거율, 범죄 유형 순위, 발생 요일 등을 시각화
3) 최근 5년간 일어난 범죄를 기반으로 범죄 발생 지역, 장소, 요일, 시간 데이터를 사용하여 위험지수 계산
4) 행정동별로 환경 요소 (CCTV, 보안등 등)에 따라 가중치를 부여해 안전 지수 도출
5) 행정동별로 환경 요소를 다양한 방식으로 시각화
6) 범죄와의 상관 관계 제시

### 안전 지수 시각화 화면
![](https://i.imgur.com/eSE3q3Q.jpg)  <br>
![](https://i.imgur.com/IHGRmKx.jpg)


### 행정동별 환경 요소 시각화 화면
![](https://i.imgur.com/pgWgGbW.jpg)

### 범죄 현황 시각화 화면
![](https://i.imgur.com/3S3raTA.png)  <br>
![](https://i.imgur.com/me29nil.png)





## 📚 Tech Stack
<details>
    <summary>Front</summary>
    <ul>
        <li>Vue2</li>
        <li>Vue CLI</li>
        <li>Node</li>
        <li>Npm</li>
    </ul>
</details>
<details>
    <summary>Back</summary>
    <ul>
        <li>SpringBoot</li>
        <li>MySQL</li>
        <li>Swagger</li>
        <li>JPA</li>
        <li>docker</li>
        <li>nginx</li>
        <li>Jenkins</li>
    </ul>
</details>
<br>

## 📊 서비스 구조도
![service-architecture](https://i.imgur.com/Cegmf9p.png)



## 💿 DB 모델링
![erd](https://i.imgur.com/qqFhZZY.png)


## 💻 개발환경
- Java : jdk1.8 (1.8.0_192)
- node.js : 14.17.4
- npm : 6.14.14
- vue-cli : 4.5.13
- IntelliJ : 2020.3.1
- Spring boot : 2.5.5
- gradle : 7.2
- MySQL : 8.0.26
- VsCode : 1.60.2
- Jenkins : 2.303.1
- Docker : 20.10.8
- nginx :1.21.3

## 💻 실행방법
### Frontend

```bash=
cd frontend
npm install
run build
docker build -t nginx:0.1 -f FrontDockerfile .
docker run -d --name nginx -p 80:80 -p 443:443 nginx:0.1
```

### Backend
```bash=
cd backend
docker build -t back:0.1 -f BackDockerfile .
docker run -d --name back -p 8443:8443 back:0.1
```

### DB
```bash=
# database properties
spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
spring.data.web.pageable.one-indexed-parameters=true
spring.datasource.url={yourAddress}useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.username={yourName}
spring.datasource.hikari.password={yourPassword}

```
