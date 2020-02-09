package com.skiffin.tellsytest.service;

import com.skiffin.tellsytest.dao.QuestionDao;
import com.skiffin.tellsytest.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public UUID addQuestion(Question question) {
        return questionDao.insertQuestion(question);
    }
    public Optional<Question> getQuestionById(UUID id) {
        return questionDao.getQuestionById(id);
    }

    public void likeQuestion(UUID id) {
        Question old = getQuestionById(id).get();
        questionDao.updateQuestion(id, old.getContent(), old.getLikeCount() + 1);
    }
}
