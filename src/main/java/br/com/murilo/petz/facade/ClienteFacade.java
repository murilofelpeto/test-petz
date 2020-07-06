package br.com.murilo.petz.facade;

import br.com.murilo.petz.dto.request.ClienteRequest;
import br.com.murilo.petz.dto.response.ClienteResponse;
import br.com.murilo.petz.model.Cliente;
import br.com.murilo.petz.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
}
