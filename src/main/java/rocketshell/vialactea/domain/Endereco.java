package rocketshell.vialactea.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends BaseEntity {

  private String rua;
  private String numero;
  private String bairro;
  private String cidade;
  private String cep;
  private String estado;

}
