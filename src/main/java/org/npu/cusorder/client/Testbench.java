package org.npu.cusorder.client;

import java.util.Random;

import org.npu.cusorder.domain.CusOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testbench {
	public static void main(String args[]) {
		CusOrder order=null;
		ApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		SessionFactory sessionFactory = (SessionFactory) container.getBean("sessionFactory");
		Session session=null;
		Transaction tx=null;
		Random randomGenerator = new Random();
	    int randomInt = randomGenerator.nextInt(9000);
	    double randomAmt = Math.random() * 10000 + 100;
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			order = new CusOrder();
			order.setOrderNum("GX59" + randomInt); // randomly generate order number so we don't get duplicates
			order.setAmount(randomAmt);
			session.save(order);

			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("****An Exception occurred: " + ex.getMessage());
		} finally {
			session.close();
		}
		System.out.println(order);
	}

}
