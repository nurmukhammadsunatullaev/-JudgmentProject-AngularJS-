package undp.judgment.list.judgment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleMessageService {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String messageId){
        try {
            Locale locale=LocaleContextHolder.getLocale();
            return messageSource.getMessage(messageId,null,locale);
        }
        catch (NoSuchMessageException ex ){
            return "";
        }
    }

}
