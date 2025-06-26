package com.eaxmple.hello_project._3jdbc.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        log.info("로그인 로직 처리, doPost");
        //로그인 화면에서 mid, mpw의 값을 가져오기
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        String str = mid+mpw;
        // 세션(서버의 임시 메모리 공간)에 키 : loginInfo , 값 : mid+mpw으로 저장하기
        HttpSession session = req.getSession();
        //키 : loginInfo , 값 : mid+mpw으로 저장
        session.setAttribute("loginInfo", str);
        resp.sendRedirect("/todo/list2");
    }
}
