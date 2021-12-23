package br.com.rh.batidas.controller.registros;

import br.com.rh.batidas.service.RegistroDePontoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class BatidasControllerTest {
    @Mock
    RegistroDePontoService registroDePontoService;
    @Mock
    Logger log;
    @InjectMocks
    BatidasController batidasController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrarHorario() throws Exception {
        ResponseEntity result = batidasController.registrarHorario("2018-08-22T08:00:00");
        Assert.assertEquals(new ResponseEntity(HttpStatus.CREATED), result);
    }
}
