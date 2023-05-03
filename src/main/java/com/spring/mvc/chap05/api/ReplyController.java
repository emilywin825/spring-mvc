package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController //요거요거!!
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
public class ReplyController {
    private final ReplyService replyService; //service 주입받음
    
    //댓글 목록 조회 요청 : get
    //URL : /api/v1/replies/3/page/1 -> 3번 개시물에 대한 댓글주세요. 근데 그 중에서도 1페이지 댓글 주세요
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list ( @PathVariable long boardNo,
                                    @PathVariable int pageNo){
        log.info("/api/v1/replies/{}/page/{} : GET!!",boardNo,pageNo);

        Page page = new Page();
        page.setPageNo(pageNo);
        page.setAmount(10);//댓글 10개씩 보여줌

        ReplyListResponseDTO replyList
                = replyService.getList(boardNo, page);//ssr 방식에서는 model에 담아서 보냈는데

        return ok().body(replyList);//csr 방식에서는 그대로 json으로 보냄
    }
    
}
