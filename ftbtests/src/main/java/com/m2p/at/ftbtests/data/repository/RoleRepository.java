package com.m2p.at.ftbtests.data.repository;

import com.m2p.at.ftbtests.data.model.Role;
import com.m2p.at.ftbtests.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<User> findByName(String rolename);

}
