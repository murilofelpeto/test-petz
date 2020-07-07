package br.com.murilo.petz.config;

import br.com.murilo.petz.converter.ClienteRequestToClienteConverter;
import br.com.murilo.petz.converter.ClienteToClienteResponseConverter;
import br.com.murilo.petz.converter.PetRequestToPetConverter;
import br.com.murilo.petz.converter.PetToPetResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ConversionService conversionService;

    @Autowired
    public WebConfig(@Lazy ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new PetRequestToPetConverter());
        registry.addConverter(new PetToPetResponseConverter());
        registry.addConverter(new ClienteRequestToClienteConverter(conversionService));
        registry.addConverter(new ClienteToClienteResponseConverter(conversionService));
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS",
                "HEAD",
                "TRACE",
                "CONNECT"
        );
    }
}
