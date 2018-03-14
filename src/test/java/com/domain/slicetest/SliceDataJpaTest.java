package com.domain.slicetest;

import com.domain.slicetest.model.Product;
import com.domain.slicetest.product.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SliceDataJpaTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByUsernameShouldReturnUser() {
        // Arrange
        String productName = "test product";
        entityManager.persist(new Product(productName, 10, 100));

        // Act
        Product product = productRepository.findByName(productName);

        // Assert
        assertThat(product.getId(), notNullValue());
    }
}
