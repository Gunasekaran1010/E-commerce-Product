package com.ecommerce.product.Repository;

import com.ecommerce.product.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Integer> {

   Category findById(int id);
}
