package launchcode.org.Grocery.App.project.models;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    public User(){}

    public User (String username, String password){
        this.username = username;
        this.pwHash = password;
    }

    public String getUsername(){
        return username;
    }
}
