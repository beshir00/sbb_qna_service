package com.ll.exam.sbb;

import com.ll.exam.sbb.repository.AnswerRepository;
import com.ll.exam.sbb.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
@SpringBootTest
public class AnswerRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    void clearData(){
        QuestionRepositoryTest.clearData(questionRepository);
        questionRepository.disableForeignKeyCheck();
        answerRepository.truncate();
        questionRepository.enableForeignKeyCheck();
    }

    private void makeSampleData(){
        QuestionRepositoryTest.makeSampleData(questionRepository);
    }

    @BeforeEach
    void beforeEach(){
        clearData();
        makeSampleData();
    }

    @Test
    void 저장(){
        Question q = questionRepository.findById(2).get();

        Answer a = new Answer();
        a.setQuestion(q);
        a.setContent("네 자동으로 생성됩니다.");
        a.setCreateDate(LocalDateTime.now());
        answerRepository.save(a);

    }


}
