package com.eaxmple.hello_project._3jdbc.controller;

import com.eaxmple.hello_project._3jdbc.dto.TodoDTO;
import com.eaxmple.hello_project._3jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name="todoUpdateController2", urlPatterns = "/todo/update2")
public class TodoUpdateController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //수정폼 , doGet
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // 수정할 tno 번호를 이용해서 수정폼 화면으로가기 , 하나의 정보를 조회해서 화면에 보내기
            Long tno = Long.parseLong(request.getParameter("tno"));
            // 하나의 정보 조회
            TodoDTO todoDTO = todoService.getByTno(tno);
            //수정 폼에 하나 조회한 정보를 전달하기 , 화면에 데이터 전달
            request.setAttribute("dto",todoDTO);
            //화면 전달
            request.getRequestDispatcher("/WEB-INF/todo/todoUpdate.jsp").forward(request,response);
        }catch(Exception ex){
            log.info(ex.getMessage());
            throw new ServletException("수정폼 조회 에러");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 한글 처리 임시 방편
//        request.setCharacterEncoding("UTF-8");
        //체크박스 처리 여부.
        //쿼리로 넘어온것들은 전부다 문자열임 true이면"on"이 들어옴
        String finishedStr = request.getParameter("finished");

        //화면에서 수정 처리를 하겠다. 수정할 내용을 전달 받음
        //1. 전달받은 데이터 확인
        Long getTno = Long.parseLong(request.getParameter("tno"));
        String getTitle = request.getParameter("title");
        LocalDate getDueDate = LocalDate.parse(request.getParameter("dueDate"), dateTimeFormatter);
        Boolean getFinished = finishedStr != null && finishedStr.equals("on");

        //모델클래스, TodoDTO에 담아서 서비스에 전달
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(getTno)
                .title(getTitle)
                .dueDate(getDueDate)
                .finished(getFinished)
                .build();

        log.info("TodoUpdateController 에서 doPost작업중 .");
        log.info("화면에서 전달 받은 데이터를 모델 TodoDTO에 담기 확인 " +todoDTO);

        //서비스에 전달
        try{
            todoService.modify(todoDTO);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        response.sendRedirect("/todo/list2");
    }

    // 로직처리 doPost
}
