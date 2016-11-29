
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
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
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
public class JanelaDeConsumo {
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
	private JTextField tfDataEntrada;
	private JLabel lbDataEntrada;
	private JComboBox cbCliente;
	private JLabel lbCliente;
	private JDialog frame;
	private int op;
	int cont;

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
	public boolean fechar = false;


	public int getOp() {
		return op;
	}
	public JTable getTable2() {
		return table2;
	}
	public static void main(String[] args) {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeConsumo(JanelaMenuPrincipal janMenPrin) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // n√£o redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(718, 422));
		
		
		{
			btSalvar = new JButton();
			btSalvar.setForeground(new Color(30, 144, 255));
			btSalvar.setIcon(new ImageIcon("..\\2bim\\icons\\save.png"));
			btSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btSalvar);
			btSalvar.setText("Salvar");
			btSalvar.addActionListener(new MostrarListener());
			btSalvar.setBounds(359, 372, 107, 39);
		}
		{
			butCancelar = new JButton();
			butCancelar.setIcon(new ImageIcon("..\\2bim\\icons\\cancel.png"));
			butCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(butCancelar);
			butCancelar.setText("Cancelar");
			butCancelar.addActionListener(new SairListener());
			butCancelar.setBounds(226, 377, 124, 34);
		}
		{
			String colunas[] = new String[] {"ID","Nome", "Valor", "Caract"};
			modelo = new DefaultTableModel(colunas,0);
			
			ArrayList<Produto> vetProd = new ArrayList<Produto>();
			ArrayList<Servico> vetServ = new ArrayList<Servico>();

			Connection conexao = null;
			ProdutoDAO daoProd = null;
			ServicoDAO daoServ = null;

			
			try {
				conexao = ConnectionFactory.getConnection();
				daoProd = new ProdutoDAO(conexao);
				daoServ = new ServicoDAO(conexao);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				vetProd = daoProd.listaTodos();
				vetServ = daoServ.listaTodos();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < (vetProd.size()+vetServ.size()); i++) {
				if (i < vetProd.size()){
					modelo.addRow(new Object[] {vetProd.get(i).getCod(),vetProd.get(i).getNome(), vetProd.get(i).getValor(), "Produto"});	
				}else{
					modelo.addRow(new Object[] {vetServ.get(i-vetProd.size()).getCod(),vetServ.get(i-vetProd.size()).getNome(), vetServ.get(i-vetProd.size()).getValor(), "Servico"});
				}
			}
			
			table1 = new JTable(modelo);

			table1.getColumnModel().getColumn(0).setPreferredWidth(40);
			table1.getColumnModel().getColumn(0).setResizable(true);
			table1.getColumnModel().getColumn(1).setPreferredWidth(147);
			table1.getColumnModel().getColumn(1).setResizable(false);
			table1.getColumnModel().getColumn(2).setPreferredWidth(56);
			table1.getColumnModel().getColumn(2).setResizable(false);
			table1.getColumnModel().getColumn(3).setPreferredWidth(70);
			table1.getColumnModel().getColumn(3).setResizable(false);
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
			TabTodasCaract.setBounds(12, 96, 262, 257);
			
			
			
		}
		{
			String colunas[] = new String[] {"ID","Qtd", "Nome", "Valor", "Caract"};
			modelo2 = new DefaultTableModel(colunas,0);
			table2 = new JTable(modelo2);

			table2.getColumnModel().getColumn(0).setPreferredWidth(36);
			table2.getColumnModel().getColumn(0).setResizable(false);
			table2.getColumnModel().getColumn(1).setPreferredWidth(36);
			table2.getColumnModel().getColumn(1).setResizable(false);
			table2.getColumnModel().getColumn(2).setPreferredWidth(122);
			table2.getColumnModel().getColumn(2).setResizable(true);
			table2.getColumnModel().getColumn(3).setPreferredWidth(40);
			table2.getColumnModel().getColumn(3).setResizable(true);
			table2.getColumnModel().getColumn(4).setPreferredWidth(70);
			table2.getColumnModel().getColumn(4).setResizable(true);
			table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//table2.setPreferredSize(new java.awt.Dimension(196, 18));
			table2.addMouseListener(new MouseListener2());
			
			TabCaract = new JScrollPane(table2);
			panel.add(TabCaract);
			TabCaract.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			TabCaract.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			TabCaract.setBounds(440, 96, 253, 257);
		}
		{
			btAdd = new JButton();
			btAdd.setForeground(new Color(0, 128, 0));
			btAdd.setIcon(new ImageIcon("..\\2bim\\icons\\next.png"));
			btAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btAdd);
			btAdd.setText("Adicionar");
			btAdd.addActionListener(new AdicionaListener());
			btAdd.setBounds(280, 143, 143, 40);
			btAdd.setEnabled(false);
		}
		{
			tfTdCar = new JLabel();
			panel.add(tfTdCar);
			tfTdCar.setText("Produtos e ServiÁos");
			tfTdCar.setBounds(12, 70, 160, 14);
			tfTdCar.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			lcCar = new JLabel();
			panel.add(lcCar);
			lcCar.setText("ServiÁos e Produtos Consumidos");
			lcCar.setBounds(435, 70, 214, 14);
			lcCar.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			btRemove = new JButton();
			btRemove.setForeground(new Color(255, 0, 0));
			btRemove.setIcon(new ImageIcon("..\\2bim\\icons\\preview.png"));
			btRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
			panel.add(btRemove);
			btRemove.setText("Remover");
			btRemove.addActionListener(new RemoveListener());
			btRemove.setBounds(290, 192, 124, 34);
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
			
			for (int i = 0; i < vetor2.size(); i++){
				if (vetor2.get(i).getHospedado().equals("true")){
					cont++;
				}
			}
			String list[] = new String[cont];
			
			int j=0;
			for (int i = 0; i < vetor2.size(); i++){
				if (vetor2.get(i).getHospedado().equals("true")){
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
			lbDataEntrada.setText("Data de Consumo");
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
		
		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Registro de consumo - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.CENTER, panel);
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		janMenPrin.frameConteudo.setVisible(true); // torna a janela vis√≠vel.ss	
		
		// programa
		if (cont == 0){
			//JOptionPane.showMessageDialog(frame, "Nenhum Cliente Hospedado", "Hotel Vazio", JOptionPane.ERROR_MESSAGE);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ArrayList<Hospedagem> vetHosp = new ArrayList<Hospedagem>();
			HospedagemDAO daoHosp = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoHosp = new HospedagemDAO(conexao);
				vetHosp = daoHosp.listaPorCodCli(vetCli.get(0).getCod());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Consumo consu = new Consumo();
			consu.setCod_conta(vetHosp.get(0).getCod());
			
			Calendar calen = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			try {
				calen.setTime(f.parse(tfDataEntrada.getText()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			consu.setDataCons(calen);
			//consu.set
			int cont = table2.getRowCount();
			ConsumoDAO daoCons = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoCons = new ConsumoDAO(conexao);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			double valorTotal=0;
			for(int i=0; i<cont; i++){
				String tipo = (String)table2.getValueAt(i, 4);
				if (tipo.equals("Produto")){
					consu.setCod_produto(Integer.parseInt((String)table2.getValueAt(i, 0)));
					consu.setQuantidade(Integer.parseInt((String)table2.getValueAt(i, 1)));	
					consu.setValorUnit(Double.parseDouble((String)table2.getValueAt(i, 3)));
					valorTotal = valorTotal + (consu.getQuantidade()*consu.getValorUnit());
				}else{
					consu.setCod_servico(Integer.parseInt((String)table2.getValueAt(i, 0)));
					consu.setQuantidade(Integer.parseInt((String)table2.getValueAt(i, 1)));	
					consu.setValorUnit(Double.parseDouble((String)table2.getValueAt(i, 3)));
					valorTotal = valorTotal + (consu.getQuantidade()*consu.getValorUnit());
				}
				System.out.println(consu.getCod_produto()+"e"+consu.getCod_servico());
				daoCons.adiciona(consu);
				consu.setCod_produto(0);
				consu.setCod_servico(0);
				
			}
			ContaDAO daoConta = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoConta = new ContaDAO(conexao);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			daoConta.addValor(valorTotal, vetHosp.get(0).getCod());
			fechar = true;
		}
	}
	private class AdicionaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int l = table1.getSelectedRow();
			String aux1 = "" + modelo.getValueAt(l, 0);
			String aux2 = "" + modelo.getValueAt(l, 1);
			String aux6 = "" + modelo.getValueAt(l, 2);
			String aux4 = "" + modelo.getValueAt(l, 3);
			
			JanelaDeQuantidade3 JanQtd = new JanelaDeQuantidade3();
			if (JanQtd.getOp() == 1){
				String aux3 = JanQtd.getTfQtd().getText();
				modelo2.addRow(new Object[] {aux1, aux3, aux2, aux6, aux4});
			}
			//modelo.removeRow(l);
			btAdd.setEnabled(false);
			
		}
	}
	private class RemoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int l = table2.getSelectedRow();
			
//			String aux1 = "" + modelo2.getValueAt(l, 0);
//			String aux2 = "" + modelo2.getValueAt(l, 1);
//			modelo.addRow(new Object[] {aux1, aux2});
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