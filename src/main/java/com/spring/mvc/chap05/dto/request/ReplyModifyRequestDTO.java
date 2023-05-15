package com.spring.mvc.chap05.dto.request;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ReplyModifyRequestDTO {
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE) // 반드시 정수 입력하도록 체크
    private Long bno;
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long rno;
    @NotBlank
    private String text;

    public Reply toEntity() {
        return Reply.builder()
                .replyNo(this.rno)
                .boardNo(this.bno)
                .replyText(this.text)
                .build();
    }

}
