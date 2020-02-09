package com.skiffin.tellsytest.controller;

import com.skiffin.tellsytest.model.Question;
import com.skiffin.tellsytest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @MessageMapping("/question.send")
    @SendTo("/topic/question")
    public Optional<Question> sendMessage(@Payload final Question question){
        UUID newId = questionService.addQuestion(question);
        return questionService.getQuestionById(newId);
    }

    @MessageMapping("/question.like")
    @SendTo("/topic/like")
    public Optional<Question> like(@Payload final Question question){
        questionService.likeQuestion(question.getId());
        return questionService.getQuestionById(question.getId());
    }
}


























