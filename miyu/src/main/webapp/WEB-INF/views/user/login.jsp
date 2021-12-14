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

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      	html,
		body {
		  height: 100%;
		}
		
		.form-signin {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		}
		
		.form-signin .checkbox {
		  font-weight: 400;
		}
		
		.form-signin .form-floating:focus-within {
		  z-index: 2;
		}
		
		.form-signin input[type="email"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		
		.form-signin input[type="password"] {
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
    </style>
    
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container">
  <div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold">Log In</h1>
    <hr>
    
    
    <main class="form-signin">
  <form>
    <img class="mb-4" src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
  </form>
</main>
    
    
    
    
    
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
        	$(window).on('keydown', function(e) {
        		if(e.keyCode == 13) {
        			$('#loginButton').trigger('click');
        		}
        	});
        	
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
	  						
	  						// 사용자가 버튼을 누르지 않고 엔터키를 누를 경우 메인으로 이동
	  						$(window).on('keydown', function(e) {
	  			        		if(e.keyCode == 13) {
	  			        			location.href = 'index.do';
	  			        		}
	  			        	});
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