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
import java.awt.Dimension;
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
public class JanelaDeCadastroServico {



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
	
	public boolean fechar = false;
	
	private int op;
	public JTextField getTfValor() {
		return tfValor;
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


	public void setTfValor(JTextField tfValor) {
		this.tfValor = tfValor;
	}

	public JTextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(JTextField tfNome) {
		this.tfNome = tfNome;
	}

	public JTextField getTfCod() {
		return tfCod;
	}

	public void setTfCod(JTextField tfCod) {
		this.tfCod = tfCod;
	}

	public int getOp() {
		return op;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeCadastroServico(JanelaMenuPrincipal janMenPrin) {
		ArrayList<Servico> vetor = new ArrayList<Servico>();

		Connection conexao = null;
		ServicoDAO daoservico = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoservico = new ServicoDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoservico.listaTodos();
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
			lbNome = new JLabel();
			panel.add(lbNome);
			lbNome.setText("Nome *");
			lbNome.setFont(new java.awt.Font("Tahoma",1,12));
			lbNome.setBounds(77, 13, 51, 14);
		}
		{
			lbCod = new JLabel();
			panel.add(lbCod);
			lbCod.setText("Código");
			lbCod.setFont(new java.awt.Font("Tahoma",1,12));
			lbCod.setBounds(12, 12, 65, 14);
			
		}
		{
			tfCod = new JTextField();
			panel.add(tfCod);
			tfCod.setEditable(false);
			tfCod.setBounds(12, 32, 45, 21);
			tfCod.setText(""+(cont+1));
		}
		{
			lbValor = new JLabel();
			panel.add(lbValor);
			lbValor.setText("Valor *");
			lbValor.setFont(new java.awt.Font("Tahoma",1,12));
			lbValor.setBounds(12, 65, 65, 14);
		}
		{
		/*	
			DecimalFormat dFormat = new DecimalFormat ( "#######.##" ) ;
			NumberFormatter Formatter = new NumberFormatter ( dFormat ) ;
			Formatter.setFormat ( dFormat ) ;
			Formatter.setAllowsInvalid ( false ) ; 
			*/
			
			tfValor = new JTextField();
			tfValor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfValor.setBackground(Color.white);
				}
			});
			panel.add(tfValor);
			tfValor.setBounds(12, 85, 292, 21);
		}
		{
			btSalvar = new JButton();
			btSalvar.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\save.png"));
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.setBounds(197, 119, 107, 39);
			btSalvar.addActionListener(new MostrarListener());
		}
		{
			btCancelar = new JButton();
			btCancelar.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
			btCancelar.setForeground(new Color(255, 0, 0));
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btCancelar);
			btCancelar.setText("Cancelar");
			btCancelar.setBounds(59, 121, 124, 34);
			btCancelar.addActionListener(new SairListener());
		}

		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Cadastro de Serviço - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(panel);
		
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visÃ­vel.ss		
	
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
				Servico servico = new Servico();
				servico.setNome(getTfNome().getText());
				if (getTfValor().getText() != null){
					servico.setValor(Double.parseDouble(getTfValor().getText()));
				}
				Connection conexao = null;
				ServicoDAO daoServico = null;
				try {
					conexao = ConnectionFactory.getConnection();
					daoServico = new ServicoDAO(conexao);
					
					daoServico.adiciona(servico);
					
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
				fechar = true;
			}else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
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
		//j.getPessoa();
	}


}