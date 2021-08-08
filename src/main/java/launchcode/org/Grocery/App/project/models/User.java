package launchcode.org.Grocery.App.project.models;

import org.dom4j.tree.AbstractEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//@NamedQuery(name = "User.findAllGroceryItemsById", query = "SELECT gi FROM grocery_item gi WHERE user_id = :id")
@Entity
@Table(name = "user")
public class User extends AbstractEntity {


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    private int id;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final List<GroceryItem> groceryItemList = new ArrayList<>();

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    public User(){}

    public User (String username, String password){
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername(){
        return username;
    }

    public List<GroceryItem> getGroceryItemList() {return groceryItemList;}


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
