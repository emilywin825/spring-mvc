package com.spring.mvc.chap03;
/*
        1번요청: 로그인 폼 화면 열어주기
        - 요청 URL : /hw/s-login-form : GET -> index.html에 이거 링크 걸기
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-form.jsp
        - html form: 아이디랑 비번을 입력받으세요.

        2번요청: 로그인 검증하기
        - 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공. 그 외에는 실패
        - 요청 URL : /hw/s-login-check : POST
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-result.jsp
        - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우

     */


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.PreparedStatement;


@Controller
@RequestMapping("/hw")
public class LoginController {
    @GetMapping("/s-login-form")
    public String loginForm(){
        System.out.println("/hw/s-login-form : GET 요청 발생!");
        return"chap03/s-login-form";
    }


    @PostMapping("/s-login-check")
    public String loginCheck(String userId, String userPwd, Model model){ //String userId, String userPwd, -> DTO로 받아도 됨.
        System.out.println("userId = "+userId);
        System.out.println("userPwd = "+userPwd);
        String result="";

        if(userId==""||userPwd==""){
            result="아이디 또는 비밀번호가 입력되지 않았습니다.";
        }else if(!(userId.equals("grape111")) || !(userPwd.equals("ggg9999"))){
            result="아이디 또는 비밀번호가 일치하지 않습니다!!";
        }else if(userId.equals("grape111")&&userPwd.equals("ggg9999")) {
            result="로그인성공!!!!";
        }

        model.addAttribute("result",result);
        return "chap03/s-login-check";
    }
}
