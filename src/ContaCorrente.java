import java.util.ArrayList;

public class ContaCorrente
{

	private Cliente cliente;
	private double saldo;
	private boolean contaEspecial;
	private ArrayList<Movimentacao> movimentacao;

	public ContaCorrente(Cliente cliente, boolean contaEspecial) {
		this.cliente = cliente;
		this.contaEspecial = false;
		this.saldo = 0.0;
		this.movimentacao = new ArrayList<>();
	}

	public void adicionarMovimentacao(String descricao, double valor, boolean depositado) {
		double valorFinal = depositado ? valor : -valor;
		movimentacao.add(new Movimentacao(valorFinal, descricao));
	}

	public void imprimirExtrato()
	{
		System.out.println("### Extrato ###");
		for (Movimentacao m : this.movimentacao) {
			System.out.println(m);
		}
		System.out.println("###############");
	}

	public void emitirSaldo() {
		System.out.println("-> SALDO ATUAL: " + this.saldo);
	}

	public void depositar(double valor, boolean transferencia) {
		this.saldo += valor;
		if(transferencia == false) adicionarMovimentacao("Deposito: ", valor, true);
		System.out.println("Deposito: " + valor);
	}

	public void retirada(double valor, boolean transferencia) {
		if(this.saldo - valor < 0)
		{
			if(!contaEspecial)
			{
				System.out.println("Saldo insuficiente para retirada.");
				emitirSaldo();
			}
		}
		else
		{
			this.saldo -= valor;
			if(transferencia == false) adicionarMovimentacao("Retirada: ", -valor, false);
			System.out.println("Retirada: " + (-valor));
		}
	}

	public void Transferencia(ContaCorrente contaDestino, double valor)
	{
		if(this.saldo - valor < 0)
		{
			if(!contaEspecial)
			{
				System.out.println("Saldo Insuficiente para Transferencia.");
				emitirSaldo();
			}
		}
		else
		{
			retirada(valor, true);
			adicionarMovimentacao("Transferencia para " + contaDestino.cliente.getNome(), -valor, false);

			contaDestino.depositar(valor, true);
			contaDestino.adicionarMovimentacao("Transferencia recebida de " + cliente.getNome(), valor, true);

			System.out.println("Transferencia Realizada com Sucesso para " + contaDestino.cliente.getNome());
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public boolean getTipoDeConta() {
		return contaEspecial;
	}

	public ArrayList<Movimentacao> getMovimentacao() {
		return movimentacao;
	}
}