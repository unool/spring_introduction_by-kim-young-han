package com.example.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Test
    void contextLoads() {

        myList<child> list = new myList<>();


        List<parent> paraList = new ArrayList<>();


        list.myClass(paraList);

    }



    public static class myList<T>
    {
        public myList() {
        }

        public void myClass(List<? super T> l)
        {

        }
    }


    public static class parent
    {
        public int id = 0;

    }


    public static class child extends parent
    {
        public int id = 1;

    }


}
