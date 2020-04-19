package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class LogoutController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
    public String logout(@RequestBody String username, HttpSession session,
                         HttpServletRequest request){

        if(session.isNew()){
            session.invalidate();
            return "User Not Logged In!!";
        }
        else{
            for(User user: userRepository.findAll()){
                if(user.getUsername().equals(username)){
                    if(user.isLoggedIn()) {
                        user.setLoggedIn(false);
                        userRepository.save(user);
                    }
                    else{
                        return "User Not Logged In";
                    }
                }
            }
            session.invalidate();
            return "User Logged out Successfully";
        }
    }
}
