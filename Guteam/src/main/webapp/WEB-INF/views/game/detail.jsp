<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.gameName }</title>
</head>
<body>
<img alt="${vo.gameName }" width="300px" height="300px" src="display?fileName=${vo.gameImageName }">
<br>
게임 이름 : ${vo.gameName }
<br>
가격 : ${vo.price }
<br>
장르 : ${vo.genre }
<br>
출시일 : ${vo.releaseDate }
<br>
마지막 업데이트일 : ${vo.updateDate }
<hr>
<a href="list?page=${page }"><button>리스트로 돌아가기</button></a>( 추후에 페이지 정보 저장해서 해당 페이지로 돌아가게 해야함)
<a href="update?gameId=${vo.gameId }&page=${page}"><button>수정하기</button></a>
</body>
</html>