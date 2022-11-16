package rocketshell.vialactea.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import rocketshell.vialactea.domain.ReciboDeVenda;

import java.util.List;

public interface ReciboDeVendaRepository extends JpaRepository<ReciboDeVenda, Long> {

    Page<ReciboDeVenda> findReciboDeVendaByPropriedadeId(Long id, Pageable pageable);

    ReciboDeVenda findFirstByPropriedadeIdOrderByIdDesc(Long id);


}
