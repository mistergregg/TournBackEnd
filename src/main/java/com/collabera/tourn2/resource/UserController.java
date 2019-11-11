package com.collabera.tourn2.resource;

import com.collabera.tourn2.model.User;
import com.collabera.tourn2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User checkLogin(@RequestBody User user, HttpServletRequest request)
    {
        String sessionUser = (String) request.getSession().getAttribute("user");
        sessionUser = (sessionUser == null) ? "" : sessionUser;

        if(sessionUser.equals(""))
        {
            User tmp = userRepository.findByUsername(user.getUsername());

            if(tmp != null)
            {
                if(tmp.getPassword().equals(user.getPassword()))
                {
                    tmp.setPassword("");
                    request.getSession().setAttribute("user", tmp.getUsername());
                    return tmp;
                }
            }
        }

        return new User();
    }

    @GetMapping("/check")
    public User check(HttpServletRequest request)
    {
        String sessionUser = (String) request.getSession().getAttribute("user");
        sessionUser = (sessionUser == null) ? "" : sessionUser;

        User tmp = new User();
        if(!sessionUser.equals(""))
        {
            tmp.setUsername(sessionUser);
        }

        return tmp;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        request.getSession().setAttribute("user", "");

        return "1";
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user, HttpSession httpSession)
    {
        String sessionUser = (String) httpSession.getAttribute("user");
        sessionUser = (sessionUser == null) ? "" : sessionUser;

        if(sessionUser.equals("")) {
            if (!user.getPassword().equals("")) {
                if (!user.getFirstName().equals("")) {
                    if (!user.getLastName().equals("")) {
                        if (!user.getUsername().equals("")) {
                            if (!user.getEmail().equals("")) {
                                User tmp = userRepository.findByUsername(user.getUsername());

                                if (tmp == null) {
                                    userRepository.save(user);
                                    user.setPassword("");
                                    return user;
                                }
                            }
                        }
                    }
                }
            }
        }
        return new User();
    }
}
