package br.com.rh.batidas.repository;


import br.com.rh.batidas.model.RegistroDePonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDePontoRepository extends JpaRepository<RegistroDePonto, Long> {

//    RegistroDePonto findTop1ByTipoRegistro();

}
