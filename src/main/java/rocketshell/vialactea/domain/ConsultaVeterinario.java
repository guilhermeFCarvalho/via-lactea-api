package rocketshell.vialactea.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consulta_veterinario")
public class ConsultaVeterinario extends BaseEntity{

	private String descricao;
	
  @JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_da_consulta")
	private Date dataDaConsulta;
	
    @ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;
	
}
