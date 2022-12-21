package com.artemoncher.market.repository.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private final Product productMock1 = new Product(1, "Apple", 1.5f);
    private final Product productMock2 = new Product(1, "Apple", 1.5f);
    private final Product productMock3 = new Product(5, "Corn", 2.5f);

    @Test
    void testEquals() {
        assertEquals(productMock1, productMock2);
        assertNotEquals(productMock1, productMock3);
    }

    @Test
    void testHashCode() {
        assertEquals(productMock1.hashCode(), productMock2.hashCode());
    }
}