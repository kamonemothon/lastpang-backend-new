## ✔️ How to Start
### 1) Prerequisites
* Java 17
* IntelliJ IDEA or eclipse
* MySQL

### 2) Clone
Clone this repo to your local machine using:
```
https://github.com/kamonemothon/lastpang-backend-new.git
```
### 3) Setup
- Add `env.properties` in `resources`
    - Fill in the blank space after the equal sign with your own words
```
DB_URL=jdbc:mysql://loclhost:3306/lastpang?serverTimezone=Asia/Seoul
DB_USERNAME=yourusername
DB_PASSWORD=yourpassword
```
- Creating a database called `lastpang` in MySQL workbrench
- Change the value of the `ddl-auto` variable inside `application.yml` from `none` to `create` only on the first execution.
- Run `BackendApplication`
  <br/>

## 📲 Easy Start
- Link to Our Site! https://nemo-pied.vercel.app

## 🏛 Project Architecture
<image src='' width="800"/>
<br/>

## ✅ Server Deployment Process (CI/CD using Github Actions)

### Local : Gradle build, Docker build
1. jar build : `gradle build`
2. image creation : `docker build -t yourAccountName/repositoryName ./`
3. push to Docker Hub : `docker push yourAccountName/repositoryName`

(`AccoutName` and `RepositoryName` are from Docker Hub)

### Server : Deploy
1. Pull from Docker Hub : `docker pull yourAccountName/repositoryName`
2. Create image as configured in Docker-compose.yml : `docker tag yourAccountName/repositoryName dockerImageName`
3. Run Docker Compose : `docker-compose up`

(`dockerImageName` should be written as the image name in Docker-compose.yml)

<br />

## 🛠 Tech Stacks
<image src='' width="800"/>

<br/>

## 📁 ERD

<image src='https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa98aa686-1767-4142-a9a2-f7303fcfa347%2F6c290957-51bd-4f9f-bb44-996c8b257683%2FUntitled.png?table=block&id=64672f17-4f89-409d-8803-63b318782c50&spaceId=a98aa686-1767-4142-a9a2-f7303fcfa347&width=2000&userId=d30f258c-d9aa-4582-87a1-adc478dcd1b6&cache=v2' width="800"/>

<br/>

## ❗ GIT Strategy

### 1) Git Workflow

### main → develop → feature/Issue#-feature, fix/Issue#-feature, refactor/Issue#-feature

1. Work individually on each branch `local - feature/Issue#-feature`
2. After completing the task, submit a PR to `remote - develop`.
3. After code review, receive approval and merge
4. Every time a merge occurs in `remote - develop`, all team members pull from `remote - develop` to maintain the latest status

### 2) Commit Convention

| Tag name | Description                                                 |
| -------- | ----------------------------------------------------------- |
| feat     | 기능과 관련된 것(기능에 변화를 줌). 새로운 기능 추가. 요구사항 변경으로 기존 기능을 추가, 수정, 삭제                             |
| fix      | 코드 상 에러, 버그 픽스                                  |
| refactor   | 기능과 관련되지 않은 것(기능에 변화 주지 않음). 기능 변화 아닌 코드 리팩토링. 파일 추가/이동/이름 변경/삭제                            |
| docs    | 문서 추가/수정. 주석 추가/수정                     |
| style    | 개행, 들여쓰기, 공백..                                      |
| test    | 테스트 코드 추가/변경                          |
| chore     | 배포, 빌드 작업                     |
| release     | 패키지 버전 올릴 때 |
| init | 프로젝트 초기 설정                                |

<br/>

## 📑 Coding Convention
### Naming Convention

- save / find / update / delete
- 요청 DTO : (자원) - (조작 행위)
    - 자원 - 저장 : ResourceSaveRequest
    - 자원 - 수정 : ResourveUpdateRequest
- 응답 DTO : (자원) - (자원 속성 | 관련 조건) - (단건 | 여러 건)
    - 자원 - 조건 없음 - 단건 : ResourceFindOneResponse
    - 자원 - 검색 - 여러 건 : ResourceSearchAllReponse
    - 자원 - 다른 자원 근처 - 여러 건 : ResourceNearbyAllResponse

<br/>

## 👥 Contributors

|                                   [추서연](https://github.com/ChooSeoyeon)    |
| :--------------------------------------------------------------------------: |
| <img src="https://avatars.githubusercontent.com/u/83302344?v=4" width=150px> |


<br/>

## 📎 Link

- Email : [t01053604256@gmail.com](mailto:t01053604256@gmail.com)