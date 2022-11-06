package rocketshell.vialactea.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recibodevenda")
public class ReciboDeVenda extends BaseEntity {

  @Column(name = "quantidadedeleite")
  private BigDecimal quantidadeLeiteVendida;

  @Column(name = "datadavenda")
  private LocalDate dataDaVenda = LocalDate.now();

  @Column(name = "observacoes")
  private String observacoes;

  @Column(name = "pago")
  private Boolean pago;

}
