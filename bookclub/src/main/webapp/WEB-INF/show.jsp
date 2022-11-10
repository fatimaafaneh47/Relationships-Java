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
<h1><c:out value="${book.title}"></c:out></h1>
<br>
<a href="/books">back to the shelves</a>
</div>
<c:choose> 
  <c:when test="${userId == book.user.id}">
   <h2 class="show"> you read <c:out value = "${book.title}"/> by <c:out value="${book.authorname}"/></h2>
   <h3 class="show"> Here are your thoughts</h3>
   <p class="show"><c:out value="${book.thoughts}"></c:out><p>
  </c:when>
  <c:otherwise>
   <h2 class="show"> <c:out value ="${book.user.userName}"/> read <c:out value = "${book.title}"/> by <c:out value="${book.authorname}"/></h2>
   <h3 class="show"> Here are <c:out value = "${book.user.userName}"/> thoughts</h3>
   <p class="show"><c:out value="${book.thoughts}"></c:out><p>
  </c:otherwise>
</c:choose>
<div class="edit-delete">
<c:if test="${userId == book.user.id}" >
        <form action="/books/${book.id}" method="post">
        <input type="hidden" name="_method" value="delete">
        <input type="submit" value="Delete" class="">
				</form>
<a href="/books/${book.id}/edit">Edit</a>|
      </c:if>
      </div>

</body>
</html>