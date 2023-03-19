package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    List<Person> people = new ArrayList<>();

    @GetMapping("/addPerson")
    @ResponseBody
    public String addPerson(Person p) {
        people.add(p);

        return "%d번 사용자가 생성되었습니다.".formatted(p.getId());
    }

    @GetMapping("/getPerson/{id}")
    @ResponseBody
    public Person getPerson(@PathVariable int id){
        Person person = people
                .stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
        return person;
    }

}
