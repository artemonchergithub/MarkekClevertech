package com.artemoncher.market.repository.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private final Card cardMock1 = new Card(1, "1234", "1,2,3,4");
    private final Card cardMock2 = new Card(1, "1234", "1,2,3,4");
    private final Card cardMock3 = new Card(2, "1234", "1,4");

    @Test
    void testEquals() {
        assertEquals(cardMock1, cardMock2);
        assertNotEquals(cardMock1, cardMock3);
    }

    @Test
    void testHashCode() {
        assertEquals(cardMock1.hashCode(), cardMock2.hashCode());
    }
}