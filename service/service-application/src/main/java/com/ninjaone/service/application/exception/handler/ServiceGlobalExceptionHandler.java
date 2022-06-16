package com.ninjaone.service.application.exception.handler;

import com.ninjaone.application.handler.ErrorDTO;
import com.ninjaone.application.handler.GlobalExceptionHandler;
import com.ninjaone.service.domain.exception.ServiceDomainException;
import com.ninjaone.service.domain.exception.ServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ServiceGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {ServiceDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ServiceDomainException serviceDomainException) {
        log.error(serviceDomainException.getMessage(), serviceDomainException);
        return  ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(serviceDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ServiceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(ServiceNotFoundException serviceNotFoundException) {
        log.error(serviceNotFoundException.getMessage(), serviceNotFoundException);
        return  ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(serviceNotFoundException.getMessage())
                .build();
    }

}
