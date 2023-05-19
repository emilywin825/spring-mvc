<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 글쓰기</title>

    <%@ include file="../include/static-head.jsp" %>
    
    <style>
        
        
        .form-container {
            width: 500px;
            margin: auto;
            padding: 20px;
            background-image: linear-gradient(135deg, #a1c4fd, #fbc2eb);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 4px;
            font-size: 18px;
        }
        .form-container h1 {
            font-size: 40px;
            font-weight: 700;
            letter-spacing: 10px;
            text-align: center;
            margin-bottom: 20px;
            color: #ffffff;
        }
        .form-container h2 {
            font-size: 30px;
            color: #222;
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-size: 20px;
        }
        #title{
            font-size: 18px;
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 2px solid #ffffff;
            border-radius: 8px;
            margin-bottom: 10px;
            background-color: rgba(255, 255, 255, 0.8);
        }
        #content {
            height: 400px;
            font-size: 18px;
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 2px solid #ffffff;
            border-radius: 8px;
            margin-bottom: 10px;
            background-color: rgba(255, 255, 255, 0.8);
        }
        textarea {
            resize: none;
            height: 200px;
        }
        .buttons {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }
        button {
            font-size: 20px;
            padding: 10px 20px;
            border: none;
            margin-right: 10px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s;
        }
        button.list-btn {
            background: #e61e8c;
        }
        button:hover {
            background-color: #3d8b40;
        }
        button.list-btn:hover {
            background: #e61e8c93;
        }
        /* 페이지 css */
        /* 페이지 액티브 기능 */
        .pagination .page-item.p-active a {
            background: #333 !important;
            color: #fff !important;
            cursor: default;
            pointer-events: none;
        }
        .pagination .page-item:hover a {
            background: #888 !important;
            color: #fff !important;
        }
        
        /* 댓글 프로필 */
        .profile-box {
            width: 70px;
            height: 70px;
            border-radius: 50%;
            overflow: hidden;
            margin: 10px auto;
        }
        .profile-box img {
            width: 100%;
        }

        .reply-profile {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 10px;

        }
    </style>
