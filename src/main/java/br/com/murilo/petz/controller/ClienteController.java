package br.com.murilo.petz.controller;

import br.com.murilo.petz.dto.request.ClienteRequest;
import br.com.murilo.petz.dto.response.ClienteResponse;
import br.com.murilo.petz.facade.ClienteFacade;
import br.com.murilo.petz.model.Cliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Endpoint para operações de Cliente")
@RestController
@RequestMapping("/petz/v1/cliente")
public class ClienteController {

    private final ClienteFacade clienteFacade;

    public ClienteController(@Autowired ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    @ApiOperation(value = "Criação de um cliente!")
    @PostMapping
    public ResponseEntity<ClienteResponse> saveCliente(@RequestBody ClienteRequest clienteRequest) {
        final ClienteResponse cliente = this.clienteFacade.saveCliente(clienteRequest);
        cliente.add(linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel());
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Buscar um cliente por seu id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteResponse> findClienteById(@PathVariable(value = "id")Long id) {
        final ClienteResponse cliente = this.clienteFacade.findClienteById(id);
        cliente.add(linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar as informações de um cliente!")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteResponse> updateCliente(@PathVariable(value = "id")Long id,
                                         @RequestBody ClienteRequest clienteRequest) {

        final ClienteResponse cliente = this.clienteFacade.updateCliente(id, clienteRequest);
        cliente.add(linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @ApiOperation(value = "Remover um cliente da base")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteClient(@PathVariable(value = "id")Long id, @RequestBody ClienteRequest clienteRequest) {
        this.clienteFacade.deleteCliente(id, clienteRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Buscar todos os clientes com paginação")
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> findAllClientes(@PageableDefault(page = 0, size = 10, direction = ASC, sort = "id") Pageable pageable) {
        Page<ClienteResponse> clientes = this.clienteFacade.findAllClientes(pageable);
        if(clientes.hasContent()) {
            clientes.stream().forEach(cliente -> cliente.add(linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel()));
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Encontra um cliente pelo CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponse> findClienteByCpf(@PathVariable(value = "cpf")Long cpf) {
        ClienteResponse cliente = this.clienteFacade.findClienteByCpf(cpf);
        cliente.add(linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @ApiOperation(value = "Encontra um cliente pelo CPF")
    @GetMapping("/email")
    public ResponseEntity<ClienteResponse> findClienteByEmail(@RequestParam(value = "email", required = true)String email) {
        ClienteResponse cliente = this.clienteFacade.findClienteByEmail(email);
        cliente.add(linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
