package com.ecommerce.product.Service;


import com.ecommerce.product.Model.Category;
import com.ecommerce.product.Model.Product;
import com.ecommerce.product.Repository.CategoryRepository;
import com.ecommerce.product.dto.CategoryDTO;
import com.ecommerce.product.dto.ProductDTO;
import com.ecommerce.product.payload.CategoryRes;
import com.ecommerce.product.payload.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    // new category create implementation
    public Category create(Category category){
        Category cs = categoryRepository.save(category);
        return  cs;
    }

// Update a Category using id
    public CategoryRes update(int id, Category category) {
        Category old = categoryRepository.findById(id);
        old.setTitle(category.getTitle());
        CategoryRes categoryRes = new CategoryRes();
        categoryRes.setCategoryId(old.getCategoryID());
        categoryRes.setTitle(old.getTitle());
        categoryRepository.save(old);

        return  categoryRes;
    }



    // Category Delete Function Implementation
    public  void  delete(int id){


        Category cat = categoryRepository.findById(id);
        categoryRepository.delete(cat);

    }




    // Getting a Single Category Using ID Function Implementation
    public CategoryDTO getCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryID());
        categoryDTO.setTitle(category.getTitle());
        // Set products (convert each product to ProductDTO)
        List<ProductDTO> productDTOs = category.getProduct().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        categoryDTO.setProducts(productDTOs);
        return categoryDTO;
    }

    // Product to ProductDTO

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDesc(product.getProductDesc());
        productDTO.setProductPrize(product.getProductPrize());
        productDTO.setProductQuantity(product.getProductQuantity());
        productDTO.setLive(product.getisLive());
        productDTO.setStock(product.getisStock());
        productDTO.setImageName(product.getImageName());

        if (product.getCategory() != null) {
            productDTO.setCategoryId(product.getCategory().getCategoryID());
            productDTO.setTittle(product.getCategory().getTitle());
        }


        return productDTO;
    }


    // Get all Category
    public List<Category> getalls(){
        List<Category> findall = categoryRepository.findAll();
        return  findall ;
    }

    // Get ALl Category in page wise
    public CategoryResponse getall(int pageNumber , int pageSize , String sortBy , String sortDir ){
        int adjustment = pageNumber -1 ;
        Sort sort = null;
        if(sortDir.trim().toLowerCase().equals("asc")){
            sort = Sort.by(sortBy).ascending();
        }else {

            sort = Sort.by(sortBy).descending();

        }

        Pageable pageable = PageRequest.of(adjustment , pageSize , sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        List<Category> pageProduct = page.getContent();
        // List<Product> collect =   pageProduct.stream().filter(p -> p.getisLive()).collect(Collectors.toList());
        List<CategoryDTO> productDTOS = pageProduct.stream().map( p -> convertToCategoryDTO(p)).collect(Collectors.toList());

        CategoryResponse response = new CategoryResponse();
        response.setContent(productDTOS);
        response.setPageNumber(page.getNumber() + 1);
        response.setPageSize(page.getSize());
        response.setPageTotal(page.getTotalPages());
        response.setLastPage(page.isLast());



        return  response;
    }


    // Convert Category to CategoryDTO
    public  CategoryDTO convertToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setTitle(category.getTitle());
        categoryDTO.setCategoryId(category.getCategoryID());

        List<ProductDTO> productDTOs = category.getProduct().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        categoryDTO.setProducts(productDTOs);

        return  categoryDTO;
    }


}


//    public  Category getbyid(int catoryid){
//        Category newid = categoryRepository.findById(catoryid);
//        return  newid ;
//    }


// Category Update Function Implementation
//    public  CategoryDTO upadte(int id, Category category)
//    {
//        Category old = categoryRepository.findById(id);
//        old.setProduct(category.getProduct());
//        old.setTitle(category.getTitle());
//        Category category1 =categoryRepository.save(old);
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setCategoryId(category1.getCategoryID());
//        categoryDTO.setTitle(category1.getTitle());
//
//        List<ProductDTO> productDTOS = category.getProduct().stream().map(this::convertToProductDTO).collect(Collectors.toList());
//        categoryDTO.setProducts(productDTOS);
//
//        return  categoryDTO;
//    }