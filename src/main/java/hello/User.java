package hello;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String Id;
    private String username;
    private String password;
    private String gender;
    private String dob;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    private boolean isLoggedIn;

    public User(String username, String password,String gender,String dob) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dob=dob;
        this.isLoggedIn = false;
    }

    public User()
    {
        this.username=null;
        this.password=null;
        this.gender=null;
        this.dob=null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDob(){
        return dob;
    }
    public String getGender() {return gender;}

    public void setUsername(String username)
    {
        this.username=username;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

    public void setDob(String dob)
    {
        this.dob=dob;
    }
    public void setGender(String g)
    {
        this.gender=g;
    }
}
