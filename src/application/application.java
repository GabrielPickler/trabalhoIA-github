package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Acao;

public class application {

	public static void main(String[] args) {

		// declara Lista para sortear as porcentagens de cada ação
		DecimalFormat df = new DecimalFormat("###,##0.00000");
		List<Double> porcentagem = new ArrayList<>();
		porcentagem.add(30.0);
		porcentagem.add(25.0);
		porcentagem.add(20.0);
		porcentagem.add(15.0);
		porcentagem.add(10.0);

		// instanciando ações
		Acao A1 = new Acao("A", 0.42292, sorteia(porcentagem));
		Acao B1 = new Acao("B", 0.25685, sorteia(porcentagem));
		Acao C1 = new Acao("C", 0.04128, sorteia(porcentagem));
		Acao D1 = new Acao("D", 0.05812, sorteia(porcentagem));
		Acao E1 = new Acao("E", 0.01731, sorteia(porcentagem));

		// mostrar carteira inicial
		Acao carteiraInicial[] = { A1, B1, C1, D1, E1 };
		Acao Vizinho[] = { A1, B1, C1, D1, E1 };
		double maior[] = new double[5];
		System.out.println("Carteira Inicial\n");
		mostraCarteiraInicial(carteiraInicial);

		// Criação da matriz da carteira vizinha

		double maiorValor = retornoCarteira(carteiraInicial);
		double retornoVizinho = 0.0;
		int add = 0;
		int aux = add + 1;
		double porcen = 5.0;
		
		// while (retornoVizinho <= maiorValor) {
		System.out.println("\n\nCarteira Vizinha " + (add+1) + "\n");
		System.out.println(
				"-----------------------------------------------------------------------------------------");

		for (int i = 0; i < Vizinho.length; i++) {
			System.out.print(" |     "+Vizinho[i].getAcao()+"     ");		
		}
		System.out.print("  |  Retorno Carteira: |");
		System.out.print(
				"\n-----------------------------------------------------------------------------------------\n");

		Vizinho[add].setPorcentagemAcao(Vizinho[add].acrescentaPorcentagem(porcen));
		for (int j = 0; j < Vizinho.length-1; j ++) {			
			Vizinho[aux].setPorcentagemAcao(Vizinho[aux].diminuiPorcentagem(porcen));
			retornoCarteira(Vizinho);
			if (retornoCarteira(Vizinho) > maiorValor) {
				maiorValor = retornoCarteira(Vizinho);
				for (int i = 0; i < maior.length; i++) {
					maior[i] = Vizinho[i].getPorcentagemAcao();
				}
			}
			mostraVizinho(Vizinho);
			Vizinho[aux].setPorcentagemAcao(Vizinho[aux].acrescentaPorcentagem(porcen));
			aux++;
			if (aux > Vizinho.length) {
				aux = 0;
			}
			Vizinho = carteiraInicial;
			
		}
			add++;
			System.out.println("");
			for(int i =0; i<maior.length;i++) {
			System.out.print(maior[i] + "%, ");
			}
			System.out.print("\nRetorno Carteira = "+df.format(maiorValor));
		// }

	}

	// método somatório para definir o retorno da carteira - Soma(RetornoAção_X * PorcentagemAção_X)
	public static double retornoCarteira(Acao somar[]) {

		double soma = 0.0;

		for (int i = 0; i < somar.length; i++) {

			soma += somar[i].retornoAnual();

		}

		return soma;

	}

	// método para sortear as porcentagens para cada ação
	public static Double sorteia(List<Double> sorteia) {

		Collections.shuffle(sorteia);

		return ((Double) sorteia.remove(0)).doubleValue();

	}

	// método para verificar a soma das porcentagens do investimento
	public static double somaPorcentagem(Acao soma[]) {

		double somar = 0;

		for (int i = 0; i < soma.length; i++) {

			somar += soma[i].getPorcentagemAcao();
		}

		return somar;
	}

	public static void aumentaPorcentagem(Acao carteira[][], int aumenta) {

		for (int i = 0; i < carteira.length; i++) {
			for (int j = 0; j < carteira.length; j++) {
				if (carteira[i][j] == carteira[i][aumenta]) {
					carteira[i][j].setPorcentagemAcao(carteira[i][j].acrescentaPorcentagem(5.0));
				}
			}
		}

	}

	public static void mostraCarteiraInicial(Acao a[]) {
		DecimalFormat df = new DecimalFormat("###,##0.00000");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------");
		System.out.print("|     Ação         |    ");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].getAcao() + "      |       ");
		}
		System.out.print("Retorno Carteira:  |");
		System.out.print(
				"\n-----------------------------------------------------------------------------------------------------------------------");
		System.out.print("\n|  Retorno Anual   |  ");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].getRetornoAcao() + "  |     ");
		}
		System.out.print("      " + df.format(retornoCarteira(a)) + "        |");
		System.out.print(
				"\n-----------------------------------------------------------------------------------------------------------------------");
		System.out.print("\n|Carteira Escolhida|   ");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].getPorcentagemAcao() + "%" + "   |      ");
		}
		System.out.print("     " + somaPorcentagem(a) + "%         |");
		System.out.print(
				"\n-----------------------------------------------------------------------------------------------------------------------\n");

	}
	
	public static void mostraVizinho(Acao a[]) {
		
		DecimalFormat df = new DecimalFormat("###,##0.00000");	
		for (int i = 0; i < a.length; i++) {
			System.out.print(" |   "+a[i].getPorcentagemAcao() + "%" + "   ");
		}
		System.out.print("  |     " + df.format(retornoCarteira(a)) + "       |");
		System.out.print(
				"\n-----------------------------------------------------------------------------------------\n");

	}
	
}
