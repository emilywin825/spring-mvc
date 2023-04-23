package com.spring.mvc.self.dto;

import lombok.*;

import java.util.Date;

@Getter
@ToString @EqualsAndHashCode
@RequiredArgsConstructor
public class SelfDetailDTO {

    private final int boardNo;
    private final String title;
    private final String content;
    private final String date;


}
