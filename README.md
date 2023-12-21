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
  <summary> 로그인&회원가입 </summary> 
  <p>
   1. 로그인이 필요한 페이지에 접근하거나 로그인 버튼으로 로그인 페이지에 들어올 수 있다.<br>
   2. 회원 가입시 아이디, 닉네임은 중복검사를 통해 중복되지 않도록 하고, 각각의 항목마다 유효성 검사를 통해 올바른 데이터가 입력될 수 있도록 한다.<br>
   3. 무분별한 가입을 막기 위해 reCAPTCHA를 통해 인증된 사용자만이 회원가입이 가능하도록 한다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/64808ab3-462b-43d1-ac26-cc4114e613ab">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/029cb82d-3d16-414c-8355-a7cc8f802529">
  <br>
</details>

<details>
  <summary> 프로필&내가 쓴 게시글 </summary> 
  <p>
   1. 로그인을 하면 상단 프로필 버튼을 통해 프로필 페이지에 들어올 수 있다.<br>
   2. 프로필 화면에서는 내가 쓴 글들을 확인할 수 있고, 조회시에 페이징을 통해 쓴 게시글이 많더라도 페이지를 이동해가며 쓴 글들을 조회할 수 있다.
   3. 내가 쓴 게시글 화면에서 해당 영역을 클릭하면 해당 게시글로 바로 이동이 가능하다.<br>
   4. 캐쉬충전이나 회원탈퇴가 가능하며 쪽지함, 회원정보 수정 페이지, 친구 페이지로 이동이 가능하다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/5f344850-6252-4c41-b256-8e069ebdbc94">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/6b6a7d17-8938-488d-8b7e-b08b5241bdac">
  <br>
</details>

<details>
  <summary> 친구 </summary> 
  <p>
   1. 친구 페이지에서는 친구 요청과 요청 수락 및 친구의 정보를 확인할 수 있다.<br>
   2. 프로필 화면에서는 내가 쓴 글들을 확인할 수 있고, 캐쉬충전이나 회원탈퇴가 가능하며 쪽지함, 회원정보 수정 페이지, 친구 페이지로 이동이 가능하다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/809011e2-8ad9-4743-884e-b6e3dd90c007">
  <br>
</details>

<details>
  <summary> 쪽지함 </summary> 
  <p>
   1. 쪽지함에 들어오면 먼저 받은 쪽지함을 볼 수 있으며, 받은 쪽지함과 보낸 쪽지함을 통해 내가 받고 보낸 쪽지들을 확인 가능하다.<br>
   2. 쪽지 보내기 페이지에서는 닉네임과 아이디를 통해 수신인을 작성하여 쪽지를 보낼 수 있다.<br>
   3. 쪽지 보관 기능을 통해 받거나 보낸 쪽지를 별도로 보관하는 기능을 구현하였고, 보관된 쪽지들은 제목 앞쪽에 보낸 쪽지인지 받은 쪽지인지 구별해주도록 하였다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/58b5b6ad-0cc4-4586-8300-9a126c266e0d">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/7c95f5a0-5126-4431-87d0-5e99eb77937d">
  <br>
   <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/c0ff378c-f17a-47a9-8e7a-b53066fe823b">
  <br>
</details>

<details>
  <summary> 게임 & 최근 조회한 게임 </summary> 
  <p>
   1. 메인 페이지는 게임의 리스트들을 보여주는 페이지이고, 페이지는 하단의 페이징 기능을 통해 클릭하는 페이지로 이동 가능하도록 구성했다.<br>
   2. 검색창을 통하여 게임의 제목이나 장르, 또는 가격을 기준으로 검색이 가능하고, 정렬 버튼들을 통해 검색된 리스트들을 특정 기준을 통해 정렬이 가능하다.<br>
   3. 게임을 클릭하게 되면 해당 게임의 상세 정보 페이지로 이동하게 되며, 데이터베이스에 최근 조회한 게임 테이블에 해당 게임이 업데이트 된다. 이때, 오늘 조회한 게임들중 최근의 5개까지가 최근 조회한 게임 버튼을 통해 조회가 가능하다.<br>
   4. 관리자 계정에서는 게임의 등록이 가능하며, 게임의 이름, 장르, 가격, 이미지를 입력하여 등록할 수 있다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/6f6ab8f5-4d3e-4e63-89b4-3543f00c3d9e">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/1298c155-6420-429f-a5df-5170017e5c90">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/93adcdb3-7899-4a76-9bcb-e78803574de6">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/2f6acced-0fdd-4276-a8e4-ff2065577ba4">
  <br>
