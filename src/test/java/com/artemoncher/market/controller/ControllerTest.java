package com.artemoncher.market.controller;

import com.artemoncher.market.repository.model.Card;
import com.artemoncher.market.repository.model.Product;
import com.artemoncher.market.service.dto.Check;
import com.artemoncher.market.service.dto.ProductDto;
import com.artemoncher.market.service.exception.ServiceException;
import com.artemoncher.market.service.impl.CheckService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest {

    @Mock
    private CheckService checkService;

    private Controller controller;

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

    private final Check discountedCheckMock = new Check(List.of(
            productDtoMock1,
            productDtoMock2
    ));

    @BeforeAll
    void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        controller = new Controller(checkService);
        Mockito.doReturn(discountedCheckMock).when(checkService).getCheck("1,5", "5,2","1234");
        Mockito.doThrow(ServiceException.class).when(checkService).getCheck("1", "5,2","a1234");
    }

    @Test
    void get() throws ServiceException {
        Assertions.assertEquals(ResponseEntity.ok(discountedCheckMock), controller.get("1,5", "5,2","1234"));
        Assertions.assertThrows(ServiceException.class, () -> controller.get("1", "5,2","a1234"));
    }
}