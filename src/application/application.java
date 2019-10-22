package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Acao;

public class application {

	public static void main(String[] args) {

		// declara Lista para sortear as porcentagens de cada ação
		List<Integer> porcentagem = new ArrayList();
		porcentagem.add(30);
		porcentagem.add(25);
		porcentagem.add(20);
		porcentagem.add(15);
		porcentagem.add(10);

		// instanciando ações
		Acao A = new Acao("A", 0.42292, sorteia(porcentagem));
		Acao B = new Acao("B", 0.25685, sorteia(porcentagem));
		Acao C = new Acao("C", 0.04128, sorteia(porcentagem));
		Acao D = new Acao("D", 0.05812, sorteia(porcentagem));
		Acao E = new Acao("E", 0.01731, sorteia(porcentagem));

		// mostrar carteira inicial
		Acao carteiraInicial[] = { A, B, C, D, E };
		Acao carteiraVizinha[] = carteiraInicial;
		mostraCarteiraInicial(carteiraInicial);
	}

	// método somatório para definir o retorno da carteira - Soma(RetornoAção_X *
	// PorcentagemAção_X)
	public static double retornoCarteira(Acao somar[]) {

		double soma = 0.0;

		for (int i = 0; i < somar.length; i++) {

			soma += somar[i].retornoAnual();

		}

		return soma;

	}

	// método para sortear as porcentagens para cada ação
	public static int sorteia(List sorteia) {

		Collections.shuffle(sorteia);

		return ((Integer) sorteia.remove(0)).intValue();

	}

	// método para verificar a soma das porcentagens do investimento
	public static double somaPorcentagem(Acao soma[]) {

		double somar = 0;

		for (int i = 0; i < soma.length; i++) {

			somar += soma[i].getPorcentagemAcao();
		}

		return somar;
	}

	public static void mostraCarteiraInicial(Acao a[]) {
		DecimalFormat df = new DecimalFormat("###,##0.00000");
		System.out.println("Carteira inicial: \n");

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
				"\n-----------------------------------------------------------------------------------------------------------------------");

	}
}
