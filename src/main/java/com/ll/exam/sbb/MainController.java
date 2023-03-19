package com.ll.exam.sbb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/test")
    @ResponseBody
    public String showMain(){
        return "안녕하세요.";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showGet(){
        return """
               <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이 입력" />
                    <input type="submit" value="page2로 POST방식으로 이동" />
               </form>
               """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age){
        return """
               <h1>입력된 나이 : %d</h1>
               
               """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요. POST 방식으로 오신걸 환영합니다.</h1>           
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public String plus(@RequestParam(defaultValue = "0") int a, int b){
        return """
               <h1>%d + %d = %d</h1>
               """.formatted(a, b, a+b);
    }
    @GetMapping("/minus")
    @ResponseBody
    public String minus(@RequestParam(defaultValue = "0") int a, int b){
        return """
               <h1>%d - %d = %d</h1>
               """.formatted(a, b, a-b);
    }

    int  increaseNo= -1;

    @GetMapping("/increase")
    @ResponseBody
    public String increase(){
        increaseNo++;
        return """
               <h1>%d</h1>
               """.formatted(increaseNo);
    }

    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession(HttpServletRequest req,@PathVariable String name,@PathVariable String value){

        HttpSession session = req.getSession();
        session.setAttribute(name, value);

        return "세션 변수 %s의 값이 %s(으)로 설정되었습니다.".formatted(name, value);

    }

    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String getSession(HttpSession session,@PathVariable String name){

        String value = (String) session.getAttribute(name);

        return "세션 변수 %s의 값은 %s 입니다.".formatted(name, value);

    }


}
