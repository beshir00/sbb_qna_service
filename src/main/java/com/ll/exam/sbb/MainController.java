package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @RequestMapping("/sbb")
    @ResponseBody
    public String index(){
        return "안녕하세요.";
    }

    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(int a, int b){
        return a+b;
    }

    @GetMapping("/minus")
    @ResponseBody
    public int showMinus(int a, int b){
        return a-b;
    }
    int increaseNo = -1;
    @GetMapping("/increase")
    @ResponseBody
    public int showIncrease(){
        increaseNo++;
        return increaseNo;
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String gugudan(Integer dan, Integer limit){

        if (dan == null){
            dan = 9;
        }
        if (limit == null){
            limit = 9;
        }
        Integer finalDan = dan;
        return IntStream.rangeClosed(1,limit)
                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, i*finalDan))
                .collect(Collectors.joining("<br>\n"));
    }
}
