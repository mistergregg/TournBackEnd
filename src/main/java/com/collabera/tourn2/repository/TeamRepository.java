package com.collabera.tourn2.repository;

import com.collabera.tourn2.model.Team;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {

    List<Team> findByNameStartsWith(String name, PageRequest of);
    Long countAllByNameStartsWith(String owner);

    List<Team> findAllByOwner(String owner, PageRequest of);

    @org.springframework.data.mongodb.repository.Query(value = "{ 'userList': { $elemMatch: { 'userId' : ?0 } }}")
    List<Team> findByUserId(String userId, PageRequest of);

    @org.springframework.data.mongodb.repository.CountQuery(value = "{ 'userList': { $elemMatch: { 'userId' : ?0 } }}")
    Long countAllByUserId(String userId);
}
