package com.skiffin.tellsytest.dao;

import com.skiffin.tellsytest.model.Question;

import java.util.Optional;
import java.util.UUID;

public interface QuestionDao {

    UUID insertQuestion(UUID id, Question question);

    default UUID insertQuestion(Question question) {
        UUID id = UUID.randomUUID();
        return insertQuestion(id, question);
    }

    Optional<Question> getQuestionById(UUID id);

    void updateQuestion(UUID id, String content, Integer likeCount);
}
