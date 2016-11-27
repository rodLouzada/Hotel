import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;



//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRResultSetDataSource;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.view.JasperViewer;


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
public class JanelaDeRelatorioQuartos implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JLabel lbCodCli;



	private JScrollPane scrollTable, scrollTable2;

	private JTable table, table2;
	private DefaultTableModel modelo, modelo2;
	
	private JButton buttonOk;
	private JLabel jLabel1;
	private JLabel lbQua;
	private JComboBox cbOpcoes;
	private JButton btImp;

	public boolean fechar = false;
	public static void main(String[] args) {
	}

	public JanelaDeRelatorioQuartos(JanelaMenuPrincipal janMenPrin) {

		String colunas[] = new String[] {"Número", "Disponibilidade", "Valor Diaria"};
		modelo = new DefaultTableModel(colunas,0);

		
		

		table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setResizable(false);
		
		table.addMouseListener(new TableListener());
		
		String colunas2[] = new String[] {"Quarto", "Nome", "Qtd"};
		modelo2 = new DefaultTableModel(colunas2,0);

		
		

		table2 = new JTable(modelo2);

		table2.getColumnModel().getColumn(0).setPreferredWidth(70);
		table2.getColumnModel().getColumn(0).setResizable(true);
		table2.getColumnModel().getColumn(1).setPreferredWidth(120);
		table2.getColumnModel().getColumn(1).setResizable(false);
		table2.getColumnModel().getColumn(2).setPreferredWidth(50);
		table2.getColumnModel().getColumn(2).setResizable(false);


		// trocar as colunas de posiï¿½ï¿½o
		// Redimensionamento automï¿½tico
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new java.awt.Dimension(572, 366));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(12, 106, 259, 248);
			
		}
		{
			scrollTable2 = new JScrollPane(table2);
			panelTable.add(scrollTable2);
			scrollTable2
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable2
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable2.setBounds(317, 106, 255, 248);
			
		}
		{
			lbCodCli = new JLabel();
			panelTable.add(lbCodCli);
			lbCodCli.setText("Relatório de:");
			lbCodCli.setBounds(12, 12, 103, 16);
			lbCodCli.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			try {
				MaskFormatter maskCPF = new MaskFormatter("******");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			{
				ComboBoxModel cbOpcoesModel = 
					new DefaultComboBoxModel(
							new String[] { "Todos os Quartos", "Quartos Vagos", "Quartos Ocupados"});
				cbOpcoes = new JComboBox();
				panelTable.add(cbOpcoes);
				cbOpcoes.setModel(cbOpcoesModel);
				cbOpcoes.setBounds(12, 34, 173, 23);
				cbOpcoes.addActionListener(new comboListener());
			}
			{
				lbQua = new JLabel();
				panelTable.add(lbQua);
				lbQua.setText("Quartos");
				lbQua.setBounds(12, 79, 65, 16);
				lbQua.setFont(new java.awt.Font("Tahoma",1,12));
			}
			{
				jLabel1 = new JLabel();
				panelTable.add(jLabel1);
				jLabel1.setText("Características do Quartos");
				jLabel1.setFont(new java.awt.Font("Tahoma",1,12));
				jLabel1.setBounds(317, 79, 232, 16);
			}
		}

		//buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.setPreferredSize(new java.awt.Dimension(201, 33));
		panelButton.setLayout(null);
		panelButton.setSize(592, 33);
		{
			buttonOk = new JButton("OK");
			panelButton.add(buttonOk);
			buttonOk.setBounds(162, 4, 118, 23);
			buttonOk.addActionListener(new OkListener());
		}
		{
			btImp = new JButton();
			panelButton.add(btImp);
			btImp.setText("Gerar PDF");
			btImp.setBounds(314, 4, 118, 23);
		}

		janMenPrin.frameConteudo.setTitle("Relatório de Quartos - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.SOUTH, panelButton);
		
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Sair do
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visÃ­vel.ss	
		}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fechar = true;
		
	}

	private class OkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fechar = true;
		}
	}
	public class comboListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (cbOpcoes.getSelectedIndex() == 0){
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				QuartoDAO daoQua = null;
				ArrayList<Quarto> vetQua = new ArrayList<Quarto>();
				try {
					Connection conexao = ConnectionFactory.getConnection();
					daoQua = new QuartoDAO(conexao);
					vetQua.addAll(daoQua.listaTodos());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String disp;
				for (int i=0; i<vetQua.size(); i++){
					if (vetQua.get(i).getOcupado().equals("true")){
						disp = "Ocupado";
					}else{
						disp = "Livre";
					}
					modelo.addRow(new Object[] {vetQua.get(i).getNumero(), disp, vetQua.get(i).getValorDiaria()});
				}
			}
			if (cbOpcoes.getSelectedIndex() == 1){
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				QuartoDAO daoQua = null;
				ArrayList<Quarto> vetQua = new ArrayList<Quarto>();
				try {
					Connection conexao = ConnectionFactory.getConnection();
					daoQua = new QuartoDAO(conexao);
					vetQua.addAll(daoQua.listaTodos());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String disp;
				for (int i=0; i<vetQua.size(); i++){
					if (vetQua.get(i).getOcupado().equals("true")){
						disp = "Ocupado";
					}else{
						modelo.addRow(new Object[] {vetQua.get(i).getNumero(), "Livre", vetQua.get(i).getValorDiaria()});
					}
					
				}
			}
			if (cbOpcoes.getSelectedIndex() == 2){
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				for(int i=0; i<modelo.getRowCount(); i++){
					modelo.removeRow(i);
				}
				QuartoDAO daoQua = null;
				ArrayList<Quarto> vetQua = new ArrayList<Quarto>();
				try {
					Connection conexao = ConnectionFactory.getConnection();
					daoQua = new QuartoDAO(conexao);
					vetQua.addAll(daoQua.listaTodos());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String disp;
				for (int i=0; i<vetQua.size(); i++){
					if (vetQua.get(i).getOcupado().equals("false")){
						
					}else{
						modelo.addRow(new Object[] {vetQua.get(i).getNumero(), "Ocupado", vetQua.get(i).getValorDiaria()});
					}
					
				}
			}
		}
	}
	private class TableListener implements java.awt.event.MouseListener {
		public void mouseClicked(MouseEvent e) {
			for(int i=0; i<modelo2.getRowCount(); i++){
				modelo2.removeRow(i);
			}
			for(int i=0; i<modelo2.getRowCount(); i++){
				modelo2.removeRow(i);
			}
			for(int i=0; i<modelo2.getRowCount(); i++){
				modelo2.removeRow(i);
			}
			for(int i=0; i<modelo2.getRowCount(); i++){
				modelo2.removeRow(i);
			}
			for(int i=0; i<modelo2.getRowCount(); i++){
				modelo2.removeRow(i);
			}
			for(int i=0; i<modelo2.getRowCount(); i++){
				modelo2.removeRow(i);
			}
			int r = table.getSelectedRow();
			int num = Integer.parseInt(""+table.getValueAt(r, 0));
			
			QuartoDAO daoQua = null;
			int codQua=0;
			try {
				daoQua = new QuartoDAO(ConnectionFactory.getConnection());
				codQua = daoQua.getCodPorNum(""+num);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			ArrayList<Quarto_Caract> vetQuaCa = new ArrayList<Quarto_Caract>();
			Quarto_CaractDAO daoQuaCa = null;
			try {
				daoQuaCa = new Quarto_CaractDAO(ConnectionFactory.getConnection());
				vetQuaCa = daoQuaCa.listaPorCodQua(codQua);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			int qtd[] = new int[vetQuaCa.size()];
			for (int i=0; i<vetQuaCa.size(); i++){
				qtd[i] = vetQuaCa.get(i).getQuantidade();
			}
			
			String nome[] = new String[vetQuaCa.size()];
			CaractDAO daoCa = null;
			try {
				daoCa = new CaractDAO(ConnectionFactory.getConnection());
				for(int i=0; i<vetQuaCa.size(); i++){
					nome[i] = daoCa.getNomePorCod(vetQuaCa.get(i).getCaract_cod());
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			for (int i=0; i<vetQuaCa.size(); i++){
				modelo2.addRow(new Object[] {num, nome[i], qtd[i]});
			}
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
}
