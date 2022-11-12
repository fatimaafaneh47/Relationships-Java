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
<link rel="stylesheet" type="text/css" href="/css/style.css">
	<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
<h1 class="welcome" >Book Club</h1>
<h2>A place for friends to share thoughts on book</h2>
<div class="main">
<div class="register">
<h1>Register</h1>
<form:form action="/register" method="post" modelAttribute="newUser">
    <p>
        <form:label path="userName">User Name:</form:label>
        <form:errors path="userName"/>
        <form:input path="userName"/>
    </p>
    <p>
        <form:label path="email">Email:</form:label>
        <form:errors path="email"/>
        <form:input path="email"/>
    </p>
    <p>
        <form:label path="password">Password</form:label>
        <form:errors path="password"/>
        <form:input path="password"/>
    </p>
    <p>
        <form:label path="confirm">Confirm PW:</form:label>
        <form:errors path="confirm"/>
        <form:input path="confirm"/>
    </p>
    <input type="submit" value="Submit" class="input"/>
</form:form> 
</div>
<div class="login">
<h1>Log In</h1>
<form:form action="/login" method="post" modelAttribute="newLogin">
    <p>
        <form:label path="email">Email:</form:label>
        <form:errors path="email"/>
        <form:input path="email"/>
    </p>
    <p>
        <form:label path="password">Password:</form:label>
        <form:errors path="password"/>
        <form:input path="password"/>
    </p>
    <input type="submit" value="Submit" class="input"/>
</form:form>  
</div>  
</div>
</body>
</html>