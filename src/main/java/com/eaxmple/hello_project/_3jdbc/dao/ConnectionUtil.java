package com.eaxmple.hello_project._3jdbc.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum ConnectionUtil {
    INSTANCE;
    //히카리 CP 설정 불러오기
    private HikariDataSource ds;
    //생성자 만들기
    ConnectionUtil(){
        // 단위 테스트에 사용했던 설정을 재사용 하기
        HikariConfig config = new HikariConfig();
        //2. 어느 디비 서버에 연결 할지 정보를 등록함
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        //3. 옵션 설정, 캐시, 크기 , 제한량,
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // 위의 설정을 적용한 객체에 담기
        ds = new HikariDataSource(config);
    }

    //위에서 히카리 설정된 내용을 연결 객체에 설정후 적용하기
    // 메서드만 호출하면 쉽게 디비 연결 도구로 사용 가능
    public Connection getConnection() throws Exception{
        return ds.getConnection();
    }
}
