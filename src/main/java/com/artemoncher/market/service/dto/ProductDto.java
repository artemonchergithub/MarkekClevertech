package com.artemoncher.market.service.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ProductDto implements DtoEntity{
    private Integer id;
    private String name;
    private Float cost;
    private Boolean discounted;
    private Float savedMoney = 0f;
    private Integer quantity;

    private ProductDto() {}

    public Float getTotal(){
        if (discounted && quantity >= 5) {
            savedMoney = new BigDecimal(quantity * cost * 0.1f).setScale(2, RoundingMode.HALF_EVEN).floatValue();
            return new BigDecimal(quantity * cost * 0.9f).setScale(2, RoundingMode.HALF_EVEN).floatValue();
        } else {
            return new BigDecimal(quantity * cost).setScale(2, RoundingMode.HALF_EVEN).floatValue();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Boolean getDiscounted() {
        return discounted;
    }

    public void setDiscounted(Boolean discounted) {
        this.discounted = discounted;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(Float savedMoney) {
        this.savedMoney = savedMoney;
    }

    public static Builder getBuilder(){
        return new ProductDto().new Builder();
    }

    public class Builder {
        private Builder(){}

        public Builder setId(int id){
            ProductDto.this.id = id;
            return this;
        }

        public Builder setName(String name){
            ProductDto.this.name = name;
            return this;
        }

        public Builder setCost(float cost){
            ProductDto.this.cost = cost;
            return this;
        }

        public Builder setDiscounted(boolean discounted){
            ProductDto.this.discounted = discounted;
            return this;
        }

        public Builder setQuantity(int quantity){
            ProductDto.this.quantity = quantity;
            return this;
        }

        public ProductDto build(){
            return ProductDto.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto that = (ProductDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(cost, that.cost)) return false;
        if (!Objects.equals(discounted, that.discounted)) return false;
        return Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (discounted != null ? discounted.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return quantity + "\t\t" +
                name + "\t\t$" +
                cost + "\t\t$" +
                getTotal();
    }
}
