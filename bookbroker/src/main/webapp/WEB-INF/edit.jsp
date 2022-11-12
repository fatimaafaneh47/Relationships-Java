<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Insert title here</title>
</head>
<body>
<div class="show-title">
<h1>Change your Entry</h1>
<br>
<a href="/bookmarket">back to the shelves</a>
</div>
<div class="edit-form">
<form:form action="/books/${book.id}/edit" method="post" modelAttribute="book">
    <input type="hidden" name="_method" value="put">
    <p>
        <form:label path="title">Title</form:label>
       <form:errors path="title" class="error" style="color:red;"/>
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
    <input type="submit" value="Submit" class="edit"/>   
</form:form>

 <form action="/books/${book.id}" method="post">
        <input type="hidden" name="_method" value="delete">
        <input class="delete-button" type="submit" value="Delete Book" class="">
				</form>
				
				
</div>
</body>
</html>