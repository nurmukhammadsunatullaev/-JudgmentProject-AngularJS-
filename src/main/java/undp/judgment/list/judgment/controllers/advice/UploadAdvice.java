package undp.judgment.list.judgment.controllers.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UploadAdvice {

    @ExceptionHandler(Exception.class)
    public String errorPage(){
        return "error";
    }

}
