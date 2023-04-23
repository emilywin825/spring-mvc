package com.spring.mvc.self.dto;

import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private String title;
    private String content;
}
