package com.project.ess.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value=CustomGenericException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody //pakai ini biar tidak ngikut default runtime
    public ErrorMessage customException(RuntimeException ex) {
        //System.out.println("run time");
        ErrorMessage cm = new ErrorMessage(new Date(),ex.getMessage(),true);

        return cm;
    }
}
