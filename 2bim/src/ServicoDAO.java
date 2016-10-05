import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ServicoDAO {
	
	private Connection conexao = null;

	public ServicoDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(Servico servico) {
		String sql = "INSERT INTO hotel.servico (nome, valor) VALUES (?,?)"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			//stmt.setInt(1, cliente.getCod());
			stmt.setString(1, servico.getNome());
			stmt.setDouble(2, servico.getValor());
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void excluir(int codigo) {
		String sql = "DELETE FROM hotel.servico WHERE servico.cod = ?";
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Servico> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM hotel.servico");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Servico> vetor = new ArrayList<Servico>();

		while (rs.next()) {
			Servico servico = new Servico();
			servico.setCod(rs.getInt("cod"));
			servico.setNome(rs.getString("nome"));
			servico.setValor(rs.getDouble("valor"));


			vetor.add(servico);
		}
		

		return vetor;
	}
	public void edita(Servico servico, int cod) {
		String sql = "UPDATE `hotel`.`servico` SET `nome` = ?, `valor` = ? WHERE `servico`.`cod` =?;"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setString(1, servico.getNome());
			stmt.setDouble(2, servico.getValor());
			stmt.setInt(3, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public ArrayList<Servico> listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM servico WHERE servico.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Servico> vetor = new ArrayList<Servico>();

		while (rs.next()) {
			Servico servico = new Servico();
			servico.setCod(rs.getInt("cod"));
			servico.setNome(rs.getString("nome"));
			servico.setValor(rs.getDouble("valor"));
			

			vetor.add(servico);
		}
		

		return vetor;
	}
	public String getNomePorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM servico WHERE servico.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		String nome = null;

		while (rs.next()) {
			nome = rs.getString("nome");
		}
		

		return nome;
	}
	public ResultSet listaTodosRS() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("select * from hotel.servico");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}
}