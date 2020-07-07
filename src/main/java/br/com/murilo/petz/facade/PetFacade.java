package br.com.murilo.petz.facade;

import br.com.murilo.petz.dto.request.PetRequest;
import br.com.murilo.petz.dto.response.PetResponse;

import br.com.murilo.petz.model.Pet;
import br.com.murilo.petz.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetFacade {

    private final PetService petService;
    private final ConversionService conversionService;

    public PetFacade(@Autowired PetService petService,
                     @Autowired ConversionService conversionService) {
        this.petService = petService;
        this.conversionService = conversionService;
    }

    public PetResponse savePet(final PetRequest petRequest) {
        final Pet pet = this.conversionService.convert(petRequest, Pet.class);
        final Pet savedPet = this.petService.savePet(pet);
        return this.conversionService.convert(savedPet, PetResponse.class);
    }

    public PetResponse updatePet(final Long id, final PetRequest petRequest) {
        final Pet pet = this.conversionService.convert(petRequest, Pet.class);
        final Pet updatedPet = this.petService.updatePet(id, pet);
        return this.conversionService.convert(updatedPet, PetResponse.class);
    }

    public void deletePet(final Long id, final PetRequest petRequest) {
        final Pet pet = this.conversionService.convert(petRequest, Pet.class);
        this.petService.deletePet(id, pet);
    }

    public PetResponse findPetByID(Long id) {
        final Pet pet = this.petService.findById(id);
        return this.conversionService.convert(pet, PetResponse.class);
    }

    public List<PetResponse> findPetByNome(final String nome) {
        List<Pet> pets = this.petService.findPetByNome(nome);
        return pets.stream().map(pet -> this.conversionService.convert(pet, PetResponse.class)).collect(Collectors.toList());
    }

    public Page<PetResponse> findAllPets(Pageable pageable) {
        Page<Pet> pets = this.petService.findAllPets(pageable);
        return pets.map(pet -> this.conversionService.convert(pet, PetResponse.class));
    }
}
