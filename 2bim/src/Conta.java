
public class Conta {
	private int cod;
	private int cod_hospedagem;
	private double valorTotal;
	private String pago;
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getCod_hospedagem() {
		return cod_hospedagem;
	}
	public void setCod_hospedagem(int codHospedagem) {
		cod_hospedagem = codHospedagem;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getPago() {
		return pago;
	}
	public void setPago(String pago) {
		this.pago = pago;
	}
}
