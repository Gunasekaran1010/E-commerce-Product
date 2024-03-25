package com.ecommerce.product.Controller;


import com.ecommerce.product.Model.Category;
import com.ecommerce.product.Service.CategoryService;
import com.ecommerce.product.dto.CategoryDTO;
import com.ecommerce.product.payload.*;
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


    //Format for create Categories
//    {
//        "title" : "Bags"
//    }



    // Create a New Category
    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category created = categoryService.create(category);
        return new ResponseEntity<Category>(created , HttpStatus.OK);
    }



// Update Category
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryRes> updateCategory(@PathVariable int id, @RequestBody Category category) {
        CategoryRes updatedCategory = categoryService.update(id, category);
        return ResponseEntity.ok(updatedCategory);
    }




    // Delete a Category Using ID
    @DeleteMapping("/categories/{id}")
    public  String delete(@PathVariable int id){
        categoryService.delete(id);
        return  "Deleted";

    }


        // Get a One Category Using ID
        @GetMapping("/categories/{id}")
        public CategoryDTO getCategoryById(@PathVariable int id){
                     return categoryService.getCategoryById(id);
        }



    // Get all Category and also we get page wise also we get a Category
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


//    @GetMapping("/category")
//    public  ResponseEntity<List<Category>> allcatrgory(){
//
//        List<Category> allfind = categoryService.getall();
//        return new  ResponseEntity<List<Category>>(allfind , HttpStatus.OK);
//    }

//    @GetMapping("/category/{id}")
//    public  ResponseEntity<Category> getbyid (@PathVariable int id){
//        Category values = categoryService.getbyid(id);
//        return new ResponseEntity<Category>(values , HttpStatus.OK);
//
//    }


// Update a Existing Category using Category ID
//    @PutExchange("/categories/{id}")
//    public ResponseEntity<CategoryDTO> upadte( @PathVariable int id ,@RequestBody Category category){
//        CategoryDTO update = categoryService.upadte(id ,category);
//        return new ResponseEntity<CategoryDTO>(update , HttpStatus.OK);
//    }