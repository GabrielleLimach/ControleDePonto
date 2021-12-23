package br.com.rh.batidas.controller;

import br.com.rh.batidas.model.exception.FinalDeSemanaException;
import br.com.rh.batidas.model.exception.HorarioJaRegistradoException;
import br.com.rh.batidas.model.exception.HorarioMinimoIntervaloException;
import br.com.rh.batidas.model.handle.StandardErrorHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import static org.mockito.Mockito.*;

public class ExceptionHandlerControllerTest {
    @Mock
    Logger log;
    @Mock
    private FinalDeSemanaException finalDeSemanaException;
    @Mock
    private MissingServletRequestParameterException missingServletRequestParameterException;
    @Mock
    private DateTimeParseException dateTimeParseException;
    @Mock
    private HorarioJaRegistradoException horarioJaRegistradoException;
    @Mock
    private IllegalStateException illegalStateException;
    @Mock
    private HorarioMinimoIntervaloException horarioMinimoIntervaloException;
    @Mock
    private Exception exception;
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    ExceptionHandlerController exceptionHandlerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandlerDateTimeParseViolada() throws Exception {
        ResponseEntity<StandardErrorHandler> result = exceptionHandlerController.handlerDateTimeParseViolada(dateTimeParseException, request);
        ResponseEntity<StandardErrorHandler> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardErrorHandler());
        Assert.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assert.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }

    @Test
    public void testHanlderValidacaoTipoInvalido() throws Exception {
        ResponseEntity<StandardErrorHandler> result = exceptionHandlerController.hanlderValidacaoTipoInvalido(illegalStateException, request);
        ResponseEntity<StandardErrorHandler> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardErrorHandler());
        Assert.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assert.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }

    @Test
    public void testHandlerAll() throws Exception {
        ResponseEntity<StandardErrorHandler> result = exceptionHandlerController.handlerAll(exception, request);
        ResponseEntity<StandardErrorHandler> expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardErrorHandler());
        Assert.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assert.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }

    @Test
    public void testCampoNaoEncontrado() throws Exception {
        ResponseEntity<StandardErrorHandler> result = exceptionHandlerController.campoNaoEncontrado(missingServletRequestParameterException, request);
        ResponseEntity<StandardErrorHandler> expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardErrorHandler());
        Assert.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assert.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }

    @Test
    public void testHandlerObjetoNaoEncontrado() throws Exception {
        ResponseEntity<StandardErrorHandler> result = exceptionHandlerController.handlerObjetoNaoEncontrado(finalDeSemanaException, null);
        ResponseEntity<StandardErrorHandler> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardErrorHandler());
        Assert.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assert.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }

    @Test
    public void testHandlerDestinatarioInvalido() throws Exception {
        ResponseEntity<StandardErrorHandler> result = exceptionHandlerController.handlerDestinatarioInvalido(horarioJaRegistradoException, null);
        ResponseEntity<StandardErrorHandler> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardErrorHandler());
        Assert.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assert.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }

    @Test
    public void testHandlerHorarioMinimoInvalido() throws Exception {
        ResponseEntity<StandardErrorHandler> result = exceptionHandlerController.handlerHorarioMinimoInvalido(horarioMinimoIntervaloException, null);
        ResponseEntity<StandardErrorHandler> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardErrorHandler());
        Assert.assertEquals(expected.getStatusCode(), result.getStatusCode());
        Assert.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }
}