package rocketshell.vialactea.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import rocketshell.vialactea.base.BaseEntity;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal extends BaseEntity{
   @OneToOne
   private Animal parentesco;
   private String especie;
   private Float peso;
   private String raca;
   private int quantidadeDeCrias;
   private Date dataDeNascimento;
   private Date dataUltimaGestacao;
   private String tipoAlimentação;
   private String identificacao;
   @OneToOne
   private Animal animalQueCruzou;
   @Enumerated(EnumType.STRING)
   private Sexo sexo;
   
   
}
