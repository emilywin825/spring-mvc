package com.spring.mvc.self.repository;

import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.self.dto.SelfDetailDTO;
import com.spring.mvc.self.entity.Self;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Repository //db와 관련된 작업을 처리
public class SelfRepositoryImpl implements SelfRepository{

    private final static HashMap<Integer, Self> map;
    private static int sequence;
    static{
        map=new HashMap<>();
        Self s1=new Self(++sequence,"title1","content1");
        Self s2=new Self(++sequence,"title2","content2");
        map.put(++sequence,s1);
        map.put(++sequence,s2);
    }
    @Override
    public List<Self> findAll() { //목록조회
        List<Self> list=map.values()
                .stream().sorted(comparing((Self self) -> self.getBoardNo()).reversed())
                .collect(toList());
        return list;
    }

    @Override
    public Self findOne(int boardNo) {  // 게시물 상세 조회
        return map.get(boardNo);
    }

    @Override
    public boolean save(Self self) {// 게시물 등록
        self.setBoardNo(++sequence);
        map.put(self.getBoardNo(),self);
        return true;
    }

    @Override
    public boolean deleteByNo(int boardNo) {  // 게시물 삭제
        return false;
    }
}
