package com.example.common.entity;

/**
 * @author zk
 * @Description:
 * @date 2019-09-19 11:22
 */
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "productId='" + productId + '\'' +
                ", productQuantity=" + productQuantity +
                '}';
    }
}