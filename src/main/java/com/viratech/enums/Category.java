package com.viratech.enums;

import com.viratech.exceptions.InvalidCategoryException;

public enum Category {
    ELECTRONICS,
    CLOTHING,
    FOOD,
    BOOKS,
    OTHER;

    public static Category fromApi(String valor){
        if(valor == null || valor.isBlank()) return null;
        return switch (valor){
            case "electronics" -> ELECTRONICS;
            case "clothing" -> CLOTHING;
            case "food" -> FOOD;
            case "books" -> BOOKS;
            case "other" -> OTHER;
            default -> throw new InvalidCategoryException("Category not found: " + valor);

        };

    }

    public String toApi(){
        return switch (this){
            case ELECTRONICS -> "electronics";
            case CLOTHING -> "clothing";
            case FOOD -> "food";
            case BOOKS -> "books";
            case OTHER -> "other";
        };
    }
}
