public class Cliente
{
	private String nome;
	private String endereco;
	private double saldoBancario;

	//Construtor
	public Cliente(String nome, String endereco, double saldo)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.saldoBancario = saldo;
	}

	void imprimirDados()
	{
		System.out.println("Nome: " + this.nome + " | Endereco: " + this.endereco + " | Saldo Bancario: " + this.saldoBancario);
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public void setEndereco(String endereco){
		this.endereco = endereco;
	}

	public void setSaldo(double saldo){
		this.saldoBancario = saldo;
	}

	public String getNome(){
		return this.nome;
	}

	public String getEndereco(){
		return this.endereco;
	}

	public double getSaldo(){
		return this.saldoBancario;
	}
}