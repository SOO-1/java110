<%@ page language="java" 
    contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
Hello!
<!-- 
 javascript 동기방식은 값을 리턴할 때 까지, 웹브라우저는 block!
 -->
<%
Thread.currentThread().sleep(10000); //10초동안 잠자게, 이 10초가 지나야 client에 출력
%>
