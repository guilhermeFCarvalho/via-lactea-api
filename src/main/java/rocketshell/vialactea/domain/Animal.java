package rocketshell.vialactea.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
   @JoinColumn(name = "parentesco_animal_id")
   private Animal parentescoAnimal;
   
   private String especie;
   
   
   private Float peso;
   
   private String raca;
   
   @Column(name = "quantidade_de_crias")
   private int quantidadeDeCrias;
   
   @Column(name = "data_de_nascimento")
   private Date dataDeNascimento;
   
   @Column(name = "data_ultima_gestacao")
   private Date dataUltimaGestacao;
   
   @Column(name = "tipo_alimentacao")
   private String tipoAlimentacao;
   
   private String identificacao;
   
   @OneToOne
   @JoinColumn(name = "animal_que_cruzou_id")
   private Animal animalQueCruzou;
   
   @Enumerated(EnumType.STRING)
   private Sexo sexo;
   
   @ManyToOne
   @JoinColumn(name = "fazenda_id")
   private Fazenda fazenda;
   
}
