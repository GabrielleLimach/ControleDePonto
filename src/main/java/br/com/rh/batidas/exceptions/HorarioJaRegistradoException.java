package br.com.rh.batidas.exceptions;

public class HorarioJaRegistradoException extends RuntimeException{

    public HorarioJaRegistradoException(String message) {
        super( message );
    }
}
