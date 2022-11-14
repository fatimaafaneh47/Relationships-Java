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
<h1>Edit Project</h1>
<form:form action="/projects/edit/${project.id}" method="post" modelAttribute="project">
    <input type="hidden" name="_method" value="put">
    <p>
        <form:label path="title">Project Title:</form:label>
       <form:errors path="title" class="error" style="color:red;"/>
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
    <input type="submit" value="Submit" class="edit"/>   
    <button class="btn btn-secondary"><a href="/dashboard">Cancel</a></button>
    <form:input type="hidden" path="lead" value="${user.id}"/>
</form:form>


</body>
</html>