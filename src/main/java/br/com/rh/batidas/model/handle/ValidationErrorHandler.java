package br.com.rh.batidas.model.handle;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorHandler extends StandardErrorHandler {

    public ValidationErrorHandler(Integer status,String message) {
        super(status, message);
    }

}