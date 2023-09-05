package pl.coderslab.magazyn;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class CustomMessageSource implements MessageSource {

    private final ReloadableResourceBundleMessageSource validationMessageSource;
    private final ReloadableResourceBundleMessageSource localeMessageSource;

    public CustomMessageSource() {
        validationMessageSource = new ReloadableResourceBundleMessageSource();
        validationMessageSource.setBasename("classpath:validation_message");
        validationMessageSource.setDefaultEncoding("UTF-8");

        localeMessageSource = new ReloadableResourceBundleMessageSource();
        localeMessageSource.setBasename("classpath:message");
        localeMessageSource.setDefaultEncoding("UTF-8");
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        try {
            return validationMessageSource.getMessage(code, args, defaultMessage, locale);
        } catch (NoSuchMessageException e) {
            return localeMessageSource.getMessage(code, args, defaultMessage, locale);
        }
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        try {
            return validationMessageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            return localeMessageSource.getMessage(code, args, locale);
        }
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        try {
            return validationMessageSource.getMessage(resolvable, locale);
        } catch (NoSuchMessageException e) {
            return localeMessageSource.getMessage(resolvable, locale);
        }
    }
}
