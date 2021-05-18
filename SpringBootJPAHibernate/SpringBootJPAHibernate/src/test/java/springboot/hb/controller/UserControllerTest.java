package springboot.hb.controller;


import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonIgnore;

import springboot.hb.entity.User;
import springboot.hb.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class UserControllerTest {

	@Autowired
	public MockMvc mockMvc;
	@MockBean
	public UserRepository repo;
	@InjectMocks
	public UserController userctrl;

	public static Optional<User> userB;
	public static User userP;
	public static User userN;
	public static User userV;
	public static List list = new ArrayList<>();
	
	@BeforeAll
	public static void beforeClass() {
		userB = Optional.of(new User(3,"balu","khammam"));
		userP = new User(4,"prashanth","medak");
		userN = new User(5, "nagendra","hyd");
		userV = new User(6,"vivek","karimnagar");
		list.add(userB);
		list.add(userP);
		list.add(userN);
		list.add(userV);
	}
	
	@Before
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(userctrl).build();
	}

	@Test
	public void getEmployee() throws Exception {
		Mockito.when(repo.findById((long) 3)).thenReturn(userB);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user/get/3"))
				  .andExpect(MockMvcResultMatchers.status().is(200))
				  .andExpect(MockMvcResultMatchers.status().isOk())
				  .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("balu")))
				  .andReturn();
		Mockito.verify(repo).findById((long) 3);
	}
	@Test
	public void getAllEmployee() throws Exception {
		
		Mockito.when(repo.findAll()).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user/get/all"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].*", Matchers.hasSize(3))) //first object's fields 3 or not
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].location", Matchers.is("medak"))) //2nd object's location
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*]", Matchers.hasSize(4))) //Checks 4 Objects present in list or not
				.andReturn();
		Mockito.verify(repo).findAll();
	}
	
	
	@Test
//	@Disabled
	@JsonIgnore
	public void createEmployee() throws Exception{	
		String jsonObj="{\"id\":4,\"name\":\"prashanth\",\"location\":\"medak\"}";
		Mockito.when(repo.save(userP)).thenReturn(userP);
		mockMvc.perform(MockMvcRequestBuilders.post("/user/insert").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonObj))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				//.andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect((MockMvcResultMatchers.content().json(jsonObj)))
				.andReturn();
		Mockito.verify(repo).save(userP);
	}
	@Test
	public void deleteEmployee() throws Exception {
		
//		Mockito.when(repo.deleteById((long) 20)).thenReturn(userN);
		Mockito.when(repo.findById((long) 20)).thenReturn(userB);
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/20"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("balu")))
				.andReturn();
		Mockito.verify(repo).findById(((long) 20));
	}
	
	@Test
	public void deleteEmployeeByName() throws Exception {
		
		Mockito.when(repo.deleteUserByName()).thenReturn("User Record Deleted");
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/deletebyname"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string("User Record Deleted"))
				.andReturn();
		Mockito.verify(repo).deleteUserByName();
	}
}
