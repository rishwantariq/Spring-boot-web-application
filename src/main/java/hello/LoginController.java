package hello;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Scanner;


@RestController
public class LoginController {

    static String extractPostRequestBody(HttpServletRequest request) throws IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String login(@RequestBody User user,
                        HttpServletRequest request,
                        HttpServletRequest response,
                        HttpSession session) {
        {
            if ((request.getAttribute("IsValidRequest") != null) && request.getAttribute("IsValidRequest").equals("false")) {
                return "Invalid Request! Token Mismatch Error!";
            }

            String headerPassword = request.getHeader("password").trim();


            if (session.isNew()) {
                session.setMaxInactiveInterval(100);
                for (User u : repository.findAll()) {
                    if (u.getUsername().equals(user.getUsername().trim())) {
                        if (bCryptPasswordEncoder.matches(headerPassword, u.getPassword())) {
                            System.out.println("Valid Credentials");
                            if (!user.isLoggedIn()) {
                                user.setLoggedIn(true);
                                repository.save(user);
                                session.setAttribute("token", "randomtoken" + user.getUsername());
                                return "User Logged In Successfully!";
                            } else {
                                return "User Already Logged In";
                            }
                        } else
                            return "Password Incorrect Error!!";
                    }
                }
                return "User Name Not Found";
            }

            return "User Already Logged In";
        }
    }
}