import java.awt.BorderLayout;
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
public class JanelaDeEditarCaract2 {



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

	public JanelaDeEditarCaract2(int co) {
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
			vetor = daoCaract.listaPorCod(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // não redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800, 600));
		{
			tfNome = new JTextField();
			tfNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfNome.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					tfNome.setBackground(Color.white);
				}
			});
			panel.add(tfNome);
			tfNome.setPreferredSize(new java.awt.Dimension(227, 21));
			tfNome.setSize(227, 21);
			tfNome.setBounds(293, 32, 296, 19);
			tfNome.setText(""+vetor.get(0).getNome());
		}
		{
			lbNomeCaract = new JLabel();
			panel.add(lbNomeCaract);
			lbNomeCaract.setText("Nome *");
			lbNomeCaract.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbNomeCaract.setBounds(293, 13, 108, 14);
		}
		{
			lbCodCaract = new JLabel();
			panel.add(lbCodCaract);
			lbCodCaract.setText("C�digo");
			lbCodCaract.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbCodCaract.setBounds(228, 7, 55, 26);
			
		}
		{
			tfCodCaract = new JTextField();
			panel.add(tfCodCaract);
			tfCodCaract.setEditable(false);
			tfCodCaract.setBounds(228, 32, 45, 21);
			tfCodCaract.setText(""+(co));
		}
		{
			lbDescCaract = new JLabel();
			panel.add(lbDescCaract);
			lbDescCaract.setText("Descri��o");
			lbDescCaract.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbDescCaract.setBounds(225, 54, 80, 35);
		}
		{
			tfDescCaract = new JTextField();
			tfDescCaract.setFont(new Font("Tahoma", Font.PLAIN, 13));
			panel.add(tfDescCaract);
			tfDescCaract.setBounds(228, 85, 361, 71);
			tfDescCaract.setText(""+vetor.get(0).getDescricao());
		}
		{
			btSalvar = new JButton();
			btSalvar.setIcon(new ImageIcon("..\\2bim\\icons\\save.png"));
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.setBounds(413, 169, 124, 34);
			btSalvar.addActionListener(new MostrarListener());
		}
		{
			btCancelar = new JButton();
			btCancelar.setForeground(new Color(255, 0, 0));
			btCancelar.setIcon(new ImageIcon("..\\2bim\\icons\\cancel.png"));
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btCancelar);
			btCancelar.setText("Cancelar");
			btCancelar.setBounds(274, 169, 124, 34);
			btCancelar.addActionListener(new SairListener());
		}
		{
			lbOpcional = new JLabel();
			panel.add(lbOpcional);
			lbOpcional.setText("(opcional)");
			lbOpcional.setFont(new java.awt.Font("Segoe UI",0,9));
			lbOpcional.setBounds(303, 62, 55, 24);
		}
		op = co;
		frame = new JDialog();
		frame.setResizable(false);
		frame.setTitle("Edi��o de Caracter�stica - Hotel");
		frame.setModal(true);
		//frame.add(panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		frame.pack(); // ajusta o tamanho da janela (frame)
		frame.setLocationRelativeTo(null); // coloca no meio
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true); // torna a janela visível
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
				Caract caract = new Caract();
				caract.setNome(tfNome.getText());
				caract.setDescricao(tfDescCaract.getText());
				Connection conexao;
				int codigo = getOp();
				try {
					conexao = ConnectionFactory.getConnection();
					CaractDAO dao = new CaractDAO(conexao);
					dao.edita(caract, codigo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
			}else{
				String wr = "Preencha todos os campos obrigat�rios \n";
				if (tfNome.getText().isEmpty()){ wr = wr+"- Nome \n";}
				JOptionPane.showMessageDialog(frame, wr, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class SairListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
		}
	}


	public static void main(String[] args) {
		new JanelaDeEditarCaract2(1);
		//j.getPessoa();
	}


}