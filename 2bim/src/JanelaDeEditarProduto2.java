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
		cons.fill = GridBagConstraints.HORIZONTAL; // nÃ£o redimensionar objeto inserido;
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
				public void keyPressed(KeyEvent e) {
					tfNome.setBackground(Color.white);
				}
			});
			panel.add(tfNome);
			tfNome.setPreferredSize(new java.awt.Dimension(227, 21));
			tfNome.setSize(227, 21);
			tfNome.setBounds(269, 32, 286, 19);
			tfNome.setText(""+vetor.get(0).getNome());
		}
		{
			lbNome = new JLabel();
			panel.add(lbNome);
			lbNome.setText("Nome *");
			lbNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbNome.setBounds(269, 13, 94, 14);
		}
		{
			lbCod = new JLabel();
			panel.add(lbCod);
			lbCod.setText("Código");
			lbCod.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbCod.setBounds(204, 7, 65, 26);
			
		}
		{
			tfCod = new JTextField();
			panel.add(tfCod);
			tfCod.setEditable(false);
			tfCod.setBounds(204, 32, 45, 21);
			tfCod.setText(""+(co));
		}
		{
			lbValor = new JLabel();
			panel.add(lbValor);
			lbValor.setText("Valor *");
			lbValor.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbValor.setBounds(204, 65, 65, 14);
		}
		{
			tfValor = new JTextField();
			tfValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfValor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfValor.setBackground(Color.white);
				}
			});
			panel.add(tfValor);
			tfValor.setBounds(204, 85, 351, 21);
			tfValor.setText(""+vetor.get(0).getValor());
		}
		{
			btSalvar = new JButton();
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setIcon(new ImageIcon("..\\2bim\\icons\\save.png"));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.setBounds(389, 119, 124, 34);
			btSalvar.addActionListener(new MostrarListener());
		}
		{
			btCancelar = new JButton();
			btCancelar.setForeground(new Color(255, 0, 0));
			btCancelar.setIcon(new ImageIcon("..\\2bim\\icons\\cancel.png"));
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btCancelar);
			btCancelar.setText("Cancelar");
			btCancelar.setBounds(239, 119, 124, 34);
			btCancelar.addActionListener(new SairListener());
		}
		op = co;
		frame = new JDialog();
		frame.setTitle("Edição de Produto - Hotel");
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
				String wr = "Preencha todos os campos obrigatórios \n";
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
		new JanelaDeEditarProduto2(1);
		//j.getPessoa();
	}


}