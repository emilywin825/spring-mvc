<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kakao 지도 시작하기</title>

<!-- Kakao Map API Key -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1934691a196583ecb3ee1dbcc2acf9cc"></script>

</head>

</head>
<body>
    <div id="map" style="width:1000px;height:800px;"></div>
    	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>

</body>
</html>