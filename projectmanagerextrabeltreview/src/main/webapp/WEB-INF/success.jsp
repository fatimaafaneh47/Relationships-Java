<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<h2 class="welcome">Welcome ,<c:out value="${user.userName}"/></h2>
<a href="/logout">logout</a>
<a href="/projects/new">New Project</a>
<h4>All Projects</h4>
<table style="width:70%;" class="table table-striped table-bordered mytable">
<thead>
	<tr>
			<th>Project</th>
            <th>Team Lead</th>
            <th>Due Date</th>
            <th>Actions</th>
</tr>
    </thead>
     <tbody>
		<c:forEach var="project" items="${unassignedProjects}">
		<tr>
			<c:if test = "${project.lead.id!=user.id}">
			<td><a href="/projects/${project.id}">${project.title}</a></td>
			<td><c:out value="${project.lead.userName}"></c:out></td>
			<td><c:out value="${project.date}"></c:out></td>
		    <td><a href="/dashboard/join/${project.id}">Join Team</a></td>
		    </c:if>
		</tr>	
		</c:forEach>
		</tbody>
    </table>
    <h4>Your Projects</h4>
<table style="width:70%;" class="table table-striped table-bordered mytable">
    <thead> 
    	<tr>
    		<th>Project</th>
    		<th>Team Lead</th>
    		<th>Due Date</th>
    		<th>Actions</th>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="project" items="${assignedProjects}">
		<tr>
			<td><a href="/projects/${project.id}">${project.title}</a></td>
			<td><c:out value="${project.lead.userName}"></c:out></td>
			<td><c:out value="${project.date}"></c:out></td>
			<c:if test = "${project.lead.id==user.id}">
		       <td><a href="/projects/edit/${project.id}">Edit Project</a></td>
		    </c:if>
		    <c:if test = "${project.lead.id!=user.id}">
		       <td><a href="/dashboard/leave/${project.id}">Leave Team</a></td>
		    </c:if>
		</tr>	
	</c:forEach>
	<c:forEach var="project" items="${createdProjects}">
		<tr>
			<td><a href="/projects/${project.id}">${project.title}</a></td>
			<td><c:out value="${project.lead.userName}"></c:out></td>
			<td><c:out value="${project.date}"></c:out></td>
			<c:if test = "${project.lead.id==user.id}">
		       <td><a href="/projects/edit/${project.id}">Edit Project</a></td>
		    </c:if>
		    <c:if test = "${project.lead.id!=user.id}">
		       <td><a href="/dashboard/leave/${project.id}">Leave Team</a></td>
		    </c:if>
		</tr>	
	</c:forEach>
    </tbody>
</table>

</body>
</html>