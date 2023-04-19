package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//어떤 요청들을 처리할지 공통 URL을 설계
@RequestMapping("/spring/*")
//이 클래스의 객체를 스프링이 관리하도록 빈을 등록
@Controller // @Component을 포함하는 더 확장된 개념
//new ControllerV1(); -> 스프링이 알아서 만들어서 주입해주고 생애주기를 관리해줌
public class ControllerV1 {

    //세부요청들을 메서드를 통해 처리
    @RequestMapping("/hello") // 요청 이런식으로 해야 함 -> http://localhost:8181/spring/hello
    public String hello(){
        System.out.println("\n====== 헬로 요청이 들어옴! ======\n");

        //어떤 JSP를 열어줄지 경로를 적음
        return "hello"; // -> == /WEB-INF/views/hello.jsp
    }
    
    // /spring/food 요청이 오면 food.jsp를 열어보세요
    @RequestMapping("/food")
    public String food(){
        System.out.println("\n===== 푸드 요청이 들어옴! =====\n");

        return "chap01/food";
    }
    //==================== 요청 파라미터 읽기 (Query String parameter) ====================//
    //== 1. HttpServletRequest 사용하기
    // --> ex) /spring/person?name=kim&age=30   이렇게 요청오면 서버에서 어떻게 읽어올 것인가?

    @RequestMapping("/people")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name ="+name);
        System.out.println("age ="+age);
        return "";
    }

    //==2.@RequestParam 사용하기 -> 1번보다 훨신 간편
    // ==> ex) /spring/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major( //쿼리스트링과 이름을 같게 해야함
            String stu, //쿼리문이랑 이름 같고, defaultValue도 안할거면 @RequestParam 생략 가능
            @RequestParam("major") String mj, //지역변수랑 파라미터랑 이름 같으면? @RequestParam("major") 이렇게 쓰면 클라이언트가 major라고 보내면 mj에 넣음
            @RequestParam(defaultValue = "1") int grade //클라가 grade 전달 안했어? 그려면 defaultValue에 들어있는 값이 들어감 -> 옵션
    ){
        String major="전공전공";

        //http://localhost:8181/spring/major?stu=Kang&major=math이렇게 쿼리문 작성
        System.out.println("stu = "+stu); //stu = Kang
        System.out.println("major = "+mj);//major = math
        System.out.println("grade = "+grade);//grade = 1
        return "";
    }



}
