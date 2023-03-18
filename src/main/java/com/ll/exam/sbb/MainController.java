package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
