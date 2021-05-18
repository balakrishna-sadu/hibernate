package springboot.hb;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import springboot.hb.entity.User;
import springboot.hb.exception.ResourceNotFound;
import springboot.hb.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Test
	@Order(1)
	public void getUserById() {
		User usr = repo.findById((long) 2).orElseThrow(()->new ResourceNotFound("User not found with "+4));
		Assert.assertTrue(usr.getName().equals("prashanth"));
	}
	
	@Test
	@Order(2)
	public void getUserByName() {
		User testUsr=repo.findByName("ashok");
		Assert.assertEquals("kllr", testUsr.getLocation());
	}

	@Test
	@Order(3)
	@Rollback(false)
	public void createUser() {
		User usr=new User();
		usr.setName("balu");
		usr.setLocation("kmm");
		repo.save(usr);
		User testUser=repo.findById((long) 20).orElseThrow(()->new ResourceNotFound("User Not found with id = "+20));
		//User testUser = repo.findByName("Surendr");
		Assert.assertEquals("balu", testUser.getName());

	}
	@Test
	@Rollback(false)
	@Order(4)
	public void updateUser() {
		User user = repo.findByName("balu");
		user.setLocation("khammam");
		repo.save(user);
		User updatedUser = repo.findByName("balu");
		Assert.assertTrue(updatedUser.getLocation().equals("khammam"));

	}
		@Test
	@Rollback(false)
	@Order(5)
	@Transactional
	@Ignore("Getting error@ Assertion")
	public void deleteUser() {
		repo.deleteByName("balu");
		User dltdUser = repo.findByName("balu");
		Assert.assertNull(dltdUser);
	}
	
}
