package today.wander.notes.repository;

import org.springframework.data.repository.CrudRepository;
import today.wander.notes.model.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
