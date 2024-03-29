<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Address</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

</style>
</head>
<body> 
	<!-- add.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
	<table>
		<tr>
			<td><input type="text" name="name" placeholder="이름" class="full" required></td>
		</tr>
		<tr>
			<td><input type="text" name="age" placeholder="나이" class="full" required></td>
		</tr>
		<tr>
			<td>
				<select name="gender">
					<option value="m">남자</option>
					<option value="f">여자</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="text" name="address" placeholder="주소" class="full" required></td>
		</tr>
	</table>
	<div>
		<button type="button" class="add" onclick="add();">등록하기</button>
		<button type="button" class="list" onclick="location.href='/client/list.do';">목록보기</button>
	</div>
	
	<script>
	
		function add() {
			
			//전송할 데이터
			let obj = {
				name: $('input[name=name]').val(),
				age: $('input[name=age]').val(),
				gender: $('select[name=gender]').val(),
				address: $('input[name=address]').val()
			};
			
			//자바스크립트 객체 > (변환) > JSON 표기법 > JSON.stringify(obj) 사용! > 객체를 JSON 표기법으로 바꿔준다.
			//alert(JSON.stringify(obj));
			//기본적으로 ajax가 server로 넘기는 데이터는 문자열로 넘어간다. 따라서 이대로 넘어가면 표기법은 json이지만, 자료형 자체는 json으로 넘어가지 못한다. 따라서 headers에 '네가 받는 데이터는 json이야!' 라고 알려주어야 한다.
			
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8090/rest/address',
				headers: {'Content-Type': 'application/json'},
				data: JSON.stringify(obj),
				dataType: 'json',
				success: result => {
					//alert(result);
					if (result == 1) {
						location.href='/client/list.do';
					} else {
						alert('failed');
					}
				},
				error: (a,b,c) => {
					console.log(a,b,c);
				}
			});
			
		}
	
	</script>
</body>
</html>