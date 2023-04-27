package com.spring.mvc.etc;

import lombok.*;

@Setter @Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor { //Employee 클래스에서 만들었던 거 간단하게 구현가능
    private String actorName;
    private int actorAge;
    private boolean hasPhone;
}
