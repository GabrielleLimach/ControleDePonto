package br.com.rh.batidas.controller;


import br.com.rh.batidas.model.exception.FinalDeSemanaException;
import br.com.rh.batidas.model.exception.HorarioJaRegistradoException;
import br.com.rh.batidas.model.exception.HorarioMinimoIntervaloException;
import br.com.rh.batidas.model.handle.StandardErrorHandler;
import br.com.rh.batidas.model.handle.ValidationErrorHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController{

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<StandardErrorHandler> handlerDateTimeParseViolada(DateTimeParseException e, HttpServletRequest request) {
        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.BAD_REQUEST, e, request);
        err.setMessage("Data e hora em formato inválido");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<StandardErrorHandler> hanlderValidacaoTipoInvalido(IllegalStateException e, HttpServletRequest request) {
        StandardErrorHandler err = this.geraStandarErrorHandler(HttpStatus.BAD_REQUEST, e, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorHandler> handlerAll(Exception e, HttpServletRequest request) {
        StandardErrorHandler err = this.geraStandarErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardErrorHandler> campoNaoEncontrado(MissingServletRequestParameterException e, HttpServletRequest request) {
        StandardErrorHandler err = this.geraStandarErrorHandler(HttpStatus.BAD_REQUEST, e, request);
        err.setMessage("Campo obrigatório não informado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(FinalDeSemanaException.class)
    public ResponseEntity<StandardErrorHandler> handlerObjetoNaoEncontrado(FinalDeSemanaException e, HttpServletRequest request) {
        StandardErrorHandler err = this.geraStandarErrorHandler(HttpStatus.FORBIDDEN, e, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HorarioJaRegistradoException.class)
    public ResponseEntity<StandardErrorHandler> handlerDestinatarioInvalido(HorarioJaRegistradoException e, HttpServletRequest request) {
        StandardErrorHandler err = this.geraStandarErrorHandler(HttpStatus.CONFLICT, e, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HorarioMinimoIntervaloException.class)
    public ResponseEntity<StandardErrorHandler> handlerHorarioMinimoInvalido(HorarioMinimoIntervaloException e, HttpServletRequest request) {
        StandardErrorHandler err = this.geraStandarErrorHandler(HttpStatus.FORBIDDEN, e, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    private ValidationErrorHandler geraValidationErrorHandler(HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        return new ValidationErrorHandler(
                LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.name(),
                e.getMessage(),
                request.getServletPath());
    }

    private StandardErrorHandler geraStandarErrorHandler(HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        return new StandardErrorHandler(
                httpStatus.value(),
                e.getMessage());
    }

}