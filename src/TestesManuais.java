import java.util.ArrayList;
import java.util.List;

public class TestesManuais {
	private List<Banco> bancos = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();

	public void executarTestes() {
		System.out.println("Iniciando testes manuais do sistema bancário...");

		Banco nubank = new Banco("Nubank");
		Banco bancoDoBrasil = new Banco("Banco do Brasil");

		Cliente vinicius = new Cliente("Vinicius", "Av. Lorem, 444", 5000);
		nubank.abrirNovaConta(vinicius, false);

		Cliente camila = new Cliente("Camila", "Rua Ipsum, 321", 10000);
		nubank.abrirNovaConta(vinicius, false);

		bancos.add(nubank);
		bancos.add(bancoDoBrasil);
		clientes.add(vinicius);
		clientes.add(camila);
	}

	public static void main(String[] args) {
		new TestesManuais().executarTestes();
	}
}