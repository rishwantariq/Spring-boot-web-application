package hello;

import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class ViewProfileController {


    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/viewprofile", method = RequestMethod.GET, produces = "application/json")
    public String viewProfile(@RequestBody User user,
                               HttpSession session) {
        System.out.println("View Profile Request!");

        if(session.isNew()){
            return "User not logged in error!!";
        }
        else{
            //get user balance from user repository
            for(User u: userRepository.findAll()){
                if(u.getUsername().equals(user.getUsername())){
                    System.out.println("User profile: " +"Username: " + u.getUsername() + "\n" +"gender: " + u.getGender()
                    +"\n" + "user date of birth: " + u.getDob() + "password: " + u.getPassword());
                    return "User profile: \n" +"Username: " + u.getUsername() + "\n" +"gender: " + u.getGender()
                            +"\n" + "user date of birth: " + u.getDob();
                }
                


            }

        }
        return "User profile viewed";
    }
}
