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
<style>
#map {height: 50vh; width: 50vh; }
#infowindow-content .title {font-weight: bold; }
#infowindow-content {display: none; }
#map #infowindow-content {display: inline; }
</style>
<script>
let map;
let infowindow;
let marker;
let infowindowContent;
function initMap() {
	// 맵 초기화 코드
  	map = new google.maps.Map(document.getElementById("map"), {
    	center: { lat: 38, lng: 133 },
    	zoom: 5,
    	mapTypeControl: false,
  	});
	
  	infowindow = new google.maps.InfoWindow(); // 마커에 정보를 보여주는 역할
  	infowindowContent = document.getElementById("infowindow-content");
  	infowindow.setContent(infowindowContent);
  	marker = new google.maps.Marker({ //실제 마커
	    	map,
	    	anchorPoint: new google.maps.Point(0, -29),
	  	});
  	
}
</script>
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
	      <tr class="placesave" role="button">
	        <th scope="row">${ps.pidx }</th>
	        <td class="text-start ps-5">${ps.name }</td>
	        <td>${ps.place }</td>
	        <td>${ps.activity }</td>
	      </tr>
	      </c:forEach>
	    </tbody>
	  </table>
    </div>
    <div class="d-grid justify-content-sm-center mt-2">
    ${page }
    </div>
    <div class="mx-auto col-9 row gap-5 position-relative">
      <div class="d-grid gap-2 d-md-block">
	    <button class="btn btn-outline-secondary" type="button">Return</button>
	  </div>    	
	  <script>
	  	$('.placesave').on('click', function() {
	  		var pidx = $(this).find('[scope="row"]').html();
	  		
	  		$.ajax({
	  			url: 'tripContent.do',
	  			data: {pidx: pidx},
	  			success: function(data) {
	  				infowindow.close(); // 마커 정보창 초기화
	  				marker.setVisible(false); // 마커 초기화
	  				
	  				const myLatLng = { lat: data.lat, lng: data.lng };
	  				
	  				map.setCenter(myLatLng);
		    	    map.setZoom(13);
	  				
		    	    marker.setPosition(myLatLng);
		    	    marker.setVisible(true);
		    	    
		    	    infowindowContent.children["place-name"].textContent = data.name;
		    	    
		    	    infowindow.open(map, marker);
	  				
	  				$('#tripContentModalLabel').html(data.name);
	  				$('#tripContentModalButton').trigger('click');
	  				
	  				createMarker();
	  			}
	  		})
	  		.fail(function() {
	  			window.alert('request: failed!');
	  		});
	  	});
    	$('.btn-outline-secondary').on('click', function() {
    		location.href = 'tripMainPage.do';
    	});
    	</script>
    </div>
  </div>
</section>

<!-- Button trigger modal -->
<input id="tripContentModalButton" type="hidden" data-bs-toggle="modal" data-bs-target="#tripContentModal">
<!-- Modal -->
<div class="modal fade" id="tripContentModal" tabindex="-1" aria-labelledby="tripContentModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="tripContentModalLabel">Title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<!-- 맵 관련 코드 -->
        <div id="map"></div><div id="infowindow-content"><span id="place-name" class="title"></span></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Changes</button>
      </div>
    </div>
  </div>
</div>
			
<%@ include file="/WEB-INF/views/footer.jsp" %>
<script async src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPg6sDuTdTWAWj17NeU9JkTVNEs3gJfIU&libraries=places&callback=initMap&v=weekly&region=kr"></script>
</body>
</html>