package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository <Question, Integer> {

    Question findBySubject(String subject);

    Question findBySubjectAndContent(String question, String content);

    List<Question> findBySubjectLike(String subject);

}

