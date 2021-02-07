package com.example.springdatajpa;

import com.example.springdatajpa.repository.JdbcMemberRepository;
import com.example.springdatajpa.repository.JdbcTemplateMemberRepository;
import com.example.springdatajpa.repository.MemberRepository;
import com.example.springdatajpa.repository.MemoryMemberRepository;
import com.example.springdatajpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){

        return new MemberService(jdbcTemplateMemberRepository());
    }

    @Bean
    public MemberRepository memberRepository()
    {

        return new MemoryMemberRepository();
    }

    @Bean
    public JdbcMemberRepository jdbcMemberRepository()
    {

        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public JdbcTemplateMemberRepository jdbcTemplateMemberRepository()
    {
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
