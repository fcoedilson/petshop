package br.com.sample.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value="P")
@PrimaryKeyJoinColumn(name="pessoa_id")
public class Medico extends Pessoa{

	private String especialidade;
	
	private String crmv;
	
	private Integer anoFormacao;
	
	private String formacao;
	
	private String turno;
	
	private String horario;

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getCrmv() {
		return crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}

	public Integer getAnoFormacao() {
		return anoFormacao;
	}

	public void setAnoFormacao(Integer anoFormacao) {
		this.anoFormacao = anoFormacao;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((crmv == null) ? 0 : crmv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (crmv == null) {
			if (other.crmv != null)
				return false;
		} else if (!crmv.equals(other.crmv))
			return false;
		return true;
	}
	
}
