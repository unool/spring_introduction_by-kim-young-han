package com.example.springdatajpa.domain;

import java.util.Collection;

public class Member<E> {

    private Long id;
    private  String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void ss(Collection<? extends E> c)
    {


    }


    public void ssa(Collection<?> c)
    {


    }

}
