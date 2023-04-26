package com.spring.mvc.self.controller;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.self.dto.RequestDTO;
import com.spring.mvc.self.dto.ResponseDTO;
import com.spring.mvc.self.dto.SelfDetailDTO;
import com.spring.mvc.self.service.SelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/self")
@RequiredArgsConstructor
public class SelfController {
    private final SelfService selfService;

    @GetMapping("/list")
    public String list(Model model){
        System.out.println("/self/list : GET");
        List<ResponseDTO> responseDTOS = selfService.getList();
        model.addAttribute("sList",responseDTOS);
        return "self/list";
    }

    @PostMapping("/list")
    public String list(RequestDTO dto){
        System.out.println("/self/list : POST");
        selfService.modify(dto);
        return "redirect:/self/list";
    }

    @GetMapping("/write")
    public String write(){
        System.out.println("/self/write : GET");
        return "self/write";
    }

    @PostMapping("/write")
    public String write(RequestDTO dto){
        System.out.println("/self/write : POST");
        selfService.save(dto);
        return "redirect:/self/list";
    }

    @GetMapping("/detail")
    public String detail(int boardNo, Model model){
        System.out.println("/self/detail : GET");
        System.out.println(boardNo);
        model.addAttribute("sList",selfService.findOne(boardNo));
        return "self/detail";
    }

    @PostMapping("/delete")
    public String delete(){
        System.out.println("/self/delete : POST");

        return "redirect:/self/list";
    }

}
