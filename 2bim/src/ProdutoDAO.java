import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutoDAO {
	
	private Connection conexao = null;

	public ProdutoDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(Produto produto) {
		String sql = "INSERT INTO hotel.produto (nome, valor) VALUES (?,?)"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			//stmt.setInt(1, cliente.getCod());
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getValor());
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void excluir(int codigo) {
		String sql = "DELETE FROM hotel.produto WHERE produto.cod = ?";
		PreparedStatement stmt;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Produto> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM hotel.produto");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Produto> vetor = new ArrayList<Produto>();

		while (rs.next()) {
			Produto produto = new Produto();
			produto.setCod(rs.getInt("cod"));
			produto.setNome(rs.getString("nome"));
			produto.setValor(rs.getDouble("valor"));


			vetor.add(produto);
		}
		

		return vetor;
	}
	public void edita(Produto produto, int cod) {
		String sql = "UPDATE `hotel`.`produto` SET `nome` = ?, `valor` = ? WHERE `produto`.`cod` =?;"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getValor());
			stmt.setInt(3, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public ArrayList<Produto> listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM produto WHERE produto.cod = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Produto> vetor = new ArrayList<Produto>();

		while (rs.next()) {
			Produto produto = new Produto();
			produto.setCod(rs.getInt("cod"));
			produto.setNome(rs.getString("nome"));
			produto.setValor(rs.getDouble("valor"));
			

			vetor.add(produto);
		}
		

		return vetor;
	}
	public String getNomePorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM produto WHERE produto.cod = ?";
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
		PreparedStatement stmt = conexao.prepareStatement("select * from hotel.produto");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}
}
