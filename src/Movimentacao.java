public class Movimentacao
{
	private double valor;
	private String descricao;

	public Movimentacao(double valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao + ": R$ " + valor;
	}
}