<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Test14 페이지 컨트롤러</h1>

[Car]<br>
model : ${requestScope.car.model}<br>
maker : ${car.maker}<br> <!-- requestScope 생략 가능! -->
auto : ${car.auto}<br> <!-- boolean타입이기 때문에 자동으로 getter => isAuto로 셋팅 -->
<p/>
[Engine]<br>
name : ${car.engine.name}<br> <!-- 각각의 getter 뽑아옴(get 생략 + 대문자를 소문자로!) -->
cc : ${car.engine.cc}<br>
valve : ${car.engine.valve}<br>

</body>
</html>