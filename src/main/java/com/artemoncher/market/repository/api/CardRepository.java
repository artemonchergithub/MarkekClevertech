package com.artemoncher.market.repository.api;

import com.artemoncher.market.repository.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
    Card findByName(String name);
}
