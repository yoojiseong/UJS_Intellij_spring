package com.eaxmple.hello_project._3jdbc.service;


import com.eaxmple.hello_project._3jdbc.dao.TodoDAO;
import com.eaxmple.hello_project._3jdbc.domain.TodoVO;
import com.eaxmple.hello_project._3jdbc.dto.TodoDTO;
import com.eaxmple.hello_project._3jdbc.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
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
        dao.insert(todoVO);
    }
    // 목록기능, 전체조회.
    public List<TodoDTO> listAll() throws  Exception {
        // DAO에서 , 디비에서 데이터 전체 조회 기능 있음. 일단, 이용하기.
        List<TodoVO> voList = dao.selectAll();
        log.info("현재 TodoService 작업중.listAll ");
        log.info("데이터 확인 : " + voList);


        // 전
        // TodoVO -> TodoDTO
//        List<TodoDTO> dtoListFor = new ArrayList<>();
//        for(int i = 0 ; i < voList.size(); i++){
//            TodoVO todoVO = voList.get(i);
//            TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
//            dtoListFor.add(todoDTO);
//        }

        // 후
        // 병렬 처리
        List<TodoDTO> dtoList
                = voList.stream().map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
        return dtoList;

    }
    //하나조회.
    // 전달 개요 : 화면 -> 컨트롤러(C) -> 서비스 (S):현위치 - > DAO() -> DB
    // 기능 만들고, -> 단위 테스트 하자.
    public TodoDTO getByTno(Long tno) throws Exception{
        log.info("TodoService : 하나 조회 기능 작업");
        // DAO 로 부터 전달 받은 데이터 타입 , TodoVO
        TodoVO todoVO = dao.selectOne(tno);
        //받은 VO -> DTO 변환하기.
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }


    //삭제 기능
    public void remove(Long tno) throws Exception{
        log.info("TodoService : 삭제 기능 작업, tno 번호 확인 : "+tno);
        dao.deleteOne(tno);
    }

    //수정기증
    public void modify(TodoDTO todoDto) throws Exception{
        log.info("TodoService : 수정 기능 작업");
        TodoVO todoVO = modelMapper.map(todoDto, TodoVO.class);
        dao.updateOne(todoVO);
    }
}
