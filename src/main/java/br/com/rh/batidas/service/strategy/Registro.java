package br.com.rh.batidas.service.strategy;

import br.com.rh.batidas.model.RegistroDePonto;

import java.time.LocalDateTime;

public interface Registro {

    RegistroDePonto validarRegistroDePonto(RegistroDePonto ponto, LocalDateTime registro);

    RegistroDePonto montarRegistro(LocalDateTime horario, Boolean retorno);
}
