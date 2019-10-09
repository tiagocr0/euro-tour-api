package ifsc.edu.br.eurotour.apirest.model.mapeamento;

import ifsc.edu.br.eurotour.apirest.model.grafo.Arco;

/**
 * Representação da relação entre dois Países, com o País de origem, de destino,
 * e a distância entre os dois. Classe semelhante ao {@link Arco}
 * 
 * @author Osmar
 * @see Arco
 */
public class DistanciaEntre2Paises {
	private String origem;
	private String destino;
	private Double distancia;

	public DistanciaEntre2Paises() {
	}

	public DistanciaEntre2Paises(String origem, String destino, Double distancia) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

	/**
	 * Retorna o valor que representa o país de origem
	 * 
	 * @return {@link String} com o rótulo do país de origem
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Atribui o valor referente ao país de origem
	 * 
	 * @param origem {@link String} com o rótulo do país de origem a ser atribuido
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	/**
	 * Retorna o valor que representa o país de destino
	 * 
	 * @return {@link String} com o rótulo do país de destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Atribui o valor referente ao país de destino
	 * 
	 * @param origem {@link String} com o rótulo do país de destino a ser atribuido
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	/**
	 * Retorna o valor que representa a distância entre o país de origem e destino
	 * 
	 * @return {@link Double} da distância entre a origem e o destino
	 */
	public Double getDistancia() {
		return distancia;
	}

	/**
	 * Atribui o valor da distância entre os países de origem e destino
	 * 
	 * @param distancia {@link Double} com o valor da nova distância entre os dois
	 *                  países
	 */
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "\n{\n\torigem = " + origem + "\n\tdestino = " + destino + "\n\tdistancia = " + distancia + "\n}\n";
	}

}
