package com.artemoncher.market.service.impl;

import com.artemoncher.market.repository.api.CardRepository;
import com.artemoncher.market.repository.api.ProductRepository;
import com.artemoncher.market.repository.model.Card;
import com.artemoncher.market.repository.model.Product;
import com.artemoncher.market.service.api.Service;
import com.artemoncher.market.service.dto.Check;
import com.artemoncher.market.service.dto.ProductDto;
import com.artemoncher.market.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CheckService implements Service<Check> {

    private final ProductRepository productRepository;
    private final CardRepository cardRepository;

    @Autowired
    public CheckService(ProductRepository productRepository, CardRepository cardRepository) {
        this.productRepository = productRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public Check getCheck(String idsString, String quantityString, String cardId) throws ServiceException {
        List<Product> products = new ArrayList<>();
        List<ProductDto> productDtos = new ArrayList<>();
        Card card;
        List<Integer> ids;
        List<Integer> quantity;
        List<Integer> promotedProductsId = new ArrayList<>();
        Check check;
        try {
            ids = Arrays.stream(idsString.split(",")).map(Integer::parseInt).toList();
            quantity = Arrays.stream(quantityString.split(",")).map(Integer::parseInt).toList();
        } catch (NumberFormatException e){
            throw new ServiceException("URL is malformed", e);
        }

        if (ids.size() != quantity.size()){
            throw new ServiceException("URL is malformed");
        }

        productRepository.findAllById(ids).forEach(products::add);

        if (products.contains(null)){
            throw new ServiceException("Id is wrong");
        }

        if (cardId.length() > 0) {
            card = cardRepository.findByName(cardId);

            if (Objects.isNull(card)) {
                throw new ServiceException("URL is malformed");
            }
            promotedProductsId = Arrays.stream(card.getProductsId().split(",")).map(Integer::parseInt).toList();
        }

        for (int i = 0; i < products.size(); i++) {
            ProductDto.Builder builder = ProductDto.getBuilder();
            builder.setId(products.get(i).getId());
            builder.setName(products.get(i).getName());
            builder.setCost(products.get(i).getCost());
            builder.setDiscounted(cardId.length() > 0 && promotedProductsId.contains(products.get(i).getId()));
            builder.setQuantity(quantity.get(i));
            productDtos.add(builder.build());
        }

        check = new Check(productDtos);
        System.out.println(check);

        return check;
    }
}
