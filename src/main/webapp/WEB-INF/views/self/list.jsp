<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <!-- reset -->
    <style type="text/css" media="screen, print">
        @font-face{
            font-family: "DungGeunMo-Regular";
            src: url('https://cdn.jsdelivr.net/gh/wooin21/web/fonts/etc/DungGeunMo-Regular.woff') format('woff');
            font-weight: normal; 
            font-style: normal;
        }
    </style>


    <link rel="stylesheet" href="/assets/css/main.css">
    <link rel="stylesheet" href="/assets/css/self-list.css">

</head>

<body>

    <div id="wrap">
        <div class="main-title-wrapper">
            <h1 class="main-title">MJ's 게시판</h1>
            <button class="add-btn">새 글 쓰기</button>
        </div>

    <div class="card-container">
        <c:forEach var="s" items="${sList}">
            <div class="card-wrapper">
                <div class="card-title-wrapper">
                    <div class="title">
                        ${s.shortTitle}
                    </div>
                    <div class="time-view-wrapper">
                        <div class="time">⏰ ${s.date}</div> 
                        <div class="view-count">👁️‍🗨️${s.viewCount}</div>   
                    </div>
                </div>
            
                <div class="content">
                    ${s.shortContent}
                </div>
            </div>
            
            <!-- 취소 버튼 -->
        <div class="cancel-btn">
            <div class="btn-bg">x</div>
        </div>
        
        </c:forEach>
    </div>
        


    </div>



    <script>
        const $addBtn=document.querySelector(".add-btn");
        $addBtn.addEventListener('click',e=>{
            window.location.href = '/self/write';
        })

        const $list=document.querySelector(".card-container");
        $list.addEventListener('click',e=>{
            window.location.href = '/self/detail';
        })

    </script>
</body>
</html>