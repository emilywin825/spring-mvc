package com.spring.mvc.interceptor;

//인터셉터 : 하위 컨트롤러에 요청이 들어가기 전, 후에
//           공통으로 검사할 일들을 정의해놓는 클래스

//게시판 관련 인가 처리

import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.chap05.repository.BoardRepository;
import com.spring.mvc.util.LoginUtil;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.util.LoginUtil.isAdmin;
import static com.spring.mvc.util.LoginUtil.isLogin;

@Configuration
@Slf4j
@RequiredArgsConstructor
//implements HandlerInterceptor 이거 붙여야 스프링이 인터셉터라고 알게됨
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardMapper boardMapper;

    @Override
    public boolean preHandle(
                 HttpServletRequest request, //세션 정보 여기서 얻음
                 HttpServletResponse response,
                 Object Handler) throws  Exception{

        //로그인을 했는지 확인할 것임.

        //로그인을 안했으면 로그인페이지로 강제로 리다이렉션 할 것임.
        HttpSession session = request.getSession();
        if(!isLogin(session)){
            log.info("this request( {} ) denied!!", request.getRequestURI());
            response.sendRedirect("/members/sign-in");
            return false;
        }

        //삭제요청을 한다면 자기가 쓴글 또는 관리자인지 체크
        /*
        * 1. 우선 로그인계정과 삭제하려는게시물의 계정명이 일치해야함
        * 2. 로그인계정은 세션에서 구할 수 있음
        * 3. 삭제하려는 게시물의 계정은 어떻게 구함?
        *       -> 삭제 요청에는 글번호 정복가 있다.
        *       -> 글번호를 DB에서 조회해서 계정명을 얻어낸다.
        * */
        
        //삭제요청인지 확인
        String uri=request.getRequestURI();
        if(uri.contains("delete")) {

            //쿼리 파라미터 읽기
            int bno= Integer.parseInt(request.getParameter("bno"));

            //게시물 정보 읽기
            Board board=boardMapper.findOne(bno);

            String targetAccount = board.getAccount();

            //관리자 맞으면 그냥 통과
            if(isAdmin(session)) return true;

            //관리자 아니면 내꺼인지 확인
            if (!LoginUtil.isMine(session, targetAccount)) {
                response.sendRedirect("/access-deny");
                return false;
            }

        }
        
        log.info("board interceptor pass!");
        return true;
    }
}
