/**
 * 
 */
package org.temadison.embeddb.controller.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.temadison.embeddb.domain.User;
import org.temadison.embeddb.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class UserController {

	private final UserService userService;

	@Inject
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> findAll() {
		return this.userService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findUser(@PathVariable("id") int id) {
		return this.userService.findUserById(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody @Valid final User user) {
		User savedUser = this.userService.create(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody @Valid final User user) {
		User savedUser = this.userService.update(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
