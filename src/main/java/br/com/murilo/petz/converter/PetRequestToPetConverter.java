package br.com.murilo.petz.converter;

import br.com.murilo.petz.dto.request.PetRequest;
import br.com.murilo.petz.model.Pet;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PetRequestToPetConverter implements Converter<PetRequest, Pet> {

    @Override
    public Pet convert(final PetRequest petRequest) {
        return null;
    }
}
