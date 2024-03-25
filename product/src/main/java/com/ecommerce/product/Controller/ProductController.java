package com.ecommerce.product.Controller;


import com.ecommerce.product.Model.Category;
import com.ecommerce.product.Model.Product;
import com.ecommerce.product.Repository.CategoryRepository;
import com.ecommerce.product.Service.CategoryService;
import com.ecommerce.product.Service.ProductService;
import com.ecommerce.product.dto.ProductDTO;
import com.ecommerce.product.exception.ServiceException;
import com.ecommerce.product.payload.AppConstants;
import com.ecommerce.product.payload.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;




    //For Creating Product body Structure Should be below like
//    {
//        "productName" : "ce",
//            "productDesc" : "Most Famous mobile",
//            "productPrize" : 11233,
//            "stock" : true,
//            "productQuantity" : 43,
//            "live" : true ,
//            "imageName" : "image12.jpg",
//            "categoryId": 252 // Product what category need enter category id
//    }


    // Create a New Product Implementation
    @PostMapping("/products")
    @ResponseBody
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId());
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductDesc(productDTO.getProductDesc());
        product.setProductPrize(productDTO.getProductPrize());
        product.setStock(productDTO.isStock());
        product.setProductQuantity(productDTO.getProductQuantity());
        product.setLive(productDTO.isLive());
        product.setImageName(productDTO.getImageName());
        product.setCategory(category);

        Product savedProduct = productService.createProduct(product);
        return savedProduct;
    }




    // Get ALl Product List and also page wise also we can get
    @GetMapping("/products")
    public ProductResponse getallproduct(@RequestParam(value = "page", defaultValue = AppConstants.PAGE_NUMBER_STRING) int page,
                                         @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING) int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_STRING) String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING) String sortDir) {

        try {
            ProductResponse response = productService.getallproducts(page , pageSize , sortBy , sortDir);
            return response;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("Error occurred while fetching categories", ex);
        }
    }


    // Get a Product using an ID

    @GetMapping("/products/{id}")
    public ProductDTO getproductbyid(@PathVariable int id){
        ProductDTO p = productService.getProductById(id);
        return  p ;
    }


    // Delete a  Single Product Using ID
    @DeleteMapping("/products/{id}")
    public String deletebyids(@PathVariable int id){
        productService.deletebyid(id);
        return  "deleted Successfully";
    }


    // Update Product Using a ID
    @PutMapping("/products/{id}")
    public Product Updatethat(@PathVariable int id , @RequestBody Product P){
        Product ps = productService.UpadteProduct(id,P);
        return  ps ;
    }
}


// Create a New Product
//    @PostMapping("/products/{catid}")
//    @ResponseBody
//    public Product craeteproduct(@RequestBody Product product , @PathVariable int catid){
//
//        Product res = productService.createproduct(product, catid);
//        return  res ;
//    }