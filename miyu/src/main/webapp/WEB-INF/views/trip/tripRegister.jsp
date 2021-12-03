<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Trip</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="css/header.css" rel="stylesheet">
<style>
#map, #input-form{height: 50vh; }
#infowindow-content .title {font-weight: bold; }
#infowindow-content {display: none; }
#map #infowindow-content {display: inline; }
</style>
<script>
function initPage() {
	contentSizeCheck();
}
$(window).resize(function() {
	contentSizeCheck();
});
function contentSizeCheck() {
	var width = $(window).width();
	var height = $(window).height();
	var portrait = width-height < 0? true:false;
	
	if(portrait) {
		$('#map').removeClass('col');
		$('#input-form').removeClass('col');
	} else {
		$('#map').addClass('col');
		$('#input-form').addClass('col');
	}
}

let map;
function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 38, lng: 133 },
    zoom: 5,
    mapTypeControl: false,
  });
  
  const input = document.getElementById("pac-input");
  const options = {
		    fields: ["formatted_address", "geometry", "name"],
		    strictBounds: false,
		    types: ["establishment"],
		  };
  const autocomplete = new google.maps.places.Autocomplete(input, options);
  
  autocomplete.bindTo("bounds", map);
  
  const infowindow = new google.maps.InfoWindow();
  const infowindowContent = document.getElementById("infowindow-content");
  
  infowindow.setContent(infowindowContent);
  
  const marker = new google.maps.Marker({
	    map,
	    anchorPoint: new google.maps.Point(0, -29),
	  });

	  autocomplete.addListener("place_changed", () => {
	    infowindow.close();
	    marker.setVisible(false);

	    const place = autocomplete.getPlace();

	    if (!place.geometry || !place.geometry.location) {
	      // User entered the name of a Place that was not suggested and
	      // pressed the Enter key, or the Place Details request failed.
	      window.alert("No details available for input: '" + place.name + "'");
	      return;
	    }

	    // If the place has a geometry, then present it on a map.
	    if (place.geometry.viewport) {
	      map.fitBounds(place.geometry.viewport);
	    } else {
	      map.setCenter(place.geometry.location);
	      map.setZoom(17);
	    }

	    marker.setPosition(place.geometry.location);
	    marker.setVisible(true);
	    infowindowContent.children["place-name"].textContent = place.name;
	    infowindowContent.children["place-address"].textContent = place.formatted_address;
	    infowindow.open(map, marker);
	    
	    var name = place.name;
	    $('#pac-input').val(name);
	    var addr = place.formatted_address;
	    var lat = place.geometry.location.lat();
	    var lng = place.geometry.location.lng();
	    
	    regionPicker(addr);
	  });
}

function regionPicker(addr) {
	$.ajax({
		url: 'regionPick.do',
		data: addr,
		success: function(data) {
			window.alert(data.pidx); //데이터는 번호와 이름을 같이 반환
			window.alert(data.name); //데이터는 번호와 이름을 같이 반환
			
			$('#region-input').val(data.name);
		}
	})
	.fail(function() {
		window.alert('地域が探せません！直接入力してください');
		//숨겨놓았던 셀렉트박스 div의 display를 block으로 바꿈
	});
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container">
  <div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold mb-5">Register Trip</h1>
    <hr> 
    <div class="mt-5 mx-auto row gap-5">
      <div id="map" class="rounded col w-100"></div>
      <div id="input-form" class="col w-100 text-start">
		<div class="mb-3">
		  <label for="pac-input" class="form-label">Place Search</label>
		  <input type="text" class="form-control" id="pac-input" placeholder="Search...">
		    <div id="infowindow-content">
		      <span id="place-name" class="title"></span><br />
		      <span id="place-address"></span>
		    </div>
		</div>
		<div class="mb-3">
		  <label for="region-input" class="form-label">Region</label>
		  <input type="text" class="form-control" id="region-input" placeholder="Please Write Place" readOnly>
		  <script>
		  	$('#region-input').on('click', function() {
		  		$('#pac-input').focus();
		  	});
		  </script>
		</div>
      </div>
    </div>
  </div>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>
<script async src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPg6sDuTdTWAWj17NeU9JkTVNEs3gJfIU&libraries=places&callback=initMap&v=weekly&region=kr"></script>
</body>
</html>