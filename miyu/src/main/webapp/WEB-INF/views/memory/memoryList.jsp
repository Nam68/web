<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memory</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="css/header.css" rel="stylesheet">
<link href="css/memory.css" rel="stylesheet">
<style>
.carousel-item>img {
	width: 400px;
	height: 300px;
}
@media screen and (max-width:700px), screen and (orientation:portrait) {
	.col-5 {
		width: 100%;
		margin-bottom: 10%;
	}
}
</style>
</head>
<script>
function initPage() { //페이지 초기화시 실행 (실행 코드는 헤더에 있음)
	var divs = $('.card');
	for(var i = 0; i < divs.length; ++i) {
		var div = divs[i];
	    var divAspect = div.offsetHeight / div.offsetWidth;
	    
	    var img = div.querySelector('img');
	    var imgAspect = img.height / img.width;

	    if (imgAspect <= divAspect) {
	      // 이미지가 div보다 납작한 경우 세로를 div에 맞추고 가로는 잘라낸다
	      var imgWidthActual = div.offsetHeight / imgAspect;
	      var imgWidthToBe = div.offsetHeight / divAspect;
	      var marginLeft = -Math.round((imgWidthActual - imgWidthToBe) / 2);
	      img.style.cssText = 'width: auto; height: 100%; margin-left: ' + marginLeft + 'px;';
	    } else {
	      // 이미지가 div보다 길쭉한 경우 가로를 div에 맞추고 세로를 잘라낸다
	      img.style.cssText = 'width: 100%; height: auto; margin-left: 0;';
	    }
	}
}

function memoryAddCode() { //이미지 삽입 버튼 코드를 반환
	var code = 
	'<div class="carousel-item active" role="button" id="addMemoryButton">'+
		'<svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="400" height="300" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: First slide" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/><text x="50%" y="50%" fill="#555" dy=".3em">Image</text></svg>'+
		'<div class="carousel-caption d-none d-md-block">'+
			'<h5>Click Here To Add Picture</h5>'+
            '<p>Let\'s add pictures and comments of our memory!</p>'+
		'</div>'+
	'</div>'+
	'<script>'+
		'$(\'#addMemoryButton\').on(\'click\', function() {'+
			'$(\'#addImageInput\').trigger(\'click\');'+
		'});'+
	'<\/script>';
	return code;
}

function memoryImgCode(img) {
	var code = 
		'<div class="carousel-item" role="button" id="addMemoryButton">'+
			'<img src="'+img+'" class="d-block w-100" alt="...">'+
			'<div class="carousel-caption d-none d-md-block">'+
				'<h5>Click Here To Add Picture</h5>'+
	            '<p>Let\'s add pictures and comments of our memory!</p>'+
			'</div>'+
		'</div>'+
		'<script>'+
			'$(\'#addMemoryButton\').on(\'click\', function() {'+
				'$(\'#addImageInput\').trigger(\'click\');'+
			'});'+
		'<\/script>';
	return code;
}

function memoryContentfooter() {
	var code = ''+
	<c:if test="${!empty sessionScope.userDTO}">
		'<button type="button" class="btn btn-outline-danger">Delete</button>'+
		'<button type="button" class="btn btn-outline-success">Update</button>'+
		//각 버튼별로 script 만들기
	</c:if>
		'<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>';
	return code;
}

function memoryAddfooter() {
	var code = 
		'<button type="button" class="btn btn-primary">Submit</button>'+
		'<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>';
	return code;
}
</script>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container">
  <div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold">Memory</h1>
    <hr>
    <!-- c:if test="${sessionScope.userDTO.permit==2 }" -->
    <div class="text-end mb-3">
  	  <a class="btn btn-outline-danger mb-1" role="button" data-bs-toggle="modal" data-bs-target="#memoryContentModal">
	    Add New Memory
	  </a>
	  <script>
	  	$('a[data-bs-target="#memoryContentModal"]').on('click', function() {
	  		$('#memoryContentModalLabel').html('Add Memory');
	  		$('.carousel-inner').html(memoryAddCode());
	  		$('#memoryContentModalFooter').html(memoryAddfooter());
	  	});
	  </script>
	</div>
	<!-- /c:if -->
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <c:forEach items="${list }" var="m">
      <div class="col">
        <div class="card shadow-sm overflow-hidden position-relative" role="button">
          <span><input type="hidden" value="${m.idx }"></span>
          <span class="w-100 text-center text-white fs-4 fw-bold title">${m.title }</span>
          <span class="w-100 text-center text-white fs-5 city">${m.placeName }</span>
          <span class="w-100 text-center text-white fs-6 startdate">${m.startdate }~${m.enddate }</span>
          <div class="scale">
          	<img class="bd-placeholder-img card-img-top" alt="" src="${empty m.img? 'http://myyk.co.kr/img/noimage.jpg':m.img }"/>    	  
          </div>
        </div>
      </div>
      </c:forEach>
      <script>
      	$('.card').on('click', function() {
      		var idx = $(this).find('input').val();
      		$.ajax({
      			url: 'test',
      			data: {idx: idx},
      			success: function(data) {
      				$('#memoryContentModalLabel').html(data.title);
      				var imgs = data.img;
      				for(var i = 0; i < imgs.length; i++) {
      					$('.carousel-inner').append(memoryImgCode(imgs[i]));
      					$('#memoryContentModalFooter').html(memoryContentfooter());
      				}
      			}
      		})
      		.fail(function() {
      			window.alert('request failed!');
      		});
      	});
	  	$('.card').mouseover(function(){
			$(this).find('.scale').trigger('mouseenter');
		});
		$('.card').mouseout(function(){
			$(this).find('.scale').trigger('mouseleave');
		});
		$('.scale').mouseover(function(){
			$(this).addClass('scale_hover');
		});
		$('.scale').mouseout(function(){
			$(this).removeClass('scale_hover');
		});
      </script>
    </div>
    <div class="d-grid gap-2 d-sm-flex justify-content-sm-center mt-5">
      ${page }
    </div>
  </div>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>

<!-- Content Modal -->
<div class="modal fade" id="memoryContentModal" tabindex="-1" aria-labelledby="memoryContentModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="memoryContentModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body container" id="memoryContentModalContent">
        <div class="row">
	      <div class="col-5">
			<div id="carouselCaptions" class="carousel slide" data-bs-ride="carousel" data-bs-touch="false" data-bs-interval="false">
			  <div class="carousel-indicators">
			    <span><button type="button" data-bs-target="#carouselCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button></span>
			  </div>
			  <div class="carousel-inner" id="memory-carousel-inner">
			  	Image Code
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselCaptions" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselCaptions" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
	      </div>
	      <div class="col-6">
	        Column
	      </div>
	    </div>
      </div>
      <div class="modal-footer" id="memoryContentModalFooter">
        <button type="button" class="btn btn-primary">Submit</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<form id="memoryImgAddForm" method="post" enctype="multipart/form-data">
  <input type="file" id="addImageInput" multiple="multiple" accept="image/*" style="display: none;">
</form>
<script>
$('#addImageInput').on('change', function() {
	var form = $('memoryImgAddForm')[0];
	var formData = new FormData(form);
	window.alert('add Image Check');
	/*
	$.ajax({
		url: '',
		type: 'POST',
		enctype: 'multipart/form-data',
		data: data,
		processData: false,
		contentType: false,
		success: function(data) {
			alert(data);
		}
	});
	*/
});
</script>
</body>
</html>