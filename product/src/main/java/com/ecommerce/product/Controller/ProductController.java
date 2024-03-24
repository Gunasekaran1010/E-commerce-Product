package com.ecommerce.product.Controller;


import com.ecommerce.product.Model.Product;
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

    @PostMapping("/products/{catid}")
    @ResponseBody
    public Product craeteproduct(@RequestBody Product product , @PathVariable int catid){

        Product res = productService.createproduct(product, catid);
        return  res ;
    }

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


    @GetMapping("/products/{id}")
    public ProductDTO getproductbyid(@PathVariable int id){
        ProductDTO p = productService.getProductById(id);
        return  p ;
    }

    @DeleteMapping("/products/{id}")
    public void deletebyids(@PathVariable int id){
        productService.deletebyid(id);
    }

    @PutMapping("/products/{id}")
    public Product Updatethat(@PathVariable int id , @RequestBody Product P){
        Product ps = productService.UpadteProduct(id,P);
        return  ps ;
    }
}
