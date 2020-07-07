package br.com.murilo.petz.controller;

import br.com.murilo.petz.dto.request.PetRequest;
import br.com.murilo.petz.dto.response.PetResponse;
import br.com.murilo.petz.facade.PetFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Endpoint para operações de Pet")
@RestController
@RequestMapping("/petz/v1/pets")
public class PetController {

    private final PetFacade petFacade;

    public PetController(@Autowired PetFacade petFacade) {
        this.petFacade = petFacade;
    }

    @ApiOperation(value = "Criação de um Pet!")
    @PostMapping
    public ResponseEntity<PetResponse> savePet(@RequestBody PetRequest petRequest) {
        final PetResponse pet = this.petFacade.savePet(petRequest);
        pet.add(linkTo(methodOn(PetController.class).findPetById(pet.getId())).withSelfRel());
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualização de um Pet!")
    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable(value = "id") Long id,
                                                 @RequestBody PetRequest petRequest) {
        final PetResponse pet = this.petFacade.updatePet(id, petRequest);
        pet.add(linkTo(methodOn(PetController.class).findPetById(pet.getId())).withSelfRel());
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar um Pet!")
    @DeleteMapping("/{id}")
    public ResponseEntity deletePet(@PathVariable(value = "id") Long id,
                                    @RequestBody PetRequest petRequest) {
        this.petFacade.deletePet(id, petRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Encontrar um pet pelo ID!")
    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> findPetById(@PathVariable(value = "id") Long id) {
        final PetResponse pet = this.petFacade.findPetByID(id);
        pet.add(linkTo(methodOn(PetController.class).findPetById(pet.getId())).withSelfRel());
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @ApiOperation(value = "Encontrar um pet pelo nome!")
    @GetMapping
    public ResponseEntity<List<PetResponse>> findPetByNome(@RequestParam(value = "nome", required = true) String nome) {
        final List<PetResponse> pets = this.petFacade.findPetByNome(nome);
        if(pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        pets.stream().forEach(pet -> pet.add(linkTo(methodOn(PetController.class).findPetById(pet.getId())).withSelfRel()));
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @ApiOperation(value = "Encontrar todos os pets!")
    @GetMapping("/findAll")
    public ResponseEntity<Page<PetResponse>> findAllPets(@PageableDefault(page = 0, size = 10, direction = ASC, sort = "id") Pageable pageable) {
        final Page<PetResponse> pets = this.petFacade.findAllPets(pageable);
        if(pets.hasContent()) {
            pets.stream().forEach(pet -> pet.add(linkTo(methodOn(PetController.class).findPetById(pet.getId())).withSelfRel()));
            return new ResponseEntity<>(pets, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

}
