package com.domain.slicetest.order;

import com.domain.slicetest.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, Long> {
    @Query("select o from OrderProduct o join fetch o.customer c join fetch o.product where c.username = ?1")
    List<OrderProduct> findByCustomerName(String name);

}
