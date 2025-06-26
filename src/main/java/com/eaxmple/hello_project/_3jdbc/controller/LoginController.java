package com.eaxmple.hello_project._3jdbc.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginController", urlPatterns = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        log.info("LoginController doGet 확인 중");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }
}
