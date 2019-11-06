package com.collabera.tourn2.repository;

import com.collabera.tourn2.model.Bracket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BracketRepository extends MongoRepository<Bracket, String> {
}