</head>
<body>

    <%@ include file="../include/header.jsp" %>

    <div id="wrap" class="form-container">
        <h1>${b.boardNo}번 게시물 내용~ </h1>
        <h2># 작성일자: ${b.date}</h2>

        <label for="writer">작성자</label>
        <input type="text" id="writer" name="writer" value="${b.writer}" readonly>
        
        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="${b.title}" readonly>
        <label for="content">내용</label>
        <div id="content">${b.content}</div>
        <div class="buttons">
            <button class="list-btn" type="button" onclick="window.location.href='/board/list?pageNo=${s.pageNo}&type=${s.type}&keyword=${s.keyword}'">목록</button>
        </div>

       <!-- 댓글 영역 -->

       <div id="replies" class="row">
        <div class="offset-md-1 col-md-10">
            <!-- 댓글 쓰기 영역 -->
            <div class="card">
                <div class="card-body">

                    <c:if test="${empty login}">
                        <a href="/members/sign-in">댓글은 로그인 후 작성 가능합니다.</a>
                    </c:if>

                    <c:if test="${not empty login}">

                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label for="newReplyText" hidden>댓글 내용</label>
                                    <textarea rows="3" id="newReplyText" name="replyText" class="form-control"
                                        placeholder="댓글을 입력해주세요."></textarea>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <div class="profile-box">
                                        <c:choose>
                                            <c:when test="${login.profile != null}">
                                                <img src="${login.profile}" alt="프사">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="/assets/img/anonymous.jpg" alt="프사">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <label for="newReplyWriter" hidden>댓글 작성자</label>
                                    <input id="newReplyWriter" name="replyWriter" type="text"
                                        class="form-control" placeholder="작성자 이름"
                                        style="margin-bottom: 6px;" value="${login.nickName}" readonly>
                                    <button id="replyAddBtn" type="button"
                                        class="btn btn-dark form-control">등록</button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div> <!-- end reply write -->

                <!--댓글 내용 영역-->
                <div class="card">
                    <!-- 댓글 내용 헤더 -->
                    <div class="card-header text-white m-0" style="background: #343A40;">
                        <div class="float-left">댓글 (<span id="replyCnt">0</span>)</div>
                    </div>

                    <!-- 댓글 내용 바디 -->
                    <div id="replyCollapse" class="card">
                        <div id="replyData">
                            <!-- 
                            < JS로 댓글 정보 DIV삽입 > 
                        -->
                        </div>

                        <!-- 댓글 페이징 영역 -->
                        <ul class="pagination justify-content-center">
                            <!-- 
                            < JS로 댓글 페이징 DIV삽입 > 
                        -->
                        </ul>
                    </div>
                </div> <!-- end reply content -->
            </div>
        </div> <!-- end replies row -->

        <!-- 댓글 수정 모달 -->
        <div class="modal fade bd-example-modal-lg" id="replyModifyModal">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header" style="background: #343A40; color: white;">
                        <h4 class="modal-title">댓글 수정하기</h4>
                        <button type="button" class="close text-white" data-bs-dismiss="modal">X</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="form-group">
                            <input id="modReplyId" type="hidden">
                            <label for="modReplyText" hidden>댓글내용</label>
                            <textarea id="modReplyText" class="form-control" placeholder="수정할 댓글 내용을 입력하세요."
                                rows="3"></textarea>
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button id="replyModBtn" type="button" class="btn btn-dark">수정</button>
                        <button id="modal-close" type="button" class="btn btn-danger"
                            data-bs-dismiss="modal">닫기</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- end replyModifyModal -->
        
    </div>


    <script>
        // 댓글 관련 스크립트
        // 원본 글 번호
        const bno = '${b.boardNo}';
        // 댓글 요청 URI
        const URL = '/api/v1/replies';

        // 로그인한 회원 계정명
        //  '${login.account}';. '${login.auth}'; 세션에서 가져옴
        const currentAccount = '${login.account}';
        const auth = '${login.auth}';

        // 페이지 렌더링 함수
        function renderPage({
            begin, end, prev, next, page, finalPage
        }) {
            let tag = "";
            //이전 버튼 만들기
            if (prev) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (begin - 1) +
                    "'>이전</a></li>";
            }
            //페이지 번호 리스트 만들기
            for (let i = begin; i <= end; i++) {
                let active = '';
                if (page.pageNo === i) {
                    active = 'p-active';
                }
                tag += "<li class='page-item " + active + "'><a class='page-link page-custom' href='" + i +
                    "'>" + i + "</a></li>";
            }
            //다음 버튼 만들기
            if (next) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (end + 1) +
                    "'>다음</a></li>";
            }
            // 페이지태그 렌더링
            const $pageUl = document.querySelector('.pagination');
            $pageUl.innerHTML = tag;
            // ul에 마지막페이지 번호 저장.
            $pageUl.dataset.fp = finalPage;
        }
        // 페이지 클릭 이벤트 핸들러
        function makePageButtonClickEvent() {
            // 페이지 버튼 클릭이벤트 처리
            const $pageUl = document.querySelector('.pagination');
            $pageUl.onclick = e => {
                if (!e.target.matches('.page-item a')) return;
                e.preventDefault(); // 태그의 기본 동작 중단
                // 누른 페이지 번호 가져오기
                const pageNum = e.target.getAttribute('href');
                // console.log(pageNum);
                // 페이지 번호에 맞는 목록 비동기 요청
                getReplyList(pageNum);
            };
        }
        // 댓글 목록 렌더링 함수
        function renderReplyList({
            count, pageInfo, replies
        }) {
            // 총 댓글 수 렌더링
            document.getElementById('replyCnt').textContent = count;
            // 댓글 내용 렌더링
            // 각 댓글 하나의 태그
            let tag = '';
            if (replies === null || replies.length === 0) {
                tag += "<div id='replyContent' class='card-body'>댓글이 아직 없습니다! ㅠㅠ</div>";
            } else {
                for (let rep of replies) {
                    const {rno, writer, text, regDate, account : replyWriter, profile} = rep;
                    tag += "<div id='replyContent' class='card-body' data-replyId='" + rno + "'>" +
                        "    <div class='row user-block'>" +
                        "       <span class='col-md-3'>" +
                            (profile 
                            ? `<img class='reply-profile' src\${profile}' alt='profile'>` 
                            : `<img class='reply-profile' src='/assets/img/anonymous.jpg' alt='profile'>`) +
                        "         <b>" + writer + "</b>" +
                        "       </span>" +
                        "       <span class='offset-md-6 col-md-3 text-right'><b>" + regDate +
                        "</b></span>" +
                        "    </div><br>" +
                        "    <div class='row'>" +
                        "       <div class='col-md-6'>" + text + "</div>" +
                        "       <div et-md-2 col-md-4 text-right'>";
                    if (currentAccount === replyWriter || auth === 'ADMIN') {
                        tag +=
                            "         <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;" +
                            "         <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>";
                    }
                    tag += "       </div>" +
                        "    </div>" +
                        " </div>";
                }
            }
            // 생성된 댓글 tag 렌더링
            document.getElementById('replyData').innerHTML = tag;
            // 페이지 렌더링
            renderPage(pageInfo);
        }
        // 댓글 목록 불러오기 함수 
        function getReplyList(page=1) {
            fetch(`\${URL}/\${bno}/page/\${page}`)
                .then(res => res.json())
                .then(responseResult => {
                    // console.log(responseResult);
                    renderReplyList(responseResult);
                });
        }
        // 댓글 등록 처리 이벤트 함수
        function makeReplyRegisterClickEvent() {
            const $regBtn = document.getElementById('replyAddBtn');
            $regBtn.onclick = e => {
                const $rt = document.getElementById('newReplyText');
                const $rw = document.getElementById('newReplyWriter');
                // console.log($rt.value);
                // console.log($rw.value);

                //클라이언트 입력값 검증
                if($rt. value.trim()===''){
                    alert('댓글 내용은 필수입니다!');
                    return;
                }
                else if($rw.value.trim().length <2 || $rw.value.trim().length >8 ){
                    alert('댓글 작성자 이름 2~8자 사이로 작성하세요!');
                    return;
                }


                // # 서버로 보낼 데이터
                const payload = {
                    text: $rt.value,
                    author: $rw.value,
                    bno: bno
                };
                // # GET방식을 제외하고 필요한 객체
                const requestInfo = {
                    method: 'POST',
                    headers: {
                        'content-type': 'application/json'
                    },
                    body: JSON.stringify(payload)
                };
                // # 서버에 POST요청 보내기
                fetch(URL, requestInfo)
                    .then(res => {
                        if (res.status === 200) {
                            alert('댓글이 정상 등록됨!');
                            //입력창 비우기
                            $rt.value='';
                            // $rw.value='';

                            //마지막 페이지 번호
                            const lastPageNo = document.querySelector('.pagination').dataset.fp;
                            getReplyList(lastPageNo);
                        } else {
                            alert('댓글 등록에 실패함!');
                        }
                    });
            };
        }

        //댓글 삭제 + 수정 모달 이벤트 처리 함수
        function replyRemoveClickEvent(){
            const $replyData = document.getElementById('replyData');

            $replyData.onclick = e=>{
                    e.preventDefault();

                    //삭제할 댓글의 PK값 읽기
                    const rno=e.target.closest('#replyContent').dataset.replyid;

                    // 버블링 걸린 곳에서 replyDelBtn 아이디를 가진 버튼 클릭시에만 이벤트 처리 일어나도록
                    if(e.target.matches('#replyDelBtn')){
                        console.log('삭제버튼 클릭!!');
                        
                        if(!confirm('정말 삭제합니까?')) return;

                        //서버에 삭제 비동기 요청
                        fetch(URL+'/'+rno,{
                            method : 'DELETE',
                        }).then(res=>{
                            if(res.status===200){
                                alert('댓글이 정상 삭제됨!');
                                return res.json();
                            } else{
                                alert('댓글 삭제 실패');
                            }
                        }).then(responseResult =>{
                                renderReplyList(responseResult);
                        });
                    } else if(e.target.matches('#replyModBtn')) {
                        console.log('수정 화면 진입!');
                        //클릭한 수정 버튼 근처에 있는 텍스트 읽기
                        const replyText = e.target.parentElement.previousElementSibling.textContent;
                        // console.log(replyText);

                        //모달에 모달바디에 textarea에 읽은 텍스트를 삽입
                        document.getElementById('modReplyText').value=replyText;

                        //다음 수정완료 처리를 위해 미리 수정창을 띄울 때 댓글번호를 모달에 붙여놓자
                        const $modal = document.querySelector('.modal');
                        $modal.dataset.rno = rno;
                    }
            };
        }

        //서버에 수정 비동기 요청 처리 함수
        function replyModifyClickEvent() {

const $modBtn = document.getElementById('replyModBtn');

$modBtn.onclick = e => {

    const payload = {
        rno: +document.querySelector('.modal').dataset.rno,
        bno: +bno,
        text: document.getElementById('modReplyText').value
    };

    // console.log(payload);

    fetch(URL, {
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(payload)
    }).then(res => {
        if (res.status === 200) {
            alert('댓글이 정상 수정되었습니다!');
            // 모달창 닫기
            document.getElementById('modal-close').click();
            return res.json();
        } else {
            alert('댓글 수정에 실패했습니다.');
        }
    }).then(result => {
        renderReplyList(result);
    });
};
}

        //========= 메인 실행부 =========//
        (function() {
            // 첫 댓글 페이지 불러오기
            getReplyList();

            // 페이지 버튼 이벤트 등록
            makePageButtonClickEvent();

            // 댓글 등록 이벤트 등록
            makeReplyRegisterClickEvent();

            //삭제 이벤트 등록
            replyRemoveClickEvent();

            //수정 이벤트 등록
            replyModifyClickEvent();


        })();
    </script>

</body>
</html>
