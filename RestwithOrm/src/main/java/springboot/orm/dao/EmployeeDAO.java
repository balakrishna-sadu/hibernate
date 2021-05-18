package springboot.orm.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import springboot.orm.entity.Employee;

@Component
public class EmployeeDAO {
	StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	
	Metadata mt= new MetadataSources(ssr).getMetadataBuilder().build();
	
	SessionFactory sf=mt.getSessionFactoryBuilder().build();
	
	public List<Employee> getEmployees(){
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		TypedQuery tq = session.createQuery("from Employee");
		List<Employee> list = tq.getResultList();
		
		Iterator<Employee> it = list.iterator();
		while ( it.hasNext()) {
			Employee e=it.next();
			System.out.println(e);
		}
		tx.commit();
		session.close();
		return list;
	}
	
	public Employee getEmployee(int eid) {
		Session ss=sf.openSession();
		String query="from Employee where eid="+eid;
		System.out.println(query);
		TypedQuery tq = ss.createQuery(query);
		Employee e=(Employee) tq.getSingleResult();
		System.out.println(e);
		ss.close();
		return e;
		
	}

	public Employee insertEmployee(Employee e) {
		Session ss=sf.openSession();
		Transaction tx=ss.beginTransaction();
		ss.save(e);
		tx.commit();
		ss.close();
		return e;
	}

	public void deleteEmployee(int eid) {
		Session ss=sf.openSession();
		Transaction tx=ss.beginTransaction();
		
		//String hql2= "Delete from Employee where eid = "+eid;
		
		String hql = "DELETE FROM Employee WHERE eid = :employee_id";
		TypedQuery query = ss.createQuery(hql);
		query.setParameter("employee_id",eid);
		query.executeUpdate();
		tx.commit();
		ss.close();
	}

	public Employee updateEmployee(Employee e, int eid) {
		Session ss=sf.openSession();
		Transaction tx=ss.beginTransaction();		
		String hql= "update Employee set name=:ename where eid=:empid";
		TypedQuery tq= ss.createQuery(hql);
		tq.setParameter("ename", e.getName());
		tq.setParameter("empid", eid);
		tq.executeUpdate();
		tx.commit();
		ss.close();
		return e;
	}

}
