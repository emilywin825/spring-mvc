package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.taglibs.standard.tag.common.xml.XPathUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 목록 조회 요청
    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/board/list : GET");
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList();
        model.addAttribute("bList", responseDTOS);
        return "chap05/list";
    }

    @PostMapping("/list")
    public String list() {
        System.out.println("/board/list : POST");
        return "redirect:/board/list?";
    }

    @GetMapping("/write")
    public String wirte(){
        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    @PostMapping("/write")
    public String wirte(Model model){
        System.out.println("/board/write : POST");
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(){
        System.out.println("/board/remove : GET");
        return "redirect :/board/list";
    }

}