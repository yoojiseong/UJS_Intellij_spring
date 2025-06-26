package com.eaxmple.hello_project._3jdbc.dao;

import com.eaxmple.hello_project._3jdbc.domain.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    //1. 테스트용으로 현재 시간을 가져오는 메서드 , DB서버에서 받아오는거임
    public String getTime(){
        String now = null;
        //디비 연결하기 , sql 전달, 값 가져오기, 자원반납
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select now()");
            ResultSet rs = pstmt.executeQuery();) {
            //rs, 가상 테이블, 디비의 결과 내용을 테이블 형식으로 가지고 있다.
            rs.next();
            now = rs.getString(1);

        } catch(Exception e){
            e.printStackTrace();
        }
        return now;
    } //getTime
    public String getTime2() throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement("select now()");
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next(); // 가상 테이블에서 데이터 가져오고,
        String now = rs.getString(1);
        return now;
    }

    //등록 기능 화면에서 todo의 내용을 전달 받아와서 -> 디비에 넣기
    // 화면에서 데이터를 각각 받는 것 보다 모델 클래스에 담아서 전달
    public void insert(TodoVO todoVO) throws Exception{
        //insert하는 sql문 작성
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?,?,?)";

        //디비에 연결할 객체 작성, @Cleanup사용(자원 반납)
        // 연결 객체, sql 문장 담는 개체
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, todoVO.getTitle());

        pstmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3, todoVO.isFinished());

        //실제 디비서버에 반영하기. 쓰기 작업
        pstmt.executeUpdate();
    }//insert기능

    //전체 조회
    public List<TodoVO> selectAll() throws Exception{
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        // 조회, ResultSet이 필료
        @Cleanup ResultSet rs = pstmt.executeQuery();

        //DB로부터 전달 받은 내용을 담아 둘 임시 리스트
        List<TodoVO> list = new ArrayList<>();

        //결과를 모델에 담아서 리스트에 담기
        while(rs.next()){
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();

            //모델을 리스트에 담기
            list.add(vo);
        }
        return list;
    }
    //하나만 선택하기
    public TodoVO selectOne(Long tno) throws Exception{
        String sql = "select * from tbl_todo where tno=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        // 조회, ResultSet이 필료
        pstmt.setLong(1, tno);
        @Cleanup ResultSet rs = pstmt.executeQuery();
        rs.next();
        TodoVO vo = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .build();
        return vo;
    }

    //삭제 기능
    public void deleteOne(Long tno) throws Exception{
        String sql = "delete from tbl_todo where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        // 조회, ResultSet이 필료
        pstmt.setLong(1, tno);

        pstmt.executeUpdate();
    }

    //수정기능
    public void updateOne(TodoVO todoVO) throws Exception{
        String sql = "update tbl_todo set title=?, dueDate=?, finished=? where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,todoVO.getTitle());
        pstmt.setDate(2,Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3,todoVO.isFinished());
        pstmt.setLong(4,todoVO.getTno());
        pstmt.executeUpdate();
    }
}
