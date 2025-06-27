package dao.service;


import com.eaxmple.hello_project._3jdbc.dto.MemberDTO;
import com.eaxmple.hello_project._3jdbc.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Log4j2
public class MemberServiceTests {
    // 서비스의 기능을 불러오기.
    private MemberService memberService;

    // 각 메서드가 실행 되기전 , 먼저 실행하는 메서드.
    @BeforeEach
    public void ready() {
        memberService = MemberService.INSTANCE;
    }

    @Test
    public void testLogin() throws Exception {
        String mid = "ujs";
        String mpw = "1234";
       MemberDTO memberDTO = memberService.login(mid, mpw);
       log.info("멤버서비스 테스트 : 로그인 확인 된 유저 :"+memberDTO);
    }

    // 업데이트 uuid
    @Test
    public void updateUuid() throws Exception {
        String mid = "ujs";
        String uuid = UUID.randomUUID().toString();
        memberService.updateUuid(mid, uuid);
    }

}
