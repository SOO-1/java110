<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Test26 - 4 페이지</h1>

<form action="m5" method="post">
<!-- 현재와 같이 분류된 것 안에서 여러 항목이 있을 때,
        각 항목의 값이 무엇인지 알기 위해 value를 사용한다! -->
<input type="checkbox" name="language" value="java">Java<br> 
<input type="checkbox" name="language" value="c/c++">C/C++<br> 
<input type="checkbox" name="language" value="sql">SQL<br> 
<input type="checkbox" name="language" value="python">Python<br> 
<input type="checkbox" name="language" value="javascript">JavaScript<br> 
<button>다음</button>
</form>
</body>
</html>