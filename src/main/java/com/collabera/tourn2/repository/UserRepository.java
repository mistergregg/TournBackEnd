package com.collabera.tourn2.repository;
import com.collabera.tourn2.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
    public List<User> findAllByUsername(String username);
    public List<User> findAllByEmail(String email);
}
