package hibernate.ManytoOne.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hibernate.ManytoOne.entities.Department;
import hibernate.ManytoOne.entities.Employee;
import hibernate.ManytoOne.repository.DepartmentRepo;
import hibernate.ManytoOne.repository.EmployeeRepo;

@RestController
@ComponentScan
@RequestMapping("/app")
public class Controller {
	
	@Autowired
	EmployeeRepo empRepo;
	@Autowired
	DepartmentRepo deptRepo;
	
	@RequestMapping("/allemployees")
	public List<Employee> getAll(){
		return empRepo.findAll();
	}
	@RequestMapping("/alldepts")
	public List<Department> getDepts(){
		return deptRepo.findAll();
	}
	@RequestMapping("/deptbyid/{id}")
	public Optional<Department> getDept(@PathVariable("id") Integer id) {
		
		return deptRepo.findById(id);
	}
	
	@RequestMapping("/dept/{id}/employees")
	public List<Employee> getEmployeeByDepartment(@PathVariable("id") int id){
		return deptRepo.getEmployeeByDepartment(id);
	}
}
