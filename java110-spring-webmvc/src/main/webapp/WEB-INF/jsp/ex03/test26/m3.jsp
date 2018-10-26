<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Test26 - 3 페이지</h1>

<form action="m4" method="post">
<!-- checkbox일 경우, value="true"라고 지정하지 않도록 한다.
         값을 받을 때는 true/false인지 검사하는 것이 아니라, null인지를 검사하면 된다. 
    지정하지 않은 상황에서 "on"으로 넘어가지만, "on".equals(teacher) 보다는
    null인지 여부로 검사하는 게 낫다. -->
<input type="checkbox" name="teacher">강사<br> <!-- checkbox는 체크하지 않으면 값이 서버로 넘어가지 않음. -->
<button>다음</button>
</form>
</body>
</html>