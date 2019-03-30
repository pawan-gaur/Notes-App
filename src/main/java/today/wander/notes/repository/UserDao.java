package today.wander.notes.repository;

import org.springframework.data.repository.CrudRepository;
import today.wander.notes.model.User;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long>
{
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();
}
