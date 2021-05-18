package hibernate.ManytoOne.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "department")
public class Department {

	
	@Id
	private Integer did;
	private String dname;
	@JsonIgnore
	 @OneToMany(mappedBy = "department")
	 private List<Employee> employee;
	 
	 public Department() {
		 
	 }
	public Department(int did, String dname, List<Employee> employees) {
		super();
		this.did = did;
		this.dname = dname;
		this.employee = employees;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public List<Employee> getEmployees() {
		return employee;
	}
	public void setEmployees(List<Employee> employees) {
		this.employee = employees;
	}
	@Override
	public String toString() {
		return "Department [did=" + did + ", dname=" + dname + ", employee=" + employee + "]";
	}
}
