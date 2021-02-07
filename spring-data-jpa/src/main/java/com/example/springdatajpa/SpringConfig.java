package com.example.springdatajpa;

import com.example.springdatajpa.repository.*;
import com.example.springdatajpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {

        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(jpaMemeberRepository());
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    @Bean
//    public JdbcMemberRepository jdbcMemberRepository() {
//        return new JdbcMemberRepository(dataSource);
//    }
//
//    @Bean
//    public JdbcTemplateMemberRepository jdbcTemplateMemberRepository() {
//        return new JdbcTemplateMemberRepository(dataSource);
//    }

    @Bean
    public JpaMemberRepository jpaMemeberRepository() {
        return new JpaMemberRepository(em);
    }
}
