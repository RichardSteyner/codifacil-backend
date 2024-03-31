package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonServiceImpl;
import com.codifacil.codifacilbackend.models.entity.MergeField;
import com.codifacil.codifacilbackend.models.entity.QuickText;
import com.codifacil.codifacilbackend.models.repository.QuickTextRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class QuickTextServiceImpl extends CommonServiceImpl<QuickText, Long, QuickTextRepository>
        implements QuickTextService {
    @Override
    public String calculate(QuickText quickText) {
        /*String result = quickText.getBody();
        for(MergeField mf : quickText.getMergeFields()) {
            result = result.replace("{{"+ mf.getName() + "}}", mf.getValue());
        }*/
        String result = quickText.getMergeFields().stream()
                .reduce(quickText.getBody(),
                        (body, mf) -> body.replace("{{" + mf.getName() + "}}", mf.getValue()!=null ? mf.getValue() : ""),
                        (s1, s2) -> s1); //función combinadora, si se omite, la función combinadora devolverá el mismo tipo de dato de entrada que es MergeField
        return result;
    }

    @Override
    public Iterable<QuickText> findByOwner(Long ownerId) {
        return this.repository.findByOwner(ownerId);
    }
}
