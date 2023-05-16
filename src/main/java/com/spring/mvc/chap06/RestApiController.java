package com.spring.mvc.chap06;

import com.spring.mvc.jdbc.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/rests")
//@Controller
//@ResponseBody // 이렇게 붙여놓으면 response방식으로 처리됨
@RestController // == @Controller + @ResponseBody
public class RestApiController {

    @GetMapping("/hello")
    public String hello(){
        return "안녕하세요!"; //이렇게하면 안녕하세요.jsp를 찾아서 html로 변경해서 클라에게 내려줌
    }

    @GetMapping("/foods")

    public List<String> foods(){
        List<String> foodList=List.of("탕수육","족발","마라탕");
        return foodList;
    }

    @GetMapping("/person-list")
    public ResponseEntity<?> person(){
        Person p = new Person(1L,"루피",3);
        Person p2 = new Person(2L,"맹구",5);
        Person p3 = new Person(3L,"짱구",7);
        List<Person> personList=List.of(p,p2,p3);
        return ResponseEntity.ok().body(personList);
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(@RequestParam(required = false) Double cm
                                    ,@RequestParam(required = false) Double kg){
        if(cm==null || kg==null){
//            return ResponseEntity.status(401).build(); //응답 코드 커스텀
            return ResponseEntity.badRequest().body("키랑 몸무게 보내"); //응답 코드 커스텀

        }
        double bmi=kg/(cm/100) * cm/100;

        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits","melon");
        headers.add("hobby","soccer"); //헤더에 추가적인 정보 담아서 클라이언트에게 전송
        return ResponseEntity.ok()
                .headers(headers) //헤더 추가
                .body(bmi);
    }
}
