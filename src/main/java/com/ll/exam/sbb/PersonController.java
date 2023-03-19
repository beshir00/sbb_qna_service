package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}
