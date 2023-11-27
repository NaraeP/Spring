<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example</title>
<tiles:insertAttribute name="asset"></tiles:insertAttribute>
<style>

</style>
</head>
<body> 
	<!-- layout > index.jsp -->
	<tiles:insertAttribute name="main_menu"></tiles:insertAttribute>
	<main>
		<h1>시작 페이지</h1>
	</main>
	

	
	<script>
	
	</script>
</body>
</html>