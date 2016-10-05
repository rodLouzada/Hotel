import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class HospedagemDAO {

	private Connection conexao = null;

	public HospedagemDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(Hospedagem hosp) {
		String sql = "INSERT INTO hotel.hospedagem (cliente_cod, dataEntrada, dataSaida, dataPrevistaSaida, dataPrevistaEntrada, checkout) VALUES (?, ?, ?, ?, ?, ?)"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, hosp.getCod_cliente());
			if (hosp.getDataEntrada() != null){
				stmt.setDate(2, new java.sql.Date(hosp.getDataEntrada().getTimeInMillis()));
			}else{
				stmt.setInt(2, 0);
			}
			System.out.println(hosp.getDataSaida());
			if (hosp.getDataSaida() != null){
				stmt.setDate(3, new java.sql.Date(hosp.getDataSaida().getTimeInMillis()));
			}else{
				stmt.setInt(3, 0);
			}
			if (hosp.getDataPreSaida() != null){
				stmt.setDate(4, new java.sql.Date(hosp.getDataPreSaida().getTimeInMillis()));
			}else{
				stmt.setInt(4, 0);
			}
			if (hosp.getDataPreEntrada() != null){
				stmt.setDate(5, new java.sql.Date(hosp.getDataPreEntrada().getTimeInMillis()));
			}else{
				stmt.setInt(5, 0);
			}
			stmt.setString(6, hosp.getCheckout());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public ArrayList<Hospedagem> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM hotel.hospedagem");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Hospedagem> vetor = new ArrayList<Hospedagem>();

		while (rs.next()) {
			Hospedagem hosped = new Hospedagem();
			hosped.setCod(rs.getInt("cod"));
			hosped.setCod_cliente(rs.getInt("cliente_cod"));
			//DATA
			Calendar calen = new GregorianCalendar();
			Calendar calen2 = new GregorianCalendar();
			Calendar calen3 = new GregorianCalendar();
			Calendar calen4 = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				calen.setTime(f.parse(rs.getString("dataEntrada")));
				
				if (rs.getString("checkout").equals("true")){
					calen2.setTime(f.parse(rs.getString("dataSaida")));
				}
//				if (rs.getString("dataPrevistaEntrada") != "0000-00-00"){
//					calen3.setTime(f.parse(rs.getString("dataPrevistaEntrada")));
//				}
				calen4.setTime(f.parse(rs.getString("dataPrevistaSaida")));
								
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			hosped.setDataEntrada(calen);
			hosped.setDataSaida(calen2);
			hosped.setDataPreEntrada(calen3);
			hosped.setDataPreSaida(calen4);
			hosped.setCheckout(rs.getString("checkout"));

			vetor.add(hosped);
		}
		

		return vetor;
	}
	public ArrayList<Hospedagem> listaPorCodCli(int cod) throws SQLException {
		String sql = "SELECT * FROM hotel.hospedagem WHERE `cliente_cod` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Hospedagem> vetor = new ArrayList<Hospedagem>();

		while (rs.next()) {
			Hospedagem hosped = new Hospedagem();
			hosped.setCod(rs.getInt("cod"));
			hosped.setCod_cliente(rs.getInt("cliente_cod"));
			//DATA
			Calendar calen = new GregorianCalendar();
			Calendar calen2 = new GregorianCalendar();
			Calendar calen3 = new GregorianCalendar();
			Calendar calen4 = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if (rs.getString("dataEntrada") != "0000-00-00"){
					calen.setTime(f.parse(rs.getString("dataEntrada")));
				}
//				if (rs.getString("dataSaida") != "0000-00-00"){
//					calen2.setTime(f.parse(rs.getString("dataSaida")));
//				}
//				if (rs.getString("dataPrevistaEntrada") != "0000-00-00"){
//					calen3.setTime(f.parse(rs.getString("dataPrevistaEntrada")));
//				}
				if (rs.getString("dataPrevistaSaida") != "0000-00-00"){
					calen4.setTime(f.parse(rs.getString("dataPrevistaSaida")));
				}				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			hosped.setDataEntrada(calen);
			hosped.setDataSaida(calen2);
			hosped.setDataPreEntrada(calen3);
			hosped.setDataPreSaida(calen4);

			vetor.add(hosped);
		}
		

		return vetor;
	}
	public Hospedagem listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM hotel.hospedagem WHERE `cod` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		Hospedagem hosped = new Hospedagem();

		while (rs.next()) {
			
			hosped.setCod(rs.getInt("cod"));
			hosped.setCod_cliente(rs.getInt("cliente_cod"));
			//DATA
			Calendar calen = new GregorianCalendar();
			Calendar calen2 = new GregorianCalendar();
			Calendar calen3 = new GregorianCalendar();
			Calendar calen4 = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if (rs.getString("dataEntrada") != "0000-00-00"){
					calen.setTime(f.parse(rs.getString("dataEntrada")));
				}
//				if (rs.getString("dataSaida") != "0000-00-00"){
//					calen2.setTime(f.parse(rs.getString("dataSaida")));
//				}
//				if (rs.getString("dataPrevistaEntrada") != "0000-00-00"){
//					calen3.setTime(f.parse(rs.getString("dataPrevistaEntrada")));
//				}
				if (rs.getString("dataPrevistaSaida") != "0000-00-00"){
					calen4.setTime(f.parse(rs.getString("dataPrevistaSaida")));
				}				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			hosped.setDataEntrada(calen);
			hosped.setDataSaida(calen2);
			hosped.setDataPreEntrada(calen3);
			hosped.setDataPreSaida(calen4);

		}
		return hosped;
	}
	public Hospedagem getCodCliPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM hotel.hospedagem WHERE `cod` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		Hospedagem hosped = new Hospedagem();

		while (rs.next()) {
			hosped.setCod_cliente(rs.getInt("cliente_cod"));

		}
		return hosped;
	}
	public void setDataSaida(Calendar data){
		String sql = "UPDATE `hotel`.`hospedagem` SET `dataSaida` = ? "; 
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setDate(1, new java.sql.Date(data.getTimeInMillis()));
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setCheckout(){
		String sql = "UPDATE `hotel`.`hospedagem` SET `checkout` = 'true' "; 
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Hospedagem getPorCodCli(int cod) throws SQLException {
		String sql = "SELECT * FROM hotel.hospedagem WHERE `cliente_cod` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		Hospedagem hosped = new Hospedagem();

		while (rs.next()) {
			
			hosped.setCod(rs.getInt("cod"));
			hosped.setCod_cliente(rs.getInt("cliente_cod"));
			//DATA
			Calendar calen = new GregorianCalendar();
			Calendar calen2 = new GregorianCalendar();
			Calendar calen3 = new GregorianCalendar();
			Calendar calen4 = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if (rs.getString("dataEntrada") != "0000-00-00"){
					calen.setTime(f.parse(rs.getString("dataEntrada")));
				}
//				if (rs.getString("dataSaida") != "0000-00-00"){
//					calen2.setTime(f.parse(rs.getString("dataSaida")));
//				}
//				if (rs.getString("dataPrevistaEntrada") != "0000-00-00"){
//					calen3.setTime(f.parse(rs.getString("dataPrevistaEntrada")));
//				}
				if (rs.getString("dataPrevistaSaida") != "0000-00-00"){
					calen4.setTime(f.parse(rs.getString("dataPrevistaSaida")));
				}				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			hosped.setDataEntrada(calen);
			hosped.setDataSaida(calen2);
			hosped.setDataPreEntrada(calen3);
			hosped.setDataPreSaida(calen4);

			
		}
		

		return hosped;
	}
}
