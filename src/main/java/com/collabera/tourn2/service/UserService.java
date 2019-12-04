package com.collabera.tourn2.service;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.repository.UserRepository;
import com.collabera.tourn2.security.JWTValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTValidator validator;

    public User loadUserByUsername(String user) throws UsernameNotFoundException
    {
        User tmp = userRepository.findByUsername(user);

        if(tmp != null)
        {
            tmp.setPassword("");

            return tmp;
        }

        return new User();
    }

    public User checkLogin(User user)
    {
        User tmp = userRepository.findByUsername(user.getUsername());

        if(tmp != null)
        {
            if(tmp.getPassword().equals(user.getPassword()))
            {
                tmp.setPassword("");
                return tmp;
            }
        }

        return null;
    }

    public User createUser(User user)
    {
        if (!user.getPassword().equals(""))
        {
            if (!user.getFirstName().equals(""))
            {
                if (!user.getLastName().equals(""))
                {
                    if (!user.getUsername().equals(""))
                    {
                        if (!user.getEmail().equals(""))
                        {
                            User tmp = userRepository.findByUsername(user.getUsername());

                            if (tmp == null)
                            {
                                userRepository.save(user);
                                user.setPassword("");
                                return user;
                            }
                        }
                    }
                }
            }
        }

        return new User();
    }

    public Boolean checkUser(User user)
    {
        User aUser = validator.validate(user.getToken());

        return !aUser.getUsername().equals("");
    }

    public User validate(User user)
    {
        return validator.validate(user.getToken());
    }
}
