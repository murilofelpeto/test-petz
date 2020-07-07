package br.com.murilo.petz.converter;

import br.com.murilo.petz.dto.response.ClienteResponse;
import br.com.murilo.petz.dto.response.PetResponse;
import br.com.murilo.petz.model.Cliente;
import br.com.murilo.petz.utils.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteToClienteResponseConverter implements Converter<Cliente, ClienteResponse> {

    private final ConversionService conversionService;

    public ClienteToClienteResponseConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ClienteResponse convert(final Cliente cliente) {
        final String documento = DocumentUtils.colocarPontuacao(String.valueOf(cliente.getCpf()));
        final List<PetResponse> pets = cliente.getPets().stream().map(pet -> conversionService.convert(pet, PetResponse.class)).collect(Collectors.toList());
        return new ClienteResponse(cliente.getId(),
                documento,
                cliente.getNome(),
                cliente.getSexo(),
                cliente.getIdade(),
                cliente.getTelefone(),
                cliente.getEmail(),
                pets);
    }
}
