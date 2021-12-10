package testgroup.BOOT_prilozhenie.dao;

import testgroup.BOOT_prilozhenie.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> allRoles();

    void add(Role role);

    void delete(Role role);

    void edit(Role role);

    Role getById(int id);
}