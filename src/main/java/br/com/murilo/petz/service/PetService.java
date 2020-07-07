package br.com.murilo.petz.service;

import br.com.murilo.petz.exception.ResourceAlreadyExistException;
import br.com.murilo.petz.exception.ResourceNotFoundException;
import br.com.murilo.petz.model.Pet;
import br.com.murilo.petz.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private static final String PET_NAO_ENCONTRADO = "Pet não encontrado!";
    private static final String PET_EXISTENTE = "Pet já cadastrado!";

    private final PetRepository petRepository;

    public PetService(@Autowired PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(Pet pet) {
        if(pet.getId() == null) {
            this.petRepository.save(pet);
        }
        throw new ResourceAlreadyExistException(PET_EXISTENTE);
    }

    public Pet updatePet(Long id, Pet pet) {
        if(petExists(id)) {
            pet.setId(id);
            this.petRepository.save(pet);
        }
        throw new ResourceNotFoundException(PET_NAO_ENCONTRADO);
    }

    public void deletePet(Long id, Pet pet) {
        if(petExists(id)) {
            this.petRepository.delete(pet);
            return;
        }
        throw new ResourceNotFoundException(PET_NAO_ENCONTRADO);
    }

    public Pet findById(Long id) {
        return this.petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PET_NAO_ENCONTRADO));
    }

    public List<Pet> findPetByNome(String nome) {
        return this.petRepository.findByNome(nome);
    }

    public Page<Pet> findAllPets(Pageable pageable) {
        return this.petRepository.findAll(pageable);
    }

    private Boolean petExists(final Long id) {
        return this.petRepository.findById(id).isPresent();
    }
}
