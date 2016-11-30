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
public class Inicio {



	private JPanel panel;
	private JDialog frame;
	
	public boolean fechar= false;
	
	private int op;
	private JButton btnNewButton;


	public int getOp() {
		return op;
	}

	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			
			  
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
	public Inicio(JanelaMenuPrincipal janMenPrin) {
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
		panel.setPreferredSize(new Dimension(800, 600));
		{
			janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		}

		//frame = new JDialog();
		janMenPrin.frameConteudo.setTitle("Cadastro de Caracter�stica - Hotel");
//		frame.setModal(true);
		//frame.add(panel);
		janMenPrin.frameConteudo.getContentPane().add(panel);
		{
			btnNewButton = new JButton("New button");
			btnNewButton.setBounds(10, 11, 109, 82);
			panel.add(btnNewButton);
		}
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		//janMenPrin.frameConteudo.setLocationRelativeTo(null); // coloca no meio
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		//JanelaMenuPrincipal.frameConteudo.setResizable(false);														// programa
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visível.ss
	}


	public static void main(String[] args) {
		//new JanelaDeCadastroDeCaract();
		//j.getPessoa();
	}


}