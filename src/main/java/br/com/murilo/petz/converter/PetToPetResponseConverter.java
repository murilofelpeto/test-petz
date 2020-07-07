package br.com.murilo.petz.converter;

import br.com.murilo.petz.dto.response.PetResponse;
import br.com.murilo.petz.model.Pet;
import org.springframework.stereotype.Service;
import org.springframework.core.convert.converter.Converter;

@Service
public class PetToPetResponseConverter implements Converter<Pet, PetResponse> {
    @Override
    public PetResponse convert(final Pet pet) {
        return new PetResponse(pet.getId(),
                               pet.getNome(),
                               pet.getRaca());
    }
}
