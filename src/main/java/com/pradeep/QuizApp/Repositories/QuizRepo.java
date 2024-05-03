package com.pradeep.QuizApp.Repositories;

import com.pradeep.QuizApp.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
