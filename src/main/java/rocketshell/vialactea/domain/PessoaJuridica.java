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
public class PessoaJuridica extends Pessoa {

  @Column(name = "razao_social")
  private String razaoSocial;
  
  private String telefone;

  @Column(name = "cnpj")
  private String CNPJ;
  
  @Column(name = "inscricao_estadual")
  private String inscricaoEstadual;

}
