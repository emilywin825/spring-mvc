package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController //요거요거!!
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
//클라이언트의 접근ㅇ르 어떻게 허용할 것인가
@CrossOrigin(origins={"http://127.0.0.1:5500"})
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
    
    //댓글 등록 요청
    @PostMapping
//    @ResponseBody //응답할 때 JSON으로 보내줄게
    public ResponseEntity<?> create(
//            @RequestBody : 요청 메시지 바디에 JSON으로 보내주세요
            // @Validated : 검증할거야 ReplyPostRequestDTO에 @NotBlank,@Size(min=2, max=8)한거대로
            @Validated @RequestBody ReplyPostRequestDTO dto //이렇게 받으려면 GET방식에서는 ?text=어쩌고&bno=저쩌고 이렇게 줘야하고,
            // POST 방식은 form으로 줘야함 -> form은 html밖에 못보내는데 android,ios는 어떻게 보내?
            // -> 요청메서드 body에 json으로 담아서 = @RequestBody
            , BindingResult result //검증결과를 가진 객체
            ) {
        //입력값 검증에 걸리면 4xx 상태코드 리턴
        if(result.hasErrors()){ //@Validated에 걸렸을 때
            return ResponseEntity.badRequest()
                    .body(result.toString());
        }
        
        log.info("/api/v1/replies : POST! ");
        log.info("param : {} ", dto); //이런건 debug로 로그 찍는게 좋음

        // 서비스에 비즈니스 로직 처리 위임
        try {
            ReplyListResponseDTO responseDTO = replyService.register(dto);
            // 성공시 클라이언트에 응답하기
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            // 문제발생 상황을 클라이언트에게 전달
            log.warn("500 Status code response!! caused by: {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    //댓글 삭제 요청 -> DELETE 요청을 보내야지 restpull한 방식
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove( //@PathVariable : ?(물음표)로 들어오니까
            @PathVariable(required = false) Long replyNo
    ) {

        if(replyNo==null){
            return ResponseEntity.badRequest()
                    .body("댓글 번호를 보냇주세요!");
        }
        log.info("/api/v1/replies/{} DELETE!", replyNo);
        try {
            ReplyListResponseDTO responseDTO = replyService.delete(replyNo);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        //이 replyNo랑 위의   @DeleteMapping("/{replyNo}")랑 이름 같으면 @PathVariable("replyNo")의 replyNo안써도 됨
    }


    }
