package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UpdateProfileController {

    @Autowired
    public UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/update", method = RequestMethod.POST,consumes ="application/json", produces = "application/json")
    public String signup(@RequestBody User user,
                         HttpServletRequest request) {


        {

            String password = request.getHeader("newPassword"); //recheck already entered password before allowing to update
            String confirmPassword = request.getHeader("confirmNewPassword");

            if (password == null || confirmPassword == null) {
                return "Password Missing Error!!";
            }

            if (user.getUsername() != null) {
                for (User u : repository.findAll()) {
                    if (u.getUsername().equals(user.getUsername())) {


                        System.out.println("updated details to: ");
                        user.setPassword(request.getHeader("newPassword"));
                        user.setDob(user.getDob());
                        user.setGender(user.getGender());
                        return "User profile: \n" + "Username: " + user.getUsername() + "\n" + "gender: " + user.getGender()
                                + "\n" + "user date of birth: " + user.getDob();

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
            }

            return "profile updated";
        }
    }
}


