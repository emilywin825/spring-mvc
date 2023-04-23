<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>${inform.name}</h1>님의 성적 정보
    <li>#국어 : ${inform.kor} 점</li>
    <li>#영어 : ${inform.eng} 점</li>
    <li>#수학 : ${inform.math} 점</li>
    <li>#총점 : ${inform.total} 점</li>
    <li>#평균 : ${inform.average} 점</li>
    <li>#학점 : ${inform.grade} 점</li>
    <a href="/score/list ">목록</a>
    <a href="/score/modify?stuNum=${inform.stuNum}">수정</a>

    <script>
        // const average = +'${inform.average}';
        
    </script>



</body>
</html>

