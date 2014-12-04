<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Submit your feeds</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>

<c:if test="${feed != null}">
Przesłano: <c:out value="${feed.title}"></c:out> <br/>
<ul>
	<c:forEach items="${feed.items}" var="item">
	<li><c:out value="${item.title}"></c:out>
	</c:forEach>
</ul>
</c:if>

<form action="data" method="POST">
	<textarea name="feed-data" cols="80" rows="20">
<?xml version="1.0" encoding="UTF-8" ?>
<rss version="2.0">

<channel>
  <title>W3Schools Home Page</title>
  <link>http://www.w3schools.com</link>
  <description>Free web building tutorials</description>
  <item>
    <title>RSS Tutorial</title>
    <link>http://www.w3schools.com/rss</link>
    <description>New RSS tutorial on W3Schools</description>
  </item>
  <item>
    <title>XML Tutorial</title>
    <link>http://www.w3schools.com/xml</link>
    <description>New XML tutorial on W3Schools</description>
  </item>
</channel>

</rss> 	
	</textarea>
	<br />
	<input type="submit" value="Submit!">
</form>

</body>
</html>