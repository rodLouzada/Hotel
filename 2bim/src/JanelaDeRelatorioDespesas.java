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
import java.text.SimpleDateFormat;
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
public class JanelaDeRelatorioDespesas implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JLabel lbCodCli;



	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;
	private JTextField tfTotal;
	private JLabel lbTT;
	private JButton btImp;
	private JComboBox cbCli;

	public boolean fechar = false;
	public static void main(String[] args) {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeRelatorioDespesas(JanelaMenuPrincipal janMenPrin) {

		String colunas[] = new String[] {"Nome", "Tipo", "Data Consumo", "Qtd", "Valor Unitário", "Valor"};
		modelo = new DefaultTableModel(colunas,0);

		ArrayList<Cliente> vetor = new ArrayList<Cliente>();

		Connection conexao = null;
		ClienteDAO daoCliente = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoCliente = new ClienteDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoCliente.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int c=0;
		for (int i=0; i<vetor.size(); i++){
			if (vetor.get(i).getHospedado().equals("true"))
			c++;
		}
		String vetCli[] = new String[c];
		
		int j=0;
		for (int i=0; i<vetor.size(); i++){
			if (vetor.get(i).getHospedado().equals("true")){
				vetCli[j] = vetor.get(i).getNome();
				j++;
			}
			
		}
		

		table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setResizable(false);
		


		// trocar as colunas de posiï¿½ï¿½o
		// Redimensionamento automï¿½tico
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new Dimension(650, 329));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(12, 69, 592, 248);
			
		}
		{
			lbCodCli = new JLabel();
			panelTable.add(lbCodCli);
			lbCodCli.setText("Cliente:");
			lbCodCli.setBounds(12, 12, 103, 16);
			lbCodCli.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			ComboBoxModel cbCliModel = 
				new DefaultComboBoxModel(vetCli);
			cbCli = new JComboBox();
			panelTable.add(cbCli);
			cbCli.setModel(cbCliModel);
			cbCli.setBounds(12, 34, 223, 23);
			cbCli.addActionListener(new comboListener());
		}
		{
			lbTT = new JLabel();
			panelTable.add(lbTT);
			lbTT.setText("Total:    R$");
			lbTT.setBounds(350, 36, 77, 16);
			lbTT.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			try {
				MaskFormatter maskCPF = new MaskFormatter("******");
				tfTotal = new JFormattedTextField(maskCPF);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tfTotal.setEditable(false);
			panelTable.add(tfTotal);
			tfTotal.setBounds(428, 34, 73, 23);
		}

		//buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.setPreferredSize(new java.awt.Dimension(201, 33));
		panelButton.setLayout(null);
		panelButton.setSize(600, 33);
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

		janMenPrin.frameConteudo.setTitle("Relatório de Consumo - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.SOUTH, panelButton);
		
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Sair do
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visÃ­vel.ss	
		}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
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
			double total = 0;
			for(int i=0; i<modelo.getRowCount(); i++){
				modelo.removeRow(i);
			}
			for(int i=0; i<modelo.getRowCount(); i++){
				modelo.removeRow(i);
			}
			
			String nomeCli = cbCli.getSelectedItem().toString();
			ClienteDAO daoCli = null;
			Cliente cli = new Cliente();
			try {
				Connection conexao = ConnectionFactory.getConnection();
				daoCli = new ClienteDAO(conexao);
				cli = daoCli.getPorNome(nomeCli);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			HospedagemDAO daoHosp = null;
			Hospedagem hosp = new Hospedagem();
			try {
				Connection conexao = ConnectionFactory.getConnection();
				daoHosp = new HospedagemDAO(conexao);
				hosp = daoHosp.getPorCodCli(cli.getCod());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConsumoDAO daoCons = null;
			ArrayList<Consumo> vetCons = new ArrayList<Consumo>();
			try {
				Connection conexao = ConnectionFactory.getConnection();
				daoCons = new ConsumoDAO(conexao);
				vetCons.addAll(daoCons.listaPorCodConta(hosp.getCod()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ServicoDAO daoServ = new ServicoDAO(ConnectionFactory.getConnection());
				ProdutoDAO daoPro = new ProdutoDAO(ConnectionFactory.getConnection());
				for (int i=0; i<vetCons.size(); i++){
					Date d = new java.sql.Date(vetCons.get(1).getDataCons().getTimeInMillis());
					SimpleDateFormat formatIso = new SimpleDateFormat(
					"yyyy-MM-dd");
					SimpleDateFormat formatBra = new SimpleDateFormat(
					"dd/MM/yyyy");
					String dataE = null;
					try {
						java.util.Date date = formatIso.parse(d + "");
						dataE = formatBra.format(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if (vetCons.get(i).getCod_produto() == 0){
						modelo.addRow(new Object[] {daoServ.getNomePorCod(vetCons.get(i).getCod_servico()), "Serviço", dataE, vetCons.get(i).getQuantidade(), vetCons.get(i).getValorUnit(), vetCons.get(i).getValorTotal()});
						total = total + vetCons.get(i).getValorTotal();
					}else{
						modelo.addRow(new Object[] {daoPro.getNomePorCod(vetCons.get(i).getCod_produto()), "Produto", dataE, vetCons.get(i).getQuantidade(), vetCons.get(i).getValorUnit(), vetCons.get(i).getValorTotal()});
						total = total + vetCons.get(i).getValorTotal();
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tfTotal.setText(""+total);
			table = new JTable(modelo);
		}
	}
	
	
}
