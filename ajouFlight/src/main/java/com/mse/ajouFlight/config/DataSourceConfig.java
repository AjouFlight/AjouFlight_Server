package com.mse.ajouFlight.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
// 스프링 Iic Container에게 해당 클래스를 Bean 구성 class라고 알려주는 것
public class DataSourceConfig {

    @Bean
    //개발자가 직접 제어 불가능한 라이브러리를 활용할 때
    // 초기에 설정을 하기 위해 활용할 때
    public DataSource dataSource(){
        String username = "whereboy"; //
        String password = "where1234"; //
        String url = "jdbc:mysql://wheredatabase.cplrxb3kh41a.ap-northeast-2.rds.amazonaws.com:3306/mseAjou?characterEncoding=utf8";
        String driverClass = "com.mysql.jdbc.Driver";

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        dataSourceBuilder.url(url);
        dataSourceBuilder.driverClassName(driverClass);
        return dataSourceBuilder.build();
    }
}