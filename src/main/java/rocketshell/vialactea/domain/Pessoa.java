package rocketshell.vialactea.domain;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa extends BaseEntity {

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "pessoa_id")
  private List<Propriedade> propriedades;

  private String telefone;

  @OneToOne(cascade = CascadeType.ALL)
  private Usuario usuario;

}
