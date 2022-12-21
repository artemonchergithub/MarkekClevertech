package com.artemoncher.market.service.impl;

import com.artemoncher.market.repository.api.CardRepository;
import com.artemoncher.market.repository.api.ProductRepository;
import com.artemoncher.market.repository.model.Card;
import com.artemoncher.market.repository.model.Product;
import com.artemoncher.market.service.dto.Check;
import com.artemoncher.market.service.dto.ProductDto;
import com.artemoncher.market.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CardRepository cardRepository;

    private CheckService checkService;

    private final Product productMock1 = new Product(1, "Apple", 1.5f);
    private final Product productMock2 = new Product(5, "Corn", 2.5f);
    private final Card cardMock = new Card(1, "1234", "1,2,3,4");

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
            .setId(5)
            .setName("Corn")
            .setCost(2.5f)
            .setDiscounted(false)
            .setQuantity(2)
            .build();

    private final ProductDto productDtoMock3
            = ProductDto.getBuilder()
            .setId(1)
            .setName("Apple")
            .setCost(1.5f)
            .setDiscounted(false)
            .setQuantity(5)
            .build();

    private final Check discountedCheckMock = new Check(List.of(
            productDtoMock1,
            productDtoMock2
    ));

    private final Check notDiscountedCheckMock = new Check(List.of(
            productDtoMock3,
            productDtoMock2
    ));

    @BeforeAll
    void init(){
        MockitoAnnotations.initMocks(this);
        checkService = new CheckService(productRepository, cardRepository);
        Mockito.doReturn(List.of(productMock1, productMock2)).when(productRepository).findAllById(List.of(1,5));
        Mockito.doReturn(cardMock).when(cardRepository).findByName("1234");
    }

    @Test
    void getCheck() throws ServiceException {
        Assertions.assertEquals(discountedCheckMock, checkService.getCheck("1,5", "5,2", "1234"));
        Assertions.assertEquals(notDiscountedCheckMock, checkService.getCheck("1,5", "5,2", ""));
        Assertions.assertThrows(ServiceException.class, () -> checkService.getCheck("1,2", "2", ""));
    }
}