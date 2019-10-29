package model;

public class Acao {

	private String acao;
	private double retornoAcao;
	private double porcentagemAcao;

	public Acao() {
	}

	public Acao(String acao, double retornoAnual, double porcentagemAcao) {
		this.acao = acao;
		this.retornoAcao = retornoAnual;
		this.porcentagemAcao = porcentagemAcao;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public double getRetornoAcao() {
		return retornoAcao;
	}

	public void setRetornoAcao(double retornoAcao) {
		this.retornoAcao = retornoAcao;
	}

	public double getPorcentagemAcao() {
		return porcentagemAcao;
	}

	public void setPorcentagemAcao(double porcentagemAcao) {
		this.porcentagemAcao = porcentagemAcao;
	}

	@Override
	public String toString() {
		return " "+acao + "        " + retornoAcao  + "             " + porcentagemAcao + "%";
	}

	public double retornoAnual() {

		return retornoAcao * (porcentagemAcao / 100);

	}
	
	public double acrescentaPorcentagem(double add) {
		return porcentagemAcao + add;
	}
	
	public double diminuiPorcentagem(double add) {
		return porcentagemAcao - add;
	}

}
