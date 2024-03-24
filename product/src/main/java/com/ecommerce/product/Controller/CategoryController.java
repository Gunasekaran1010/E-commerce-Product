package com.ecommerce.product.Controller;


import com.ecommerce.product.Model.Category;
import com.ecommerce.product.Service.CategoryService;
import com.ecommerce.product.dto.CategoryDTO;
import com.ecommerce.product.payload.AppConstants;
import com.ecommerce.product.payload.CategoryAppConstant;
import com.ecommerce.product.payload.CategoryResponse;
import com.ecommerce.product.payload.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category created = categoryService.create(category);
        return new ResponseEntity<Category>(created , HttpStatus.OK);
    }

    @PutExchange("/categories/{id}")
    public ResponseEntity<Category> upadte( @PathVariable int id ,@RequestBody Category category){
        Category update = categoryService.upadte(id ,category);
        return new ResponseEntity<Category>(update , HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public  String delete(@PathVariable int id){
        categoryService.delete(id);
        return  "Deleted";

    }

//    @GetMapping("/category/{id}")
//    public  ResponseEntity<Category> getbyid (@PathVariable int id){
//        Category values = categoryService.getbyid(id);
//        return new ResponseEntity<Category>(values , HttpStatus.OK);
//
//    }
@GetMapping("/categories/{id}")
public CategoryDTO getCategoryById(@PathVariable int id){
    return categoryService.getCategoryById(id);
}

//    @GetMapping("/category")
//    public  ResponseEntity<List<Category>> allcatrgory(){
//
//        List<Category> allfind = categoryService.getall();
//        return new  ResponseEntity<List<Category>>(allfind , HttpStatus.OK);
//    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponse> getallcategory(
            @RequestParam(value = "page", defaultValue = CategoryAppConstant.PAGE_NUMBER_STRING) int page,
            @RequestParam(value = "pageSize", defaultValue = CategoryAppConstant.PAGE_SIZE_STRING) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = CategoryAppConstant.SORT_BY_STRING) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = CategoryAppConstant.SORT_DIR_STRING) String sortDir) {

        try {
            CategoryResponse response = categoryService.getall(page, pageSize, sortBy, sortDir);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            // You can customize the error response based on the exception
            String errorMessage = "An error occurred while processing the request and Page Number Start With 1.";
            CategoryResponse errorResponse = new CategoryResponse();
            errorResponse.setErrorMessage(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

