import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




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
public class JanelaDeCadastroDeUsuario {
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
	private JLabel lblPerguntaDeSegurana;
	private JTextField tfPergunta;
	private JTextField tfResposta;
	
	public JanelaDeCadastroDeUsuario(){
		labelNome = new JLabel("Nome *");
		labelNome.setBounds(5, 5, 100, 20);
		tfNome = new JTextField(20);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				tfNome.setBackground(Color.white);
			}
		});
		tfNome.setBounds(5,30,250,20);
		lbSenha = new JLabel("Senha *");
		lbSenha.setBounds(5, 117, 69, 20);
		tfSenha = new JPasswordField(20);
		tfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfSenha.setBackground(Color.white);
			}
		});
		tfSenha.setBounds(5,142,117,20);
		btOk = new JButton("Ok");
		btOk.setBounds(66, 276, 90, 20);
		btOk.addActionListener(new OkListener());
		JButton btSair = new JButton("Cancelar");
		btSair.setBounds(166, 276, 89, 20);
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
		frame.setTitle("Cadastro de Usu\u00E1rio - Hotel");
		frame.setModal(true);
		frame.getContentPane().add(panel);
		panel.setPreferredSize(new java.awt.Dimension(234, 145));
		
		JLabel lblLogin = new JLabel("Login *");
		lblLogin.setBounds(5, 61, 46, 14);
		panel.add(lblLogin);
		
		tfLogin = new JTextField();
		tfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfLogin.setBackground(Color.white);
			}
		});
		tfLogin.setBounds(5, 86, 250, 20);
		panel.add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblConfirmeASenha = new JLabel("Confirme a senha *");
		lblConfirmeASenha.setBounds(132, 120, 117, 14);
		panel.add(lblConfirmeASenha);
		
		tfConfirma = new JPasswordField(20);
		tfConfirma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfConfirma.setBackground(Color.white);
			}
		});
		tfConfirma.setBounds(132, 142, 123, 20);
		panel.add(tfConfirma);
		tfConfirma.setColumns(10);
		
		lblPerguntaDeSegurana = new JLabel("Pergunta de seguran\u00E7a *");
		lblPerguntaDeSegurana.setBounds(5, 173, 152, 14);
		panel.add(lblPerguntaDeSegurana);
		
		tfPergunta = new JTextField();
		tfPergunta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfPergunta.setBackground(Color.white);
			}
		});
		tfPergunta.setBounds(5, 195, 250, 20);
		panel.add(tfPergunta);
		tfPergunta.setColumns(10);
		
		JLabel lblResposta = new JLabel("Resposta *");
		lblResposta.setBounds(5, 226, 117, 14);
		panel.add(lblResposta);
		
		tfResposta = new JPasswordField(20);
		tfResposta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfResposta.setBackground(Color.white);
			}
		});
		tfResposta.setBounds(5, 245, 250, 20);
		panel.add(tfResposta);
		tfResposta.setColumns(10);
		frame.setSize(267, 334);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		new JanelaDeCadastroDeUsuario();
		
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
					op = 1;
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
			tfSenha.setText("1111");
			frame.dispose();
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