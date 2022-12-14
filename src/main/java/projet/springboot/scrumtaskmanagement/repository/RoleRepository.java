package projet.springboot.scrumtaskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.springboot.scrumtaskmanagement.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String user);
}
