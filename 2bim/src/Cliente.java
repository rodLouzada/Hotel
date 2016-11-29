import java.util.Calendar;


public class Cliente {
	private static int c;
	private int numero, cod;
	private String identidade, cpf, telefone;
	private String nome, email, rua, cidade, uf, cep;
	private Calendar dataNasc;
	private String dep1, dep2, dep3;
	private String hospedado;
	Cliente(){
		c++;
		cod = c;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getHospedado() {
		return hospedado;
	}
	public void setHospedado(String hospedado) {
		this.hospedado = hospedado;
	}
	public String getIdentidade() {
		return identidade;
	}
	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Calendar getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	//--
	public String getDep1() {
		return dep1;
	}
	public void setDep1(String dep1) {
		this.dep1 = dep1;
	}
	//--
	public String getDep2() {
		return dep2;
	}
	public void setDep2(String dep2) {
		this.dep2 = dep2;
	}
	//--
	public String getDep3() {
		return dep3;
	}
	public void setDep3(String dep3) {
		this.dep3 = dep3;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
