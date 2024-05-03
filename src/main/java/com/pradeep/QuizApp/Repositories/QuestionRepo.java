package com.pradeep.QuizApp.Repositories;

import com.pradeep.QuizApp.Entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Integer> {
    List<Questions> findByCategory(String category);
    @Query(value = "SELECT * FROM questions q where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Questions> findRandomQuestionByCategory(String category, int numQ);
}
