package ifsc.edu.br.eurotour.apirest.model.mapeamento;

/**
 * Classe que recebe {@link FrontToBack} para formatá-la e receber do front-end
 * no formato exemplo:
 * 
 * { "front":{ "algoritmo": 3, "origem": "Albânia – Tirana", "destino": "Bélgica
 * – Bruxelas" } }
 * 
 * @author Tiago
 *
 */

public class Front {

	private FrontToBack front;

	/**
	 * Retorna a classe {@link FrontToBack},classe responsável para receber os dados
	 * do Front-end
	 * 
	 * @return {@link FrontToBack} contendo as informações de algoritmo,origem e
	 *         destino
	 */
	public FrontToBack getFront() {
		return front;
	}

	public void setFront(FrontToBack front) {
		this.front = front;
	}

}
