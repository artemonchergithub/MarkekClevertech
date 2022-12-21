package com.artemoncher.market.repository.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "discount_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;

    @Column(name = "products_id")
    String productsId;

    public Card(Integer id, String name, String productsId) {
        this.id = id;
        this.name = name;
        this.productsId = productsId;
    }

    public Card() {
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

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!Objects.equals(id, card.id)) return false;
        if (!Objects.equals(name, card.name)) return false;
        return Objects.equals(productsId, card.productsId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (productsId != null ? productsId.hashCode() : 0);
        return result;
    }

    //todo implement toString()
}
