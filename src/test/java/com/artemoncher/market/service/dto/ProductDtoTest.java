package com.artemoncher.market.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {

    private final ProductDto productDtoMock1
            = ProductDto.getBuilder()
            .setId(1)
            .setName("Apple")
            .setCost(1.5f)
            .setDiscounted(true)
            .setQuantity(5)
            .build();
    private final ProductDto productDtoMock2
            = ProductDto.getBuilder()
            .setId(1)
            .setName("Apple")
            .setCost(1.5f)
            .setDiscounted(true)
            .setQuantity(5)
            .build();

    private final ProductDto productDtoMock3
            = ProductDto.getBuilder()
            .setId(5)
            .setName("Corn")
            .setCost(2.5f)
            .setDiscounted(false)
            .setQuantity(2)
            .build();
    @Test
    void testEquals() {
        assertEquals(productDtoMock1, productDtoMock2);
        assertNotEquals(productDtoMock1, productDtoMock3);
    }

    @Test
    void testHashCode() {
        assertEquals(productDtoMock1.hashCode(), productDtoMock2.hashCode());
    }
}