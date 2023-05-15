package com.spring.mvc.chap05.dto.request;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Setter @Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoLoginDTO {
    private String sessionId;
    private LocalDateTime limitTime;
    private String account;
}
