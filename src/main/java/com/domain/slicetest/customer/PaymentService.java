package com.domain.slicetest.customer;

import com.domain.slicetest.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PaymentService {
    protected transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomerRepository customerRepository;

    public void payment(String customerName, double total) {
        Customer customer = customerRepository.getOne(customerName);
        double credit = customer.getCredit() - total;
        customer.setCredit(credit);
    }
}
