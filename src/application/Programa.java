package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Veiculo;
import model.entities.aluguelCarro;
import model.services.servicoAluguel;
import model.services.taxaServicoBrasil;

public class Programa {

	public static void main(String[] args) throws ParseException {
	
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Entre com os dados do aluguel: ");
		System.out.printf("Modelo do Carro: ");
		String modelo = sc.nextLine();
		Veiculo veiculo = new Veiculo(modelo);
		System.out.printf("Horário da Retirada(dd/mmm/aaaa hh:mm): ");
		Date inicio = sdf.parse(sc.nextLine());
		System.out.printf("Horário de Entrega (dd/mm/aaaa hh:mm): ");
		Date fim = sdf.parse(sc.nextLine());

		aluguelCarro aluguel = new aluguelCarro(inicio, fim, veiculo);

		System.out.printf("Preço por Hora: R$ ");
		double precoPorHora = sc.nextDouble();
		System.out.printf("Preço por Dia: R$ ");
		double precoPorDia = sc.nextDouble();
		
		servicoAluguel servAluguel = new servicoAluguel(precoPorHora, precoPorDia, new taxaServicoBrasil());
		
		servAluguel.processoFatura(aluguel);
		
		System.out.println();
		System.out.println("FATURA: ");
		System.out.println("Pagamento Básico: R$"+ String.format("%.2f", aluguel.getFatura().getPagamentoBasico()));
		System.out.println("Taxa: R$"+String.format("%.2f", aluguel.getFatura().getTaxa()));
		System.out.println("Total de Pagamento: R$"+String.format("%.2f", (aluguel.getFatura().totalPagamento())));
		sc.close();
	}
}
