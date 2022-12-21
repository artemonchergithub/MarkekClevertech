package com.artemoncher.market.service.dto;

import com.artemoncher.market.repository.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void printCheck(){
        System.out.printf("%-5s %-20s %10s %10s %n", "QTY", "DESCRIPTION", "PRICE", "TOTAL");
        for (ProductDto product : products) {
            System.out.printf("%-5s %-20s %10s %10s %n", product.getQuantity(), product.getName(),
                    "$" + product.getCost(), "$" + product.getTotal());
        }
        System.out.printf("%-10s %37s %n", "DISC10%", "$" +
                new BigDecimal(savedMoney.toString()).setScale(2, RoundingMode.HALF_EVEN).floatValue());
        System.out.printf("%-10s %37s %n", "TOTAL", "$" +
                new BigDecimal(total.toString()).setScale(2, RoundingMode.HALF_EVEN));

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
        return "Check{" +
                "products=" + products +
                ", savedMoney=" + savedMoney +
                ", total=" + total +
                '}';
    }
}
