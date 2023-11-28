<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	table th { width: 150px; }
	table tr:nth-child(1) td:nth-child(2){ width: 150px; }
	table tr:nth-child(3) td{ height: 100px; }
	table tr:nth-child(4) img {
		display: block;
		border: 1px solid #CCC;
		border-radius: 3px;
		padding: 5px;
		margin: 15px auto;
		max-width: 700px;
	}
	#map {
		width: 700px;
		height: 500px;
		margin: 15px auto;	
		border: 1px solid #CCC;
		border-radius: 3px;
	}
</style>
</head>
<body> 
	<!-- multi > view.jsp -->
	<h1>장소 <small>상세보기</small></h1>
	
	<table>
		<tr>
			<th>번호</th>
			<td>${dto.seq}</td>
			<th>날짜</th>
			<td>${dto.regdate}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">${dto.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><c:out value="${dto.content}" /></td>
		</tr>
		<tr>
			<td colspan="4">
			<c:forEach items="${dto.picList}" var="pdto">
				<img alt="Image" src="/file/resources/files/${pdto.filename}">
			</c:forEach>
			</td>
		</tr>
		<c:if test="${not empty lat}">
		<tr>
			<td colspan="4">
				<div id="map"></div>
			</td>		
		</tr>
		</c:if>
	</table>
	<div>
		<button type="button" class="back" onclick="location.href='/file/multi/list.do';">돌아가기</button>
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2b631c4003121607c7cb22ff2f41d62f"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
	<c:if test="${not empty lat}">
	
		const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	    
	    const options = { //지도를 생성할 때 필요한 기본 옵션
	       center : new kakao.maps.LatLng(${lat}, ${lng}), //지도의 중심좌표.
	       level : 3 //지도의 레벨(확대, 축소 정도)
	    };
	
	    const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	    
	    const marker = new kakao.maps.Marker({
			position: new kakao.maps.LatLng(${lat}, ${lng})
		});
		
		marker.setMap(map);
		
	</c:if>
	</script>
</body>
</html>