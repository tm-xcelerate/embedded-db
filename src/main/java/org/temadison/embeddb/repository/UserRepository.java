package org.temadison.embeddb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.temadison.embeddb.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.email=?1 and u.password=?2")
	User login(String email, String password);

	User findByEmailAndPassword(String email, String password);

	User findUserByEmail(String email);

}
