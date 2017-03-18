<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
</head>
<body>
  <h2>Hello!</h2>

<%
    String output = (String)request.getAttribute("output");
	String name = (String)request.getAttribute("name");
%>
  
  <!-- [START form] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
  <h3>
    Welcome to Roshan Shaik GAE Service!!
  </h3>

  <form method="POST" action="create">

    <div class="form-group">
      <label for="name">Enter your name:  </label>
      <input type="text" name="name" id="name" value="${fn:escapeXml(book.title)}" class="form-control" />
    </div>

    <button type="submit" class="btn btn-success">Say Hello</button>
  </form>
  
  <h3>
    <% if ( output != null)  {%>
       Hello <%= name %>. Output from API Post Service is: <%= output %>
    <% }%>
  </h3>
  
</div>
  
</body>
</html>
