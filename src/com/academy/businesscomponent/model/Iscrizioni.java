package com.academy.businesscomponent.model;

public class Iscrizioni {
	private long codCorso;
	private long codCorsista;
	private String adminUsername;

	public long getCodCorso() {
		return codCorso;
	}

	public void setCodCorso(long codCorso) {
		this.codCorso = codCorso;
	}

	public long getCodCorsista() {
		return codCorsista;
	}

	public void setCodCorsista(long codCorsista) {
		this.codCorsista = codCorsista;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	@Override
	public String toString() {
		return "Iscrizioni [codCorso=" + codCorso + ", codCorsista=" + codCorsista + ", adminUsername=" + adminUsername
				+ "]";
	}

}
