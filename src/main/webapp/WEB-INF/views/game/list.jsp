<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap css -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
   crossorigin="anonymous" />
<!-- Bootstrap icons -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
   rel="stylesheet" />
<!-- Bootstrap core JS-->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
ul {
	list-style-type: none;
	text-align: center;
}

li {
	display: inline-block;
	
}
.bi {
	color:#ffc100;
}
</style>
<meta charset="UTF-8">
<title>Guteam Game List</title>
</head>
<body>
<a href="/guteam/"><button>홈으로 돌아가기</button></a><jsp:include page="/WEB-INF/views/home.jsp"></jsp:include>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="register"><button>게임등록</button></a>
	</sec:authorize>
	<br>
	<c:forEach varStatus="status" var="vo" items="${list }">
	<div style="width:350px; height:500px; display:inline-block;" class="gameInfo">
		<img alt="${vo.gameName }" width="300px" height="300px" src="display?fileName=${vo.gameImageName }">
		<input type="hidden" class="gameId" value="${vo.gameId }">
		<br>
		name : ${vo.gameName } <br>
		price : ${vo.price }<br>
		genre : ${vo.genre }<br>
		releaseDate : ${vo.releaseDate }<br>
		rating : 
		<c:if test="${ratingList[status.index]!=0 }">
		<c:forEach begin="1" end="${ratingList[status.index]/2 }" step="1">
			<i class="bi bi-star-fill"></i>
		</c:forEach>
		<c:if test="${ratingList[status.index]%2!=0 }">
			<i class="bi bi-star-half"></i>
		</c:if>
		<c:forEach begin="1" end="${5-(ratingList[status.index]/2) }" step="1">
			<i class="bi bi-star"></i>
		</c:forEach>
		</c:if>
		<br>
		<!-- <img alt="${vo.gameName }" src=""><br> -->
	</div>
	</c:forEach>
	<ul>
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list?page=${pageMaker.startPageNo-1 }"><button>이전</button></a></li>
		</c:if>
		<c:forEach var="pageLink" begin="${pageMaker.startPageNo }"
			end="${pageMaker.endPageNo }">
			<c:if test="${pageMaker.criteria.page==pageLink }">
				<c:if test="${empty keyword }">
					<li><a href="list?page=${pageLink }" style="color: green;">${pageLink }</a></li>
				</c:if>
				<c:if test="${not empty keyword }">
					<li><a href="list?page=${pageLink }&keyword=${keyword}" style="color: green;">${pageLink }</a></li>
				</c:if>
			</c:if>
			<c:if test="${pageMaker.criteria.page!=pageLink }">
				<c:if test="${empty keyword }">
					<li><a href="list?page=${pageLink }">${pageLink }</a></li>
				</c:if>
				<c:if test="${not empty keyword }">
					<li><a href="list?page=${pageLink }&keyword=${keyword}">${pageLink }</a></li>
				</c:if>
			</c:if>
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list?page=${pageMaker.endPageNo+1 }"><button>다음</button></a></li>
		</c:if>
	</ul>
	
	<input type="hidden" id="insertResult" value="${insert_result }">
	<script type="text/javascript">
		$(document).ready(function(){
			var insertResult = $('#insertResult').val();
			if(insertResult=='success'){
				alert('게임 등록 성공!');
			}
			
			$('.gameInfo').on('click',  function(){
				var gameId = $(this).find("input").val();
				var prevListUrl = window.location.href;
				var keyword = $('#keyword').val();
				var url = "detail?gameId="+gameId+"&prevListUrl="+prevListUrl;
				location.href=url;
			});
		}); // document
	</script>
</body>
</html>