package model;

public class Password {
    private String username;
    private String webpage;
    private String p_username;
    private String p_password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public String getP_username() {
        return p_username;
    }

    public void setP_username(String p_username) {
        this.p_username = p_username;
    }

    public String getP_password() {
        return p_password;
    }

    public void setP_password(String p_password) {
        this.p_password = p_password;
    }

    @Override
    public String toString() {
        return "Acc.username: " + username +
                ", Webpage: " + webpage +
                ", Username: " + p_username +
                ", Password: " + p_password +
                "\n";
    }
}
