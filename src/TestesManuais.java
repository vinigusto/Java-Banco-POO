import java.util.List;

public class TestesManuais {
	public void executarTestes() {
		System.out.println("=== INÍCIO DOS TESTES MANUAIS ===");

		System.out.println("\n1. Teste de criação de bancos:");
		Banco nubank = new Banco("Nubank");
		Banco bancoDoBrasil = new Banco("Banco do Brasil");
		System.out.println("- Bancos criados: " + nubank.getNome() + " e " + bancoDoBrasil.getNome());

		System.out.println("\n2. Teste de criação de clientes:");
		Cliente cliente1 = new Cliente("Vinicius Augusto", "Rua Lorem, 123", 0);
		Cliente cliente2 = new Cliente("Camila Ciscoto", "Av. Ipsum, 456", 0);
		System.out.println("- Clientes criados: " + cliente1.getNome() + " e " + cliente2.getNome());

		System.out.println("\n3. Teste de abertura de contas:");
		nubank.abrirNovaConta(cliente1, 5000, false); // Conta normal
		nubank.abrirNovaConta(cliente2, 15000, true); // Conta especial
		System.out.println("- Contas abertas no Nubank:\n");
		nubank.listarContas();

		System.out.println("\n4. Teste de operações bancárias:");
		ContaCorrente contaVinicius = nubank.getContas().get(0);
		ContaCorrente contaCamila = nubank.getContas().get(1);

		System.out.println("\n4.1 Depósito:");
		contaVinicius.depositar(1000, false);
		System.out.print("CONTA: Vinicius ");
		contaVinicius.emitirSaldo();

		System.out.println("\n4.2 Retirada:");
		contaVinicius.retirada(300, false);
		System.out.print("CONTA: Vinicius ");
		contaVinicius.emitirSaldo();

		System.out.println("\n4.3 Tentativa de retirada com saldo insuficiente:");
		System.out.print("CONTA: Vinicius -> ");
		contaVinicius.retirada(6000, false);

		System.out.println("\n4.4 Transferência:");
		System.out.println("Saldo antes da transferência:");
		System.out.print("CONTA: Vinicius ");
		contaVinicius.emitirSaldo();
		System.out.print("CONTA: Camila ");
		contaCamila.emitirSaldo();

		System.out.print("Transferencia em andamento...\n");
		contaCamila.transferencia(contaVinicius, 2000);

		System.out.println("\nSaldo após transferência:");
		System.out.print("CONTA: Vinicius ");
		contaVinicius.emitirSaldo();
		System.out.print("CONTA: Camila ");
		contaCamila.emitirSaldo();

		System.out.println("\n5. Teste de extratos:");
		System.out.println("Extrato da conta do Vinicius:");
		contaVinicius.imprimirExtrato();

		System.out.println("\nExtrato da conta da Camila:");
		contaCamila.imprimirExtrato();

		System.out.println("\n6. Teste de listagem de contas:");
		System.out.println("Contas no Nubank:\n");
		nubank.listarContas();

		System.out.println("\n=== FIM DOS TESTES MANUAIS ===");
	}

	public static void main(String[] args) {
		new TestesManuais().executarTestes();
	}
}