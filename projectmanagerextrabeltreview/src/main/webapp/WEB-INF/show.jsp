<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Project Details</h1>
<p>Project : <c:out value="${project.title}"></c:out></p>
<p>Description : <c:out value="${project.title}"></c:out></p>
<p>Due Date : <c:out value="${project.title}"></c:out></p>
<a href="/">see tasks!</a>
 <form action="/projects/${project.id}/delete" method="post">
        <input type="hidden" name="_method" value="delete">
        <input class="delete-button" type="submit" value="Delete Book" class="">
				</form>
				


</body>
</html>