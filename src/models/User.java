package models;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String confirm_password;
    private String roles;


    public User(){}
    public User(int id,String username,String email,String roles){
        this.id=id;
        this.username=username;
        this.email=email;
        this.roles=roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    @Override
    public String toString() {
        String s = '{' +
                "\"email\":\"" + email + "\"," +
                "\"username\":\"" + username + "\"," +
                "\"password\":\"" + password + "\"," +
                "\"confirm_password\":\"" + confirm_password + "\"}";
        return s;
    }
}
