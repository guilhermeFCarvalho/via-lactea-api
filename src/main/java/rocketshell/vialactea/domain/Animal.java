package rocketshell.vialactea.domain;

import java.math.BigDecimal;
import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import rocketshell.vialactea.base.BaseEntity;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal extends BaseEntity{
	
   @OneToOne
   @JoinColumn(name = "parentesco_animal_id")
   private Animal parentescoAnimal;
   
   private String especie;
   
   
   private BigDecimal peso;
   
   private String raca;
   
   @Column(name = "quantidade_de_crias")
   private int quantidadeDeCrias;
   
   @JsonFormat(pattern = "dd/MM/yyyy")
   @Column(name = "data_de_nascimento")
   private LocalDate dataDeNascimento;
   
   @JsonFormat(pattern = "dd/MM/yyyy")
   @Column(name = "data_ultima_gestacao")
   private LocalDate dataUltimaGestacao;
   
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
