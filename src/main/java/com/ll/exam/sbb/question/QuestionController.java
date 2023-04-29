package com.ll.exam.sbb.question;

import com.ll.exam.sbb.DataNotFoundException;
import com.ll.exam.sbb.answer.AnswerForm;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController {

    // @Autowired 필드 주입
    private final QuestionService questionService;
    private final UserService userService;


    @GetMapping("/list")
    // 이 자리에 @ResponseBody가 없으면 resources/templates/question_list.html 파일을 뷰로 삼는다.
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = questionService.getList(page);

        // 미리 실행된 question_list.html에서
        // questionList라는 이름으로 questionList 변수를 사용할 수 있다.
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);

        model.addAttribute("question", question);

        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(Model model, @Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()) {
            return "question_form";
        }

        SiteUser siteuser = userService.getUser(principal.getName());

        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteuser);

        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {

        Question question = questionService.getQuestion(id);
        if (question == null){
            throw new DataNotFoundException("%d번 질문은 존재하지 않습니다.");
        }
        if (!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }

        questionService.delete(question);
        return "redirect:/";
    }

}