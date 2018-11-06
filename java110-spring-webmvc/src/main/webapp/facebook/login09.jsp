<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="facebookUrl" value="https://graph.facebook.com/v3.2/me">
    <c:param name="access_token" value="${param.accessToken}"/>
    <c:param name="fields" value="id,name,email,gender"/>
</c:url>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${param.accessToken}
<c:import url="${facebookUrl}"/> 
<!-- 
위에서  param에 facebookUrl뒤에 access_token과 fields 값을 붙여서 저장해두면,
여기서 facebookUrl을 뽑아 쓴다.
 -->
</body>
</html>









