package br.com.rh.batidas.service;

import br.com.rh.batidas.model.RegistroDePonto;

import java.time.LocalDateTime;

public interface RegistroDePontoService {

    void registrarPonto(LocalDateTime horario);

    RegistroDePonto validarRegistroDePonto(LocalDateTime registro);
}
