package launchcode.org.Grocery.App.project.data;

import launchcode.org.Grocery.App.project.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
