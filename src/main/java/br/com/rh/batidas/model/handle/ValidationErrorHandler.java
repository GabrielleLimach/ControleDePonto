package br.com.rh.batidas.model.handle;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorHandler extends StandardErrorHandler {

    public ValidationErrorHandler(LocalDateTime timeStamp, Integer status, String error, String message, String path) {
        super(status, message);
    }

}