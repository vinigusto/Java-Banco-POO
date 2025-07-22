package bancosistema.entidades;

import java.util.ArrayList;

public class ContaCorrente
{
	private Cliente cliente;
	private double saldo;
	private boolean contaEspecial;
	private ArrayList<Movimentacao> movimentacao;

	public ContaCorrente(Cliente cliente, double saldoInicial, boolean contaEspecial) {
		this.cliente = cliente;
		this.contaEspecial = false;
		this.saldo = saldoInicial;
		this.movimentacao = new ArrayList<>();
	}

	protected void atualizarTipoDeConta(){
		contaEspecial = saldo > 10000;
	}

	protected void adicionarMovimentacao(String descricao, double valor, boolean depositado) {
		double valorFinal = depositado ? valor : -valor;
		movimentacao.add(new Movimentacao(valorFinal, descricao));
		atualizarTipoDeConta();
	}

	public void imprimirExtrato()
	{
		for (Movimentacao m : this.movimentacao) {
			System.out.println(m);
		}
		if(movimentacao.isEmpty()) System.out.println("Extrato vazio.");
	}

	public void emitirSaldo() {
		System.out.println("-> SALDO ATUAL: " + this.saldo);
	}

	public void depositar(double valor, boolean transferencia, boolean printMsg) {
		this.saldo += valor;
		if(!transferencia) adicionarMovimentacao("Deposito: ", valor, true);
		if(printMsg) System.out.println("Deposito: " + valor);
	}

	public void retirada(double valor, boolean transferencia, boolean printMsg) {
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
			if(!transferencia) adicionarMovimentacao("Retirada: ", -valor, false);
			if(printMsg) System.out.println("Retirada: " + (-valor));
		}
	}

	public void transferencia(ContaCorrente contaDestino, double valor, boolean printMsg)
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
			retirada(valor, true, false);
			adicionarMovimentacao("Transferencia para " + contaDestino.cliente.getNome(), -valor, false);

			contaDestino.depositar(valor, true, false);
			contaDestino.adicionarMovimentacao("Transferencia recebida de " + cliente.getNome(), valor, true);
			if(printMsg) System.out.printf("Transferencia Realizada com Sucesso para '%s'.\n", contaDestino.cliente.getNome());
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public boolean getTipoDeConta() {
		atualizarTipoDeConta();
		return contaEspecial;
	}
}