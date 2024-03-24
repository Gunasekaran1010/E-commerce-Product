package com.ecommerce.product.Service;

import com.ecommerce.product.Model.Category;
import com.ecommerce.product.Model.Product;
import com.ecommerce.product.Repository.CategoryRepository;
import com.ecommerce.product.Repository.ProductRepository;
import com.ecommerce.product.dto.ProductDTO;
import com.ecommerce.product.payload.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // Update a Single Product
    public  Product UpadteProduct(int id, Product p) {

        Product oldpro = productRepository.findById(id);

        oldpro.setImageName(p.getImageName());
        oldpro.setProductDesc(p.getProductDesc());
        oldpro.setProductName(p.getProductName());
        oldpro.setProductPrize(p.getProductPrize());
        oldpro.setProductQuantity(p.getProductQuantity());
        oldpro.setLive(p.getisLive());
        oldpro.setStock(p.getisStock());

        Product save = productRepository.save(oldpro);
        return  save ;
    }

    // Create a new Product
    public Product createproduct(Product product, int catid) {

        Category cat = categoryRepository.findById(catid);
        product.setCategory(cat);
        Product res = productRepository.save(product);
        return  res ;
    }



    // Get all Product List

    public List<ProductDTO> getall(){
        List<Product> all =   productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for( Product product : all){

            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductPrize(product.getProductPrize());
            productDTO.setProductDesc(product.getProductDesc());


            if (product.getCategory() != null) {
                productDTO.setCategoryId(product.getCategory().getCategoryID());
            }

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }


    // Get ALl the product in page wise
    public ProductResponse getallproducts(int page , int pageSize , String sortBy , String sortDir ){
        int adjustedPage = page - 1;

        Sort sort = null;
        if(sortDir.trim().toLowerCase().equals("asc")){
            sort = Sort.by(sortBy).ascending();
        }else {

            sort = Sort.by(sortBy).descending();

        }

       Pageable pageable = PageRequest.of(adjustedPage , pageSize , sort);
        Page<Product> pages = productRepository.findAll(pageable);
        List<Product> pageProduct = pages.getContent();
      // List<Product> collect =   pageProduct.stream().filter(p -> p.getisLive()).collect(Collectors.toList());
      List<ProductDTO> productDTOS = pageProduct.stream().map( p -> convertToProductDTO(p)).collect(Collectors.toList());

//        return productRepository.findAll();

        ProductResponse response = new ProductResponse();
        response.setContent(productDTOS);
        response.setPageNumber(pages.getNumber() + 1);
        response.setPageSize(pages.getSize());
        response.setPageTotal(pages.getTotalPages());
        response.setLastPage(pages.isLast());



        return  response;
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





    // Get single Product Using a ID
    public ProductDTO getProductById(int productId) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            return null; // Or handle the case when the product is not found
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDesc(product.getProductDesc());
        productDTO.setProductPrize(product.getProductPrize());
        productDTO.setStock(product.getisStock());
        productDTO.setProductQuantity(product.getProductQuantity());
        productDTO.setLive(product.getisLive());
        productDTO.setImageName(product.getImageName());

        // Check if the category is not null before setting its ID
        if (product.getCategory() != null) {
            productDTO.setCategoryId(product.getCategory().getCategoryID());
            productDTO.setTittle(product.getCategory().getTitle());
        }

        return productDTO;
    }


    // Delete a Product Using a ID
    public  void deletebyid(int id){
        productRepository.deleteById(id);
    }
}



//    public Product getprobyid(int productid) {
//
//        Product ps = productRepository.findById(productid);
//        return  ps ;
//    }
