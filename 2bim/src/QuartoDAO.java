import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class QuartoDAO {
	private Connection conexao = null;

	public QuartoDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(Quarto qua) {
		String sql = "INSERT INTO hotel.quarto (numero, valorDiaria, ocupado) VALUES (?,?, 'false')"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			//stmt.setInt(1, qua.getCod());
			stmt.setInt(1, qua.getNumero());
			stmt.setDouble(2, qua.getValorDiaria());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void tornaOcupado(int cod){
		String sql = "UPDATE `hotel`.`quarto` SET `ocupado` = 'true' WHERE `quarto`.`cod` = ?;"; 
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void tornaDisponivel(int cod){
		String sql = "UPDATE `hotel`.`quarto` SET `ocupado` = 'false' WHERE `quarto`.`cod` = ?;"; 
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void edita(Quarto qua, int cod) {
		String sql = "UPDATE `hotel`.`quarto` SET `numero` = ?, `valorDiaria` = ? WHERE `quarto`.`cod` =?;"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setInt(1, qua.getNumero());
			stmt.setDouble(2, qua.getValorDiaria());
			stmt.setInt(3, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void excluir(int codigo) {
		String sql = "DELETE FROM hotel.quarto WHERE quarto.cod = ?";
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Quarto> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM quarto");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Quarto> vetor = new ArrayList<Quarto>();

		while (rs.next()) {
			Quarto qua = new Quarto();
			qua.setCod(rs.getInt("cod"));
			qua.setNumero(Integer.parseInt(rs.getString("numero")));
			qua.setValorDiaria(Double.parseDouble(rs.getString("valorDiaria")));
			qua.setOcupado(rs.getString("ocupado"));


			vetor.add(qua);
		}
		

		return vetor;
	}
	public ArrayList<Quarto> listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM quarto WHERE quarto.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Quarto> vetor = new ArrayList<Quarto>();

		while (rs.next()) {
			Quarto qua = new Quarto();
			qua.setCod(rs.getInt("cod"));
			qua.setNumero(rs.getInt("numero"));
			qua.setValorDiaria(rs.getDouble("valorDiaria"));

			vetor.add(qua);
		}
		

		return vetor;
	}
	public Quarto getPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM quarto WHERE quarto.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		
		Quarto qua = new Quarto();
		while (rs.next()) {
			
			qua.setCod(rs.getInt("cod"));
			qua.setNumero(rs.getInt("numero"));
			qua.setValorDiaria(rs.getDouble("valorDiaria"));

			
		}
		

		return qua;
	}
	public ResultSet listaTodosRS() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("select * from quarto");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}
	public ResultSet listaPorCodRS(int valor) throws SQLException {
		String sql = "SELECT * FROM dvdjogo WHERE dvdjogo.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, valor);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();

		return rs;
	}
	public int getCodPorNum(String cod) throws SQLException {
		String sql = "SELECT * FROM quarto WHERE `numero` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		int codQua=0;

		while (rs.next()) {
			codQua = rs.getInt("cod");			
		}
		

		return codQua;
	}
}