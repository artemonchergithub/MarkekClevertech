package com.artemoncher.market.service.dto;

import java.util.List;
import java.util.Objects;

public class Check implements DtoEntity{
    private List<ProductDto> products;
    private Float savedMoney = 0f;
    private Float total = 0f;

    public Check(List<ProductDto> products) {
        this.products = products;
        products.forEach(product -> {
            total+= product.getTotal();
            savedMoney += product.getSavedMoney();
        });
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        return Objects.equals(products, check.products);
    }

    @Override
    public int hashCode() {
        return products != null ? products.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("QTY\t\tDESCRIPTION\t\t\t\tPRICE\t\tTOTAL\n" +
                                                "-------------------------------------------------\n");
        for (ProductDto product : products) {
            result.append(product.toString())
                    .append("\n");
        }
        result.append("-------------------------------------------------\n" + "VAl10%\t\t\t\t\t\t\t$")
                .append(savedMoney).append("TOTAL\t\t\t\t\t\t\t$")
                .append(total);
        return result.toString();
    }
}
