<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="css/header.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container">
  <div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold">Log In</h1>
    <hr>
    <div class="col-lg-6 mx-auto">
      <div class="lead mb-4 text-start">
      	<form>
		  <div class="row mb-3">
		    <div class="col-12">
		      <label for="loginId" class="form-label">ID</label>
		      <input type="text" class="form-control" id="loginId" name="userid" placeholder="Please Enter Your ID">
		    </div>
		  </div>		  
		  <div class="row mb-3">
		    <div class="col-12">
		      <label for="loginPassword" class="form-label">Password</label>
		      <input type="password" class="form-control" id="loginPassword" name="userpwd" placeholder="Please Enter Your Password">
		    </div>
		  </div>      	
		</form>
      </div>
      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
        <button type="button" class="btn btn-primary btn-lg px-4 gap-3" id="loginButton">Log In</button>
        <a type="button" class="btn btn-outline-secondary btn-lg px-4" href="index.do" role="button">Return</a>
        <script>
        	$('#loginButton').on('click', function() {
        		$.ajax({
        			url: 'login.do',
        			method: 'POST',
        			data: {userid: $('#loginId').val(), userpwd: $('#loginPassword').val()},
        			success: function(data) {
        				if(data > 0) {
	  						$('#resultModalLabel').html('Log in Success');
	  						$('#resultModalContent').html('Welcome!!');
	  						$('#resultModalButton').attr('href', 'index.do');
	  					} else {
	  						$('#resultModalLabel').html('Log in failed');
	  						$('#resultModalContent').html('Please check you ID and Password');
	  						$('#resultModalButton').attr('data-bs-dismiss', 'modal');
	  					}
	  					$('#resultModalOn').trigger('click');
        			}
        		})
        		.fail(function(xhr, status) {
        		    window.alert('request failed : '+status);
        		});
        	});
        </script>
      </div>
    </div>
  </div>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>