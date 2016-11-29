import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Quarto_HospedagemDAO {
	private Connection conexao = null;

	public Quarto_HospedagemDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(int quarto_cod, int hospedagem_cod, int quantidade) {
		String sql = "INSERT INTO hotel.quarto_hospedagem (quarto_cod, hospedagem_cod, qtdPessoa) VALUES (?,?,?)"; 
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			//stmt.setInt(1, cliente.getCod());
			stmt.setInt(1, quarto_cod);
			stmt.setInt(2, hospedagem_cod);
			stmt.setInt(3, quantidade);
			
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public int getCodPorCodQua(int cod) throws SQLException {
		String sql = "SELECT * FROM `quarto_hospedagem` WHERE `quarto_cod` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		int codHosp=0;
		while (rs.next()) {
			codHosp = rs.getInt("hospedagem_cod");
		}
		return codHosp;
	}
	public ArrayList<Quarto_Hospedagem> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM hotel.quarto_hospedagem");

		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Quarto_Hospedagem> vetor = new ArrayList<Quarto_Hospedagem>();

		while (rs.next()) {
			Quarto_Hospedagem hosped = new Quarto_Hospedagem();
			hosped.setHospedagem_cod(rs.getInt("hospedagem_cod"));
			hosped.setQuarto_cod(rs.getInt("quarto_cod"));
			hosped.setQuarto_cod(rs.getInt("qtdPessoa"));

			vetor.add(hosped);
		}
		

		return vetor;
	}
	public ArrayList<Quarto_Hospedagem> listaPorCodHosp(int cod) throws SQLException {
		String sql = "SELECT * FROM hotel.quarto_hospedagem where `hospedagem_cod` = ?"; 
		PreparedStatement stmt = null;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Quarto_Hospedagem> vetor = new ArrayList<Quarto_Hospedagem>();

		while (rs.next()) {
			Quarto_Hospedagem hosped = new Quarto_Hospedagem();
			hosped.setHospedagem_cod(rs.getInt("hospedagem_cod"));
			hosped.setQuarto_cod(rs.getInt("quarto_cod"));
			hosped.setQuantidade(rs.getInt("qtdPessoa"));

			vetor.add(hosped);
		}
		

		return vetor;
	}
}
