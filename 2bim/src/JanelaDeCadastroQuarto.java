
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
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
public class JanelaDeCadastroQuarto {
	private JLabel lbNum;
	private JScrollPane TabTodasCaract;
	private JScrollPane TabCaract;
	private JTextField tfValorDiaria;
	private JLabel lbValorDiaria;
	private JLabel lcCar;
	private JLabel tfTdCar;
	private JButton btRemove;
	private JButton btAdd;
	private JButton butCancelar;
	private JButton btSalvar;
	private JTextField tfNum;
	private DefaultTableModel modelo, modelo2;
	private JTable table1;
	private JTable table2;
	private JPanel panel;
	private JDialog frame;
	private int op;


	public JTextField getTfValorDiaria() {
		return tfValorDiaria;
	}

	public void setTfValorDiaria(JTextField tfValorDiaria) {
		this.tfValorDiaria = tfValorDiaria;
	}

	public JTextField getTfNum() {
		return tfNum;
	}

	public void setTfNum(JTextField tfNum) {
		this.tfNum = tfNum;
	}

	public int getOp() {
		return op;
	}
	public JTable getTable2() {
		return table2;
	}
	public static void main(String[] args) {
		//new JanelaDeCadastroQuarto();
	}

	public JanelaDeCadastroQuarto(JanelaMenuPrincipal janMenPrin) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // não redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new java.awt.Dimension(572, 407));
		
