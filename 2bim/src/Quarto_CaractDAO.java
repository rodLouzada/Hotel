import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Quarto_CaractDAO {
	
	private Connection conexao = null;

	public Quarto_CaractDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(int quarto_cod, int caract_cod, int quantidade) {
		String sql = "INSERT INTO hotel.quarto_caract (quarto_cod, caract_cod, quantidade) VALUES (?,?,?)"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			//stmt.setInt(1, cliente.getCod());
			stmt.setInt(1, quarto_cod);
			stmt.setInt(2, caract_cod);
			stmt.setInt(3, quantidade);
			
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void edita(int quarto_cod, int caract_cod, int quantidade) {
		String sql = "UPDATE `hotel`.`quarto_caract` SET `caract_cod` = ?, `quantidade` = ? WHERE `quarto_caract`.`quarto_cod` =?;"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			
			
			stmt.setInt(1, caract_cod);
			stmt.setInt(2, quantidade);
			stmt.setInt(3, quarto_cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void excluir(int codigo) {
		String sql = "DELETE FROM hotel.quarto_caract WHERE quarto_caract.quarto_cod = ?";
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Quarto_Caract> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM hotel.quarto_caract");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Quarto_Caract> vetor = new ArrayList<Quarto_Caract>();

		while (rs.next()) {
			Quarto_Caract quarto_caract = new Quarto_Caract();
			quarto_caract.setQuarto_cod(rs.getInt("quarto_cod"));
			quarto_caract.setCaract_cod(rs.getInt("caract_cod"));
			quarto_caract.setQuantidade(rs.getInt("quantidade"));


			vetor.add(quarto_caract);
		}
		

		return vetor;
	}
	
	
	public ResultSet listaTodosRS() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("select * from hotel.quanto_caract");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}
	public ArrayList<Quarto_Caract> listaPorCodQua(int codQua) throws SQLException {
		String sql = "SELECT * FROM quarto_caract WHERE quarto_caract.quarto_cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, codQua);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Quarto_Caract> vetor = new ArrayList<Quarto_Caract>();

		while (rs.next()) {
			Quarto_Caract qua_car = new Quarto_Caract();
			qua_car.setQuarto_cod(rs.getInt("quarto_cod"));
			qua_car.setCaract_cod(rs.getInt("caract_cod"));
			qua_car.setQuantidade(rs.getInt("quantidade"));

			vetor.add(qua_car);
		}
		

		return vetor;
	}
}
