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
#map {height: 50vh; width: 100%; }
#content {height: 50vh; width: 100%; }
#content .memo {height: 22.5vh; overflow: auto; }
#content .info {height: 27.5vh; overflow: hidden; }
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
function initPage() {
	var portrait = ($(window).width() - $(window).height()) < 0;
	if(portrait) {
		$('table').find('tr:eq(0)').find('th:gt(1)').css('display', 'none');
		$('table').find('tr:gt(0)').find('td:gt(0)').css('display', 'none');
	}
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
	        <td>${ps.jpname }</td>
	        <td>${ps.activityText }</td>
	      </tr>
	      </c:forEach>
	    </tbody>
	  </table>
	  <input type="hidden" id="selected_pidx">
    </div>
    <div class="mx-auto mt-2 col-9 position-relative">
    ${page }
    <button class="btn btn-outline-secondary position-absolute top-0 end-0" type="button">Return</button>
    </div>   	
	  <script>
	  	$('.placesave').on('click', function() {
	  		var pidx = $(this).find('[scope="row"]').html();
	  		$('#selected_pidx').val(pidx);
	  		
	  		$.ajax({
	  			url: 'tripContent.do',
	  			data: {pidx: pidx},
	  			success: function(data) {
	  				// 맵 세팅 코드
	  				infowindow.close();
	  				marker.setVisible(false);
	  				const myLatLng = { lat: data.lat, lng: data.lng };
	  				map.setCenter(myLatLng);
		    	    map.setZoom(13);
		    	    marker.setPosition(myLatLng);
		    	    marker.setVisible(true);
		    	    infowindowContent.children["place-name"].textContent = data.name;
		    	    infowindow.open(map, marker);
	  				
		    	    // 모달 생성 코드
	  				$('#tripContentModalLabel').html(data.name);
		    	    $('.name').html(data.name);
		    	    $('.place').html(data.jpname);
		    	    $('.activity').html(data.activityText);
		    	    $('.addr').html(data.addr);
		    	    $('.memo').html(data.memo);
		    	    
	  				$('#tripContentModalButton').trigger('click');
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
      <div class="modal-body row">
      	<div class="col-sm">
      	  <!-- 맵 관련 코드 -->
          <div id="map"></div><div id="infowindow-content"><span id="place-name" class="title"></span></div>
        </div>
        <div class="col-sm">
          <div id="content">
          	<div class="info">
	        <p class="h4 name"></p>
	        <p class="place"></p>
	        <p class="activity"></p>
	        <p class="addr"></p>
	        <hr>
	        </div>
	        <div class="memo"></div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Changes</button>
        <script>
        	$('.btn-primary').on('click', () => {
        		var pidx = $('#selected_pidx').val();
        		location.href = 'tripRegister.do?pidx='+pidx;
        	});
        </script>
      </div>
    </div>
  </div>
</div>
			
<%@ include file="/WEB-INF/views/footer.jsp" %>
<script async src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPg6sDuTdTWAWj17NeU9JkTVNEs3gJfIU&libraries=places&callback=initMap&v=weekly&region=kr"></script>
</body>
</html>