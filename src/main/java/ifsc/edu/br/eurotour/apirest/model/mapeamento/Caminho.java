package ifsc.edu.br.eurotour.apirest.model.mapeamento;

import java.util.ArrayList;
import java.util.List;

import ifsc.edu.br.eurotour.apirest.model.grafo.Grafo;
import ifsc.edu.br.eurotour.apirest.model.grafo.Vertice;

/**
 * Classe que representa o resultado final de uma busca, no qual terá a
 * distância total a ser percorrida, e a lista dos países que devem ser
 * percorridos
 * 
 * @author equipe.mapa
 *
 */
public class Caminho {

	private List<DistanciaEntre2Paises> caminho;
	private Double distanciaTotal;
	private int nosGerados;
	private int nosExpandidos;
	private double tempoProcessamento;

	public Caminho(Double distanciaTotal) {
		this.caminho = new ArrayList<>();
		this.distanciaTotal = distanciaTotal;
	}

	public Caminho(List<DistanciaEntre2Paises> caminho, Double distanciaTotal) {
		this.caminho = caminho;
		this.distanciaTotal = distanciaTotal;
	}

	public Caminho(Double distanciaTotal, int nosGerados, int nosExpandidos, double tempoProcessamento) {
		super();
		this.caminho = new ArrayList<>();
		this.distanciaTotal = distanciaTotal;
		this.nosGerados = nosGerados;
		this.nosExpandidos = nosExpandidos;
		this.tempoProcessamento = tempoProcessamento;
	}

	/**
	 * Retorna a lista que contém os caminhos percorridos para chegar ao destino
	 * 
	 * @return {@link List} de {@link DistanciaEntre2Paises} que representam o
	 *         caminho percorrido
	 */
	public List<DistanciaEntre2Paises> getCaminho() {
		return caminho;
	}

	/**
	 * Atribui um novo valor a Lista de caminhos percorridos
	 * 
	 * @param caminho nova {@link List} de {@link DistanciaEntre2Paises} a ser
	 *                atribuída
	 */
	public void setCaminho(List<DistanciaEntre2Paises> caminho) {
		this.caminho = caminho;
	}

	/**
	 * Retorna o valor numérico que representa a distância total a ser percorrida
	 * até o destino
	 * 
	 * @return {@link Double} que representa a distância total
	 */
	public Double getDistanciaTotal() {
		return distanciaTotal;
	}

	/**
	 * Atribui uma distância total a ser percorrida até o destino
	 * 
	 * @param distanciaTotal {@link Double} que representa a distancia total a ser
	 *                       percorrida
	 */
	public void setDistanciaTotal(Double distanciaTotal) {
		this.distanciaTotal = distanciaTotal;
	}

	/**
	 * Retorna a quantidade de {@link Vertice} gerados para chegar ao destino
	 * 
	 * @return quantidade de {@link Vertice} que foram gerados
	 */
	public int getNosGerados() {
		return nosGerados;
	}

	/**
	 * Atribui um novo valor a quantidade de nós gerados
	 * 
	 * @param nosGerados nova quantidade de {@link Vertices} que foram gerados
	 */
	public void setNosGerados(int nosGerados) {
		this.nosGerados = nosGerados;
	}

	/**
	 * Retorna a quantidade de {@link Vertice} expandidos para chegar ao destino
	 * 
	 * @return quantidade de {@link Vertice} que foram expandidos
	 */
	public int getNosExpandidos() {
		return nosExpandidos;
	}

	/**
	 * Atribui um novo valor a quantidade de nós expandidos
	 * 
	 * @param nosExpandidos nova quantidade de {@link Vertices} que foram expandidos
	 */
	public void setNosExpandidos(int nosExpandidos) {
		this.nosExpandidos = nosExpandidos;
	}

	/**
	 * Retorna o tempo de processamento que irá levar para concluir cada busca
	 * 
	 * @return tempo de processsamento
	 * 
	 */
	public double getTempoProcessamento() {
		return tempoProcessamento;
	}

	/**
	 * Atribui um novo valor ao tempo de processamento
	 * 
	 * @param tempoProcessamento que foi gasto para realizar a busca
	 */
	public void setTempoProcessamento(double tempoProcessamento) {
		this.tempoProcessamento = tempoProcessamento;
	}

	/**
	 * Converte o {@link Grafo}, para um {@link Caminho} a partir de um
	 * {@link Vertice} final
	 * 
	 * @param aGrafo           {@link Grafo} a ser convertido
	 * @param aFinal           {@link Vertice} de destino da busca
	 * @param lDistanciaMinima {@link Double} que representa a distância total a ser
	 *                         percorrida
	 * @return
	 */
	@Deprecated
	public static Caminho converter(Grafo aGrafo, Vertice aFinal, Double lDistanciaMinima) {
		String[] lNomes = aFinal.getCaminho().split("/");
		Caminho lCaminho = new Caminho(lDistanciaMinima);
		for (int indice = 0; indice < lNomes.length; indice++) {
			if ((indice + 1) != lNomes.length) {
				Vertice lVerticeOrigem = aGrafo.pesquisaVertice(lNomes[indice].trim());
				Vertice lVerticeDestino = aGrafo.pesquisaVertice(lNomes[indice + 1].trim());
				lCaminho.getCaminho().add(new DistanciaEntre2Paises(lVerticeOrigem.toString(),
						lVerticeDestino.toString(), lVerticeDestino.obterDistancia()));
			}
		}
		return lCaminho;
	}

