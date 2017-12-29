package jaksiemasz.edu.shouter.api.user.request;

import jaksiemasz.edu.shouter.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterRequest {

    @NotNull
    @Size(min = 4, max = 16)
    private String nickname;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Pattern(regexp = User.emailRegex)
    private String email;

    public User extractUser() {
        return new User(nickname, email, password);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
