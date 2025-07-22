package bancosistema.interfaceusuario;

import bancosistema.entidades.Banco;
import bancosistema.entidades.Cliente;
import bancosistema.entidades.ContaCorrente;

import java.util.*;

public class Aplicacao {
	private List<Banco> bancos = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();
	private static final Scanner entrada = new Scanner(System.in);

	private boolean sair = false;

	public void executar(){
		mostrarMenu();
	}

	private void mostrarCabecalho(){
		System.out.println("=========================================");
		System.out.println("=== BEM-VINDO AO MEU SISTEMA BANCARIO ===");
		System.out.println("=========================================");
	}

	private void mostrarMenu(){
		mostrarCabecalho();
		do{
			mostrarOpcoes();
			System.out.print("Opcao: ");
			int opcao = Integer.parseInt(entrada.nextLine());
			usuarioEscolheuOpcao(opcao);
		} while(!sair);

		sair();
		entrada.close();
	}

	private void mostrarOpcoes(){
		System.out.println("\n### MENU PRINCIPAL ###");
		System.out.println("1. Criar Banco     2. Listar Bancos    3. Criar Cliente");
		System.out.println("4. Listar Clientes 5. Abrir Conta      6. Listar Contas");
		System.out.println("7. Operacoes       8. Dados Teste      0. Sair");
	}

	private void usuarioEscolheuOpcao(int opcao){
		switch (opcao){
			case 1 -> criarBanco();
			case 2 -> listarBancos();
			case 3 -> criarCliente();
			case 4 -> listarClientes();
			case 5 -> abrirConta();
			case 6 -> listarContas();
			case 7 -> realizarOperacoes();
			case 8 -> adicionarDadosTeste();
			case 0 -> sair = true;
			default -> System.out.println("OPCAO INVALIDA. Tente novamente.");
		}
	}

	private void criarBanco(){
		System.out.print("Nome do Banco: ");

		String nome = entrada.nextLine();
		bancos.add(new Banco(nome));

		System.out.printf("Banco '%s' criado.\n", nome);
	}

	private void listarBancos(){
		for(int i = 0; i < bancos.size(); i++){
			System.out.printf("Banco %d: %s.\n", i+1, bancos.get(i).getNome());
		}
	}

	private void criarCliente(){
		System.out.print("Nome do Titular: ");
		String nome = entrada.nextLine();
		System.out.print("Endereco: ");
		String endereco = entrada.nextLine();

		clientes.add(new Cliente(nome, endereco));

		System.out.printf("Cliente '%s' criado com sucesso.\n", nome);
	}

	private void listarClientes(){
		for(int i = 0; i < clientes.size(); i++){
			System.out.printf("Titular %d: %s | %s.\n", i+1,
					clientes.get(i).getNome(),
					clientes.get(i).getEndereco());
		}
	}

	private void abrirConta() {
		System.out.print("Banco: ");
		Banco banco = buscarBanco(entrada.nextLine());
		if (banco == null) {
			System.out.printf("Banco '%s' nao encontrado.\n", banco);
			return;
		}

		System.out.print("Cliente: ");
		Cliente cliente = buscarCliente(entrada.nextLine());
		if (cliente == null) {
			System.out.printf("Cliente '%s' nao encontrado.\n", cliente);
			return;
		}

		System.out.print("Saldo Inicial: ");
		double saldo = Double.parseDouble(entrada.nextLine());
		boolean ehContaEspecial = saldo > 10000;
		banco.abrirNovaConta(cliente, saldo, ehContaEspecial);

		System.out.printf("Nova conta criada para '%s' no banco '%s'.\n", cliente.getNome(), banco.getNome());
	}

	private Banco buscarBanco(String nome){
		for(Banco b: bancos){
			if(b.getNome().equalsIgnoreCase(nome)) return b;
		}
		return null;
	}

	private Cliente buscarCliente(String nome){
		for(Cliente c: clientes){
			if(c.getNome().equalsIgnoreCase(nome)) return c;
		}
		return null;
	}

	private ContaCorrente buscarConta(Banco banco, String nomeCliente){
		for(ContaCorrente c: banco.getContas()){
			if(c.getCliente().getNome().equalsIgnoreCase(nomeCliente)) return c;
		}
		return null;
	}

