package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    ReplyMapper replyMapper;

/*    @Test
    @DisplayName("게시물 300개 등록하고 각 게시물에 랜덤으로 1000개의 댓글을 나눠서 등록해야 함.")
    void  bulkInsetTest(){
        for (int i = 0; i <300; i++) {
            Board b = Board.builder()
                        .title("재밌는 게시물"+i)
                        .content("노잼 게ㅐ시물 내용 "+i)
                        .build();
            boardMapper.save(b);
        }
        assertEquals(300,boardMapper.count(new Search()));

        for (int i = 0; i < 1000; i++) {
            Reply r=Reply.builder()
                    .replyWriter("BOBBY"+i)
                    .replyText("김진환!!!"+i)
                    .boardNo((long)(Math.random()*300+1))
                    .build();
            replyMapper.save(r);
        }
    }*/
    
/*    @Test
    void bulkReplyInsert(){ //댓글 넣기
        for (int i = 0; i < 300; i++) {
            Reply reply = Reply.builder()
                    .replyText("페이지 댓글 내용" + i)
                    .replyWriter("망둥어")
                    .boardNo(298L)
                    .build();
            replyMapper.save(reply);

        }
    }*/
    
    
    
    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 3번 게시물의 총 댓글 수는 3개여야 한다.")
    @Transactional
    @Rollback //테스트 끝난 이후 롤백해라 -> 롤백안하면 한번 테스트 실행되면 db에 값 누적되니까. 몇번을 돌려도 계속 같은값이 나와야 잘 된 test
    void saveTest(){
        //given
        long boardNo=3L;
        Reply newReply = Reply.builder()
                .replyText("세이브세이브")
                .replyWriter("김동혁")
                .boardNo(boardNo)
                .build();
        //when
        boolean flag = replyMapper.save(newReply);

        //then
        assertTrue(flag); //세이브가 성공했을 것이라고 단언
        assertEquals(3,replyMapper.count(boardNo));
//        assertEquals("김동혁",replyMapper.findOne(boardNo).getReplyWriter());
    }

    @Test
    @DisplayName("댓글 번호가 1001번인 댓글을 삭제하면 3번 게시물의 총 댓글수가 1여야 한다.")
    @Transactional @Rollback
    void removeTest(){
        //given
        long replyNo=1001L;
        long boardNo=3L;

        //when
        boolean flag=replyMapper.deleteOne(replyNo);

        //then
        assertTrue(flag);
        assertEquals(1,replyMapper.count(boardNo));
    }

    @Test
    @DisplayName("999번 댓글의 내용을 수정한 후 " +
            "다시 조회했을 때 제목이 수정된 제목이어야 한다.")
    @Transactional @Rollback
    void modifyTest() {
        //given
        long replyNo = 999L;
        String newReplyText = "수정댓그을";
        Reply r = Reply.builder()
                .replyText(newReplyText)
                .replyNo(replyNo)
                .build();
        //when
        boolean flag = replyMapper.modify(r);
        //then
        assertTrue(flag);
        assertEquals(newReplyText, replyMapper.findOne(replyNo).getReplyText());
    }

    @Test
    @DisplayName("3번 게시물의 댓글 목록을 조회했을 때" +
            "리스트의 크기가 4이고, " +
            "0번 인덱스의 댓글작성자가 잼민이 69여야 한다.")
    void findAllTest() {
        //given
        long boardNo = 3L;
        //when
        List<Reply> replyList = replyMapper.findAll(boardNo, new Page());
        //then
        assertEquals(4, replyList.size());
        assertEquals("잼민이 69", replyList.get(0).getReplyWriter());
    }





}