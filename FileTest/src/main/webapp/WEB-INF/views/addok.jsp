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

</style>
</head>
<body> 
	<!-- addok.jsp -->
	<h1>결과</h1>
	
	<div class="message" title="txt">${txt}</div>
	
	<div class="message" title="file(하드 저장 이름으로 다운로드)">
		<a href="/file/resources/files/${filename}" download>${filename}</a>
	</div>
	
	<div class="message" title="file(사용자의 원본 파일명으로 다운로드)">
		<a href="/file/download.do?filename=${filename}">${orgfilename}</a>
	</div>
	

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
	</script>
</body>
</html>