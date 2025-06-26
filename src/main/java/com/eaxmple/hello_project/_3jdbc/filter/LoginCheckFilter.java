package com.eaxmple.hello_project._3jdbc.filter;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/todo/*")// todo로 시작되는 request를 모두 검사한다.
@Log4j2
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("LoginCheckFilter 에서, 작업중. ");
        //로그인 체크 규칙,
        //ServletRequest -> HttpServletRequest로 다운캐스팅 한다.
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        //세션 이용하는 도구 가져오기
        HttpSession session = req.getSession();

        //세션 키 : loginInfo , 값 : mid+mpw 이 존재하면 로그인이 된 상태
        // 세션 키-값 이 없으면 로그인이 안되어있다.
        if(session.getAttribute("loginInfo") == null){
            log.info("LoginCheckFilter에서 로그인 확인중 : loginInfo가 비어있음");
            resp.sendRedirect("/login");
            return;
        }


        //위의 검사 규칙(키-값이 있는지 확인)이 끝나고 이어서 진행하겠다. 라고 보면 됨
        filterChain.doFilter(request,response);
    }
}
