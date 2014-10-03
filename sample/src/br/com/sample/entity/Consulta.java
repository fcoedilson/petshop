package br.com.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.sample.type.StatusConsulta;

@Entity
public class Consulta implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="consulta_id")
	private long id;

	@ManyToOne
	@JoinColumn(name="medico_id", nullable=false)
	private Medico medico;

	@ManyToOne
	@JoinColumn(name="animal_id")
	private Animal animal;

	@ManyToOne
	@JoinColumn(name="tipo_consulta_id")
	private TipoConsulta consulta;

	@Enumerated(EnumType.STRING)
	private StatusConsulta status;

	private String prontuario;

	private String anotacoes;

	public long getId() {
		return id;
	}

	public TipoConsulta getConsulta() {
		return consulta;
	}

	public void setConsulta(TipoConsulta consulta) {
		this.consulta = consulta;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}



	public StatusConsulta getStatus() {
		return status;
	}

	public void setStatus(StatusConsulta status) {
		this.status = status;
	}

	public String getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
