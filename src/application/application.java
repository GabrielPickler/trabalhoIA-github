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
		// for (int x = 0; x < 5; x++) {
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
		int contaCarteira = 0;
		Acao carteiraInicial[] = { A1, B1, C1, D1, E1 };
		double porcentagemInicial[] = { A1.getPorcentagemAcao(), B1.getPorcentagemAcao(), C1.getPorcentagemAcao(),
				D1.getPorcentagemAcao(), E1.getPorcentagemAcao() };
		Acao Vizinho[] = { A1, B1, C1, D1, E1 };
		double maior[] = new double[5];
		System.out.println("Carteira Inicial\n");
		mostraCarteiraInicial(carteiraInicial);

		// Criação da matriz da carteira vizinha
		double vizinhoAnterior = 0.0;
		double vizinhoAtual = retornoCarteira(carteiraInicial);
		double carteiraAtual = retornoCarteira(carteiraInicial);
		int add = 0;
		int aux = add + 1;
		double porcen = 5.0;
		boolean verificarPorcentagem = false;

		System.out.println("\n\nCarteira Vizinha " + "\n");
		System.out.println("-----------------------------------------------------------------------------------------");
		// Aplicando a lógica do vizinho
		while (vizinhoAtual > vizinhoAnterior) {
			if(add == 0) {
			vizinhoAnterior = vizinhoAtual;
			}
			// Fazendo a tabela
			for (int i = 0; i < carteiraInicial.length; i++) {
				System.out.print(" |     " + carteiraInicial[i].getAcao() + "     ");
			}
			System.out.print("  |  Retorno Carteira: |");
			System.out.print(
					"\n-----------------------------------------------------------------------------------------\n");
			// Aumenta em 5 porcento a ação escolhida
			Vizinho[add].setPorcentagemAcao(Vizinho[add].acrescentaPorcentagem(porcen));
			// for para percorrer as ações
			for (int j = 0; j < Vizinho.length - 1; j++) {
				// if para caso o for chegue na ação que foi acrescentada 5 porcento, ele pula
				// para a próxima ação
				if (aux == add) {
					aux++;
				}
				// diminui as próximas ações em 5 por cento
				Vizinho[aux].setPorcentagemAcao(Vizinho[aux].diminuiPorcentagem(porcen));
				// if para verificar se o retorno da carteira atual é maior do que a carteira
				// anterior
				if (verificaPorcentagem(Vizinho) == false) {
					if (retornoCarteira(Vizinho) > carteiraAtual) {
						carteiraAtual = retornoCarteira(Vizinho);
						vizinhoAtual = carteiraAtual;
						// vetor para armazenar a carteira com o maior valor
						for (int i = 0; i < maior.length; i++) {
							maior[i] = Vizinho[i].getPorcentagemAcao();
						}
					}
				}
				// método para mostrar os vizinhos
				mostraVizinho(Vizinho);
				// acrescentando novamente a porcentagem que foi retirada
				Vizinho[aux].setPorcentagemAcao(Vizinho[aux].acrescentaPorcentagem(porcen));
				// aumenta o aux em 1, sendo assim a próxima ação irá diminuir em 5 porcento
				aux++;
				// if para verificar caso o aux ultrapasse o tamanho do vetor, ele retornará
				// para o valor 0
				if (aux >= Vizinho.length) {
					aux = 0;
				}
			}
			// add é acrescido (variável add é responsável por determinar qual ação será
			// escolhida para ser acrescida 5%
			add++;

			if (add == Vizinho.length) {
				contaCarteira++;
				System.out.println("");
				System.out.print("Maior carteira:\n");
				// mostra carteira com o maior valor
				for (int i = 0; i < maior.length; i++) {
					System.out.print(maior[i] + "%, ");
				}
				// mostra o maior retorno
				System.out.print("\nRetorno Carteira = " + df.format(carteiraAtual) + "\n\n\n");
				System.out.println("Nova Carteira " + contaCarteira + "\n");
				System.out.print(
						"-----------------------------------------------------------------------------------------\n");

				for (int y = 0; y < maior.length; y++) {
					Vizinho[y].setPorcentagemAcao(maior[y]);
					porcentagemInicial[y] = maior[y];
				}
				add = 0;
				aux = 1;
				
			}
			// reseta as porcentagens que foram modificas durante o processo, para a
			// porcentagem inicial, para que assim o próximo vizinho inicie
			for (int i = 0; i < maior.length; i++) {
				Vizinho[i].setPorcentagemAcao(porcentagemInicial[i]);
			}
		}

	}

	public static boolean verificaPorcentagem(Acao carteira[]) {

		for (int i = 0; i < carteira.length; i++) {
			if ((carteira[i].getPorcentagemAcao() < 0) || (carteira[i].getPorcentagemAcao() > 100)) {
				return true;
			}
		}

		return false;

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

	// metódo apenas para mostrar a carteira inicial
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

	// método para mostrar o vizinho
	public static void mostraVizinho(Acao a[]) {

		DecimalFormat df = new DecimalFormat("###,##0.00000");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" |   " + a[i].getPorcentagemAcao() + "%" + "   ");
		}
		System.out.print("  |     " + df.format(retornoCarteira(a)) + "       |");
		System.out
				.print("\n-----------------------------------------------------------------------------------------\n");

	}

}
