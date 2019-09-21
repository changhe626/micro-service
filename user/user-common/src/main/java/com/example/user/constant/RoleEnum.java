package com.example.user.constant;

public enum RoleEnum {


    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;

    private Integer code;
    private String role;

    RoleEnum(Integer code, String role) {
        this.code = code;
        this.role = role;
    }

    public Integer getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }
}
