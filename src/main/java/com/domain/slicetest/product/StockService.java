package com.domain.slicetest.product;

import com.domain.slicetest.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StockService {
    protected transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductRepository productRepository;

    public Product getRemainProduct(Long productId, int amount) {
        return productRepository.getOne(productId);
    }

    public void reduceStock(Long productId, int amount) {
        Product product = productRepository.getOne(productId);
        int remain = product.getRemain() - amount;
        product.setRemain(remain);
    }
}
