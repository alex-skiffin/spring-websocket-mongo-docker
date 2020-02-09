package com.skiffin.tellsytest.domain;

import com.skiffin.tellsytest.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository  extends MongoRepository<Question, Long> {

    Question findFirstById(UUID id);

    Question findByDomainAndDisplayAds(String domain, boolean displayAds);

    //Supports native JSON query string
    @Query("{id:'?0'}")
    Question findCustomById(UUID id);

    @Query("{id: { $regex: ?0 } })")
    List<Question> findCustomByRegExDomain(UUID id);
}
