package br.com.rh.batidas.repository;


import br.com.rh.batidas.model.RegistroDePonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RegistroDePontoRepository extends JpaRepository<RegistroDePonto, Long> {

    @Query(value = "SELECT  id, data_hora, is_retorno_almoco, tipo_registro FROM registro_de_ponto WHERE cast(data_hora as date) =:data order by data_hora desc limit 1", nativeQuery = true)
    RegistroDePonto findTop1ByDataHora(@Param("data") LocalDate data);

}