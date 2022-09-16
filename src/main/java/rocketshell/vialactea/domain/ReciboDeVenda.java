package rocketshell.vialactea.domain;

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

  private int asdf;
  private Date dataDaColeta;
  private String observacoes;
  private boolean pago;

}
