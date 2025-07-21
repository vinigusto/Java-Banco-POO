import java.util.*;

public class Aplicacao {
	private List<Banco> bancos = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();

	private static final Scanner entrada = new Scanner(System.in);
	boolean sair = false;
	int opcao = 0;

	public void executar(){
		mostrarMenu();
	}

	public void mostrarMenu(){
		System.out.println("=== BEM-VINDO AO NOSSO SISTEMA ===");

		do{
			mostrarOpcoes();
			System.out.print("Opcao Escolhida: ");
			opcao = Integer.parseInt(entrada.nextLine());
			usuarioEscolheuOpcao(opcao);
		} while(!sair);

		sair();
		entrada.close();
	}

	public void mostrarOpcoes(){
		System.out.println("\nO que voce deseja fazer?");
		System.out.println("1 - Criar Banco");
		System.out.println("2 - Listar Bancos");
		System.out.println("3 - Criar Cliente");
		System.out.println("4 - Listar Clientes");
		System.out.println("5 - Abrir Conta do Cliente no Banco");
		System.out.println("6 - Listar Contas do Banco");
		System.out.println("7 - Realizar Operacoes Bancarias");
		System.out.println("11 - Adicionar Dados de Teste");
		System.out.println("0 - Sair");
	}

	public void usuarioEscolheuOpcao(int opcao){
		switch (opcao){
			case 1 -> criarBanco();
			case 2 -> listarBancos();
			case 3 -> criarCliente();
			case 4 -> listarClientes();
			case 5 -> abrirConta();
			case 6 -> listarContas();
			case 7 -> realizarOperacoes();
			case 11 -> adicionarDadosTeste();
			case 0 -> sair = true;
			default -> System.out.println("OPCAO INVALIDA. Tente novamente.");
		}
	}

	public void criarBanco(){
		System.out.print("Nome do Banco: ");

		String nome = entrada.nextLine();
		bancos.add(new Banco(nome));

		System.out.println("Banco criado.");
	}

	public void listarBancos(){
		for(int i = 0; i < bancos.size(); i++){
			System.out.printf("Banco %d: %s.\n", i+1, bancos.get(i).getNome());
		}
	}

	public void criarCliente(){
		System.out.print("Nome do Titular: ");
		String nome = entrada.nextLine();
		System.out.print("Endereco: ");
		String endereco = entrada.nextLine();
		System.out.print("Saldo: ");
		double saldo = Double.parseDouble(entrada.nextLine());

		clientes.add(new Cliente(nome, endereco, saldo));

		System.out.println("Cliente criado com sucesso.");
	}

	public void listarClientes(){
		for(int i = 0; i < clientes.size(); i++){
			System.out.printf("Titular %d: %s | %s.\n", i+1,
					clientes.get(i).getNome(),
					clientes.get(i).getEndereco());
		}
	}

	public void abrirConta() {
		System.out.print("Banco: ");
		Banco banco = buscarBanco(entrada.nextLine());
		if (banco == null) {
			System.out.println("Banco nao encontrado.");
			return;
		}

		System.out.print("Cliente: ");
		Cliente cliente = buscarCliente(entrada.nextLine());
		if (cliente == null) {
			System.out.println("Cliente nao encontrado.");
			return;
		}

		System.out.print("Saldo Inicial: ");
		double saldo = entrada.nextDouble();
		boolean ehContaEspecial = saldo > 10000;
		banco.abrirNovaConta(cliente, saldo, ehContaEspecial);

		System.out.printf("Nova conta criada para %s no banco %s.\n", cliente.getNome(), banco.getNome());
	}

	public Banco buscarBanco(String nome){
		for(Banco b: bancos){
			if(b.getNome().equalsIgnoreCase(nome)) return b;
		}
		return null;
	}

	public Cliente buscarCliente(String nome){
		for(Cliente c: clientes){
			if(c.getNome().equalsIgnoreCase(nome)) return c;
		}
		return null;
	}

	public ContaCorrente buscarConta(Banco banco, String nomeCliente){
		for(ContaCorrente c: banco.getContas()){
			if(c.getCliente().getNome().equalsIgnoreCase(nomeCliente)) return c;
		}
		return null;
	}

	public void listarContas(){
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

	public void realizarOperacoes(){
		System.out.print("Banco: ");
		Banco banco = buscarBanco(entrada.nextLine());
		if (banco == null) {
			System.out.println("Banco nao encontrado.");
			return;
		}

		System.out.print("Nome do Cliente da Conta: ");
		String nomeCliente = entrada.nextLine();
		ContaCorrente conta = buscarConta(banco, nomeCliente);
		if (conta == null) {
			System.out.println("Conta nao encontrada.");
			return;
		}

		int opcao;
		double valor;

		do{
			System.out.print("\nEscolha a operacao (1 - Retirada, 2 - Deposito, 3 - Transferencia, 4 - Emitir Extrato, 0 - Sair): ");
			opcao = Integer.parseInt(entrada.nextLine());

			switch(opcao){
				case 1:
					System.out.print("Digite o valor de Retirada: ");
					valor = Double.parseDouble(entrada.nextLine());
					conta.retirada(valor, false);

					break;

				case 2:
					System.out.print("Digite o valor de Deposito: ");
					valor = Double.parseDouble(entrada.nextLine());
					conta.depositar(valor, false);
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
					conta.transferencia(contaDestino, valor);
					break;

				case 4:
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

	public void adicionarDadosTeste(){
		Banco nubank = new Banco("Nubank");
		Banco bancobrasil = new Banco("Banco do Brasil");

		Cliente vinicius = new Cliente("Vinicius", "Av. Lorem, 444", 5000);
		nubank.abrirNovaConta(vinicius, 5000,false);

		Cliente camila = new Cliente("Camila", "Rua Ipsum, 321", 15000);
		nubank.abrirNovaConta(camila, 15000,	true);

		Cliente marcio = new Cliente("Marcio", "Alameda Brasil, 44A", 12000);
		bancobrasil.abrirNovaConta(camila, 12000,	true);

		bancos.add(nubank);
		bancos.add(bancobrasil);
		clientes.add(vinicius);
		clientes.add(camila);
		clientes.add(marcio);

		System.out.println("Dados testes adicionados com sucesso.");
	}

	public void sair(){
		System.out.println("Obrigado!");
		System.out.println("Saindo do programa...");
	}

}
