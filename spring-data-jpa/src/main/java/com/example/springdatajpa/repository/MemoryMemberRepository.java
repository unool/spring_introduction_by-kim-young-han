package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        //store의 값들을 stream으로 만들어서 filter의 내용으로 거른것을 어떤 하나 찾으면 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());

    }

    @Override
    public void clearAll() {
        store.clear();
    }



}
