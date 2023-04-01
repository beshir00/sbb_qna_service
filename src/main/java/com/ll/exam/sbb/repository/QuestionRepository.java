package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.Question;
import com.ll.exam.sbb.base.repository.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository <Question, Integer> , RepositoryUtil {

    Question findBySubject(String subject);

    Question findBySubjectAndContent(String question, String content);

    List<Question> findBySubjectLike(String subject);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)
    void truncate();

}

