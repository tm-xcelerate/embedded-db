package org.temadison.embeddb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temadison.embeddb.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
