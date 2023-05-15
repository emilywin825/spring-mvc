package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor //@Autowired는 필드 여러개라면 하나하나씩 다 붙여줘야 하지만 얘는 final이 붙어있다면 알아서 넣어줌
@RequestMapping("/board")
@Slf4j //로그 라이브러리 : log찍을 때 사용
public class BoardController {

    private final BoardService boardService;

    // 목록 조회 요청
    @GetMapping("/list")
    public String list(
            Search page,
            Model model,
            HttpServletRequest request) {

        /*boolean flag=false;

        //세션을 확인
        Object login = request.getSession().getAttribute("login");

        if(login!=null) flag=true;*/

        //쿠키를 확인
//        Cookie[] cookies=request.getCookies();
//        for (Cookie c: cookies) {
//            if(c.getName().equals("login")){
//                flag=true;
//                break;
//            }
//        }
//        if (!flag) return "redirect:/members/sign-in";

        // Page : 기본 생성자 만들고, setter로 넣음
        log.info("/board/list : GET");
        log.info("page : {}",page);
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList(page);

        //페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page,boardService.getCount(page));
        
        model.addAttribute("bList", responseDTOS);
        model.addAttribute("maker",maker); //페이징 정보를 줌
        model.addAttribute("s",page);  //키워드 검색후 입력한 검색어 계속 남아있도록
//        model.addAttribute("realEnd",maker.getRealEnd());
        return "chap05/list";
    }

    // 글쓰기 화면 조회 요청
    @GetMapping("/write")
    public String write(HttpSession session) {
        //로그인 안한 사람은 튕기겠다-> 인가 처리
/*        if(!LoginUtil.isLogin(session)){
            return "redirect:/members/sign-in";
        }*/

        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    // 글 등록 요청 처리
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto, HttpSession session) {
        System.out.println("/board/write : POST");
        boardService.register(dto,session);
        return "redirect:/board/list";
    }

    // 글 삭제 요청 처리
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }

    // 글 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int bno, @ModelAttribute("s") Search search, Model model) {
        System.out.println("/board/detail : GET");
        model.addAttribute("b", boardService.getDetail(bno));
//        model.addAttribute("s", search); //@ModelAttribute("s")이거 때문에 한줄 더 안 붙여도 됨
        return "chap05/detail";
    }

}
