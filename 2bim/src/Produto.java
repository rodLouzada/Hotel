


public class Produto {
	private static int c;
	private int cod;
	private String nome;
	private double valor;
	Produto(){
		System.out.println("teste");
		c++;
		cod = c;
		boolean teste = false;
		System.out.println(teste);
		
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
