package br.com.rh.batidas.service.strategy;

import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;
import br.com.rh.batidas.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class RegistroIntervalo implements Registro{
    @Override
    public RegistroDePonto validarRegistroDePonto(RegistroDePonto ponto, LocalDateTime registro) {
        if(LocalDateUtils.diferencaEntreHoras(ponto.getDataHora(), registro) < 1)
            return null;

        if(ponto.getIsRetornoAlmoco())
            return montarRegistro(registro, false);

        return null;

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
