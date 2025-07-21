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
		System.out.print("Nome do Cliente: ");
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
			System.out.printf("Cliente %d: %s | %s | %.02f.\n", i+1,
					clientes.get(i).getNome(),
					clientes.get(i).getEndereco(),
					clientes.get(i).getSaldo());
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

		if (cliente.getSaldo() > 10000) banco.abrirNovaConta(cliente, true);
		else banco.abrirNovaConta(cliente, false);

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

	public void listarContas(){
		if(bancos.isEmpty())
		{
			System.out.println("Nao existem bancos cadastrados.");
			return;
		}
		System.out.print("#######################\n");
		for(Banco b: bancos)
		{
			System.out.printf("### Banco: %s. \n", b.getNome());
			if(b.getContas().isEmpty()){
				System.out.println("Nao existem clientes cadastrados.");
			}else{
				for(ContaCorrente c : b.getContas()){
					System.out.printf("%s | ", c.getCliente().getNome());
				}
			}

			System.out.print("\n#######################\n");
		}
	}

	public void realizarOperacoes(){
		System.out.print("Banco: ");
		Banco banco = buscarBanco(entrada.nextLine());
		if (banco == null) {
			System.out.println("Banco nao encontrado.");
			return;
		}

		listarContas();
		System.out.print("Conta: ");

		int opcao = 0;
		do{
			System.out.print("Escolha a operacao (1 - Retirada, 2 - Deposito, 3 - Transferencia):");
			opcao = entrada.nextInt();
			switch(opcao){
				case 1:

					break;
				case 2:
					break;
				case 3:
					break;

				default:
					System.out.println("OPCAO INVALIDA.");
			}
		} while(opcao < 1 || opcao > 3);

	}

	public void adicionarDadosTeste(){
		Banco nubank = new Banco("Nubank");
		Banco bancobrasil = new Banco("Banco do Brasil");

		Cliente vinicius = new Cliente("Vinicius", "Av. Lorem, 444", 5000);
		nubank.abrirNovaConta(vinicius, false);

		Cliente camila = new Cliente("Camila", "Rua Ipsum, 321", 10000);
		nubank.abrirNovaConta(camila, true);

		bancos.add(nubank);
		bancos.add(bancobrasil);
		clientes.add(vinicius);
		clientes.add(camila);
	}

	public void sair(){
		System.out.println("Obrigado!");
		System.out.println("Saindo do programa...");
	}

}
