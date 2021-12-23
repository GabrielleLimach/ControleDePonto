package br.com.rh.batidas.service.strategy;

import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;
import br.com.rh.batidas.model.exception.HorarioJaRegistradoException;

import java.time.LocalDateTime;

public class RegistroEntrada implements Registro{

    @Override
    public RegistroDePonto validarRegistroDePonto(RegistroDePonto ponto, LocalDateTime registro) {
        if(ponto.getDataHora().equals(registro))
            throw new HorarioJaRegistradoException("Horários já registrado");

        return TipoRegistroPonto.INTERVALO.registroDaBatida().montarRegistro(registro, false);
    }

    @Override
    public RegistroDePonto montarRegistro(LocalDateTime horario, Boolean retorno) {
        return RegistroDePonto
                .builder()
                .dataHora(horario)
                .tipoRegistro(TipoRegistroPonto.ENTRADA)
                .isRetornoAlmoco(retorno)
                .build();
    }
}
