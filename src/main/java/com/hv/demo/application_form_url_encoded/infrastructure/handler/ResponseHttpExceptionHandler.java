package com.hv.demo.application_form_url_encoded.infrastructure.handler;

import com.hv.demo.application_form_url_encoded.constants.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 16/07/2020
 */
//@ControllerAdvice
public class ResponseHttpExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja todas las exception que no han sido controladas
     * retorna la descripcion del error en el header
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AppConstants.ERROR, ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(null,headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        headers.add(AppConstants.ERROR,"request invalida");
        ex.printStackTrace();
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    /**
     *  Obtiene todos los errores de entrada del usuario
     *  y los retorna en el header bajo la etiqueta: Error
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        int c=0;
        for (Object error : ex.getBindingResult().getAllErrors()) {
            if(error instanceof FieldError) {
                c++;
                FieldError fieldError = (FieldError) error;
                String field = fieldError.getField();
                if(field.contains(".")) field = field.split("\\.")[1];
                StringBuilder builder = new StringBuilder();
                builder.append("variable: ");
                builder.append(field);
                builder.append(" message: ");
                builder.append(fieldError.getDefaultMessage());
                headers.add(AppConstants.ERROR, builder.toString());
            }
        }
        return new ResponseEntity<>(null,headers, HttpStatus.BAD_REQUEST);
    }
}
