package br.com.rh.batidas.service.strategy;

import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;

import java.time.LocalDateTime;

public class RegistroSaida implements Registro{

    @Override
    public RegistroDePonto validarRegistroDePonto(RegistroDePonto ponto, LocalDateTime registro) {
        return null;
//            Apenas 4 horários podem ser registrados por dia
        return montarRegistro(registro, false);
    }

    @Override
    public RegistroDePonto montarRegistro(LocalDateTime horario, Boolean retorno) {
        return RegistroDePonto
                .builder()
                .dataHora(horario)
                .tipoRegistro(TipoRegistroPonto.SAIDA)
                .isRetornoAlmoco(retorno)
                .build();
    }
}
