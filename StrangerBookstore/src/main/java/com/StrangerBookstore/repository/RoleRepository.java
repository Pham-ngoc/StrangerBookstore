package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles getByRoleName(String name);

    @Query("select r from Roles r where r.roleId = ?1")
    Roles findByRoleId(int roleId);
}
