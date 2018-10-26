<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Test26 - 1 페이지</h1>

<form action="m2" method="post">
이름 : <input type="text" name="name"><br>
<button>다음</button>
<!-- 이전 페이지에서 작성한 걸 다음페이지로 이동할 때 남기기 위해서는 session에 보관해야 함. -->
</form>
</body>
</html>