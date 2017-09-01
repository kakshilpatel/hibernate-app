package org.npu.cusorder.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.npu.cusorder.domain.CusOrder;
import org.npu.cusorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test.xml" })
@TransactionConfiguration   // So we get auto rollback of transactions on tests
@Transactional
public class OrderServiceTest {
	@Autowired
	private OrderService orderService;
	private int cusOrderId;   /* Use this Order id for all the tests */
	
	@Before
	public void init() {
		cusOrderId = 1;
	}
	
	@Test
	public void testAddAmtToOrder() throws Exception {
		CusOrder order;
		double orderAmt;
		double expectedAmt = 202.34;
		final double DELTA = 1e-15;
		
		orderService.addAmtToOrder(cusOrderId, 100.00);
		
		order = orderService.getById(cusOrderId);
		orderAmt = order.getAmount();
		assertEquals(orderAmt, expectedAmt, DELTA);
	}
	
	@Test
	public void testSaveNewOrder() throws Exception {
		CusOrder order, savedOrder;
		int orderId;
		
		order = new CusOrder();
		order.setOrderNum("HY771122");
		order.setAmount(75.60);
		savedOrder = orderService.save(order);
		orderId = savedOrder.getId();
		
		order = orderService.getById(orderId);
		assertEquals(order, savedOrder);
	}
}
