import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ContaDAO {
	private Connection conexao = null;

	public ContaDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(Conta conta) {
		String sql = "INSERT INTO hotel.conta (hospedagem_cod, valorTotal, pago) VALUES (?, ?, ?)"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, conta.getCod_hospedagem());
			stmt.setDouble(2, 0);
			stmt.setString(3, "false");
			
						
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void addValor(double valor, int cod){
		String sql = "UPDATE `hotel`.`conta` SET `valorTotal` = `valorTotal` + ? WHERE `conta`.`cod` =?;"; 
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setDouble(1, valor);
			stmt.setInt(2, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Conta listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM hotel.conta WHERE `cod` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		Conta conta = new Conta();

		while (rs.next()) {
			
			conta.setCod(rs.getInt("cod"));
			conta.setCod_hospedagem(rs.getInt("hospedagem_cod"));
			conta.setValorTotal(rs.getDouble("valorTotal"));
			conta.setPago(rs.getString("pago"));

		}
		return conta;
	}
	public void tornaPago(int cod){
		String sql = "UPDATE `hotel`.`conta` SET `pago` = 'true' WHERE `conta`.`cod` = ?;"; 
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}