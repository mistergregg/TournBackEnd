package com.collabera.tourn2.service;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.model.UserToken;
import com.collabera.tourn2.repository.UserRepository;
import com.collabera.tourn2.security.JWTValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTValidator validator;

    @Autowired
    private TokenService tokenService;

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

    public UserToken createUser(User user)
    {
        if (!user.getFirstName().equals(""))
        {
            if (user.getFirstName().length() > 2)
            {
                if (!user.getLastName().equals(""))
                {
                    if (user.getLastName().length() > 2)
                    {
                        if (!user.getUsername().equals(""))
                        {
                            if (user.getUsername().length() > 2)
                            {
                                if (!user.getEmail().equals(""))
                                {
                                    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                                            "[a-zA-Z0-9_+&*-]+)*@" +
                                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                            "A-Z]{2,7}$";

                                    Pattern pat = Pattern.compile(emailRegex);

                                    if (pat.matcher(user.getEmail()).matches())
                                    {
                                        if (!user.getPassword().equals(""))
                                        {
                                            if (user.getPassword().length() > 3)
                                            {
                                                User tmp = userRepository.findByUsername(user.getUsername());

                                                if (tmp == null)
                                                {
                                                    userRepository.save(user);

                                                    return tokenService.generate(user);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return new UserToken();
    }

    public Boolean checkUser(User user)
    {
//        User aUser = validator.validate(user.getToken());
//
//        return !aUser.getUsername().equals("");

        return null;
    }

    public User validate(User user)
    {

//        return validator.validate(user.getToken());
        return null;
    }

    public User getUser(UserToken userToken)
    {
        User user = validator.validate(userToken.getToken());

        if(user.getUsername() != null)
        {
            Optional<User> opUser = userRepository.findById(user.getId());

            if(opUser.isPresent())
            {
                User retUser = opUser.get();
                retUser.setPassword("");
                return retUser;
            }
        }

        return new User();
    }

    public User updateUser(User user)
    {
        if (!user.getFirstName().equals(""))
        {
            if (user.getFirstName().length() > 2)
            {
                if (!user.getLastName().equals(""))
                {
                    if (user.getLastName().length() > 2)
                    {
                        if (!user.getUsername().equals(""))
                        {
                            if (user.getUsername().length() > 2)
                            {
                                if (!user.getEmail().equals(""))
                                {
                                    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                                            "[a-zA-Z0-9_+&*-]+)*@" +
                                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                            "A-Z]{2,7}$";

                                    Pattern pat = Pattern.compile(emailRegex);

                                    if (pat.matcher(user.getEmail()).matches())
                                    {
                                        if (!user.getPassword().equals(""))
                                        {
                                            if (user.getPassword().length() > 3)
                                            {
                                                Optional<User> opUser = userRepository.findById(user.getId());

                                                if(opUser.isPresent())
                                                {
                                                    User retUser = opUser.get();

                                                    if (user.getPassword().equals(retUser.getPassword()))
                                                    {
                                                        if (!user.getUsername().equals(retUser.getUsername()))
                                                        {
                                                            List<User> userList = userRepository.findAllByUsername(user.getUsername());

                                                            if (userList.size() < 1)
                                                            {
                                                                retUser.setUsername(user.getUsername());
                                                            }
                                                        }

                                                        if (!user.getEmail().equals(retUser.getEmail()))
                                                        {
                                                            List<User> userList = userRepository.findAllByEmail(user.getEmail());

                                                            if (userList.size() < 1)
                                                            {
                                                                retUser.setEmail(user.getEmail());
                                                            }
                                                        }

                                                        retUser.setLastName(user.getLastName());
                                                        retUser.setFirstName(user.getFirstName());

                                                        userRepository.save(retUser);
                                                    }

                                                    retUser.setPassword("");
                                                    return retUser;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        user.setPassword("");
        return user;
    }
}
