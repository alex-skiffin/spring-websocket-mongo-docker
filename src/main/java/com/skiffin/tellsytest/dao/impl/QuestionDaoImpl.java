package com.skiffin.tellsytest.dao.impl;

import com.skiffin.tellsytest.dao.QuestionDao;
import com.skiffin.tellsytest.model.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private static List<Question> DB = new ArrayList<>();

    @Override
    public UUID insertQuestion(UUID id, Question question) {
        DB.add(new Question(id, question.getContent()));
        return id;
    }

    @Override
    public Optional<Question> getQuestionById(UUID id) {
        return DB.stream()
                .filter(question -> question.getId().equals(id))
                .findFirst();
    }

    @Override
    public void updateQuestion(UUID id, String content, Integer likeCount) {
        Optional<Question> old = getQuestionById(id);
        old.get().setLikeCount(likeCount);
    }
}
