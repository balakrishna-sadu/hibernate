package springboot.orm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import springboot.orm.dao.EmployeeDAO;
import springboot.orm.entity.Employee;

@RestController
@ComponentScan
@RequestMapping(value = "/emp")
public class EmployeeController {

	@Autowired
	EmployeeDAO ed;
	
	@RequestMapping("/all")
	public List<Employee> getEmployees(){
		return ed.getEmployees();
	}
	
	@RequestMapping("/{id}")
	public Employee getUser(@PathVariable(value = "id") int eid) {
		return this.ed.getEmployee(eid);
	}
	
	@RequestMapping("/insert")
	public Employee createUser(@RequestBody Employee e) {
		return this.ed.insertEmployee(e);
	}
	@RequestMapping("/update/{id}")
	public Employee updateUser(@RequestBody Employee e,@PathVariable(value = "id") int eid) {
		 return ed.updateEmployee(e,eid);
	}
	@RequestMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteUser(@PathVariable(value = "id") int eid){
		ed.deleteEmployee(eid);
		return ResponseEntity.ok().build();
	}
}
