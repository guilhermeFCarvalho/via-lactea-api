package rocketshell.vialactea.domain;

import javax.persistence.Entity;

import lombok.Data;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
public class Endereco extends BaseEntity{

	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String cep;
	
	public Endereco() {
		super();
	}
}