		{
			lbNum = new JLabel();
			panel.add(lbNum);
			lbNum.setText("N\u00FAmero *");
			lbNum.setBounds(12, 12, 59, 14);
			lbNum.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			tfNum = new JTextFieldSomenteNumeros();
			tfNum.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfNum.setBackground(Color.white);
				}
			});
			panel.add(tfNum);
			tfNum.setBounds(12, 32, 51, 21);
		}
		{
			btSalvar = new JButton();
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.addActionListener(new MostrarListener());
			btSalvar.setBounds(176, 372, 104, 23);
		}
		{
			butCancelar = new JButton();
			panel.add(butCancelar);
			butCancelar.setText("Cancelar");
			butCancelar.addActionListener(new SairListener());
			butCancelar.setBounds(291, 372, 104, 23);
		}
		{
			String colunas[] = new String[] {"ID", "Nome"};
			modelo = new DefaultTableModel(colunas,0);
			
			ArrayList<Caract> vetor = new ArrayList<Caract>();

			Connection conexao = null;
			CaractDAO caract = null;

			
			try {
				conexao = ConnectionFactory.getConnection();
				caract = new CaractDAO(conexao);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				vetor = caract.listaTodos();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int i = 0; i < vetor.size(); i++) {
				modelo.addRow(new Object[] {vetor.get(i).getCod(),vetor.get(i).getNome()});
			}
			
			table1 = new JTable(modelo);

			table1.getColumnModel().getColumn(0).setPreferredWidth(46);
			table1.getColumnModel().getColumn(0).setResizable(true);
			table1.getColumnModel().getColumn(1).setPreferredWidth(150);
			table1.getColumnModel().getColumn(1).setResizable(false);
			table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//table1.setPreferredSize(new java.awt.Dimension(196, 18));
			//table1.getTableHeader().setMinimumSize(new java.awt.Dimension(90, 18));
			table1.addMouseListener(new MouseListener());
			TabTodasCaract = new JScrollPane(table1);
			
			panel.add(TabTodasCaract);
			TabTodasCaract
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			TabTodasCaract
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			TabTodasCaract.setBounds(12, 96, 214, 257);
			
			
			
		}
		{
			String colunas[] = new String[] {"ID", "Nome", "Qtd"};
			modelo2 = new DefaultTableModel(colunas,0);
			table2 = new JTable(modelo2);

			table2.getColumnModel().getColumn(0).setPreferredWidth(46);
			table2.getColumnModel().getColumn(0).setResizable(true);
			table2.getColumnModel().getColumn(1).setPreferredWidth(110);
			table2.getColumnModel().getColumn(1).setResizable(false);
			table2.getColumnModel().getColumn(2).setPreferredWidth(40);
			table2.getColumnModel().getColumn(2).setResizable(false);
			table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//table2.setPreferredSize(new java.awt.Dimension(196, 18));
			table2.addMouseListener(new MouseListener2());
			
			TabCaract = new JScrollPane(table2);
			panel.add(TabCaract);
			TabCaract.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			TabCaract.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			TabCaract.setBounds(346, 96, 214, 257);
		}
		{
			btAdd = new JButton();
			panel.add(btAdd);
			btAdd.setText("Adiciona >>");
			btAdd.addActionListener(new AdicionaListener());
			btAdd.setBounds(232, 143, 109, 21);
			btAdd.setEnabled(false);
		}
		{
			tfTdCar = new JLabel();
			panel.add(tfTdCar);
			tfTdCar.setText("Caracter�sticas Gerais");
			tfTdCar.setBounds(12, 70, 160, 14);
			tfTdCar.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			lcCar = new JLabel();
			panel.add(lcCar);
			lcCar.setText("Caracter�sticas do Quarto");
			lcCar.setBounds(346, 70, 171, 14);
			lcCar.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			btRemove = new JButton();
			panel.add(btRemove);
			btRemove.setText("Remove <<");
			btRemove.addActionListener(new RemoveListener());
			btRemove.setBounds(232, 196, 109, 21);
			btRemove.setEnabled(false);
		}
		{
			lbValorDiaria = new JLabel();
			panel.add(lbValorDiaria);
			lbValorDiaria.setText("Valor da Di\u00E1ria *");
			lbValorDiaria.setBounds(83, 12, 101, 14);
			lbValorDiaria.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			tfValorDiaria = new JTextField();
			tfValorDiaria.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfValorDiaria.setBackground(Color.white);
				}
			});
			panel.add(tfValorDiaria);
			tfValorDiaria.setBounds(83, 31, 90, 21);
		}
		janMenPrin.frameConteudo.setTitle("Cadastro de Quartos - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(panel);
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		//janMenPrin.frameConteudo.setLocationRelativeTo(null); // coloca no meio
		janMenPrin.frameConteudo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Sair do
		//JanelaMenuPrincipal.frameConteudo.setResizable(false);														// programa
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visível.ss
		
	}
	private class MouseListener implements java.awt.event.MouseListener {
		public void mouseClicked(MouseEvent e) {
			btAdd.setEnabled(true);
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
		
	}
	private class MouseListener2 implements java.awt.event.MouseListener {
		public void mouseClicked(MouseEvent e) {
			btRemove.setEnabled(true);
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	private class MostrarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean erro = false;
			if(tfNum.getText() == null || tfNum.getText().isEmpty()){
				tfNum.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfValorDiaria.getText() == null || tfValorDiaria.getText().isEmpty()){
				tfValorDiaria.setBackground(Color.pink);
				erro = true;
			}
			
			if(!erro){
				op = 1;
				//frame.dispose();
			}else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigat�rios", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class AdicionaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int l = table1.getSelectedRow();
			String aux1 = "" + modelo.getValueAt(l, 0);
			String aux2 = "" + modelo.getValueAt(l, 1);
			
			JanelaDeQuantidade JanQtd = new JanelaDeQuantidade();
			if (JanQtd.getOp() == 1){
				String aux3 = JanQtd.getTfQtd().getText();
				modelo2.addRow(new Object[] {aux1, aux2, aux3});
			}
			modelo.removeRow(l);
			btAdd.setEnabled(false);
			
		}
	}
	private class RemoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int l = table2.getSelectedRow();
			
			String aux1 = "" + modelo2.getValueAt(l, 0);
			String aux2 = "" + modelo2.getValueAt(l, 1);
			modelo.addRow(new Object[] {aux1, aux2});
			modelo2.removeRow(l);
			btRemove.setEnabled(false);
		}
	}

	private class SairListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			//frame.dispose();
		}
	}
	
}