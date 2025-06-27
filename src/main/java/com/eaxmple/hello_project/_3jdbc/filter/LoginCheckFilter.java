package com.eaxmple.hello_project._3jdbc.filter;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

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

        //자동 로그인(uuid)만들기 전
//        if(session.getAttribute("loginInfo") == null){
//            log.info("LoginCheckFilter에서 로그인 확인중 : loginInfo가 비어있음");
//            resp.sendRedirect("/login");
//            return;
//        }
        //uuid만들고 난 후
        //===================================================================
        //loginInfo가 있다면 if아래 코드 실행
        if(session.getAttribute("loginInfo") != null) {
            //loginInfo 정보가 있다면 계속 진행하기(리스트로 가려다가 필터에 걸려서 검사 당함. 다시 리스트로 가기
            filterChain.doFilter(request,response);
        }
        //loginInfo가 없다면
        // 쿠키를 체크(uuid들어가있는 쿠키 찾기)
        Cookie cookie = findCookie(req.getCookies(), "remember-me");

        //===================================================================


        //위의 검사 규칙(키-값이 있는지 확인)이 끝나고 이어서 진행하겠다. 라고 보면 됨
        filterChain.doFilter(request,response);
    }

    //todoReadController에 있는 건데 잠시 가져와서 씀
    // 나중에 많이 사용시 파일 분리해서 사용
    private Cookie findCookie(Cookie[] cookies, String findCookieName) {
        //쿠키가 없을 경우
        if(cookies != null || cookies.length > 0) {
            return null;
        }
        //전체 쿠키의 목록이 존재한다면
        //Optional 값이 있으면 값을 넣고 없으면 null이 들어가게됨
        Optional<Cookie> result = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(findCookieName))
                .findFirst();
        return result.isPresent() ? result.get() : null;

    }
}
