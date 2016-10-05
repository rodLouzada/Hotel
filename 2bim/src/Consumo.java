import java.util.Calendar;


public class Consumo {
	private int cod, cod_servico, cod_produto, cod_conta;
	private Calendar dataCons;
	private int quantidade;
	private double valorUnit, valorTotal;
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getCod_servico() {
		return cod_servico;
	}
	public void setCod_servico(int codServico) {
		cod_servico = codServico;
	}
	public int getCod_produto() {
		return cod_produto;
	}
	public void setCod_produto(int codProduto) {
		cod_produto = codProduto;
	}
	public int getCod_conta() {
		return cod_conta;
	}
	public void setCod_conta(int codConta) {
		cod_conta = codConta;
	}
	public Calendar getDataCons() {
		return dataCons;
	}
	public void setDataCons(Calendar dataCons) {
		this.dataCons = dataCons;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorUnit() {
		return valorUnit;
	}
	public void setValorUnit(double valorUnit) {
		this.valorUnit = valorUnit;
	}
	
}
