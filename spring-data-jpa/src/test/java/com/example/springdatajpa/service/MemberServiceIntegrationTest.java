package com.example.springdatajpa.service;

import com.example.springdatajpa.domain.Member;
import com.example.springdatajpa.repository.MemberRepository;
import com.example.springdatajpa.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
//@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService service;



    @Test
    void join() {

        Member member = new Member();
        member.setName("오께바레");

        Long id = service.join(member);

        Member getMember = service.findOne(id).get();
        assertThat(getMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void findMembers() {
        Member member = new Member();
        member.setName("ni hao");

        Member member2 = new Member();
        member2.setName("ni hao");


        service.join(member);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 있는 놈입니다");

//        try {
//            service.join(member2);
//            fail();
//        }
//        catch (IllegalStateException e)
//        {
//            assertThat(e.getMessage()).isEqualTo("이미 있는 놈입니다");
//        }

    }


}