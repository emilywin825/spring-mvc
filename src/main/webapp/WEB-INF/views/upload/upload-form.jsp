<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   .upload-box {
            width: 150px;
            height: 150px;
            border: 3px dashed orange;
            display: flex;
            justify-content: center;
            align-items: center;
            color: red;
            font-weight: 700;
            cursor: pointer;
        }

    #img-input {
            display: none;
        }

    
</style>
</head>
<body>
    <h1>파일 업로드 예제</h1>
<!-- <input type="file" >은 기본 디자인 바꿀 수 없음. 그래서 보통 이렇게 디자인 만들고 이거 선택하면 
         -<input type="file" > 이거 클릭되도록 script 걺-->
    <div class="upload-box">파일 첨부</div>

    <form action="/upload-file" method="post" enctype="multipart/form-data">
            <!-- accept : 업로드 파일 제한, multiple : 여러개 첨부 가능 -->
            <!-- 근데 보통 multiple 잘 안하고 그냥 여러개 만듦 -->
            
            <input id="img-input" type="file" name="thumbnail" accept="image/*" >
            <button type="submit"> 전송</button> 
    </form>

    <script>
        const $box = document.querySelector('.upload-box');
        const $input = document.getElementById('img-input');

        $box.onclick = e => {
            $input.click();
        };

    </script>
</body>
</html>