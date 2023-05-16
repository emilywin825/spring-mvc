package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.dto.request.AutoLoginDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원가입 처리 서비스
    public boolean join(final SignUpRequestDTO dto, final String savePath) {

        // dto를 entity로 변환
        Member member = Member.builder()
                .account(dto.getAccount())
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encoder.encode(dto.getPassword()))
                .profileImg(savePath)
                .build();

        // 매퍼에게 회원정보 전달해서 저장명령
        return memberMapper.save(member);
    }
    
    //중복검사 서비스 처리
    public boolean checkSignUpValue(String type, String keyword){
        int flagNum = memberMapper.isDuplicate(type, keyword);

        return flagNum==1; //flagNum이 1이면 true,flagNum이 0이면 false
    }

    //로그인 검증
    public LoginResult authenticate(LoginRequestDTO dto,
                                    HttpSession session,
                                    HttpServletResponse response) {
        Member foundMember = memberMapper.findMember(dto.getAccount());

        //회원가입 여부 확인
        if (foundMember==null) {
            log.info("{} - 회원가입 안했음",dto.getAccount());
            return NO_ACC;
        }

        //비밀번호 일치 확인
        if(!encoder.matches(dto.getPassword(),foundMember.getPassword())){
// if(!dto.getPassword().equals(foundMember.getPassword())) -> 이렇게 하면 비교 안됨
//dto.getPassword()이게 암호화된 비번인데 어떻게 비교하냐고 안되지, 그래서 위에처럼 encoder.matches를 사용
//내부적으로 비교해서 true,false return 해줌            
            log.info("비밀번호 불일치!");
            return NO_PW;
        }
        //자동로그인 체크 여부 확인
        if(dto.isAutoLogin()){
            //1. 쿠키 생성 - 쿠키값에 세션아이디를 저장
            Cookie autoLoginCookie = new Cookie(LoginUtil.AUTO_LOGIN_COOKIE,session.getId());

            //2. 쿠키 셋팅 - 수명이랑 사용경로
             int limitTime = 60*60*24*90;
             autoLoginCookie.setMaxAge(limitTime);
             autoLoginCookie.setPath("/"); //전체 경로

            //3. 쿠키를 클라이언트에 응답 전송
            response.addCookie(autoLoginCookie);
            
            //4. DB에도 쿠키에 저장된 값과 수명을 저장
            memberMapper.saveAutoLogin(
                    AutoLoginDTO.builder()
                            .sessionId(session.getId())
                            .account(dto.getAccount())
                            .limitTime(LocalDateTime.now().plusDays(90))
                            .build()
            );
        }
        
        return SUCESS;
    }

    public void maintainLoginState(HttpSession session, String account) { //account : 계정 달라고 해서
        // 로그인이 성공하면 세션에 로그인한 회원의 정보들을 저장
        /*
            로그인시 클라이언트에게 전달할 회원정보(화면에 뿌릴 데이터)
            ex) 닉네임, 프로필사진, 마지막 로 그인 시간
         */
        // 현재 로그인한 사람의 모든 정보 (db에 있는 모든 정보)
        Member member = getMember(account);

        // dto : 현재 로그인한 사람의 화면에 보여줄 일부정보
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .account(member.getAccount())
                .nickName(member.getName())
                .email(member.getEmail())
                .auth(member.getAuth().toString())
                .profile(member.getProfileImg())
                .build();
        // 그 정보를 세션에 저장
        session.setAttribute(LoginUtil.LOGIN_KEY, dto);
        // 세션의 수명을 설정
        session.setMaxInactiveInterval(60 * 60); // 60 * 60 : 1시간, 기본 30분
    }


    //멤버 정보를 가져오는 서비스 기능
    public Member getMember(String account){
        return memberMapper.findMember(account);
    }

    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {
//        <자동로그인 해제하는 방법>
        //1. 자동로그인 쿠키를 가져온다
        Cookie c = WebUtils.getCookie(request, LoginUtil.AUTO_LOGIN_COOKIE);
//        2. 쿠키를 삭제한다.
//        -> 쿠키의 수명을 0초로 만들어서 다시 클라이언트에게 응답
        if(c!=null){
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
        }

        // 3. 데이터베이스에도 자동로그인을 해제한다.
        memberMapper.saveAutoLogin(
                AutoLoginDTO.builder()
                        .sessionId("none")
                        .limitTime(LocalDateTime.now())
                        .account(LoginUtil.getCurrentLoginMemberAccount(request.getSession()))
                        .build()
        );

    }
}