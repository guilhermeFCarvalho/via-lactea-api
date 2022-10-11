package rocketshell.vialactea.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisica extends Pessoa {
  
  private String nome;
  private String sobrenome;

  @Column(name = "cpf")
  private String CPF;

}
