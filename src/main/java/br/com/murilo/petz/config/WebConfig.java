package br.com.murilo.petz.config;

import br.com.murilo.petz.converter.ClienteRequestToClienteConverter;
import br.com.murilo.petz.converter.ClienteToClienteResponseConverter;
import br.com.murilo.petz.converter.PetRequestToPetConverter;
import br.com.murilo.petz.converter.PetToPetResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

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

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/validation/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pt", "BR"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
}
