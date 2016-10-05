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

public class ClienteDAO {

	private Connection conexao = null;

	public ClienteDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}

	public void adiciona(Cliente cliente) {
		String sql = "INSERT INTO hotel.cliente (nome, rua, numero, cidade, uf, cep, cpf, identidade, telefone, email, dataNasc, hospedado) VALUES (?,?,?,?,?,?,?,?,?,?,?,false)";
		PreparedStatement stmt;

		try {
			stmt = conexao.prepareStatement(sql);

			// stmt.setInt(1, cliente.getCod());
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getRua());
			stmt.setInt(3, cliente.getNumero());
			stmt.setString(4, cliente.getCidade());
			stmt.setString(5, cliente.getUf());
			stmt.setString(6, cliente.getCep());
			stmt.setString(7, cliente.getCpf());
			stmt.setString(8, cliente.getIdentidade());
			stmt.setString(9, cliente.getTelefone());
			stmt.setString(10, cliente.getEmail());
			stmt.setDate(11, new java.sql.Date(cliente.getDataNasc()
					.getTimeInMillis()));

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void edita(Cliente cliente, int cod) {
		String sql = "UPDATE `hotel`.`cliente` SET `nome` = ?, `rua` = ?, `numero` = ?, `cidade` = ?, `uf` = ?, `cep` = ?, `cpf` = ?, `identidade` = ?, `telefone` = ?, `email` = ?, `dataNasc` = ? WHERE `cliente`.`cod` =?;";
		PreparedStatement stmt;

		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getRua());
			stmt.setInt(3, cliente.getNumero());
			stmt.setString(4, cliente.getCidade());
			stmt.setString(5, cliente.getUf());
			stmt.setString(6, cliente.getCep());
			stmt.setString(7, cliente.getCpf());
			stmt.setString(8, cliente.getIdentidade());
			stmt.setString(9, cliente.getTelefone());
			stmt.setString(10, cliente.getEmail());
			stmt.setDate(11, new java.sql.Date(cliente.getDataNasc()
					.getTimeInMillis()));
			stmt.setInt(12, cod);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(int codigo) {
		String sql = "DELETE FROM hotel.cliente WHERE cliente.cod = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, codigo);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT * FROM cliente");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Cliente> vetor = new ArrayList<Cliente>();

		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setCod(rs.getInt("cod"));
			cliente.setNome(rs.getString("nome"));
			cliente.setRua(rs.getString("rua"));
			cliente.setNumero(rs.getInt("numero"));
			cliente.setCidade(rs.getString("cidade"));
			cliente.setUf(rs.getString("uf"));
			cliente.setCep(rs.getString("cep"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setIdentidade(rs.getString("identidade"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEmail(rs.getString("email"));
			cliente.setHospedado(rs.getString("hospedado"));
			// DATA--
			Calendar calen = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				calen.setTime(f.parse(rs.getString("dataNasc")));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cliente.setDataNasc(calen);

			vetor.add(cliente);
		}

		return vetor;
	}

	public void tornaNaoHospedado(int cod) {
		String sql = "UPDATE `hotel`.`cliente` SET `hospedado` = 'false' WHERE `cliente`.`cod` =?;";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, cod);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void tornaHospedado(int cod) {
		String sql = "UPDATE `hotel`.`cliente` SET `hospedado` = 'true' WHERE `cliente`.`cod` =?;";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, cod);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE cliente.cod = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, cod);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Cliente> vetor = new ArrayList<Cliente>();

		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setCod(rs.getInt("cod"));
			cliente.setNome(rs.getString("nome"));
			cliente.setRua(rs.getString("rua"));
			cliente.setNumero(rs.getInt("numero"));
			cliente.setCidade(rs.getString("cidade"));
			cliente.setUf(rs.getString("uf"));
			cliente.setCep(rs.getString("cep"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setIdentidade(rs.getString("identidade"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEmail(rs.getString("email"));
			// DATA--
			Calendar calen = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				calen.setTime(f.parse(rs.getString("dataNasc")));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cliente.setDataNasc(calen);

			vetor.add(cliente);
		}

		return vetor;
	}

	public Cliente getPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE cliente.cod = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, cod);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();

		Cliente cliente = new Cliente();
		while (rs.next()) {
			cliente.setCod(rs.getInt("cod"));
			cliente.setNome(rs.getString("nome"));
			cliente.setRua(rs.getString("rua"));
			cliente.setNumero(rs.getInt("numero"));
			cliente.setCidade(rs.getString("cidade"));
			cliente.setUf(rs.getString("uf"));
			cliente.setCep(rs.getString("cep"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setIdentidade(rs.getString("identidade"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEmail(rs.getString("email"));
			// DATA--
			Calendar calen = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				calen.setTime(f.parse(rs.getString("dataNasc")));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cliente.setDataNasc(calen);
		}

		return cliente;
	}
	

	public ArrayList<Cliente> listaPorNome(String nome) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE cliente.nome = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Cliente> vetor = new ArrayList<Cliente>();

		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setCod(rs.getInt("cod"));
			cliente.setNome(rs.getString("nome"));
			cliente.setRua(rs.getString("rua"));
			cliente.setNumero(rs.getInt("numero"));
			cliente.setCidade(rs.getString("cidade"));
			cliente.setUf(rs.getString("uf"));
			cliente.setCep(rs.getString("cep"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setIdentidade(rs.getString("identidade"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEmail(rs.getString("email"));
			// DATA--
			Calendar calen = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				calen.setTime(f.parse(rs.getString("dataNasc")));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cliente.setDataNasc(calen);

			vetor.add(cliente);
		}

		rs.close();
		stmt.close();
		return vetor;
	}
	public Cliente getPorNome(String nome) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE cliente.nome = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		Cliente cliente = new Cliente();

		while (rs.next()) {
			
			cliente.setCod(rs.getInt("cod"));
			cliente.setNome(rs.getString("nome"));
			cliente.setRua(rs.getString("rua"));
			cliente.setNumero(rs.getInt("numero"));
			cliente.setCidade(rs.getString("cidade"));
			cliente.setUf(rs.getString("uf"));
			cliente.setCep(rs.getString("cep"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setIdentidade(rs.getString("identidade"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEmail(rs.getString("email"));
			// DATA--
			Calendar calen = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				calen.setTime(f.parse(rs.getString("dataNasc")));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cliente.setDataNasc(calen);

			
		}

		rs.close();
		stmt.close();
		return cliente;
	}

	public ResultSet listaTodosRS() throws SQLException {
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT * FROM cliente");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}

	public ResultSet listaPorCodRS(int valor) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE cliente.cod = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, valor);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();

		return rs;
	}
}
