<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>오늘의 날씨</title>
</head>

<body>

  <!-- api 사이트 : https://openweathermap.org/city/1835848 -->
    <div class="weather">
        <!-- <div class="CurrIcon"></div> -->
        <div class="City"></div>
        <div class="TodayWeather"></div>
        <div class="CurrTemp"></div>
        <div class="MinTemp"></div>
        <div class="MaxTemp"></div>
        <div class="Wind"></div>
        <div class="Humidity"></div>
        <div class="Clouds"></div>
  </div>

  <script src="https://code.jquery.com/jquery-3.4.1.js"></script>

  <!-- https://fontawesome.com/start 가입후 CDN 넣으면 됨 -->
  <!-- <script defer src="https://use.fontawesome.com/releases/v5.15.2/js/all.js" integrity="sha384-vuFJ2JiSdUpXLKGK+tDteQZBqNlMwAjhZ3TvPaDfN9QmbPb7Q8qUpbSNapQev3YF" crossorigin="anonymous"></script> -->
    <script type="text/javascript">

        $(document).ready(function() {
          let weatherIcon = {
            '01' : 'fas fa-sun',
            '02' : 'fas fa-cloud-sun',
            '03' : 'fas fa-cloud',
            '04' : 'fas fa-cloud-meatball',
            '09' : 'fas fa-cloud-sun-rain',
            '10' : 'fas fa-cloud-showers-heavy',
            '11' : 'fas fa-poo-storm',
            '13' : 'far fa-snowflake',
            '50' : 'fas fa-smog'
          };
    
        $.ajax({
        url:'https://api.openweathermap.org/data/2.5/weather?q=seoul+&appid=92091da86d5cc4920d435f2230554b8c',
    
        dataType:'json',
        type:'GET',
        success:function(data){
          // var $Icon = (data.weather[0].icon).substr(0,2);
          var $city = data.name;
          var $TodayWeather = data.weather[0].description;
          var $Temp ='현재온도 : '+( Math.floor(data.main.temp)- 273) + 'º';
          var $MinTemp ='최저온도 : '+( Math.floor(data.main.temp_min)- 273) + 'º';
          var $MaxTemp ='최대온도 : '+( Math.floor(data.main.temp_max)- 273) + 'º';
          var $WindSpeed = '바람 : '+Math.floor(data.wind.speed);
          var $Humidity = '현재습도 : '+Math.floor(data.main.humidity)+'%';
          var $Clouds = '구름 : '+data.clouds.all+'m/s WSW';



        //   $('.CurrIcon').append('<i class="' + weatherIcon[$Icon] +'"></i>');
          $('.City').append($city);
          $('.TodayWeather').append($TodayWeather);
          $('.CurrTemp').prepend($Temp);
          $('.MinTemp').prepend($MinTemp);
          $('.MaxTemp').prepend($MaxTemp);
          $('.Wind').prepend($WindSpeed);
          $('.Humidity').prepend($Humidity);
          $('.Clouds').prepend($Clouds);
          }
        })
        });
      </script>

      <!-- 

                     console.log(resp);
                console.log("현재온도 : "+ (resp.main.temp- 273.15) );
                console.log("현재습도 : "+ resp.main.humidity);
                console.log("날씨 : "+ resp.weather[0].main );
                console.log("상세날씨설명 : "+ resp.weather[0].description );
                console.log("날씨 이미지 : "+ resp.weather[0].icon );
                console.log("바람   : "+ resp.wind.speed );
                console.log("나라   : "+ resp.sys.country );
                console.log("도시이름  : "+ resp.name );
                console.log("구름  : "+ (resp.clouds.all) +"%" ); 
       -->

        
</body>
</html>