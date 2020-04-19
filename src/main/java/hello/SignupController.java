package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class SignupController {

    @Autowired
    public UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json")
    public String signup(@RequestBody User user,
                         HttpServletRequest request) {


        {

            String password = request.getHeader("password");
            String confirmPassword = request.getHeader("confirmPassword");

            if (password == null || confirmPassword == null) {
                return "Password Missing Error!!";
            }

            if (user.getUsername() != null) {
                for (User u : repository.findAll()) {
                    if (u.getUsername().equals(user.getUsername())) {
                        System.out.println("Username Already Exists Error");
                        return "Username Already Exists Error";
                    }
                }
            }
            if (!password.equals(confirmPassword)) {
                System.out.println("Password Mismatch Error!!");
                //return appropriate response error message
                return "Password does not match Error!!";
            } else if (password.length() < 8) { //check password
                System.out.println("Weak Password Error");
                return "Password is short. Minimum 8 characters required";
            } else {
                System.out.println("Inserting User in database");
                //System.out.println("Inserting Record with \nUsername: " + username + "\tPassword: " + password);
                repository.save(new User(user.getUsername(), bCryptPasswordEncoder.encode(password), user.getGender(), user.getDob()));
                return "User Inserted Successfully!";
            }
        }
    }
}
