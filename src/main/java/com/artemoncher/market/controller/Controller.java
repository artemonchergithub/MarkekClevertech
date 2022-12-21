package com.artemoncher.market.controller;

import com.artemoncher.market.service.dto.Check;
import com.artemoncher.market.service.exception.ServiceException;
import com.artemoncher.market.service.impl.CheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class Controller {

    private final CheckService service;

    public Controller(CheckService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Check> get(@RequestParam(value = "ids", defaultValue = "1") String ids,
                                     @RequestParam(value = "quantity", defaultValue = "1") String quantity,
                                     @RequestParam(value = "cardId", defaultValue = "") String cardId) throws ServiceException {
        return ResponseEntity.ok(service.getCheck(ids, quantity, cardId));
    }
}
