package com.example.product.dto;

/**
 * @author zk
 * @Description:
 * @date 2019-09-19 11:07
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

    @Override
    public String toString() {
        return "CartDTO{" +
                "productId='" + productId + '\'' +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
