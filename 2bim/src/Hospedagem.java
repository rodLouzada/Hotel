import java.util.Calendar;


public class Hospedagem {
	private static int c;
	private int cod, cod_cliente;
	Calendar dataEntrada;
	private Calendar dataSaida, dataPreEntrada, dataPreSaida;
	private String checkout;
	Hospedagem(){
		c++;
		cod = c;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int codCliente) {
		cod_cliente = codCliente;
	}
	public Calendar getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Calendar calen) {
		this.dataEntrada = calen;
	}
	public Calendar getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Calendar getDataPreEntrada() {
		return dataPreEntrada;
	}
	public void setDataPreEntrada(Calendar dataPreEntrada) {
		this.dataPreEntrada = dataPreEntrada;
	}
	public Calendar getDataPreSaida() {
		return dataPreSaida;
	}
	public void setDataPreSaida(Calendar calen2) {
		this.dataPreSaida = calen2;
	}
	
}

