package bancosistema.entidades;

public class Cliente
{
	private String nome;
	private String endereco;

	//Construtor
	public Cliente(String nome, String endereco)
	{
		this.nome = nome;
		this.endereco = endereco;
	}

	public String getNome(){
		return this.nome;
	}
	public String getEndereco(){
		return this.endereco;
	}

	public void alterarNome(String novo){
		nome = novo;
	}

	public void alterarEndereco(String novo){
		endereco = novo;
	}
}