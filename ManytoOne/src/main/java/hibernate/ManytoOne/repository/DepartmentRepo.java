package hibernate.ManytoOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import hibernate.ManytoOne.entities.Department;
import hibernate.ManytoOne.entities.Employee;

@Component
public interface DepartmentRepo extends JpaRepository<Department, Integer>{

	@Query("select employee from Department d where d.did=?1")
	List<Employee> getEmployeeByDepartment(Integer id);
}
