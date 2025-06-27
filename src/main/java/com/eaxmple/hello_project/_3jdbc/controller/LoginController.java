package com.eaxmple.hello_project._3jdbc.controller;

import com.eaxmple.hello_project._3jdbc.dto.MemberDTO;
import com.eaxmple.hello_project._3jdbc.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "loginController", urlPatterns = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        log.info("LoginController doGet 확인 중");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        log.info("로그인 로직 처리 , doPost");
        //로그인 화면에서, mid, mpw 의 값을 가져오기,
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        //===========================================================
        // 로그인 화면에서, 자동로그인 체크시, 키: auto , 값 : "on" 여부 확인.
        String auto = req.getParameter("auto");
        boolean rememberMe = auto != null && auto.equals("on");
        log.info("LoginController, 화면에서 전달 받은 auto 의 on 확인 : " + auto);
        log.info("LoginController, 화면에서 전달 받은 auto 의 auto.equals(\"on\") rememberMe확인2 : " + rememberMe);
        if(rememberMe){ // 자동 로그인이 맞다면,
            // UUID : 랜덤하게 생성하는 문자열.
            // uuid 가 중복되지 않게, 랜덤한 문자열 생성하기.
            String uuid= UUID.randomUUID().toString();
            log.info("LoginController, uuid 샘플 확인 " + uuid);
            // 다음 시간에 이어서 하기.

        }
        //===========================================================
        // 전,
        // 값으로, mid+mpw, 문자열을 사용했고
//        String str = mid+mpw;
//        // 임시 로그인 구현, 서버의 세션을 이용해서,
//        // 서버의 임시 메모리 공간인 세션이라는 곳에, 키 : loginInfo , 값으로 : mid+mpw, 저장,
//        HttpSession session = req.getSession();
//        // 키 : loginInfo , 값으로 : mid+mpw, 저장
//        session.setAttribute("loginInfo", str);
//        resp.sendRedirect("/todo/list2");

        // 후
        // 디비에서, 로그인 처리를 한 유저를 , 담기 MemberDTO을 값으로 저장,
        try {
            // 로그인한 유저 정보가 디비에 있다면 가져오기
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);
            // 세션 도구 이용하고
            HttpSession session = req.getSession();
            // 세션(서버의 임시 메모리 저장소), 키 : loginInfo, 값 : memberDTO , 저장
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list2");
        }catch (Exception e){
            // 서버에서 -> 웹브라우저로 데이터 전달, 쿼리스트링 형식
            resp.sendRedirect("/login?result=error");
        }

    }

}
