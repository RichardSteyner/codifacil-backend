package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonService;
import com.codifacil.codifacilbackend.models.entity.QuickText;

public interface QuickTextService extends CommonService<QuickText, Long> {

    public String calculate(QuickText quickText);

    public Iterable<QuickText> findByOwner(Long ownerId);

}
