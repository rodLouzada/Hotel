import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import java.awt.Color;
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
public class JanelaDeLogin {
	private JLabel labelNome;
	static JTextField tfNome;
	private JLabel lbSenha;
	static JPasswordField tfSenha;
	private JButton btOk;
	private JPanel panel;
	private JDialog frame;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	
	
	public JanelaDeLogin(){
		labelNome = new JLabel("Nome de usuário:");
		labelNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelNome.setBounds(78, 77, 146, 20);
		tfNome = new JTextField(20);
		tfNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		tfNome.setBounds(78,102,200,20);
		tfNome.addKeyListener(new OkKeyListener());
		lbSenha = new JLabel("Senha:");
		lbSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSenha.setBounds(78, 122, 200, 20);
		tfSenha = new JPasswordField(20);
		tfSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		tfSenha.setBounds(78,142,200,20);
		tfSenha.addKeyListener(new OkKeyListener());
		btOk = new JButton("Logar");
		btOk.setForeground(new Color(0, 128, 0));
		btOk.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\check.png"));
		btOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btOk.setBounds(224, 215, 107, 39);
		btOk.addActionListener(new OkListener());
		btOk.addKeyListener(new OkKeyListener());
		JButton btSair = new JButton("Cancelar");
		btSair.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
		btSair.setForeground(new Color(255, 0, 0));
		btSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btSair.setBounds(58, 220, 124, 34);
		btSair.addActionListener(new SairListener());
		btSair.addKeyListener(new KeySairListener());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.addKeyListener(new OkKeyListener());
		panel.add(labelNome);
		panel.add(tfNome);
		panel.add(lbSenha);
		panel.add(tfSenha);
		panel.add(btOk);
		panel.add(btSair);
		
		frame = new JDialog();
		frame.setTitle("Login de Acesso - Hotel");
		frame.setModal(true);
		frame.getContentPane().add(panel);
		panel.setPreferredSize(new java.awt.Dimension(234, 145));
		
		JLabel lblRecuperarSenha = new JLabel("Recuperar senha");
		lblRecuperarSenha.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\password.png"));
		lblRecuperarSenha.setForeground(Color.BLUE);
		lblRecuperarSenha.setBounds(201, 182, 130, 20);
		lblRecuperarSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblRecuperarSenha.addMouseListener(new java.awt.event.MouseAdapter(){
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblRecuperarSenhaMouseClicked(evt);
			}
		});
		panel.add(lblRecuperarSenha);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 363, 64);
		panel.add(panel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\Netfontes_aardvark_cafe_Logo.gif"));
		panel_1.add(lblNewLabel);
		frame.setSize(369, 321);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
				new JanelaDeLogin();
		
	}
	
	public void ValidaLogin(){
		ArrayList<Usuario> vetorUsu = new ArrayList<Usuario>();

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
			vetorUsu = daoUsuario.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean acesso = false;
		
		for (int i = 0; i < vetorUsu.size(); i++) {
			if(tfNome.getText().equals(vetorUsu.get(i).getLogin()) && tfSenha.getText().equals(vetorUsu.get(i).getSenha())){
				acesso = true;
			}
		}
		if (acesso) 
			frame.dispose();
		else{
			JOptionPane.showMessageDialog(frame, "Erro! Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
			tfNome.setText("");
			tfSenha.setText("");
			tfNome.requestFocus();
		}
	}
	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
		        	ValidaLogin();
		        }
		    }

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class OkListener implements ActionListener {
		@Override		
		public void actionPerformed(ActionEvent arg0) {
			ValidaLogin();
		}
	}
	private class KeySairListener  implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			tfSenha.setText("1111");
			frame.dispose();			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class SairListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			tfSenha.setText("1111");
			frame.dispose();
		}
	}
	private void lblRecuperarSenhaMouseClicked(java.awt.event.MouseEvent evt){
		String login = "", resposta = "";
		
		login = JOptionPane.showInputDialog("Digite seu login: ", login).toString();
		Usuario usu = new Usuario();

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
			usu = daoUsuario.getPorLogin(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(usu.getPergunta().equals("erro")){
			JOptionPane.showMessageDialog(frame, "Login inexistente!", "Erro", JOptionPane.ERROR_MESSAGE);
		}else{
			resposta = JOptionPane.showInputDialog(usu.getPergunta(), resposta).toString();
			if(resposta.equals(usu.getResposta())){
				JanelaDeEditarUsuario2 janEdU = new JanelaDeEditarUsuario2(usu.getCod());
			}else{
				JOptionPane.showMessageDialog(frame, "Resposta incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public JTextField getTfSenha(){
		return tfSenha;
	}
	public JTextField getTfUsu(){
		return tfNome;
	}
}