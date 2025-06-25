package com.eaxmple.hello_project._3jdbc.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE;

    //변환도구 불러오기
    //ModelMapper 외부 라이브러리에서 불러옴
    private ModelMapper modelMapper;

    MapperUtil() {
        // DTO <-> VO 2개의 모델 클래스의 변환하기 위한 설정
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                //private까지 접근 가능하게 한다
                .setMatchingStrategy(MatchingStrategies.STRICT);//깐깐하게 설정할건지 정함(깐깐하게)

    }
    public ModelMapper get(){
        return modelMapper;
    }
}
