package org.npu.cusorder.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.npu.cusorder.dao.CusOrderDao;
import org.npu.cusorder.domain.CusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("CusOrderDaoHibernate")
@Transactional(readOnly=true)
public class CusOrderDaoHibernateImpl implements CusOrderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CusOrder store(CusOrder order) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(order);
		return order;
	}

	@Transactional(readOnly=false)
	public void delete(CusOrder order) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(order);
	}

	public CusOrder findById(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		CusOrder order = (CusOrder) session.get(CusOrder.class, orderId);
		return order;
	}

	public List<CusOrder> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CusOrder");
		return query.list();
	}

	@Override
	public long numOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT COUNT(cusorder) FROM CusOrder cusorder");
		long count = (Long) query.uniqueResult();
		return count;
	}

}