	private void listarContas(){
		if(bancos.isEmpty())
		{
			System.out.print("Nao existem bancos cadastrados.");
			return;
		}
		for(Banco b: bancos)
		{
			System.out.printf("\n### Banco: %s. \n", b.getNome());
			if(b.getContas().isEmpty()){
				System.out.println("Nao existem clientes cadastrados.");
			}else{
				for(ContaCorrente c : b.getContas()){
					Cliente cliente = c.getCliente();
					System.out.printf("%s | Titular: %s | Saldo: %.02f.\n",
							cliente.getNome(), c.getTipoDeConta() ? "Especial" : "Basica", c.getSaldo());
				}
			}
		}
	}

	private void realizarOperacoes(){
		System.out.print("Banco: ");
		Banco banco = buscarBanco(entrada.nextLine());
		if (banco == null) {
			System.out.println("Banco nao encontrado.");
			return;
		}

		System.out.print("Nome do Titular da Conta: ");
		String nomeCliente = entrada.nextLine();
		ContaCorrente conta = buscarConta(banco, nomeCliente);
		if (conta == null) {
			System.out.println("Conta nao encontrada.");
			return;
		}

		int opcao;
		double valor;

		do{
			System.out.println("\n## OPERACOES ##");
			System.out.println("1. Retirada        2. Deposito");
			System.out.println("3. Transferencia   4. Emitir Saldo");
			System.out.println("5. Emitir Extrato  0. Sair");
			System.out.print("Opcao: ");
			opcao = Integer.parseInt(entrada.nextLine());

			switch(opcao){
				case 1:
					System.out.print("Digite o valor de Retirada: ");
					valor = Double.parseDouble(entrada.nextLine());
					conta.retirada(valor, false, true);
					break;

				case 2:
					System.out.print("Digite o valor de Deposito: ");
					valor = Double.parseDouble(entrada.nextLine());
					conta.depositar(valor, false, true);
					break;

				case 3:
					System.out.print("Banco Destino: ");
					Banco bancoDestino = buscarBanco(entrada.nextLine());
					if (bancoDestino == null) {
						System.out.println("Banco destino nao encontrado.");
						break;
					}

					System.out.print("Nome do Titular da Conta Destino: ");
					String nomeDestino = entrada.nextLine();
					ContaCorrente contaDestino = buscarConta(bancoDestino, nomeDestino);
					if (contaDestino == null) {
						System.out.println("Conta destino nao encontrada.");
						break;
					}

					System.out.print("Digite o valor de Transferencia: ");
					valor = Double.parseDouble(entrada.nextLine());
					conta.transferencia(contaDestino, valor, true);
					break;

				case 4:
					System.out.println("Emitindo saldo...");
					conta.emitirSaldo();
					break;

				case 5:
					System.out.println("Emitindo extrato...");
					conta.imprimirExtrato();
					break;

				case 0:
					System.out.println("Saindo...");
					break;

				default:
					System.out.println("OPCAO INVALIDA.");
			}
		} while(opcao != 0);

	}

	private void adicionarDadosTeste(){
		Banco nubank = new Banco("Nubank");
		Banco bancobrasil = new Banco("Banco do Brasil");
		Banco caixa = new Banco("Caixa");

		Cliente vinicius = new Cliente("Vinicius", "Av. Lorem, 444");
		Cliente camila = new Cliente("Camila", "Rua Ipsum, 321");
		Cliente marcio = new Cliente("Marcio", "Alameda Brasil, 44A");

		nubank.abrirNovaConta(vinicius, 5000,false);
		nubank.abrirNovaConta(camila, 15000,	true);
		bancobrasil.abrirNovaConta(marcio, 12000,	true);

		bancos.add(nubank);
		bancos.add(bancobrasil);
		bancos.add(caixa);
		clientes.add(vinicius);
		clientes.add(camila);
		clientes.add(marcio);

		ContaCorrente contaVinicius = nubank.getContas().get(0);
		contaVinicius.depositar(1000, false, false);
		contaVinicius.retirada(300, false, false);

		ContaCorrente contaCamila = nubank.getContas().get(1);
		contaCamila.transferencia(contaVinicius, 2000, false);

		System.out.println("Dados testes adicionados com sucesso.");
	}

	private void sair(){
		System.out.println("Obrigado!");
		System.out.println("Saindo do programa...");
	}

}
