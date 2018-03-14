package com.domain.slicetest.order;

import com.domain.slicetest.customer.CustomerRepository;
import com.domain.slicetest.customer.PaymentService;
import com.domain.slicetest.model.Customer;
import com.domain.slicetest.model.OrderProduct;
import com.domain.slicetest.model.Product;
import com.domain.slicetest.product.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OrderService {
    protected transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentService paymentService;
    @Autowired
    StockService stockService;

    public OrderProduct orderProduct(String customerName, Long productId, int amount) {
        Product product = stockService.getRemainProduct(productId, amount);

        // payment
        double total = product.getPrice() * amount;
        paymentService.payment(customerName, total);

        // order
        Customer customer = customerRepository.getOne(customerName);
        OrderProduct orderProduct = new OrderProduct(amount, total, customer, product);
        orderRepository.save(orderProduct);

        // ship
        stockService.reduceStock(productId, amount);

        return orderProduct;
    }
}
