package launchcode.org.Grocery.App.project.models.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginFormDTO {

    @NotNull
    @NotBlank(message = "Field must not be blank.")
    @Size(min = 3, max = 20, message = "Username invalid. Must be between 3 and 30 characters.")
    private String username;

    @NotNull
    @NotBlank(message = "Field must not be blank.")
    @Size(min = 5, max = 20, message = "Password invalid. Must be between 5 and 30 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}