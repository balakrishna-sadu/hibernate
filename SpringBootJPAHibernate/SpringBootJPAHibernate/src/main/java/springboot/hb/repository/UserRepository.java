package springboot.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springboot.hb.entity.User;
@Component
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByName(String name);
	public User deleteByName(String name);
	public String deleteUserByName();

}
