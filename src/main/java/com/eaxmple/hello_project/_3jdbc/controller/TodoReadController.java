package com.eaxmple.hello_project._3jdbc.controller;

import com.eaxmple.hello_project._3jdbc.dto.TodoDTO;
import com.eaxmple.hello_project._3jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController2", urlPatterns = "/todo/read2")
@Log4j2
public class TodoReadController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // 화면에서 쿼리 스트링 형식으로 전달
            // 예시) /todo/read2?tno=2, 무조건 문자열로 넘어옴.
            // 화면으로부터 전달 받은 tno 번호를 이용해서,
            // req.getParameter("tno") -> "2" 라는 결과값 타입은 문자열이다.
            // Long.parseLong(문자열) -> Long 타입으로 변환한다.
            Long tno = Long.parseLong(req.getParameter("tno"));

            // 서비스에 하나 조회를 요청 하기.
            TodoDTO todoDTO = todoService.getByTno(tno);

            // 데이터를 화면에 탑재.
            req.setAttribute("dto",todoDTO);
            //=======================================================================
            // 서버-> 웹 브라우저에게 쿠키에 저장해주세요 응답
            // 사용자가 하나 조회를 한 번호를 쿠키에 담을 예정

            //작업 개요.
            // 1. 쿠키 박스의 이름을 임의로 지정 : viewTodos
            // 2. 쿠키 박스에서 viewTodos가 없으면 새로 만들고, 있으면 쿠키에 기록하기
            // 3. 쿠키 박스의 존재 여부를 확인 할 기능이 필요함
            //=======================================================================
            // 화면 전달.
            req.getRequestDispatcher("/WEB-INF/todo/todoRead2.jsp").forward(req,resp);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    //쿠키 찾기 기능
    // 매개변수 1) cookies 쿠키 상자들이 들어 있는 전체 목록
    // 매개변수 2) 쿠키 상자의 이름 : viewTodos
    private Cookie findCookie(Cookie[] cookies, String findCookieName) {
        // 1. 찾을 쿠키를 담아둘 임시 쿠키 정의
        Cookie targetCookie = null;

        // 2. 전체 목록에서 반복문으로 쿠키 박스의 이름(viewTodos)가 있는지 조사
        // cookies에 데이터가 있는지 확인
        if(cookies!=null && cookies.length>0){
            for(Cookie ck : cookies){
                if(ck.getName().equals(findCookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }
        // 3. 찾고자 하는 쿠키 박스가 없으면 생성하기
        if(targetCookie==null){
            targetCookie = new Cookie(findCookieName,"");
            //쿠키의 적용 범위 지정 -> 전체로 지정
            targetCookie.setPath("/");
            // 쿠키의 유효 시간, 5분 초*분*시간
            targetCookie.setMaxAge(60*5);
        }
        return targetCookie;
    }
}
