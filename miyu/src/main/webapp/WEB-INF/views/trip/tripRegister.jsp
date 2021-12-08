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
		    fields: ["formatted_address", "geometry", "name", "address_components"],
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
	      //엔터가 눌리면 이쪽으로 넘어옴. 나중에 개선 필요.
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
		
	    // 지도에 이름과 간단한 정보를 출력하는 코드
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
	    
	    var addr_json = place.address_components; //주소값이 분할되어 담겨 있는 JSON
	    var region = ''; //지역명 저장 변수
	    // 주소 JSON을 뒤에서부터 거꾸로 돌려서 지역명이 들어 있는 구간을 찾아내어 region에 저장하는 코드
	    for(var i = addr_json.length-1; i >= 0; i--) {
	    	if(addr_json[i].types[0] == 'administrative_area_level_1') {
	    		region = addr_json[i].short_name;
	    	}
	    }
	    
	    // 만약 JSON에 지역명이 있는 구간이 없을 경우 직접입력을 요청하는 코드
	    if(region == '') {
	    	// 드롭다운 메뉴를 추가해서 대응 (기존 메뉴를 드롭다운으로 변경 후 잠금을 해제?)
	    }
	    
	    regionPicker(region);
	  });
}

function regionPicker(region) {
	$.ajax({
		url: 'regionPick.do',
		data: {region: region},
		success: function(data) {
			window.alert(data);
			window.alert(JSON.stringify(data.dto.pname)); //DTO를 데이터로 받아옴
			$('#region-input').val(data.dto.pname);
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