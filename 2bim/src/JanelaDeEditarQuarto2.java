
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
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
public class JanelaDeEditarQuarto2 {
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
	private int codQua;


	public int getCodQua() {
		return codQua;
	}

	public void setCodQua(int codQua) {
		this.codQua = codQua;
	}

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
		new JanelaDeEditarQuarto2(1);
	}

	public JanelaDeEditarQuarto2(int co) {
		ArrayList<Quarto> vetor2 = new ArrayList<Quarto>();

		Connection conexao = null;
		QuartoDAO daoQuarto = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoQuarto = new QuartoDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor2 = daoQuarto.listaPorCod(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codQua = vetor2.get(0).getCod();
		//AKII
		ArrayList<Quarto_Caract> vetor3 = new ArrayList<Quarto_Caract>();

		conexao = null;
		Quarto_CaractDAO daoQuarto_Caract = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoQuarto_Caract = new Quarto_CaractDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor3 = daoQuarto_Caract.listaPorCodQua(codQua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//AKI/
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // n√£o redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800, 600));
		
		{
			lbNum = new JLabel();
			panel.add(lbNum);
			lbNum.setText("N\u00FAmero *");
			lbNum.setBounds(12, 12, 90, 14);
			lbNum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		{
			tfNum = new JTextFieldSomenteNumeros();
			tfNum.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfNum.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfNum.setBackground(Color.white);
				}
			});
			panel.add(tfNum);
			tfNum.setBounds(12, 32, 51, 21);
			tfNum.setText(""+vetor2.get(0).getNumero());
		}
		{
			btSalvar = new JButton();
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setIcon(new ImageIcon("..\\2bim\\icons\\save.png"));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.addActionListener(new MostrarListener());
			btSalvar.setBounds(404, 435, 124, 34);
		}
		{
			butCancelar = new JButton();
			butCancelar.setIcon(new ImageIcon("..\\2bim\\icons\\cancel.png"));
			butCancelar.setForeground(new Color(255, 0, 0));
			butCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(butCancelar);
			butCancelar.setText("Cancelar");
			butCancelar.addActionListener(new SairListener());
			butCancelar.setBounds(270, 435, 124, 34);
		}
		ArrayList<Caract> vetor = new ArrayList<Caract>();
		{
			String colunas[] = new String[] {"ID", "Nome"};
			modelo = new DefaultTableModel(colunas,0);
			
			

			conexao = null;
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
			
			
			
			table1 = new JTable(modelo);

			table1.getColumnModel().getColumn(0).setPreferredWidth(46);
			table1.getColumnModel().getColumn(0).setResizable(true);
			table1.getColumnModel().getColumn(1).setPreferredWidth(150);
			table1.getColumnModel().getColumn(1).setResizable(false);
			table1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			//table1.setPreferredSize(new java.awt.Dimension(196, 18));
			//table1.getTableHeader().setMinimumSize(new java.awt.Dimension(90, 18));
			table1.addMouseListener(new MouseListener());
			TabTodasCaract = new JScrollPane(table1);
			
			panel.add(TabTodasCaract);
			TabTodasCaract.setBounds(12, 96, 293, 317);
			
			
			
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
			table2.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			//table2.setPreferredSize(new java.awt.Dimension(196, 18));
			table2.addMouseListener(new MouseListener2());
			
			TabCaract = new JScrollPane(table2);
			panel.add(TabCaract);
			TabCaract.setBounds(480, 96, 293, 317);
		}
		int tamVetor3 = vetor3.size();
		int j = 0, cont = 0;
		System.out.println(tamVetor3);
		for (int i = 0; i < vetor.size(); i++) {
			if (vetor.get(i).getCod() == vetor3.get(j).getCaract_cod() && cont==0){
				modelo2.addRow(new Object[] {vetor.get(i).getCod(),vetor.get(i).getNome(),vetor3.get(j).getQuantidade()});
				if(j == tamVetor3-1){
					cont = 1;
				}else{
					j++;
				}
			}else{
				modelo.addRow(new Object[] {vetor.get(i).getCod(),vetor.get(i).getNome()});
			}
			
		}
		{
			btAdd = new JButton();
			btAdd.setForeground(new Color(0, 128, 0));
			btAdd.setIcon(new ImageIcon("..\\2bim\\icons\\next.png"));
			btAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btAdd);
			btAdd.setText("Adicionar");
			btAdd.addActionListener(new AdicionaListener());
			btAdd.setBounds(326, 185, 143, 40);
			btAdd.setEnabled(false);
		}
		{
			tfTdCar = new JLabel();
			panel.add(tfTdCar);
			tfTdCar.setText("CaracterÌsticas Gerais - Hotel");
			tfTdCar.setBounds(12, 70, 244, 14);
			tfTdCar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		{
			lcCar = new JLabel();
			panel.add(lcCar);
			lcCar.setText("CaracterÌsticas do Quarto");
			lcCar.setBounds(480, 70, 244, 14);
			lcCar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		{
			btRemove = new JButton();
			btRemove.setForeground(new Color(255, 0, 0));
			btRemove.setIcon(new ImageIcon("..\\2bim\\icons\\preview.png"));
			btRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btRemove);
			btRemove.setText("Remover");
			btRemove.addActionListener(new RemoveListener());
			btRemove.setBounds(326, 236, 143, 40);
			btRemove.setEnabled(false);
		}
		{
			lbValorDiaria = new JLabel();
			panel.add(lbValorDiaria);
			lbValorDiaria.setText("Valor da Di\u00E1ria *");
			lbValorDiaria.setBounds(116, 13, 132, 14);
			lbValorDiaria.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		{
			tfValorDiaria = new JTextField();
			tfValorDiaria.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfValorDiaria.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfValorDiaria.setBackground(Color.white);
				}
			});
			panel.add(tfValorDiaria);
			tfValorDiaria.setBounds(116, 32, 90, 21);
			tfValorDiaria.setText(""+vetor2.get(0).getValorDiaria());
		}
		frame = new JDialog();
		frame.setTitle("Cadastro de Quartos - Hotel");
		frame.setModal(true);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.pack(); // ajusta o tamanho da janela (frame)
		frame.setLocationRelativeTo(null); // coloca no meio
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		frame.setResizable(false);								// programa
		frame.setVisible(true);
	}
	private class MouseListener implements java.awt.event.MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			btAdd.setEnabled(true);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
	}
	private class MouseListener2 implements java.awt.event.MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			btRemove.setEnabled(true);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
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
			Quarto quarto = new Quarto();
			quarto.setNumero(Integer.parseInt(getTfNum().getText()));
			quarto.setValorDiaria(Double.parseDouble(getTfValorDiaria().getText()));
			int cont = getTable2().getRowCount();
			int cont2 = getCodQua();
			
			//aki
			int vetCod[] = new int[cont];
			int vetQtd[] = new int[cont];
			
			for (int i=0; i<cont; i++){
				vetCod[i] = Integer.parseInt(""+getTable2().getValueAt(i, 0));
				vetQtd[i] = Integer.parseInt(""+getTable2().getValueAt(i, 2));
			}
			//ate aki
			
			Connection conexao = null;
			QuartoDAO daoQuarto = null;
			//aki
			try {
				conexao = ConnectionFactory.getConnection();
				daoQuarto = new QuartoDAO(conexao);
			
				daoQuarto.edita(quarto, getCodQua());
			
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//Tem q concertar aki - Sexta
			
			conexao = null;
			Quarto_CaractDAO daoQuarto_Caract = null;
			try {
				conexao = ConnectionFactory.getConnection();
				daoQuarto_Caract = new Quarto_CaractDAO(conexao);
				for (int i=0; i<cont; i++){
					daoQuarto_Caract.edita(cont2, vetCod[i], vetQtd[i]);
				}
				
			
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			frame.dispose();
			}else{
				String wr = "Preencha todos os campos obrigatÛrios \n";
				if (tfNum.getText().isEmpty()){ wr = wr+"- Numero \n";}
				if (tfValorDiaria.getText().isEmpty()){ wr = wr+"- Valor de Diaria \n";}
				JOptionPane.showMessageDialog(frame, wr, "Erro", JOptionPane.ERROR_MESSAGE);
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
			frame.dispose();
		}
	}
	
}