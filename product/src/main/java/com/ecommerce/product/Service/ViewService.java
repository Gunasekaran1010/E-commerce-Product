package com.ecommerce.product.Service;



import com.ecommerce.product.Model.Product;
import com.ecommerce.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.product.Model.Category;
import com.ecommerce.product.Repository.CategoryRepository;

@Service
@Transactional
public class ViewService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}

