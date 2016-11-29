import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import java.awt.Font;




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
	
	public boolean fechar = false;
	private JPanel panel_1;
	
	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btOk.doClick();
		        } 
			  else if (e.getKeyCode()== 27){
			    	fechar = true;
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
	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeCadastroDeUsuario(JanelaMenuPrincipal janMenPrin){
		labelNome = new JLabel("Nome *");
		labelNome.setBounds(67, 80, 100, 20);
		tfNome = new JTextField(20);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				tfNome.setBackground(Color.white);
			}
		});
		tfNome.setBounds(67,105,250,20);
		lbSenha = new JLabel("Senha *");
		lbSenha.setBounds(67, 192, 69, 20);
		tfSenha = new JPasswordField(20);
		tfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfSenha.setBackground(Color.white);
			}
		});
		tfSenha.setBounds(67,217,117,20);
		btOk = new JButton("Novo");
		btOk.setForeground(new Color(0, 128, 0));
		btOk.setIcon(new ImageIcon("..\\2bim\\icons\\new.png"));
		btOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btOk.setBounds(258, 351, 107, 39);
		btOk.addActionListener(new OkListener());
		JButton btSair = new JButton("Cancelar");
		btSair.setIcon(new ImageIcon("..\\2bim\\icons\\cancel.png"));
		btSair.setForeground(new Color(255, 0, 0));
		btSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btSair.setBounds(108, 353, 124, 34);
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
		panel.setPreferredSize(new java.awt.Dimension(572, 400));
		
		JLabel lblLogin = new JLabel("Login *");
		lblLogin.setBounds(67, 136, 46, 14);
		panel.add(lblLogin);
		
		tfLogin = new JTextField();
		tfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfLogin.setBackground(Color.white);
			}
		});
		tfLogin.setBounds(67, 161, 250, 20);
		panel.add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblConfirmeASenha = new JLabel("Confirme a senha *");
		lblConfirmeASenha.setBounds(194, 195, 117, 14);
		panel.add(lblConfirmeASenha);
		
		tfConfirma = new JPasswordField(20);
		tfConfirma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfConfirma.setBackground(Color.white);
			}
		});
		tfConfirma.setBounds(194, 217, 123, 20);
		panel.add(tfConfirma);
		tfConfirma.setColumns(10);
		
		lblPerguntaDeSegurana = new JLabel("Pergunta de seguran\u00E7a *");
		lblPerguntaDeSegurana.setBounds(67, 248, 162, 20);
		panel.add(lblPerguntaDeSegurana);
		
		tfPergunta = new JTextField();
		tfPergunta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfPergunta.setBackground(Color.white);
			}
		});
		tfPergunta.setBounds(67, 270, 250, 20);
		panel.add(tfPergunta);
		tfPergunta.setColumns(10);
		
		JLabel lblResposta = new JLabel("Resposta *");
		lblResposta.setBounds(67, 301, 117, 14);
		panel.add(lblResposta);
		
		tfResposta = new JPasswordField(20);
		tfResposta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfResposta.setBackground(Color.white);
			}
		});
		tfResposta.setBounds(67, 320, 250, 20);
		panel.add(tfResposta);
		tfResposta.setColumns(10);
		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Cadastro de Usuários - Hotel");
//		frame.setModal(true);
		//frame.add(panel);
		janMenPrin.frameConteudo.getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 407, 72);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("..\\2bim\\icons\\Netfontes_aardvark_cafe_Logo.gif"));
		panel_1.add(lblNewLabel);
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		//janMenPrin.frameConteudo.setLocationRelativeTo(null); // coloca no meio
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		//JanelaMenuPrincipal.frameConteudo.setResizable(false);														// programa
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visÃ­vel.ss

		
	}
	public static void main(String[] args) {
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
					op = 1111;
					fechar = true;
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
			fechar = true;
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