import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.ImageIcon;




/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class JanelaDeEditarUsuario2 {
	private JLabel labelNome;
	static JTextField tfNome;
	private JLabel lbSenha;
	static JPasswordField tfSenha;
	private JButton btOk;
	private JPanel panel;
	private JDialog frame;
	private JTextField tfLogin;
	private JTextField tfConfirma;
	
	private int op;
	private JLabel lblPergunta;
	private JTextField tfPergunta;
	private JLabel lblResposta;
	private JTextField tfResposta;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeEditarUsuario2(int co){
		ArrayList<Usuario> vetor = new ArrayList<Usuario>();

		Connection conexao = null;
		UsuarioDAO daoUsuario = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoUsuario = new UsuarioDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoUsuario.listaPorCod(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		op = vetor.get(0).getCod();
		
		
		labelNome = new JLabel("Nome *");
		labelNome.setBounds(25, 0, 100, 20);
		tfNome = new JTextField(20);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfNome.setBackground(Color.white);
			}
		});
		tfNome.setBounds(25,25,250,20);
		tfNome.setText(vetor.get(0).getNome());
		lbSenha = new JLabel("Senha *");
		lbSenha.setBounds(25, 112, 56, 20);
		tfSenha = new JPasswordField(20);
		tfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfSenha.setBackground(Color.white);
			}
		});
		tfSenha.setBounds(25,137,117,20);
		btOk = new JButton("Salvar");
		btOk.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\save.png"));
		btOk.setForeground(new Color(30, 144, 255));
		btOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btOk.setBounds(168, 283, 107, 39);
		btOk.addActionListener(new OkListener());
		JButton btSair = new JButton("Cancelar");
		btSair.setForeground(new Color(255, 0, 0));
		btSair.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
		btSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btSair.setBounds(25, 285, 124, 34);
		btSair.addActionListener(new SairListener());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(labelNome);
		panel.add(tfNome);
		panel.add(lbSenha);
		panel.add(tfSenha);
		panel.add(btOk);
		panel.add(btSair);
		
		frame = new JDialog();
		frame.setTitle("Edi\u00E7\u00E3o de Usu\u00E1rio - Hotel");
		frame.setModal(true);
		frame.getContentPane().add(panel);
		panel.setPreferredSize(new java.awt.Dimension(234, 145));
		
		JLabel lblLogin = new JLabel("Login *");
		lblLogin.setBounds(25, 56, 46, 14);
		panel.add(lblLogin);
		
		tfLogin = new JTextField();
		tfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfLogin.setBackground(Color.white);
			}
		});
		tfLogin.setBounds(25, 81, 250, 20);
		tfLogin.setText(vetor.get(0).getLogin());
		panel.add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblConfirmeASenha = new JLabel("Confirme a senha *");
		lblConfirmeASenha.setBounds(152, 115, 117, 14);
		panel.add(lblConfirmeASenha);
		
		tfConfirma = new JPasswordField(20);
		tfConfirma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfConfirma.setBackground(Color.white);
			}
		});
		tfConfirma.setBounds(152, 137, 123, 20);
		panel.add(tfConfirma);
		tfConfirma.setColumns(10);
		
		lblPergunta = new JLabel("Pergunta *");
		lblPergunta.setBounds(25, 168, 77, 14);
		panel.add(lblPergunta);
		
		tfPergunta = new JTextField();
		tfPergunta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfPergunta.setBackground(Color.white);
			}
		});
		tfPergunta.setBounds(25, 188, 250, 20);
		tfPergunta.setText(vetor.get(0).getPergunta());
		panel.add(tfPergunta);
		tfPergunta.setColumns(10);
		
		lblResposta = new JLabel("Resposta *");
		lblResposta.setBounds(25, 219, 77, 14);
		panel.add(lblResposta);
		
		tfResposta = new JPasswordField(20);
		tfResposta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfResposta.setBackground(Color.white);
			}
		});
		tfResposta.setBounds(25, 238, 250, 20);
		tfResposta.setText(vetor.get(0).getResposta());
		panel.add(tfResposta);
		tfResposta.setColumns(10);
		frame.setSize(296, 375);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		//new JanelaDeEditarUsuario2();
		
	}
	private class OkListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean erro=false;
			if (tfNome.getText().equals("")){
				tfNome.setBackground(Color.pink);
				erro = true;
			}
			if (tfLogin.getText().equals("")){
				tfLogin.setBackground(Color.pink);
				erro = true;
			}
			if (tfSenha.getText().equals("")){
				tfSenha.setBackground(Color.pink);
				erro = true;
			}
			if (tfConfirma.getText().equals("")){
				tfConfirma.setBackground(Color.pink);
				erro = true;
			}
			if (tfPergunta.getText().equals("")){
				tfPergunta.setBackground(Color.pink);
				erro = true;
			}
			if (tfResposta.getText().equals("")){
				tfResposta.setBackground(Color.pink);
				erro = true;
			}
			if(!erro){
				if (tfSenha.getText().equals(tfConfirma.getText())){
					Usuario usu = new Usuario();
					usu.setNome(getTfNome().getText());
					usu.setLogin(getTfLogin().getText());
					usu.setResposta(getTfResposta().getText());
					usu.setPergunta(getTfPergunta().getText());
					usu.setSenha(getTfSenha().getText());
					
					Connection conexao;
					int codigo = getOp();
					try {
						conexao = ConnectionFactory.getConnection();
						UsuarioDAO dao = new UsuarioDAO(conexao);
						dao.edita(usu, codigo);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					frame.dispose();
				}else if(!tfSenha.getText().equals(tfConfirma.getText()))
					JOptionPane.showMessageDialog(frame, "As senhas informadas não coincidem", "Erro", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class SairListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			frame.dispose();
			//JanelaDeEditarUsuario j = new JanelaDeEditarUsuario();
		}
	}
	public JTextField getTfSenha(){
		return tfSenha;
	}
	public JTextField getTfNome(){
		return tfNome;
	}
	public JTextField getTfLogin(){
		return tfLogin;
	}
	public JTextField getTfPergunta(){
		return tfPergunta;
	}
	public JTextField getTfResposta(){
		return tfResposta;
	}
	public int getOp(){
		return op;
	}
}