package com.eaxmple.hello_project._3jdbc.service;


import com.eaxmple.hello_project._3jdbc.dao.TodoDAO;
import com.eaxmple.hello_project._3jdbc.domain.TodoVO;
import com.eaxmple.hello_project._3jdbc.dto.TodoDTO;
import com.eaxmple.hello_project._3jdbc.util.MapperUtil;
import org.modelmapper.ModelMapper;

public enum TodoService {
    INSTANCE;
    //다른 기능 불러와서 사용할 준비
    //DB로직 처리하는 DAO기능 필요
    private TodoDAO dao;
    // DTO <-> VO, 변환 해주는 도구, 모델 맵퍼 기능 필요
    private ModelMapper modelMapper;
    TodoService(){
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //등록 기능
    //1.화면에서 데이터 입력 후 전달 -> 2.컨트롤러에서 데이터 받고 DTO에 담기
    // 3.DTO를 Service에 전달 -> 4.Service에서 받은 DTO를 VO로 변환 후 DAO에 전달
    // 5.DAO가 디비에 씀
    //현 위치 : 서비스
    // 해야할 역할 : DTO를 VO로 변환 후 DAO에 전달

    public void register(TodoDTO todoDto) throws Exception{
        System.out.println("TodoService에서 받은 데이터 확인 : "+todoDto);
        //DTO -> VO로 변환하는 방법 modelMapper사용
        TodoVO todoVO = modelMapper.map(todoDto, TodoVO.class);
        System.out.println("TodoService에서 변환한 데이터 확인 : "+todoVO);
        //서비스 -> DAO로 VO를 전달
    }
}
