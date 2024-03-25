package com.ecommerce.product.Controller;



import com.ecommerce.product.Model.Category;
import com.ecommerce.product.Model.Product;
import com.ecommerce.product.Service.CategoryService;
import com.ecommerce.product.Service.ProductService;
import com.ecommerce.product.Service.ViewService;
import com.ecommerce.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ViewService viewService;

    @GetMapping("/view")
    public String viewPage(Model model) {
        // Retrieve categories and products from the service
        List<Category> categories = categoryService.getalls();
        List<ProductDTO> products = productService.getall();

        // Add categories and products to the model
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

        // Return the name of the Thymeleaf template to render
        return "view";
    }

    @GetMapping("/productview")
    public String viewproduct(Model model){
        List<ProductDTO> products = productService.getall();
        model.addAttribute("products", products);

        return "viewproduct";

    }

    @GetMapping("/landing")
    public String landingpage(Model model){

        return "landing";


    }

    @GetMapping("/backtomain")
    public String backtomain(Model model){
        return "landing";
    }

    @GetMapping("/cancelp")
    public  String cancelp(Model model){
        return "viewproduct";
    }

    @GetMapping("/cancelc")
    public  String cancelc(Model model){
        return "view";
    }



    @GetMapping("/addCategory")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        viewService.saveCategory(category);
        return "redirect:/view"; // Redirect to the categories page after adding a category
    }

    @GetMapping("/addProduct")
    public String showProductForm(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getalls());

        return "addProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        viewService.saveProduct(product);
        return "redirect:/productview"; // Redirect to the products page after adding a product
    }
}
