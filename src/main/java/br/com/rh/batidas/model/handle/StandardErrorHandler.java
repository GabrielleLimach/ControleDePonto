package br.com.rh.batidas.model.handle;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class StandardErrorHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String message;


    public StandardErrorHandler() {
    }

    public StandardErrorHandler(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}