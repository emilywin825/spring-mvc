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
            <div class="card-wrapper" data-bno="${s.boardNo}">
                <section class="click-section">    
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
            </section>
                    <!-- 삭제 버튼 -->
                <form action="/self/delete" method="post" class="cancel-btn" >
                    <button class="btn-bg">x</button>
                    <input type="hidden"  name="boardNo" value="${s.boardNo}">
                </form>
            </div>
        </c:forEach>
    </div>
        


    </div>



    <script>
        const $addBtn=document.querySelector(".add-btn");
        $addBtn.addEventListener('click',e=>{
            window.location.href = '/self/write';
        })

        const $list=document.querySelector(".click-section");
        $list.addEventListener('click',e=>{
            console.log(e.target.closest(".card-wrapper").dataset.bno);
            const bno= e.target.closest(".card-wrapper").dataset.bno;
            window.location.href = '/self/detail?boardNo='+bno;
        })

        const $cancelBtn=document.querySelector(".cancel-btn");


        $cancelBtn.addEventListener('click',e=>{
            console.log(e.target.closest(".card-wrapper").dataset.bno);

        })


    </script>
</body>
</html>