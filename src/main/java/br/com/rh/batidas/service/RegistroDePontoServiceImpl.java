package br.com.rh.batidas.service;

import br.com.rh.batidas.model.RegistroDePonto;
import br.com.rh.batidas.model.enums.TipoRegistroPonto;
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

        validarRegistroDePonto(horario);
    }

    @Override
    public RegistroDePonto validarRegistroDePonto(LocalDateTime registro) {
        if (LocalDateUtils.isFinalDeSemana(registro.toLocalDate())) {
            return null;
//            Sábado e domingo não são permitidos como dia de trabalho
        }

        RegistroDePonto ponto = null;//registroDePontoRepository.findTop1ByTipoRegistro();
        if (ponto == null)
            TipoRegistroPonto.ENTRADA.registroDaBatida().montarRegistro(registro,false);

        if (ponto.getDataHora().toLocalDate().equals(registro.toLocalDate())) {
            return null;
            //        Horários já registrado
        }

        if (ponto.getTipoRegistro().equals(TipoRegistroPonto.SAIDA)) {
            return null;
//            Apenas 4 horários podem ser registrados por dia

        }

        if (ponto.equals(TipoRegistroPonto.ENTRADA))
            return montarRegistro(registro, TipoRegistroPonto.INTERVALO, false);

        if (ponto.getIsRetornoAlmoco()) {
            return montarRegistro(registro, TipoRegistroPonto.SAIDA, false);
        }
        RegistroDePonto ponto1 =

        return ponto;
    }

    @Override
    public RegistroDePonto montarRegistro(LocalDateTime horario, TipoRegistroPonto tipoRegistroPonto, Boolean retorno) {
        return RegistroDePonto
                .builder()
                .dataHora(horario)
                .tipoRegistro(TipoRegistroPonto.ENTRADA)
                .isRetornoAlmoco(retorno)
                .build();
    }

    @Override
    public RegistroDePonto recuperarUltimoRegistroDePonto(LocalDateTime registro) {
        return null;
    }

}
