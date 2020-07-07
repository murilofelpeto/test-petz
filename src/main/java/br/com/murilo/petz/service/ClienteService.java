package br.com.murilo.petz.service;

import br.com.murilo.petz.exception.ResourceAlreadyExistException;
import br.com.murilo.petz.exception.ResourceNotFoundException;
import br.com.murilo.petz.model.Cliente;
import br.com.murilo.petz.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(@Autowired ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente saveCliente(final Cliente cliente) {
        if(this.clienteRepository.findByCpfOrEmail(cliente.getCpf(), cliente.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistException("Cliente já cadastrado");
        }
        return this.clienteRepository.save(cliente);
    }

    public Cliente findClienteById(final Long id) {
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(id);
        if(optionalCliente.isPresent()) {
            return optionalCliente.get();
        }
        throw new ResourceNotFoundException("Cliente não encontrado");
    }

    public Cliente updateCliente(final Long id, final Cliente cliente) {
        if(clientExist(id)) {
            cliente.setId(id);
            return this.clienteRepository.save(cliente);
        }
        throw new ResourceNotFoundException("Cliente não existe na base!");
    }

    public void deleteCliente(final Long id, final Cliente cliente) {
        if(clientExist(id)){
            this.clienteRepository.delete(cliente);
        }
        throw new ResourceNotFoundException("Cliente não existe na base");
    }

    public Page<Cliente> findAll(Pageable pageable) {
        return this.clienteRepository.findAll(pageable);
    }

    private Boolean clientExist(final Long id) {
        return this.clienteRepository.findById(id).isPresent();
    }
}