	@Override
	public String toString() {
		return "Caminho=" + caminho + "\ndistanciaTotal=" + distanciaTotal;
	}

	/**
	 * Converte o {@link Grafo}, para um {@link Caminho} a partir de um
	 * {@link String} que representa o {@link Vertice}
	 * 
	 * @param aGrafo           {@link Grafo} a ser convertido
	 * @param aVertices        {@link String} que representa os {@link Vertices}
	 * @param lDistanciaMinima {@link Double} que representa a distância total
	 *                         percorrida
	 * @return
	 */
	@Deprecated
	public static Caminho converter(Grafo aGrafo, String aVertices, Double lDistanciaMinima) {
		String[] lNomes = aVertices.split(" / ");
		Caminho lCaminho = new Caminho(lDistanciaMinima);
		for (int indice = 0; indice < lNomes.length; indice++) {
			if ((indice + 1) != lNomes.length) {
				Vertice lVerticeOrigem = aGrafo.pesquisaVertice(lNomes[indice].trim());
				Vertice lVerticeDestino = aGrafo.pesquisaVertice(lNomes[indice + 1].trim());
				lCaminho.getCaminho().add(new DistanciaEntre2Paises(lVerticeOrigem.toString(),
						lVerticeDestino.toString(), lVerticeDestino.obterDistancia()));
			}
		}
		return lCaminho;
	}

	/**
	 * Converte o {@link Grafo}, para um {@link Caminho} a partir de um
	 * {@link String} que representa o {@link Vertice}
	 * 
	 * @param aGrafo           {@link Grafo} a ser convertido
	 * @param aVertices        {@link String} que representa os {@link Vertices}
	 * @param lDistanciaMinima {@link Double} que representa a distância total
	 *                         percorrida
	 * @return
	 */
	public static Caminho converter(Grafo aGrafo, String aVertices, Double lDistanciaMinima, int nosGerados,
			int nosExpandidos, double tempoProcessamento) {
		String[] lNomes = aVertices.split(" / ");
		Caminho lCaminho = new Caminho(lDistanciaMinima, nosGerados, nosExpandidos, tempoProcessamento);
		for (int indice = 0; indice < lNomes.length; indice++) {
			if ((indice + 1) != lNomes.length) {
				Vertice lVerticeOrigem = aGrafo.pesquisaVertice(lNomes[indice].trim());
				Vertice lVerticeDestino = aGrafo.pesquisaVertice(lNomes[indice + 1].trim());
				lCaminho.getCaminho().add(new DistanciaEntre2Paises(lVerticeOrigem.toString(),
						lVerticeDestino.toString(), lVerticeDestino.obterDistancia()));
			}
		}
		return lCaminho;
	}

	/**
	 * Converte o {@link Grafo}, para um {@link Caminho} a partir de um
	 * {@link Vertice} final
	 * 
	 * @param aGrafo           {@link Grafo} a ser convertido
	 * @param aFinal           {@link Vertice} de destino da busca
	 * @param lDistanciaMinima {@link Double} que representa a distância total a ser
	 *                         percorrida
	 * @return
	 */
	public static Caminho converter(Grafo aGrafo, Vertice aFinal, Double lDistanciaMinima, int nosGerados,
			int nosExpandidos, double tempoProcessamento) {
		String[] lNomes = aFinal.getCaminho().split("/");
		Caminho lCaminho = new Caminho(lDistanciaMinima, nosGerados, nosExpandidos, tempoProcessamento);
		for (int indice = 0; indice < lNomes.length; indice++) {
			if ((indice + 1) != lNomes.length) {
				Vertice lVerticeOrigem = aGrafo.pesquisaVertice(lNomes[indice].trim());
				Vertice lVerticeDestino = aGrafo.pesquisaVertice(lNomes[indice + 1].trim());
				lCaminho.getCaminho().add(new DistanciaEntre2Paises(lVerticeOrigem.toString(),
						lVerticeDestino.toString(), lVerticeDestino.obterDistancia()));
			}
		}
		return lCaminho;
	}

	/**
	 * Gera o tempo de processamento em millisegundos apartir do tempo inicial
	 * 
	 * @param aTempoInicio tempo em nanosegundos
	 */
	public static double gerarTempoProcessamento(long aTempoInicio) {
		// Variável de tempo de final do método
		long lTempoFinal = System.nanoTime();
		// Variável para calcular o tempo de demora do método, converte nanosegundos em
		// milisegundos e depois em segundos
		double lTempoProcessamento = (lTempoFinal - aTempoInicio) / 1000000d;
		return lTempoProcessamento;
	}
}