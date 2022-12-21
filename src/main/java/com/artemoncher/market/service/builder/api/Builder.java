package com.artemoncher.market.service.builder.api;

import com.artemoncher.market.service.dto.DtoEntity;

public interface Builder <T extends DtoEntity>{
    T build();
}
