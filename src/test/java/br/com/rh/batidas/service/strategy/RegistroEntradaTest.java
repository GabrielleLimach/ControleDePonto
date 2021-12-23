package br.com.rh.batidas.service.strategy;

import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;
import br.com.rh.batidas.utils.LocalDateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class RegistroEntradaTest {
    RegistroEntrada registroEntrada = new RegistroEntrada();

    RegistroDePonto registroDePonto = RegistroDePonto.builder()
            .isRetornoAlmoco(true)
            .dataHora(LocalDateUtils.toConvertStringLocalDateTime("2021-12-22T23:47:14"))
            .tipoRegistro(TipoRegistroPonto.ENTRADA)
            .build();

    @Test
    public void testValidarRegistroDePonto() throws Exception {
        registroDePonto.setTipoRegistro(TipoRegistroPonto.INTERVALO);
        RegistroDePonto result = registroEntrada.validarRegistroDePonto(registroDePonto, LocalDateTime.of(2021,12,22,9,47,14));
        Assert.assertEquals(registroDePonto.getTipoRegistro(), result.getTipoRegistro());
    }

    @Test
    public void testMontarRegistro() throws Exception {
        RegistroDePonto result = registroEntrada.montarRegistro(LocalDateTime.of(2021, Month.DECEMBER, 22, 23, 47, 14), Boolean.TRUE);
        Assert.assertEquals(registroDePonto, result);
    }
}
