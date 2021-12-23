package br.com.rh.batidas.service;

import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;
import br.com.rh.batidas.repository.RegistroDePontoRepository;
import br.com.rh.batidas.utils.LocalDateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class RegistroDePontoServiceImplTest {
    @Mock
    RegistroDePontoRepository registroDePontoRepository;
    @Mock
    Logger log;
    @InjectMocks
    RegistroDePontoServiceImpl registroDePontoServiceImpl;
    RegistroDePonto registroDePonto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registroDePonto = RegistroDePonto.builder()
                .isRetornoAlmoco(true)
                .dataHora(LocalDateUtils.toConvertStringLocalDateTime("2021-12-22T23:47:14"))
                .tipoRegistro(TipoRegistroPonto.ENTRADA)
                .build();
    }

    @Test
    public void testRegistrarPonto() throws Exception {
        when(registroDePontoRepository.findTop1ByDataHora(any())).thenReturn(registroDePonto);

        registroDePontoServiceImpl.registrarPonto(LocalDateTime.of(2021, Month.DECEMBER, 22, 23, 56, 27));
    }

    @Test
    public void testValidarRegistroDePonto() throws Exception {
        registroDePonto.setTipoRegistro(TipoRegistroPonto.SAIDA);
        registroDePonto.setIsRetornoAlmoco(false);
        when(registroDePontoRepository.findTop1ByDataHora(any())).thenReturn(registroDePonto);

        RegistroDePonto result = registroDePontoServiceImpl.validarRegistroDePonto(registroDePonto.getDataHora());
        Assert.assertEquals(registroDePonto, result);
    }
}