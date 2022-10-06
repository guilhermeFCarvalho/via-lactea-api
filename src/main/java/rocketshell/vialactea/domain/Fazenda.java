package rocketshell.vialactea.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fazenda extends BaseEntity {

  private String nomeDaFazenda;
  private String telefone;

  @OneToOne(cascade = CascadeType.ALL)
  private Endereco endereco;
}
