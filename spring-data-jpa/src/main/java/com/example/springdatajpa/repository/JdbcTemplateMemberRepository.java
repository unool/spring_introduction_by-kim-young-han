package com.example.springdatajpa.repository;


import com.example.springdatajpa.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from Member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper() {
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getLong("id"));
//            member.setName(rs.getString("name"));
//            return member;
//        };

        return new RowMapper<Member>(){
            @Override
            public Member mapRow(ResultSet rs, int i) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };
    }
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from Member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = jdbcTemplate.query("select * from Member", memberRowMapper());
        return result;
    }

    @Override
    public void clearAll() {
        boolean result = true;
        try {
            jdbcTemplate.query("truncate table MEMBER", memberRowMapper());
        }catch (Exception e)
        {
            result = false;
            System.out.println(e.getMessage());
        }
    }
}
