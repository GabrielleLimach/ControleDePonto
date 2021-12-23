package br.com.rh.batidas.model;

import br.com.rh.batidas.model.enums.TipoRegistroPonto;

import java.time.LocalDateTime;

public class BatidaDTO {

    private LocalDateTime dataHora;
    private TipoRegistroPonto tipoRegistro;
    private Boolean isRetornoAlmoco = Boolean.FALSE;

}
