package com.spring.mvc.self.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.self.dto.RequestDTO;
import com.spring.mvc.self.entity.Self;

import java.util.List;

public interface SelfMapper {

    // 게시물 목록 조회
    List<Board> findAll();

    // 게시물 상세 조회
    Self findOne(int boardNo);

    // 게시물 등록
    boolean save(Self self);

    //게시물 수정
    boolean modify(RequestDTO dto);

    // 게시물 삭제
    boolean deleteByNo(int boardNo);

    //조회수 상승
    void upViewCount(int boardNo);

    //전체 게시물 수
    int count();


}
