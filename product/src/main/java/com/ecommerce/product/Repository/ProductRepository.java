package com.ecommerce.product.Repository;

import com.ecommerce.product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Integer> {

    Product findById(int id);
}
