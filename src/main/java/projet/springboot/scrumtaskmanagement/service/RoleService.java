package projet.springboot.scrumtaskmanagement.service;

import projet.springboot.scrumtaskmanagement.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> findAll();
}
