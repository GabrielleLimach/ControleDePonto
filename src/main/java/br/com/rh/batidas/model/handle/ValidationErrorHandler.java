package br.com.rh.batidas.model.handle;

import lombok.Data;

@Data
public class ValidationErrorHandler extends StandardErrorHandler {

    public ValidationErrorHandler(Integer status,String message) {
        super(status, message);
    }

}