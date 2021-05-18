package com.javatpoint.mypackage;

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

public class FetchData {
	public static void main(String[] args) {
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata mt= new MetadataSources(ssr).getMetadataBuilder().build();
		
		SessionFactory sf=mt.getSessionFactoryBuilder().build();
		
		Session ss= sf.openSession();
		Transaction tx=ss.beginTransaction();
		
		TypedQuery tq = ss.createQuery("from Employee");
		List<Employee> list = tq.getResultList();
		
		Iterator<Employee> it = list.iterator();
		
		while ( it.hasNext()) {
			Employee e=it.next();
			System.out.println(e);
		}
	}

}
