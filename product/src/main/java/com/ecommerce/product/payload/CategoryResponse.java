package com.ecommerce.product.payload;

import com.ecommerce.product.dto.CategoryDTO;


import java.util.List;

public class CategoryResponse {

    private List<CategoryDTO> content;
    private  int pageNumber;
    private int pageSize;
    private  int pageTotal;
    private  boolean  lastPage ;

    private  String ErrorMessage;

    public List<CategoryDTO> getContent() {
        return content;
    }

    public void setContent(List<CategoryDTO> content) {
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

    public void setErrorMessage(String errorMessage) {

        System.out.println(errorMessage);



    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
