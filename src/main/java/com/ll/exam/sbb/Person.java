package com.ll.exam.sbb;

import lombok.Data;

@Data
class Person {
    private static int lastId = 0;

    private final int id;
    private int age;
    private String name;

    public Person(int age, String name) {
        this.id = ++lastId;
        this.age = age;
        this.name = name;
    }
}
