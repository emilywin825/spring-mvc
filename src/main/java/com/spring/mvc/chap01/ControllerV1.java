package com.spring.mvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

//어떤 요청들을 처리할지 공통 URL을 설계
@RequestMapping("/spring/*")
//이 클래스의 객체를 스프링이 관리하도록 빈을 등록
@Controller // @Component을 포함하는 더 확장된 개념
//new ControllerV1(); ->이걸 스프링이 알아서 만들어서 주입해주고 주기를 관리해줌
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

    //==================== <요청 파라미터 읽기 (Query String parameter)> ====================//

    //== 1. HttpServletRequest 사용하기 -> 잘안씀
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
    
    //==3. 커멘드 객체(OrderRequestDTO) 이용하기 -> 객체까지 포장해줌
    //==쿼리 스트링의 양이 너무 많은 경우 또는 연관성이 있을 경우
    // ==> ex) /spring/order?oNum=20230419007-P&goods=구두&amount=30&
    @RequestMapping("/order")
    public String order(OrderRequestDTO dto){
        //http://localhost:8181/spring/order?oNum=33&goods=%EC%83%81%ED%92%88%EA%B6%8C&amount=3&price=10000
        System.out.println("dto = "+dto); //dto = OrderRequestDTO(oNum=33, goods=상품권, amount=3, price=10000)
        //필요하면 getdto해서 쓰면 되겠죠!!
        return "";
    }

    //==4. URL에 경로로 붙어있는 데이터 읽기
    // ==> /spring/member/hong/107
    //  hong이라는 유저의 107번 게시글을 읽고싶음
//    @RequestMapping("/member/hong/107")
    @RequestMapping("/member/{userName}/{bNo}")
    public String member(
            //@PathVariable : /뒤에 있는거 읽음, 생략 불가
            @PathVariable String userName,
            @PathVariable long bNo
    ){
        //http://localhost:8181/spring/member/kim/9999
        System.out.println("userName = "+userName); //userName = kim
        System.out.println("bNo = "+bNo);//bNo = 9999
        return "";
    }

    //음식 선택 요청 처리
    // food.jsp에서 <form action="spring/food-select" method="get
//    @RequestMapping(value = "/food-select", method = RequestMethod.POST) //post만 받을 수 있음 / 아예 안쓰면 다 받을 수 있음
    @PostMapping("/food-select") //post만 받을 수 있음
    public String foodSelect(String foodName, String category){
        System.out.println("foodName = "+foodName);
        System.out.println("category = "+category);
        return "";
    }
}
