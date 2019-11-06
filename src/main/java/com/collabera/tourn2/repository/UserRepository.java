package com.collabera.tourn2.repository;
import com.collabera.tourn2.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
}
