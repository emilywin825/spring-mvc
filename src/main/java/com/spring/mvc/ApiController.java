package com.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    @GetMapping("/kakao-map")
    public String kakaoMap() {
        return "map";
    }

    @GetMapping("/weather")
    public String test() {
        return "weather";
    }
}
