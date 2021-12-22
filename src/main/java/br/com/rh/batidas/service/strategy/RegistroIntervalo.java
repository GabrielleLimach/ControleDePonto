package br.com.rh.batidas.service.strategy;

import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;
import br.com.rh.batidas.model.exception.HorarioJaRegistradoException;
import br.com.rh.batidas.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class RegistroIntervalo implements Registro{

    @Override
    public RegistroDePonto validarRegistroDePonto(RegistroDePonto ponto, LocalDateTime registro) {
        if(ponto.getIsRetornoAlmoco())
            return TipoRegistroPonto.SAIDA.registroDaBatida().montarRegistro(registro, false);

        if(LocalDateUtils.diferencaEntreHoras(ponto.getDataHora(), registro) < 1)
            new HorarioJaRegistradoException("Deve haver no mínimo 1 hora de almoço");

        return montarRegistro(registro, true);
    }

    @Override
    public RegistroDePonto montarRegistro(LocalDateTime horario, Boolean retorno) {
        return RegistroDePonto
                .builder()
                .dataHora(horario)
                .tipoRegistro(TipoRegistroPonto.INTERVALO)
                .isRetornoAlmoco(retorno)
                .build();
    }
}
