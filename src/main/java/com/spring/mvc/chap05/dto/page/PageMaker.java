package com.spring.mvc.chap05.dto.page;

import lombok.Getter;
import lombok.ToString;

//페이징 알고리즘 처리
@Getter @ToString
public class PageMaker {
    //한번에 그려낼 페이지 수
    //1~5,6~10 ? / 1~10,11~20 ? -> 어떻게 할거야?
    private static final int PAGE_COUNT=5; //다음에 우리 페이지 이동 버튼 10까지 보여주자 하면 이것만 고치면 됨

    //화면 렌더링시 페이지의 시작값과 끝값
    private int begin, end, finalPage; //알고리즘에 의해 내부에서 만들어짐
    
    //이전, 다음 버튼 활성화 여부
    private boolean prev, next; //알고리즘에 의해 내부에서 만들어짐

    //현재 요청 페이지 정보
    private Page page; //외부(브라우저)에서 받아옴

    //총 게시물 수
    private int totalCount; //외부(db)에서 받아옴


    public PageMaker(Page page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;
        makePageInfo();
    }

    //페이지 계산 알고리즘
    private void makePageInfo(){
        //1. end값 계산
        //올림 처리(현재 위치한 페이지번호 / 한 화면에 배치할 페이지 수) * 한 화면에 배치할 페이지 수
        this.end= (int)(Math.ceil(page.getPageNo()/(double)PAGE_COUNT)*PAGE_COUNT);
        //page.getPageNo()/PAGE_COUNT -> 둘다 int 타입이므로 나누기해도 int타입되니까 올림 안됨. 그래서 한쪽을 double로 형변환

        //2. begin값 계산
        this.begin = this.end - PAGE_COUNT + 1;

        /*
        - 총 게시물수가 237개고, 한 화면당 10개의 게시물을 배치하고 있다면
          페이지 구간은

          1 ~ 10페이지 구간 : 게시물 100개
          11 ~ 20페이지 구간: 게시물 100개
          21 ~ 24페이지 구간: 게시물 37개 -- 위의 1,2단계만 하면 이 페이지도 게시물 개수를 100개로 잡음

        - 마지막 페이지 구간에서는 보정이 필요함.

        - 마지막 구간 끝페이지 보정 공식:
          올림처리(총 게시물 수 / 한 페이지당 배치할 게시물 수)
         */
        finalPage = (int) Math.ceil((double) totalCount / page.getAmount());

        //마지막 페이지 구간에서만 엔드보정이 일어나야 함
        if(finalPage < this.end) this.end= finalPage;

        //이전 버튼 활성화 여부
        //1~5 : 이전 버튼 없어야 함 , 그 외에는 다 있어야 함.
        this.prev = begin > 1;
        
        //다음 버튼 활성화 여부
        //마지막 페이지 보여주는 곳이면 필요없음. 그 외에는 다 필요
        this.next = end < finalPage;
    }
}
