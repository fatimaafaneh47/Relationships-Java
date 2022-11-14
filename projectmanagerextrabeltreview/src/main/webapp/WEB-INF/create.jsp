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
<h1>Create a Project</h1>
<br>
</div>
<div class="addform">
<form:form action="/projects/new" method="post" modelAttribute="projects">
<form:input type="hidden" path="lead" value="${user.id}"/>
    <p>
        <form:label path="title">Project Title:</form:label>
        <form:errors path="title"/>
        <form:input path="title"/>
    </p>
    <p>
        <form:label path="description">Project Description:</form:label>
        <form:errors path="description"/>
        <form:input path="description"/>
    </p>
    <p>
        <form:label path="date">Due Date:</form:label>
        <form:errors path="date"/>
        <form:input path="date" type="date"/>
    </p>
     <button> <a href="/dashboard"> Cancel</a> </button>
    <input type="submit" value="Submit" class="input"/> 
</form:form> 

</div>
</body>
</html>