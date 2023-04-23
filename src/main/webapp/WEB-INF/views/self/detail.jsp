<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 글쓰기</title>

    <style type="text/css" media="screen, print">
        @font-face{
            font-family: "DungGeunMo-Regular";
            src: url('https://cdn.jsdelivr.net/gh/wooin21/web/fonts/etc/DungGeunMo-Regular.woff') format('woff');
            font-weight: normal; 
            font-style: normal;
        }
    </style>

    <link rel="stylesheet" href="/assets/css/self-write.css">
    
</head>
<body>
    
        <div id="wrap"class="form-container">
           <h1>MJ's 게시판 글쓰기</h1> 
           <form action="/self/write" method="post">
            <input type="hidden">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" required>
                <label for="content">내용</label>
                <textarea id="content" name="content" maxlength="200" required></textarea>
                <div class="buttons">
                    <button class="list-btn" type="button" onclick="window.location.href='/self/list'">목록</button>
                    <button type="submit">글쓰기</button>
                </div>
            </form>
        </div>

    <script>

    </script>
</body>
</html>