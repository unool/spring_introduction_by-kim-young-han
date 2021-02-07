package com.example.springdatajpa.service;

import com.example.springdatajpa.domain.Member;
import com.example.springdatajpa.repository.MemberRepository;
import com.example.springdatajpa.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;



    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member)
    {

        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 있는 놈입니다");
                });


//        validateDuplicateMemeber(member);

        memberRepository.save(member);

        return member.getId();
    }

//    private void validateDuplicateMemeber(Member member) {
//        memberRepository.findByName(member.getName())
//                .ifPresent(m->{
//                    throw new IllegalStateException("이미 있는 놈입니다");
//                });
//    }

    public List<Member> findMembers(){

        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

    public void clearMembers(){
        memberRepository.clearAll();
    }


}

