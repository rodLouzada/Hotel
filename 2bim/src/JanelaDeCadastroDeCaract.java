import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;


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
public class JanelaDeCadastroDeCaract {



	private JPanel panel;
	private JButton btCancelar;
	private JButton btSalvar;
	private JTextField tfDescCaract;
	private JLabel lbCodCaract;
	private JLabel lbOpcional;
	private JLabel lbDescCaract;
	private JTextField tfCodCaract;
	private JLabel lbNomeCaract;
	private JTextField tfNome;
	private JDialog frame;
	
	public boolean fechar= false;
	
	private int op;
	public JTextField getTfDescCaract() {
		return tfDescCaract;
	}

	public void setTfDescCaract(JTextField tfDescCaract) {
		this.tfDescCaract = tfDescCaract;
	}

	public JTextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(JTextField tfNome) {
		this.tfNome = tfNome;
	}

	public JTextField getTfCodCaract() {
		return tfCodCaract;
	}

	public void setTfCodCaract(JTextField tfCodCaract) {
		this.tfCodCaract = tfCodCaract;
	}

	public int getOp() {
		return op;
	}

	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btSalvar.doClick();
		        } 
			  else if (e.getKeyCode()== 27){
			    	btCancelar.doClick();
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
	public JanelaDeCadastroDeCaract(JanelaMenuPrincipal janMenPrin) {
		ArrayList<Caract> vetor = new ArrayList<Caract>();
		Connection conexao = null;
		CaractDAO daoCaract = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoCaract = new CaractDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoCaract.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cont = 0;
		for (int i = 0; i < vetor.size(); i++) {
			cont = vetor.get(i).getCod();
		}
		

		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // não redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(323, 162));
		{
			tfNome = new JTextField();
			tfNome.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfNome.setBackground(Color.white);
				}
			});
			panel.add(tfNome);
			tfNome.setPreferredSize(new java.awt.Dimension(227, 21));
			tfNome.setSize(227, 21);
			tfNome.setBounds(77, 32, 227, 19);
		}
		{
			lbNomeCaract = new JLabel();
			panel.add(lbNomeCaract);
			lbNomeCaract.setText("Nome *");
			lbNomeCaract.setFont(new java.awt.Font("Tahoma",1,12));
			lbNomeCaract.setBounds(77, 13, 51, 14);
		}
		{
			lbCodCaract = new JLabel();
			panel.add(lbCodCaract);
			lbCodCaract.setText("C�digo");
			lbCodCaract.setFont(new java.awt.Font("Tahoma",1,12));
			lbCodCaract.setBounds(12, 12, 65, 14);
			
		}
		{
			tfCodCaract = new JTextField();
			panel.add(tfCodCaract);
			tfCodCaract.setEditable(false);
			tfCodCaract.setBounds(12, 32, 45, 21);
			tfCodCaract.setText(""+(cont+1));
		}
		{
			lbDescCaract = new JLabel();
			panel.add(lbDescCaract);
			lbDescCaract.setText("Descri��o");
			lbDescCaract.setFont(new java.awt.Font("Tahoma",1,12));
			lbDescCaract.setBounds(12, 65, 65, 14);
		}
		{
			tfDescCaract = new JTextField();
			panel.add(tfDescCaract);
			tfDescCaract.setBounds(12, 85, 292, 21);
		}
		{
			btSalvar = new JButton();
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setIcon(new ImageIcon("..\\2bim\\icons\\save.png"));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.setBounds(203, 119, 107, 39);
			janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());			
			btSalvar.addActionListener(new MostrarListener());
		}
		{
			btCancelar = new JButton();
			btCancelar.setForeground(new Color(255, 0, 0));
			btCancelar.setIcon(new ImageIcon("..\\2bim\\icons\\cancel.png"));
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btCancelar);
			btCancelar.setText("Cancelar");
			btCancelar.setBounds(56, 122, 124, 34);
			btCancelar.addActionListener(new SairListener());
		}
		{
			lbOpcional = new JLabel();
			panel.add(lbOpcional);
			lbOpcional.setText("(opcional)");
			lbOpcional.setFont(new java.awt.Font("Segoe UI",0,9));
			lbOpcional.setBounds(71, 63, 55, 18);
		}

		//frame = new JDialog();
		janMenPrin.frameConteudo.setTitle("Cadastro de Caracter�stica - Hotel");
//		frame.setModal(true);
		//frame.add(panel);
		janMenPrin.frameConteudo.getContentPane().add(panel);
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		//janMenPrin.frameConteudo.setLocationRelativeTo(null); // coloca no meio
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		//JanelaMenuPrincipal.frameConteudo.setResizable(false);														// programa
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visível.ss
	}


	private class MostrarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean erro = false;
			if(tfNome.getText() == null || tfNome.getText().isEmpty()){
				tfNome.setBackground(Color.pink);
				erro = true;
			}
			
			if(!erro){
				op = 1;
				//janMenPrin.dispose();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigat�rios", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class SairListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			fechar = true;
		}
	}


	public static void main(String[] args) {
		//new JanelaDeCadastroDeCaract();
		//j.getPessoa();
	}


}