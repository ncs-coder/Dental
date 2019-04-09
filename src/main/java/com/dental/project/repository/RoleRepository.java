package com.dental.project.repository;

import com.dental.project.model.user.Role;
import com.dental.project.model.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
//@EnableJpaRepositories(basePackages="com.gamingfunserver.project.repository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
