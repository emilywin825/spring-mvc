package com.spring.mvc;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/kakao-map")
    public String kakaoMap() {
        return "map";
    }
}
