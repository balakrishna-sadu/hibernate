package hibernate.ManytoOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import hibernate.ManytoOne.entities.Employee;

@Component
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
