package com.spring.mvc.self.repository;

import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.self.dto.RequestDTO;
import com.spring.mvc.self.dto.ResponseDTO;
import com.spring.mvc.self.entity.Self;

import java.util.List;

public interface SelfRepository {
    // 게시물 목록 조회
    List<Self> findAll();

    // 게시물 상세 조회
    Self findOne(int boardNo);

    // 게시물 등록
    boolean save(Self self);

    // 게시물 삭제
    boolean deleteByNo(int boardNo);

    //게시물 수정
    boolean modify(RequestDTO dto);
}
