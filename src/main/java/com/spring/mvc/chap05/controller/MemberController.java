package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import com.spring.mvc.util.upload.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static com.spring.mvc.util.LoginUtil.isAutoLogin;
import static com.spring.mvc.util.LoginUtil.isLogin;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    @Value("${file.upload.root-path}")
    private String rootPath;

    private final MemberService memberService;

    // 회원 가입 요청
    // 회원가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp() {
        log.info("/members/sign-up GET - forwarding to jsp");
        return "members/sign-up";
    }

    // 회원가입 처리 요청
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto) {
        MultipartFile profileImage = dto.getProfileImage();

        log.info("/members/sign-up POST ! - {}", dto);
        log.info("프로필사진 이름 : {}", dto.getProfileImage().getOriginalFilename());
        String savePath = null;
        if (!profileImage.isEmpty()) { //MultipartFile은 null이여도 객체 생성 -> 프사 등록안해도 이상한 이미지 자동으로 들어감. 그거 방지
//      실제 로컬 스토리에 파일을 업로드하는 로직
            savePath = FileUtil.uploadFile(dto.getProfileImage(), rootPath);
        }

        boolean flag=memberService.join(dto,savePath); //db에 들어가는 작업

//        return "redirect:/board/list";
        return "redirect:/members/sign-in";
    }

    //아이디, 이메일 중복검사
    //비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword){
        log.info("/memebers/c heck?type={}&keyword={} ASYNC GET!",type,keyword);
        boolean flag=memberService.checkSignUpValue(type,keyword);
        return ResponseEntity.ok().body(flag); // Spring에서 HTTP 응답을 나타내는 방법 중 하나
    }

    //로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn(HttpServletRequest request){
        log.info("/members/sign-in GET -forwarding to jsp");
        //요청 정보 헤더 안에는 Referer라는 키가 있는데
        //여기 값은 이페이지로 들어올 때 어디에서 왔는지에 대한
        //URI 정보가 기록되어 있음
        String referer = request.getHeader("Referer");
        log.info("referer : {}",referer);

        return "members/sign-in";
    }

    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto
                         // RedirectAttributes : 리다이렉션시 2번째 응답에 데이터를 보내기 위함
            , RedirectAttributes ra
            , HttpServletResponse response,
        HttpServletRequest request) {
            log.info("/members/sign-in POST ! - {}", dto);

            LoginResult result = memberService.authenticate(dto,request.getSession(),response);

        // 로그인 성공시
        if (result == SUCESS) {

            //서버에서 세션에 로그인 정보를 저장
/*          HttpSession session=request.getSession();
            로그인이 성공하면 session값이 생김
            session.setAttribute("login","메롱");
            -> 이렇게하면 controller가 싫어해... service가 하도록해야함
            */
            memberService.maintainLoginState(
//                  request.getSession() : 세션을 보냄
                    request.getSession(), dto.getAccount());
/*//            쿠키의 값으로는 String만 가능
            //쿠키 만들기
            Cookie loginCookie = new Cookie("login","홍길동");

            //쿠키 셋팅
            loginCookie.setPath("/"); //유효범위
            loginCookie.setMaxAge(60*60*24); //쿠키 수명 //60*60*24 : 하루

            //쿠키를 응답시에 실어서 클라이언트에게 전송
            response.addCookie(loginCookie);*/
            return "redirect:/";
        }

        // 1회용으로 쓰고 버릴 데이터
        ra.addFlashAttribute("msg", result);

        // 로그인 실패시
        return "redirect:/members/sign-in";
    }

    //로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(
            HttpServletRequest request,
            HttpServletResponse response){ //스프링에서 session 정보를 가져옴

//      브라우저별로 만들어진 session 객체를 가져옴
//        이렇게 받아도 되고, 매개변수에 signOut(HttpSession session) 이렇게 작성하면
//        spring에서 알아서 넣어줌
        HttpSession session = request.getSession();

        //로그인 중인지 확인
        if(isLogin(session)) {
            //자동로그인 상태라면 해제한다.
            if(isAutoLogin(request)){
                memberService.autoLoginClear(request,response);
            }


            //세션에서 login정보를 제거
            session.removeAttribute("login");

            //세션을 아예 초기화(세션만료 시간) -> 해주는게 좋음
            session.invalidate();
            return "redirect:/";
        }
        return "redirect:/members/sing-in";
    }

}