package rocketshell.vialactea.domain;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReciboDeVenda extends BaseEntity {

  private BigDecimal quantidadeLeiteVendida;
  private Date dataDaVenda;
  private String observacoes;
  private Boolean pago;

}
