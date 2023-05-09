<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>지도 마커 생성하기</title>
    
</head>
<body>
<div id="map" style="width:700px;height:700px;"></div>

<!-- Kakao Map API Key -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1934691a196583ecb3ee1dbcc2acf9cc"></script>

<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.4454003, 126.9644264), // 지도의 중심좌표(관악산)
        level: 11 // 지도의 확대 레벨
    };

	//44개
	var mountains = [
  {
    name: '한라산',
    latlng: new kakao.maps.LatLng(33.361666, 126.521944)
  },
  {
    name: '지리산',
    latlng: new kakao.maps.LatLng(35.354446, 127.705619)
  },
  {
    name: '설악산',
    latlng: new kakao.maps.LatLng(38.1193416, 128.4631789)
  },
  {
    name: '내장산',
    latlng: new kakao.maps.LatLng(36.2856252, 128.9818982)
  },
  {
    name: '덕유산',
    latlng: new kakao.maps.LatLng(36.1523287, 128.1630677)
  },
  {
    name: '태백산',
    latlng: new kakao.maps.LatLng(37.1666956, 128.9880562)
  },
  {
    name: '소백산',
    latlng: new kakao.maps.LatLng(36.7604981, 128.4610502)
  },
  {
    name: '치악산',
    latlng: new kakao.maps.LatLng(37.1597381, 128.0260559)
  },
  {
    name: '속리산',
    latlng: new kakao.maps.LatLng(36.6132481, 127.7021772)
  },
  {
    name: '금강산',
    latlng: new kakao.maps.LatLng(35.3147832, 126.9341908)
  },
  {
    name: '비슬산',
    latlng: new kakao.maps.LatLng(36.4574014, 129.2871385)
  },
  {
    name: '소금산',
    latlng: new kakao.maps.LatLng(36.1268145, 128.1272281)
  },
  {
    name: '매봉산',
    latlng: new kakao.maps.LatLng(37.6691306, 127.2928097)
  },
  {
    name: '백마산',
    latlng: new kakao.maps.LatLng(37.7468172, 127.0774796)
  },
  {
    name: '한마음산',
    latlng: new kakao.maps.LatLng(37.7524228, 126.8820609)
  },
  {
    name: '관음산',
    latlng: new kakao.maps.LatLng(36.0319942, 128.4110529)
  },
  {
    name: '운문산',
    latlng: new kakao.maps.LatLng(37.7525114, 128.0062115)
  },
  {
    name: '속반도',
    latlng: new kakao.maps.LatLng(37.5833249, 130.8455237)
  },
  {
    name: '선운산',
    latlng: new kakao.maps.LatLng(37.3355081, 127.1381268)
  },
  {
    name: '금수산',
    latlng: new kakao.maps.LatLng(35.1524371, 126.9723372)
  },
  {
    name: '계방산',
    latlng: new kakao.maps.LatLng(36.3343576, 127.4432671)
  },
  {
    name: '마니산',
    latlng: new kakao.maps.LatLng(36.7573647, 127.2198865)
  },
  {
    name: '인왕산',
    latlng: new kakao.maps.LatLng(37.4593831, 126.8653375)
  },
  {
    name: '오대산',
    latlng: new kakao.maps.LatLng(36.7446623, 127.5573609)
  },
  {
    name: '방장산',
    latlng: new kakao.maps.LatLng(37.0988198, 128.0280361)
  },
  {
    name: '지리안',
    latlng: new kakao.maps.LatLng(33.2460729, 126.5783011)
  },
  {
    name: '무등산',
    latlng: new kakao.maps.LatLng(35.0652041, 127.6047648)
  },
  {
    name: '석굴암',
    latlng: new kakao.maps.LatLng(34.8711664, 127.9927774)
  },
  {
    name: '개봉산',
    latlng: new kakao.maps.LatLng(35.3838346, 126.9274551)
  },
  {
    name: '봉화산',
    latlng: new kakao.maps.LatLng(37.6018367, 127.0897796)
  },
  {
    name: '대둔산',
    latlng: new kakao.maps.LatLng(36.0294736, 128.4527162)
  },
  {
    name: '문수산',
    latlng: new kakao.maps.LatLng(36.0539863, 127.1586663)
  },
  {
    name: '의암산',
    latlng: new kakao.maps.LatLng(36.3270909, 127.4461292)
  },
  {
    name: '현무산',
    latlng: new kakao.maps.LatLng(37.1840836, 127.1840274)
  },
  {
    name: '무학산',
    latlng: new kakao.maps.LatLng(35.5465247, 129.2324641)
  },
  {
    name: '반석산',
    latlng: new kakao.maps.LatLng(36.3271783, 128.0468082)
  },
  {
    name: '가야산',
    latlng: new kakao.maps.LatLng(35.1488825, 127.5573741)
  },
  {
    name: '대봉산',
    latlng: new kakao.maps.LatLng(37.4485637, 127.2426459)
  },
  {
    name: '월악산',
    latlng: new kakao.maps.LatLng(37.639911, 128.004467)
  },
  {
    name: '도락산',
    latlng: new kakao.maps.LatLng(35.842271, 128.405853)
  },
  {
    name: '오봉산',
    latlng: new kakao.maps.LatLng(37.636228, 127.408984)
  },
  {
    name: '북한산',
    latlng: new kakao.maps.LatLng(37.659973, 126.989781)
  },
  {
    name: '마안산',
    latlng: new kakao.maps.LatLng(36.606861, 127.270617)
  },
  {
    name: '만천산',
    latlng: new kakao.maps.LatLng(36.587179, 127.126908)
  },
];

  var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
  
for (var i = 0; i < mountains.length; i++) {
  var marker = new kakao.maps.Marker({
    position: mountains[i].latlng,
    map: map,
    title: mountains[i].name
  });
  marker.setMap(map);
//   console.log(mountains.length);
}
    
</script>
</body>
</html>


