package br.com.murilo.petz.facade;

import br.com.murilo.petz.dto.request.ClienteRequest;
import br.com.murilo.petz.dto.response.ClienteResponse;
import br.com.murilo.petz.model.Cliente;
import br.com.murilo.petz.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ClienteFacade {

    private final ClienteService clienteService;
    private final ConversionService conversionService;

    public ClienteFacade(@Autowired ClienteService clienteService,
                         @Autowired ConversionService conversionService) {
        this.clienteService = clienteService;
        this.conversionService = conversionService;
    }

    public ClienteResponse saveCliente(final ClienteRequest clienteRequest) {
        final Cliente cliente = this.conversionService.convert(clienteRequest, Cliente.class);
        Cliente savedCliente = this.clienteService.saveCliente(cliente);
        return conversionService.convert(savedCliente, ClienteResponse.class);
    }

    public ClienteResponse findClienteById(final Long id) {
        final Cliente cliente = this.clienteService.findClienteById(id);
        return this.conversionService.convert(cliente, ClienteResponse.class);
    }

    public ClienteResponse updateCliente(final Long id, ClienteRequest clienteRequest) {
        final Cliente cliente = this.conversionService.convert(clienteRequest, Cliente.class);
        final Cliente updatedCliente = this.clienteService.updateCliente(id, cliente);
        return this.conversionService.convert(updatedCliente, ClienteResponse.class);
    }

    public void deleteCliente(final Long id, ClienteRequest clienteRequest) {
        final Cliente cliente = this.conversionService.convert(clienteRequest, Cliente.class);
        this.clienteService.deleteCliente(id, cliente);
    }

    public Page<ClienteResponse> findAllClientes(Pageable pageable) {
        final Page<Cliente> clientes = this.clienteService.findAll(pageable);
        return clientes.map(cliente -> this.conversionService.convert(cliente, ClienteResponse.class));
    }

    public ClienteResponse findClienteByCpf(Long cpf) {
        final Cliente cliente = this.clienteService.findClienteByCpf(cpf);
        return this.conversionService.convert(cliente, ClienteResponse.class);
    }

    public ClienteResponse findClienteByEmail(String email) {
        final Cliente cliente = this.clienteService.findClienteByEmail(email);
        return this.conversionService.convert(cliente, ClienteResponse.class);
    }
}
