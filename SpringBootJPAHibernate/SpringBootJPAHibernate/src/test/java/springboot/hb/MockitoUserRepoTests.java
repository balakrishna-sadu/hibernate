package springboot.hb;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import junit.framework.Assert;
import springboot.hb.entity.User;
import springboot.hb.repository.UserRepository;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test; 
@SpringBootTest
public class MockitoUserRepoTests {

	static User userKalyan;
	static User userKrishna;
	static User userBalu;
	
	
	@MockBean
	UserRepository repo;//UserRepository repo=Mockito.mock(UserRepository.class);
	
	@BeforeAll
	public static void beforeClass(){  
		//System.out.println("***********\n\nbefore class\n\n*********"); 
		userKalyan = new User(123,"Kalyan","SDM");
		userKrishna = new User(453,"Krishna","Sdrm");
		userBalu = new User(99,"Balakrishna","Spl");
		System.out.println(" ******* \nSetup Completed\n******");
	} 
	@Test
	public void getUserByName() {
		when(repo.findByName("balu")).thenReturn(userBalu);
		User test = repo.findByName("balu");
		Assert.assertEquals("Balakrishna", test.getName());
		System.out.println("====>GetUserByName Test Passed");
	}
	@Test
	public void createUser() {
		when(repo.save(userKalyan)).thenReturn(userKalyan);	
		Assert.assertEquals(userKalyan, repo.save(userKalyan));
		System.out.println("====>createUser Test Passed");
		
	}
	@Test
	public void deleteUser() {
		repo.delete(userKrishna);
		verify(repo,times(1)).delete(userKrishna);
		System.out.println("====>deleteUser Test Passed");
	}
}
