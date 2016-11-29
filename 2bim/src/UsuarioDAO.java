import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UsuarioDAO {

	private Connection conexao = null;

	public UsuarioDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}

	public void adiciona(Usuario usuario) {
		String sql = "INSERT INTO hotel.usuario (nome, login, senha, pergunta, resposta) VALUES (?,?,?,?,?)";
		PreparedStatement stmt;

		try {
			stmt = conexao.prepareStatement(sql);

			// stmt.setInt(1, cliente.getCod());
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getPergunta());
			stmt.setString(5, usuario.getResposta());

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void edita(Usuario usuario, int cod) {
		String sql = "UPDATE `hotel`.`usuario` SET `nome` = ?, `login` = ?, `senha` = ?, `pergunta` = ?, `resposta` = ? WHERE `usuario`.`cod` =?;";
		PreparedStatement stmt;

		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getPergunta());
			stmt.setString(5, usuario.getResposta());
			stmt.setInt(6, cod);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(int codigo) {
		String sql = "DELETE FROM hotel.usuario WHERE usuario.cod = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, codigo);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> listaTodos() throws SQLException {
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT * FROM usuario");

		ResultSet rs = stmt.executeQuery();
		ArrayList<Usuario> vetor = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setCod(rs.getInt("cod"));
			usuario.setNome(rs.getString("nome"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setPergunta(rs.getString("pergunta"));
			usuario.setResposta(rs.getString("resposta"));

			vetor.add(usuario);
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

	public ArrayList<Usuario> listaPorCod(int cod) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE usuario.cod = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, cod);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		ArrayList<Usuario> vetor = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setCod(rs.getInt("cod"));
			usuario.setNome(rs.getString("nome"));
			usuario.setLogin(rs.getString("login"));
			usuario.setPergunta(rs.getString("pergunta"));
			usuario.setResposta(rs.getString("resposta"));
			

			vetor.add(usuario);
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
	public Usuario getPorLogin(String login) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE usuario.login = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		Usuario usuario = new Usuario();
		usuario.setPergunta("erro");

		while (rs.next()) {
			
			usuario.setCod(rs.getInt("cod"));
			usuario.setNome(rs.getString("nome"));
			usuario.setLogin(rs.getString("login"));
			usuario.setPergunta(rs.getString("pergunta"));
			usuario.setResposta(rs.getString("resposta"));
			usuario.setSenha(rs.getString("senha"));
						
		}

		rs.close();
		stmt.close();
		return usuario;
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
