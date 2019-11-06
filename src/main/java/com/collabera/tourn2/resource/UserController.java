package com.collabera.tourn2.resource;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User checkLogin(@RequestBody User user)
    {
        User tmp = userRepository.findByUsername(user.getUsername());

        if(tmp != null)
        {
            if(tmp.getPassword().equals(user.getPassword()))
            {
                return tmp;
            }
        }

        return new User();
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user)
    {
        if(!user.getPassword().equals(""))
        {
            if(!user.getFirstName().equals(""))
            {
                if(!user.getLastName().equals(""))
                {
                    if(!user.getUsername().equals(""))
                    {
                        if(!user.getEmail().equals(""))
                        {
                            User tmp = userRepository.findByUsername(user.getUsername());

                            if(tmp == null)
                            {
                                userRepository.save(user);
                                return userRepository.findByUsername(user.getUsername());
                            }
                        }
                    }
                }
            }
        }
        return new User();
    }
}
