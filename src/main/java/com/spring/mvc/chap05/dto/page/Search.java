package com.spring.mvc.chap05.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Search extends Page{ //검색 필요한 곳에서는 Search 클래스 사용해서 밑에 필드 사용하고
    //검색 필요 없는 곳에서는 부모인 Page 사용해서 밑에 필드 사용안하게 사용
    
    // 검색 타입, 검색 키워드
    private String type;
    private String keyword;
    
    public Search(){
        this.type="";
        this.keyword="";
    }
}
