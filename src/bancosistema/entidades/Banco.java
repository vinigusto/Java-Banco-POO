package bancosistema.entidades;

import java.util.ArrayList;

public class Banco
{
	private ArrayList<ContaCorrente> contas;
	private String nome;

	public String getNome() {
		return nome;
	}

	public Banco(String n) {
		this.nome = n;
		this.contas = new ArrayList<>();
	}

	public void abrirNovaConta(Cliente cliente, double saldoInicial, boolean contaEspecial) {
		ContaCorrente novaConta = new ContaCorrente(cliente, saldoInicial, contaEspecial);
		contas.add(novaConta);
	}

	public void listarContas() {
		for (ContaCorrente conta : contas) {
			System.out.println("Titular: " + conta.getCliente().getNome());
			System.out.println("Tipo: " + (conta.getTipoDeConta() ? "Basica" : "Especial"));
			System.out.println("---------------------");
		}
	}

	public ArrayList<ContaCorrente> getContas() {
		return contas;
	}
}