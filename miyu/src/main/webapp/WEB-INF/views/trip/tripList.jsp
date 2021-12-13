<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trip List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="css/header.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container">
  <div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold mb-5">Trip List</h1>
    <hr>    
    <div class="mt-5 mx-auto col-9 row gap-5">
  	  <table class="table table-hover">
	    <thead class="table-dark">
	      <tr>
	        <th scope="col">#</th>
	        <th scope="col">Name</th>
	        <th scope="col">Place</th>
	        <th scope="col">Activity</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach items="${placesave }" var="ps">
	      <tr role="button">
	        <th scope="row">${ps.pidx }</th>
	        <td class="text-start ps-5">${ps.name }</td>
	        <td>${ps.place }</td>
	        <td>${ps.activity }</td>
	      </tr>
	      </c:forEach>
	    </tbody>
	  </table>
    </div>
    <div class="mt-5 mx-auto col-9 row gap-5">
    ${page } <!-- 페이지 코드 다시 확인;; -->
    </div>
  </div>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>
<script async src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPg6sDuTdTWAWj17NeU9JkTVNEs3gJfIU&libraries=places&callback=initMap&v=weekly&region=kr"></script>
</body>
</html>