package br.com.rh.batidas.service;


import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;
import br.com.rh.batidas.model.exception.FinalDeSemanaException;
import br.com.rh.batidas.repository.RegistroDePontoRepository;
import br.com.rh.batidas.utils.LocalDateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistroDePontoServiceImpl implements RegistroDePontoService {

    private final RegistroDePontoRepository registroDePontoRepository;

    @Override
    public void registrarPonto(LocalDateTime horario) {
        RegistroDePonto ponto = validarRegistroDePonto(horario);

        registroDePontoRepository.save(ponto);
    }

    @Override
    public RegistroDePonto validarRegistroDePonto(LocalDateTime registro) {

        if (LocalDateUtils.isFinalDeSemana(registro.toLocalDate()))
            new FinalDeSemanaException("Sábado e domingo não são permitidos como dia de trabalho");

        RegistroDePonto ponto = registroDePontoRepository.findTop1ByDataHora(registro.toLocalDate());
        if (ponto == null) {
            ponto =  TipoRegistroPonto.ENTRADA.registroDaBatida().montarRegistro(registro, false);
            registroDePontoRepository.save(ponto);
            return ponto;
        }

       return ponto.getTipoRegistro().registroDaBatida().validarRegistroDePonto(ponto, registro);

    }


    @Override
    public RegistroDePonto recuperarUltimoRegistroDePonto(LocalDateTime registro) {
        return null;
    }

}
