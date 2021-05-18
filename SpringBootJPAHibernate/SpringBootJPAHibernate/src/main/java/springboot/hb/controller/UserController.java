package springboot.hb.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.hb.entity.User;
import springboot.hb.exception.ResourceNotFound;
import springboot.hb.repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repo;

	@GetMapping(value = "get/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(){
		return this.repo.findAll();
	}
	@GetMapping(value = "get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable(value = "id") long userId) {
		return this.repo.findById(userId).orElseThrow(()->new ResourceNotFound("User not found with "+userId));
	}
	@PostMapping(value = "/insert")//,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody User user) {
		return this.repo.save(user);
	}
	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user,@PathVariable(value = "id") long userId) {
		 User existedUser = this.repo.findById(userId).orElseThrow(()->new ResourceNotFound("User not found with : "+userId));
		 existedUser.setId(user.getId());
		 existedUser.setName(user.getName());
		 existedUser.setLocation(user.getLocation());
		 return this.repo.save(existedUser);
	}
	@DeleteMapping(value = "/delete/{id}")
	public Optional<User> deleteUser(@PathVariable(value = "id") long userId){
		Optional<User> user = repo.findById(userId);
		this.repo.deleteById(userId);
		return user;
	}
//	@Transactional
//	@DeleteMapping(value = "/deletebyname/{name}")
//	public ResponseEntity<User> deleteUser(@PathVariable(value = "name") String name){
//		this.repo.deleteByName(name);
//		return ResponseEntity.ok().build();
//	}
	@DeleteMapping(value = "/deletebyname")
	public String deleteByName() {
		String msg = repo.deleteUserByName();
		return msg;
	}
	
}
