package hello;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Scanner;



@RestController
public class deleteController {


    @Autowired
    public UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String signup(@RequestParam(value = "Username", defaultValue = "null") String username,
                         HttpServletRequest request

    ) {

        String password = request.getHeader("password"); //recheck already entered password before allowing to update


        if (username != null) {
            for (User user : repository.findAll()) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    repository.delete(user);
                    repository.notifyAll();
                    return "deleted";
                }
            }
        }

        return "profile deleted";
    }
}

