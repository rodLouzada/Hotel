import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ConsumoDAO {
	private Connection conexao = null;

	public ConsumoDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}
	public void adiciona(Consumo cons) {
		String sql;
		if (cons.getCod_servico() != 0){
			sql = "INSERT INTO hotel.consumo (servico_cod, conta_cod, quantidade, dataConsumo, valorUnitario, valorTotal) VALUES (?, ?, ?, ?, ?, ?)";
		}else{
			sql = "INSERT INTO hotel.consumo (produto_cod, conta_cod, quantidade, dataConsumo, valorUnitario, valorTotal) VALUES (?, ?, ?, ?, ?, ?)";
		}
		PreparedStatement stmt;
		
		try{
			stmt = conexao.prepareStatement(sql);
			
			if (cons.getCod_servico() != 0){
				stmt.setInt(1, cons.getCod_servico());
			}
			if (cons.getCod_produto() != 0){
				stmt.setInt(1, cons.getCod_produto());
			}
			stmt.setInt(2, cons.getCod_conta());
			stmt.setInt(3, cons.getQuantidade());
			stmt.setDate(4, new java.sql.Date(cons.getDataCons().getTimeInMillis()));
			stmt.setDouble(5, cons.getValorUnit());
			stmt.setDouble(6, cons.getValorUnit()*cons.getQuantidade());
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public ArrayList<Consumo> listaPorCodConta(int cod) throws SQLException {
		String sql = "SELECT * FROM hotel.consumo WHERE `conta_cod` = ?";
		PreparedStatement stmt = null;
		try{
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Consumo> vetor = new ArrayList<Consumo>();
		
		while (rs.next()) {
			Consumo cons = new Consumo();
			cons.setCod(rs.getInt("cod"));
			if(rs.getInt("servico_cod") == 0){
				cons.setCod_servico(0);
			}else{
				cons.setCod_servico(rs.getInt("servico_cod"));
			}
			if(rs.getInt("produto_cod") == 0){
				cons.setCod_produto(0);
			}else{
				cons.setCod_produto(rs.getInt("produto_cod"));
			}
			cons.setCod_conta(rs.getInt("conta_cod"));
			cons.setQuantidade(rs.getInt("quantidade"));
			cons.setValorUnit(rs.getDouble("valorUnitario"));
			cons.setValorTotal(rs.getDouble("valorTotal"));
			//DATA
			Calendar calen = new GregorianCalendar();
			
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				calen.setTime(f.parse(rs.getString("dataConsumo")));			
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cons.setDataCons(calen);

			vetor.add(cons);
		}
		

		return vetor;
	}
}
