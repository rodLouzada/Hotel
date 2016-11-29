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
public class JanelaDeEditarProduto2 {



	private JPanel panel;
	private JButton btCancelar;
	private JButton btSalvar;
	private JTextField tfValor;
	private JLabel lbCod;
	private JLabel lbValor;
	private JTextField tfCod;
	private JLabel lbNome;
	private JTextField tfNome;
	private JDialog frame;
	
	private int op;
	public JTextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(JTextField tfValor) {
		this.tfValor = tfValor;
	}

	public JTextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(JTextField tfNome) {
		this.tfNome = tfNome;
	}

	public JTextField getTfCodCaract() {
		return tfCod;
	}

	public void setTfCod(JTextField tfCod) {
		this.tfCod = tfCod;
	}

	public int getOp() {
		return op;
	}

	public JanelaDeEditarProduto2(int co) {
		ArrayList<Produto> vetor = new ArrayList<Produto>();

		Connection conexao = null;
		ProdutoDAO daoProduto = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoProduto = new ProdutoDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoProduto.listaPorCod(co);
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
		panel.setPreferredSize(new Dimension(316, 165));
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
			tfNome.setText(""+vetor.get(0).getNome());
		}
		{
			lbNome = new JLabel();
			panel.add(lbNome);
			lbNome.setText("Nome *");
			lbNome.setFont(new java.awt.Font("Tahoma",1,12));
			lbNome.setBounds(77, 13, 51, 14);
		}
		{
			lbCod = new JLabel();
			panel.add(lbCod);
			lbCod.setText("C�digo");
			lbCod.setFont(new java.awt.Font("Tahoma",1,12));
			lbCod.setBounds(12, 12, 65, 14);
			
		}
		{
			tfCod = new JTextField();
			panel.add(tfCod);
			tfCod.setEditable(false);
			tfCod.setBounds(12, 32, 45, 21);
			tfCod.setText(""+(co));
		}
		{
			lbValor = new JLabel();
			panel.add(lbValor);
			lbValor.setText("Valor *");
			lbValor.setFont(new java.awt.Font("Tahoma",1,12));
			lbValor.setBounds(12, 65, 65, 14);
		}
		{
			tfValor = new JTextField();
			tfValor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfValor.setBackground(Color.white);
				}
			});
			panel.add(tfValor);
			tfValor.setBounds(12, 85, 292, 21);
			tfValor.setText(""+vetor.get(0).getValor());
		}
		{
			btSalvar = new JButton();
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setIcon(new ImageIcon("..\\2bim\\icons\\save.png"));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.setBounds(197, 119, 107, 39);
			btSalvar.addActionListener(new MostrarListener());
		}
		{
			btCancelar = new JButton();
			btCancelar.setForeground(new Color(255, 0, 0));
			btCancelar.setIcon(new ImageIcon("..\\2bim\\icons\\cancel.png"));
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btCancelar);
			btCancelar.setText("Cancelar");
			btCancelar.setBounds(48, 121, 124, 34);
			btCancelar.addActionListener(new SairListener());
		}
		op = co;
		frame = new JDialog();
		frame.setTitle("Edi��o de Produto - Hotel");
		frame.setModal(true);
		//frame.add(panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		frame.pack(); // ajusta o tamanho da janela (frame)
		frame.setLocationRelativeTo(null); // coloca no meio
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		frame.setResizable(false);								// programa
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
			
			if(tfValor.getText() == null || tfValor.getText().isEmpty()){
				tfValor.setBackground(Color.pink);
				erro = true;
			}
			
			if (!erro){
				Produto produto = new Produto();
				produto.setNome(tfNome.getText());
				produto.setValor(Double.parseDouble(tfValor.getText()));
				Connection conexao;
				int codigo = getOp();
				try {
					conexao = ConnectionFactory.getConnection();
					ProdutoDAO dao = new ProdutoDAO(conexao);
					dao.edita(produto, codigo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
			}else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigat�rios", "Erro", JOptionPane.ERROR_MESSAGE);
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
		new JanelaDeEditarProduto2(1);
		//j.getPessoa();
	}


}