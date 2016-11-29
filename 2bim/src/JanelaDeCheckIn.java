
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.Font;
import java.awt.Color;
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
public class JanelaDeCheckIn {
	private JScrollPane TabTodasCaract;
	private JScrollPane TabCaract;
	private JLabel lcCar;
	private JLabel tfTdCar;
	private JButton btRemove;
	private JButton btAdd;
	private JButton butCancelar;
	private JButton btSalvar;
	private DefaultTableModel modelo, modelo2;
	private JTable table1;
	private JTable table2;
	private JPanel panel;
	private JTextField tfDataPreSaida;
	private JLabel lbDataPreSaida;
	private JTextField tfDataEntrada;
	private JLabel lbDataEntrada;
	private JComboBox cbCliente;
	private JLabel lbCliente;
	private int op;

	public boolean fechar =false;


	public int getOp() {
		return op;
	}
	public JTable getTable2() {
		return table2;
	}
	public static void main(String[] args) {
	}

	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btSalvar.doClick();
		        } 
			  else if (e.getKeyCode()== 27){
				  butCancelar.doClick();
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
	public JanelaDeCheckIn(JanelaMenuPrincipal janMenPrin) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // nÃ£o redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(613, 422));
		
		
		{
			btSalvar = new JButton();
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\save.png"));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.addActionListener(new MostrarListener());
			btSalvar.setBounds(316, 375, 107, 39);
		}
		{
			butCancelar = new JButton();
			butCancelar.setForeground(new Color(255, 0, 0));
			butCancelar.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
			butCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(butCancelar);
			butCancelar.setText("Cancelar");
			butCancelar.addActionListener(new SairListener());
			butCancelar.setBounds(181, 380, 124, 34);
		}
		int contador=0;
		{
			String colunas[] = new String[] {"ID", "Número"};
			modelo = new DefaultTableModel(colunas,0);
			
			ArrayList<Quarto> vetor = new ArrayList<Quarto>();

			Connection conexao = null;
			QuartoDAO quarto = null;

			
			try {
				conexao = ConnectionFactory.getConnection();
				quarto = new QuartoDAO(conexao);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				vetor = quarto.listaTodos();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < vetor.size(); i++) {
				if (vetor.get(i).getOcupado().equals("false")){
					modelo.addRow(new Object[] {vetor.get(i).getCod(),vetor.get(i).getNumero()});	
					contador++;
				}
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
			String colunas[] = new String[] {"ID", "Número", "Qtd Pessoas"};
			modelo2 = new DefaultTableModel(colunas,0);
			table2 = new JTable(modelo2);

			table2.getColumnModel().getColumn(0).setPreferredWidth(46);
			table2.getColumnModel().getColumn(0).setResizable(false);
			table2.getColumnModel().getColumn(1).setPreferredWidth(80);
			table2.getColumnModel().getColumn(1).setResizable(false);
			table2.getColumnModel().getColumn(2).setPreferredWidth(70);
			table2.getColumnModel().getColumn(2).setResizable(true);
			table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//table2.setPreferredSize(new java.awt.Dimension(196, 18));
			table2.addMouseListener(new MouseListener2());
			
			TabCaract = new JScrollPane(table2);
			panel.add(TabCaract);
			TabCaract.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			TabCaract.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			TabCaract.setBounds(381, 102, 214, 257);
		}
		{
			btAdd = new JButton();
			btAdd.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\next.png"));
			btAdd.setForeground(new Color(0, 128, 0));
			btAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btAdd);
			btAdd.setText("Adicionar");
			btAdd.addActionListener(new AdicionaListener());
			btAdd.setBounds(232, 143, 143, 40);
			btAdd.setEnabled(false);
		}
		{
			tfTdCar = new JLabel();
			panel.add(tfTdCar);
			tfTdCar.setText("Quartos Disponíveis");
			tfTdCar.setBounds(12, 70, 160, 14);
			tfTdCar.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			lcCar = new JLabel();
			panel.add(lcCar);
			lcCar.setText("Quartos da Hospedagem");
			lcCar.setBounds(381, 76, 171, 14);
			lcCar.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			btRemove = new JButton();
			btRemove.setForeground(new Color(255, 0, 0));
			btRemove.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\preview.png"));
			btRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btRemove);
			btRemove.setText("Remover");
			btRemove.addActionListener(new RemoveListener());
			btRemove.setBounds(245, 196, 124, 34);
			btRemove.setEnabled(false);
		}
		{
			lbCliente = new JLabel();
			panel.add(lbCliente);
			lbCliente.setText("Cliente");
			lbCliente.setBounds(12, 12, 65, 16);
			lbCliente.setFont(new java.awt.Font("Tahoma",1,12));
		}
		ArrayList<Cliente> vetor2 = new ArrayList<Cliente>();

		Connection conexao = null;
		ClienteDAO cliente = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			cliente = new ClienteDAO(conexao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			vetor2 = cliente.listaTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		{
			int cont=0;
			for (int i = 0; i < vetor2.size(); i++){
				if (vetor2.get(i).getHospedado().equals("false")){
					cont++;
				}
			}
			String list[] = new String[cont];
			int j=0;
			for (int i = 0; i < vetor2.size(); i++){
				if (vetor2.get(i).getHospedado().equals("false")){
					list[j] = vetor2.get(i).getNome();
					j++;
				}
			}
			ComboBoxModel cbClienteModel = 
				new DefaultComboBoxModel(list);
			cbCliente = new JComboBox();
			panel.add(cbCliente);
			cbCliente.setModel(cbClienteModel);
			cbCliente.setBounds(12, 34, 135, 23);
		}
		{
			lbDataEntrada = new JLabel();
			panel.add(lbDataEntrada);
			lbDataEntrada.setText("Data de Entrada");
			lbDataEntrada.setBounds(161, 12, 130, 16);
			lbDataEntrada.setFont(new java.awt.Font("Tahoma",1,12));
		}
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = new Date();
	    String d = dateFormat.format(date);
		{
			try {
				MaskFormatter mascara = new MaskFormatter("##/##/####");
				tfDataEntrada = new JFormattedTextField(mascara);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			panel.add(tfDataEntrada);
			tfDataEntrada.setBounds(159, 34, 115, 23);
			tfDataEntrada.setText(d);
		}
		{
			lbDataPreSaida = new JLabel();
			panel.add(lbDataPreSaida);
			lbDataPreSaida.setText("Data Prevista de Saída");
			lbDataPreSaida.setBounds(286, 12, 137, 16);
			lbDataPreSaida.setFont(new java.awt.Font("Segoe UI",1,12));
		}
		{
			try {
				MaskFormatter mascara = new MaskFormatter("##/##/####");
				tfDataPreSaida = new JFormattedTextField(mascara);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			panel.add(tfDataPreSaida);
			tfDataPreSaida.setBounds(286, 34, 103, 23);
			tfDataPreSaida.setSize(115, 23);
		}
		
		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Check In - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.CENTER, panel);
		
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visÃ­vel.ss	
		
		if(contador == 0){
		//	JOptionPane.showMessageDialog(frame, "Nenhum quarto disponível!", "Hotel Lotado", JOptionPane.ERROR_MESSAGE);
			fechar = true;
		}else{
			//frame.setVisible(true);
		}
		
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
			Connection conexao = null;
			ArrayList<Cliente> vetCli = new ArrayList<Cliente>();
			String nome = (String) cbCliente.getSelectedItem();
			try{
				conexao = ConnectionFactory.getConnection();
				ClienteDAO daoCli = new ClienteDAO(conexao);
				vetCli = daoCli.listaPorNome(nome);
				daoCli.tornaHospedado(vetCli.get(0).getCod());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Hospedagem hospedagem = new Hospedagem();
			hospedagem.setCod_cliente(vetCli.get(0).getCod());
			
			Calendar calen = new GregorianCalendar();
			Calendar calen2 = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			try {
				calen.setTime(f.parse(tfDataEntrada.getText()));
				calen2.setTime(f.parse(tfDataPreSaida.getText()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			hospedagem.setDataEntrada(calen);
			hospedagem.setDataPreSaida(calen2);
			hospedagem.setCheckout("false");
			int cont = table2.getRowCount();
			int cont2 = 0;
			
			//aki
			int vetCod[] = new int[cont];
			int vetQtd[] = new int[cont];
			
			for (int i=0; i<cont; i++){
				vetCod[i] = Integer.parseInt((String)table2.getValueAt(i, 0));
				vetQtd[i] = Integer.parseInt((String)table2.getValueAt(i, 2));
			}
			//ate aki
			
			
			HospedagemDAO daoHospedagem = null;
			//aki
			ArrayList<Hospedagem> vetHosp = new ArrayList<Hospedagem>();
			try {
				conexao = ConnectionFactory.getConnection();
				daoHospedagem = new HospedagemDAO(conexao);
			
				daoHospedagem.adiciona(hospedagem);
				//aki
				vetHosp = daoHospedagem.listaTodos();
			
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//aki
			for (int j = 0; j < vetHosp.size(); j++) {
				cont2 = vetHosp.get(j).getCod();
			}
			
			Conta conta = new Conta();
			ContaDAO daoConta = null;
			conexao = null;
			conta.setCod_hospedagem(cont2);
			try {
				conexao = ConnectionFactory.getConnection();
				daoConta = new ContaDAO(conexao);
				daoConta.adiciona(conta);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Quarto_HospedagemDAO daoQuarto_Hospedagem = null;
			conexao = null;
			try {
				conexao = ConnectionFactory.getConnection();
				daoQuarto_Hospedagem = new Quarto_HospedagemDAO(conexao);
				QuartoDAO daoQua = new QuartoDAO(conexao);
				for (int i=0; i<cont; i++){
					daoQua.tornaOcupado(vetCod[i]);
					daoQuarto_Hospedagem.adiciona(vetCod[i], cont2, vetQtd[i]);
				}
				
			
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Check In realizado com sucesso!");
			fechar = true;
		}
	}
	private class AdicionaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int l = table1.getSelectedRow();
			String aux1 = "" + modelo.getValueAt(l, 0);
			String aux2 = "" + modelo.getValueAt(l, 1);
			
			JanelaDeQuantidade2 JanQtd = new JanelaDeQuantidade2();
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
			fechar = true;
		}
	}
	
}