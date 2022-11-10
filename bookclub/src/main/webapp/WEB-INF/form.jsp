<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Insert title here</title>
</head>
<body>
<div class="show-title">
<h1>Add a Book to your Shelf!</h1>
<br>
<a href="/books">back to the shelves</a>
</div>
<div class="addform">
<form:form action="/books/new" method="post" modelAttribute="books">
    <p>
        <form:label path="title">Title</form:label>
        <form:errors path="title"/>
        <form:input path="title"/>
    </p>
    <p>
        <form:label path="authorname">Author</form:label>
        <form:errors path="authorname"/>
        <form:input path="authorname"/>
    </p>
    <p>
        <form:label path="thoughts">My thoughts</form:label>
        <form:errors path="thoughts"/>
        <form:textarea path="thoughts"/>
    </p>
    <form:input type="hidden" path="user" value="${user.id}"/> 
    <input type="submit" value="Submit" class="input"/>
</form:form> 
</div>
</body>
</html>