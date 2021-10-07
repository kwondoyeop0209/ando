## 🎥 프로젝트 개요

### 진행 기간

- 2021.08.23 ~ 2021.10.07

### 주제
- 지역별(서울시) 범죄 분석과 공간 특성에 기반한 범죄 취약지 예측/안전지대 제안

### 목표
![image](/uploads/8152a969c4c9e854f63ba2285e2c90dd/image.png)  
1. 서울시 범죄 분석과 공간 특성에 기반한 범죄 안전지대 제안  
2. 서울시 지역별 범죄 발생 요인 분석 후 안전지대 제안  

### 프로젝트 RULE

![image](/uploads/61a819c1ed3781c5f2e0754ae2a0f174/image.png)  

1. JPA를 사용해서 DB와 백엔드를 연동한다.
2. Jira를 사용하여 1주일 단위의 스프린트를 진행하고 프로젝트를 관리한다.
3. 매일 오전, 오후 스크럼 미팅을 실시하고 Mattermost에 기록한다.
4. 서비스 배포 환경으로는 아마존 EC2와 Docker 컨테이너를 사용한다.
5. 정한 git commit 규칙을 준수하여 프로젝트 형상 관리를 한다.

### 와이어프레임
![image](/uploads/aa66f80e4eda3d08d0cc7b43c1813196/image.png)  
<br>

## 🍀 핵심기능
![image](/uploads/d3741d1851703fa71f7b666d38a06608/image.png)  
- 지역별 범죄 분석 시각화 - 구 단위로 범죄 발생 건수 자료 이용
- 공간 특성(CCTV, 유흥지, 유동인구 등)을 고려해서 범죄 취약지 예측
- 지도로 안전지수 및 평가 지표 제공

## 📚 Tech Stack

<details>
    <summary>Front</summary>
    <ul>
        <li>Vue2</li>
        <li>Vue CLI</li>
        <li>Vuex</li>
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
        <li>Jenkins</li>
    </ul>
</details>
<br>

## 📊 서비스 구조도

![](picture/Architecture.png)

## 💿 DB 모델링

![erd](picture/erd.png)

## 💻 개발환경
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

## 💻 빌드 및 배포

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
