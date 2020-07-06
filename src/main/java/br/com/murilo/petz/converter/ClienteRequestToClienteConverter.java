package br.com.murilo.petz.converter;

import br.com.murilo.petz.dto.request.ClienteRequest;
import br.com.murilo.petz.model.Cliente;
import br.com.murilo.petz.model.Pet;
import br.com.murilo.petz.utils.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteRequestToClienteConverter implements Converter<ClienteRequest, Cliente> {

    @Autowired
    private ConversionService conversionService;

    @Override
    public Cliente convert(final ClienteRequest clienteRequest) {
        final String documento = DocumentUtils.removerPontuacao(clienteRequest.getCpf());
        final List<Pet> pets = clienteRequest.getPets().stream().map(pet -> conversionService.convert(pet, Pet.class)).collect(Collectors.toList());
        Cliente cliente = new Cliente();
        cliente.setId(clienteRequest.getId());
        cliente.setCpf(Long.valueOf(documento));
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setIdade(clienteRequest.getIdade());
        cliente.setNome(clienteRequest.getNome());
        cliente.setPets(pets);
        cliente.setSexo(clienteRequest.getSexo());
        cliente.setTelefone(clienteRequest.getTelefone());
        return cliente;
    }
}
