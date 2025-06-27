package com.eaxmple.hello_project._3jdbc.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String mid;
    private String mpw;
    private String mname;
    //추가, 자동로그인uuid
    private String uuid;
}

