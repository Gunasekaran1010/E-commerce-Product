package com.ecommerce.product.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int categoryID;
    private  String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Set<Product> product=new HashSet<>();


    public Category() {
        super();
    }

    public Category(int categoryID, String title) {
        super();
        this.categoryID = categoryID;
        this.title = title;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }
}
