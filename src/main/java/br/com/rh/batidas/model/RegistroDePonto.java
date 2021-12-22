package br.com.rh.batidas.model;

import br.com.rh.batidas.model.enums.TipoRegistroPonto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDePonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private TipoRegistroPonto tipoRegistro;
    private Boolean isRetornoAlmoco = Boolean.FALSE;


    public LocalDate getDataHoraRegistro() {
        return this.dataHora.toLocalDate();
    }
}
