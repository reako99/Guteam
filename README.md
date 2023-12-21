<img src="https://capsule-render.vercel.app/api?type=waving&color=BDBDC8&height=150&section=header&text=서해용-nl-&fontSize=50&fontColor=fff" />

## Guteam

> 해당 프로젝트는 spring으로 구현한 게임 커뮤니티 사이트입니다.
> <br>
> 프로젝트 목표 - "GUTEAM"은 게임 웹 커뮤니티 사이트로서 게임에 대한 이용자의 커뮤니티 활동을 위한 공간의 장을 제공하는 사이트를 개발하는 것입니다.

## 개발환경 세팅 방법

1. <a href="https://www.oracle.com/database/technologies/xe-prior-release-downloads.html">Oracle Database 11g Express Edition Release 11.2.0.2.0</a> 설치
2. src/main/resources에 uploadPath.properties 파일 추가
3. <a href="https://tomcat.apache.org/download-90.cgi">아파치 톰캣 9.0</a> 설치
4. maven 을 통해서 dependency down
5. sts로 열기 및 실행

## 빌드 명령어

```bash
mvn install 
cp target/guteam-1.0.0-BUILD-SNAPSHOT.war guteam.war
```

### uploadPath.properties 예제

```properties
# windows
uploadPath.img=C:\\Study\\FileUploadTest
downloadPath.img=\\Documents\\GuteamDownload
# mac
# uploadPath.img=/Users/DHKwak/Documents/Images
# downloadPath.img=/Documents/GuteamDownload
```

## 웹 실행 모습

<details>
  <summary> 기능1 </summary> 
  <p>
   기능설명
  </p>
  <img loading="lazy" src="이미지소스">
  <br>
</details>

~~

## 사용 기술

| 분류                 | Badge                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Front - end**      | <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&amp;logo=html5&amp;logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=flat-square&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat-square&amp;logo=jQuery&amp;logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white"> |
| **Back - end**       | <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&amp;logo=Spring&amp;logoColor=white"> <img src="https://img.shields.io/badge/Spring_security-6DB33F?style=flat-square&amp;logo=springsecurity&amp;logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **Version Control**  | <img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **DB**               | <img src="https://img.shields.io/badge/ORACLE-F80000?style=flat-square&logo=oracle&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                             |

## ERD - Diagram

![guteam ERD 최종본](https://github.com/reako99/Guteam/assets/137850852/c8ba80d6-cbbc-40fd-bc51-d0978f83906e)

## Use Case

![guteam Use Case 최종본](https://github.com/reako99/Guteam/assets/137850852/b01b801c-9eee-4e92-9b4f-a2022eeddfff)

## 제작인원 및 기간

- **총 제작인원:** <a href="https://github.com/reako99">서해용</a>, <a href="https://github.com/Jeon-hwang">전황</a>, <a href="https://github.com/DHKwak00">곽동훈</a> | 해당 링크를 누르면 깃허브 페이지로 이동합니다.
- **제작 기간:** 2023/10/20 ~ 2023/12/17
<img src="https://capsule-render.vercel.app/api?type=waving&color=BDBDC8&height=150&section=footer" />
