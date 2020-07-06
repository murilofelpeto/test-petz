package br.com.murilo.petz.config;

import br.com.murilo.petz.converter.ClienteRequestToClienteConverter;
import br.com.murilo.petz.converter.PetRequestToPetConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new ClienteRequestToClienteConverter());
        registry.addConverter(new PetRequestToPetConverter());
    }
}
