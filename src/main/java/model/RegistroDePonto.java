package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroDePonto {

    private Long id;
    private Long idFuncionario;
    private LocalDateTime dataHora;

}
