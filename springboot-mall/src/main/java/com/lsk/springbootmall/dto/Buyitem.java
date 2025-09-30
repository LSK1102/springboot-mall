package com.lsk.springbootmall.dto;

import org.springframework.lang.NonNull;

public class Buyitem {
    @NonNull
    private Integer productId;
    @NonNull
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
