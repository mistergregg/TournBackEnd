package com.collabera.tourn2.repository;

import com.collabera.tourn2.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {
    public Team findByName(String name);
    public List<Team> findAllByName(String name);
}