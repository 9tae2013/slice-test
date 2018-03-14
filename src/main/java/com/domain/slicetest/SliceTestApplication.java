package com.domain.slicetest;

import com.domain.slicetest.customer.CustomerRepository;
import com.domain.slicetest.model.Customer;
import com.domain.slicetest.model.Product;
import com.domain.slicetest.order.OrderRepository;
import com.domain.slicetest.product.ProductRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SliceTestApplication implements Jackson2ObjectMapperBuilderCustomizer {
    protected transient Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.indentOutput(true);
        jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static void main(String[] args) {
        SpringApplication.run(SliceTestApplication.class, args);
    }
}
