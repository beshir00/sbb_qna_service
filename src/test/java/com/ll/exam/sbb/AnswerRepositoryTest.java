package com.ll.exam.sbb;

import com.ll.exam.sbb.repository.AnswerRepository;
import com.ll.exam.sbb.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AnswerRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    void makeSampleData(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

    }

    void clearData(){
        questionRepository.disableForeignKeyCheck();
        answerRepository.truncate();
        questionRepository.enableForeignKeyCheck();
    }

    @BeforeEach
    void beforeEach(){
        clearData();
        makeSampleData();
    }


}