</details>

<details>
  <summary> 할인 적용 </summary> 
  <p>
   1. 관리자 계정으로 로그인 하면 장르별 할인 적용이 가능하다.<br>
   2. 장르별로 할인을 하게 되면 리스트 페이지 등의 게임 정보를 보여주는 페이지에 적용이 되어 보이며, 구매시에도 할인된 금액으로 구매가 가능하다.<br>
   3. 장르별 할인 적용을 적용할 때 드롭다운 버튼 클릭시 데이터베이스에 있는 장르들을 조회하여 보여준다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/9e693957-d238-4175-96d3-216fde9001b2">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/d7a91675-3480-4157-81d7-44f6fefb29fc">
  <br>
</details>

<details>
  <summary> 커뮤니티 </summary> 
  <p>
   1. 커뮤니티 게시판에서는 로그인 시 글쓰기가 가능하며, 글 작성시 '@'문자를 통해 특정 유저를 태그할 수 있다.<br>
   2. 게시판 작성시 우측에 byte 계산이 되어 표시된다.<br>
   3. 태그된 아이디를 클릭하면 해당 유저의 정보를 다이얼로그로 보여줄 수 있다.<br>
   4. 게시글에는 로그인한 유저들이 댓글과 댓글에 대한 답글을 작성 가능하다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/930323d3-d600-4935-9687-0d913c2a2d1c">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/4177978f-0d5b-4ad4-bd16-4de8dc69f52c">
  <br>
   <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/e9b60095-9254-4971-b530-3edf9217e5b5">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/65d367b2-316e-4084-b498-a7cc72094a31">
  <br>
</details>

<details>
  <summary> 위시리스트 & 구매하기 </summary> 
  <p>
   1. 비 로그인 시에는 쿠키로 위시리스트를 저장하며, 로그인 시에는 데이터베이스에 위시리스트를 저장한다.<br>
   2. 위시리스트에 담겨있는 게임들은 체크하여 구매가 가능하고, 구매시에 유저의 캐쉬와 비교하여 바로 구매가 되거나 추가 결제를 진행하게 된다.<br>
   3. 구매한 게임 페이지에서 다운로드와 실행이 가능하도록 구현하였다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/9fe7815a-dbe4-4d79-b4cf-18df58c75e4a">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/d63a73a8-182d-4bc3-9ca5-439e9f2e7f1c">
  <br>
   <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/00e3e6b0-c1b7-448e-a9e5-c669741ffd4d">
  <br>
</details>

<details>
  <summary> 리뷰 </summary> 
  <p>
   1. 리뷰 페이지에서는 다른 유저로 로그인하면 해당 리뷰에 대해 추천/비추천이 가능하다.<br>
   2. 리뷰 작성, 수정시 별점 아이콘을 클릭하면 해당 클릭 값을 통해 별점이 수정될 수 있도록 한다.<br>
  </p>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/619eff60-a774-433d-ae7b-518330d8cb25">
  <br>
  <img loading="lazy" src="https://github.com/reako99/Guteam/assets/137850852/c5815538-d92d-4cbf-a634-3bff97de8262">
  <br>
</details>

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
- 
## 시연영상
[![Guteam Youtube](https://img.youtube.com/vi/YQktP06TqWM/0.jpg)](https://www.youtube.com/watch?v=YQktP06TqWM)
[![Guteam Youtube](https://img.youtube.com/vi/xDtbXSP46xQ/0.jpg)](https://www.youtube.com/watch?v=xDtbXSP46xQ)
