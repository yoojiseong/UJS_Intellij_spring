package com.eaxmple.hello_project._3jdbc.controller;

import com.eaxmple.hello_project._3jdbc.dao.TodoDAO;
import com.eaxmple.hello_project._3jdbc.domain.TodoVO;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
@WebServlet(name = "todoUpdateController", value = "/todo/update")
public class TodoUpdateController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private TodoDAO todoDAO = new TodoDAO();
    private TodoVO todoVO = new TodoVO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try{
            List<TodoDTO> alllist = todoService.listAll();
            req.setAttribute("dtoList",alllist);
            Long tno = Long.parseLong(req.getParameter("tno"));

            if(tno != null){
                todoVO = todoDAO.selectOne(tno);
                req.setAttribute("todoVO",todoVO);
//                req.getRequestDispatcher("/WEB-INF/todo/todoUpd.jsp").forward(req,resp);
            }
            req.getRequestDispatcher("/WEB-INF/todo/todoUpd.jsp").forward(req, resp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno =  Long.parseLong(req.getParameter("tno"));
        String title = req.getParameter("title");
        LocalDate date = LocalDate.parse(req.getParameter("dueDate"), formatter);
        boolean finished = false;

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(tno)
                .title(title)
                .dueDate(date)
                .finished(finished)
                .build();
        log.info("TodoUpdateController 작업중, doPost 처리중./todo.update");
        log.info("수정한 데이터 확인 : " +todoDTO);
        try{

            todoVO = todoService.convertDto(todoDTO);
            todoDAO.updateOne(todoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list2");
    }
}
