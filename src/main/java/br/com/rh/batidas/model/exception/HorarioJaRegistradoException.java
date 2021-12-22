package br.com.rh.batidas.model.exception;

public class HorarioJaRegistradoException extends RuntimeException{

    public HorarioJaRegistradoException(String message) {
        super( message );
    }
}
