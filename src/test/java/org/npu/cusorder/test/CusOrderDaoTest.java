package org.npu.cusorder.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.npu.cusorder.dao.CusOrderDao;
import org.npu.cusorder.domain.CusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test.xml" })
public class CusOrderDaoTest {
	
	@Autowired
	private CusOrderDao cusOrderDao;
	
	@Test
	public void getCusOrderByIdTest() throws Exception {
		CusOrder order = cusOrderDao.findById(1);
		assertEquals(order.getId(), 1);
	}
	
	@Test
	public void getAllCusOrderTest() throws Exception {
		List<CusOrder> orderList = cusOrderDao.findAll();
		long count = cusOrderDao.numOrders();
		assertEquals(count, orderList.size());
	}
}
