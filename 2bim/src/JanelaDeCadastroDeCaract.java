import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
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

	public JanelaDeCadastroDeCaract() {
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
		cons.fill = GridBagConstraints.HORIZONTAL; // nÃ£o redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new java.awt.Dimension(316, 152));
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
			lbCodCaract.setText("Código");
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
			lbDescCaract.setText("Descrição");
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
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.setBounds(66, 119, 85, 21);
			btSalvar.addActionListener(new MostrarListener());
		}
		{
			btCancelar = new JButton();
			panel.add(btCancelar);
			btCancelar.setText("Cancelar");
			btCancelar.setBounds(162, 119, 85, 21);
			btCancelar.addActionListener(new SairListener());
		}
		{
			lbOpcional = new JLabel();
			panel.add(lbOpcional);
			lbOpcional.setText("(opcional)");
			lbOpcional.setFont(new java.awt.Font("Segoe UI",0,9));
			lbOpcional.setBounds(71, 63, 55, 18);
		}

		frame = new JDialog();
		frame.setTitle("Cadastro de Característica - Hotel");
		frame.setModal(true);
		//frame.add(panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		frame.pack(); // ajusta o tamanho da janela (frame)
		frame.setLocationRelativeTo(null); // coloca no meio
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Sair do
		frame.setResizable(false);								// programa
		frame.setVisible(true); // torna a janela visÃ­vel
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
				frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class SairListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			frame.dispose();
		}
	}


	public static void main(String[] args) {
		new JanelaDeCadastroDeCaract();
		//j.getPessoa();
	}


}