import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaractDAO {
	
	private Connection conexao = null;

	public CaractDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(Caract caract) {
		String sql = "INSERT INTO hotel.caract (nome, descricao) VALUES (?,?)"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			//stmt.setInt(1, cliente.getCod());
			stmt.setString(1, caract.getNome());
			stmt.setString(2, caract.getDescricao());
			
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void excluir(int codigo) {
		String sql = "DELETE FROM hotel.caract WHERE caract.cod = ?";
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Caract> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM hotel.caract");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Caract> vetor = new ArrayList<Caract>();

		while (rs.next()) {
			Caract caracteristica = new Caract();
			caracteristica.setCod(rs.getInt("cod"));
			caracteristica.setNome(rs.getString("nome"));
			caracteristica.setDescricao(rs.getString("descricao"));


			vetor.add(caracteristica);
		}
		

		return vetor;
	}
	public void edita(Caract caract, int cod) {
		String sql = "UPDATE `hotel`.`caract` SET `nome` = ?, `descricao` = ? WHERE `caract`.`cod` =?;"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setString(1, caract.getNome());
			stmt.setString(2, caract.getDescricao());
			stmt.setInt(3, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public ArrayList<Caract> listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM caract WHERE caract.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Caract> vetor = new ArrayList<Caract>();

		while (rs.next()) {
			Caract caract = new Caract();
			caract.setCod(rs.getInt("cod"));
			caract.setNome(rs.getString("nome"));
			caract.setDescricao(rs.getString("descricao"));
			

			vetor.add(caract);
		}
		

		return vetor;
	}
	public String getNomePorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM caract WHERE caract.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		Caract caract = new Caract();
		while (rs.next()) {
			
			caract.setCod(rs.getInt("cod"));
			caract.setNome(rs.getString("nome"));
			caract.setDescricao(rs.getString("descricao"));
			

					}
		

		return caract.getNome();
	}
	public ResultSet listaTodosRS() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("select * from hotel.caract");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}
}
