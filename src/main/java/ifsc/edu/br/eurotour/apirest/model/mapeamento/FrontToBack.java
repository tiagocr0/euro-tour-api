package ifsc.edu.br.eurotour.apirest.model.mapeamento;

/**
 * Classe que recebe o tipo do algoritmo que será usado, a origem e o destino do
 * front-end
 * 
 * @author Tiago
 *
 */

public class FrontToBack {

	private int algoritmo;
	private String origem;
	private String destino;

	public FrontToBack() {

	}

	/**
	 * Retorna o identificador do algoritmo que deverá ser usado para realizar a
	 * busca
	 * 
	 * @return um inteiro que será o número do algoritmo da busca
	 */
	public int getAlgoritmo() {
		return algoritmo;
	}

	/**
	 * Retorna o país de origem da busca que deverá ser realizada
	 * 
	 * @return uma String com o rótulo do país de origem
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Retorna o país de destino da busca que deverá ser realizada
	 * 
	 * @return uma String com o rótulo do país de destino
	 */
	public String getDestino() {
		return destino;
	}
	
	

	public void setAlgoritmo(int algoritmo) {
		this.algoritmo = algoritmo;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "FrontToBack [algoritmo=" + algoritmo + ", origem=" + origem + ", destino=" + destino + "]";
	}

}
