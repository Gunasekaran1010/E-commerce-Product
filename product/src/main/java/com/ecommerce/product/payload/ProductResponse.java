package com.ecommerce.product.payload;

import com.ecommerce.product.Model.Product;
import com.ecommerce.product.dto.ProductDTO;

import java.util.List;

public class ProductResponse {

    private List<ProductDTO> content;
    private  int pageNumber;
    private int pageSize;
    private  int pageTotal;
    private  boolean  lastPage ;

    public List<ProductDTO> getContent() {
        return content;
    }

    public void setContent(List<ProductDTO> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }


    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }


}
