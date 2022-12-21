package com.artemoncher.market.service.api;

import com.artemoncher.market.service.dto.Check;
import com.artemoncher.market.service.dto.DtoEntity;
import com.artemoncher.market.service.exception.ServiceException;

public interface Service <T extends DtoEntity>{
    T getCheck(String ids, String quantity, String cardId) throws ServiceException;
}
