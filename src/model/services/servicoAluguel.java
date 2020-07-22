package model.services;

import model.entities.Fatura;
import model.entities.aluguelCarro;

public class servicoAluguel {
	
	private Double precoPorHora;
	private Double precoPorDia;
	private TaxaServico taxaServico;
	
	public servicoAluguel(Double precoPorHora, Double precoPorDia, TaxaServico taxaServico) {
		this.precoPorHora = precoPorHora;
		this.precoPorDia = precoPorDia;
		this.taxaServico = taxaServico;
	}

	public void processoFatura(aluguelCarro aluguelCarro) {
		long t1 = aluguelCarro.getInicio().getTime();
		long t2 = aluguelCarro.getFim().getTime();
		double horas = (double)(t2-t1)/1000/60/60;
		
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas)*precoPorHora;
		}
		else {
			pagamentoBasico = Math.ceil(horas/24)*precoPorDia;
		}
		double taxa = taxaServico.taxa(pagamentoBasico);
		aluguelCarro.setFatura(new Fatura(pagamentoBasico, taxa));
	
	}
}
