import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Font;

//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRResultSetDataSource;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.view.JasperViewer;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class JanelaDeRelatorioHospedagem implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JLabel lbCodCli;

	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;
	private JButton btImp;
	private JComboBox cbCli;

	public boolean fechar = false;
	
	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	buttonOk.doClick();
		        } 
			  else if (e.getKeyCode()== 27){
				fechar = true;
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
	public static void main(String[] args) {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeRelatorioHospedagem(JanelaMenuPrincipal janMenPrin) {

		String colunas[] = new String[] { "Cliente", "Data Entrada",
				"Data Saída", "Check Out" };
		modelo = new DefaultTableModel(colunas, 0);

		table = new JTable(modelo);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new Dimension(800, 600));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(12, 69, 778, 358);

		}
		{
			lbCodCli = new JLabel();
			panelTable.add(lbCodCli);
			lbCodCli.setText("Relatório de:");
			lbCodCli.setBounds(12, 12, 103, 16);
			lbCodCli.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		{
			ComboBoxModel cbCliModel = new DefaultComboBoxModel(new String[] {
					"Todas as Hospedagens", "Hospedagens Pendentes",
					"Hospedagens Finalizadas" });
			cbCli = new JComboBox();
			panelTable.add(cbCli);
			cbCli.setModel(cbCliModel);
			cbCli.setBounds(12, 34, 298, 23);
			cbCli.addActionListener(new comboListener());
		}

		// buttonOk.addActionListener(this);

		panelButton = new JPanel();
		panelButton.setPreferredSize(new java.awt.Dimension(201, 45));
		panelButton.setLayout(null);
		panelButton.setSize(478, 45);
		{
			buttonOk = new JButton("OK");
			buttonOk.setForeground(new Color(0, 128, 0));
			buttonOk.setIcon(new ImageIcon("..\\2bim\\icons\\check.png"));
			buttonOk.setFont(new java.awt.Font("Tahoma",1,13));
			
			panelButton.add(buttonOk);
			buttonOk.setBounds(99, 4, 107, 34);
			buttonOk.addActionListener(new OkListener());
			
		}
		{
			btImp = new JButton();
			panelButton.add(btImp);
			btImp.setText("Gerar PDF");
			btImp.setIcon(new ImageIcon("..\\2bim\\icons\\pdf.png"));
			btImp.setFont(new java.awt.Font("Tahoma",1,13));
			
			btImp.setBounds(259, 4, 118, 34);
			
		}

		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Relatório de Hospedagem - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.SOUTH, panelButton);
		
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
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

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (cbCli.getSelectedIndex() == 0) {
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				HospedagemDAO daoHosp = null;
				ArrayList<Hospedagem> vetHosp = new ArrayList<Hospedagem>();
				try {
					Connection conexao = ConnectionFactory.getConnection();
					daoHosp = new HospedagemDAO(conexao);
					vetHosp.addAll(daoHosp.listaTodos());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String disp;
				for (int i = 0; i < vetHosp.size(); i++) {
					if (vetHosp.get(i).getCheckout().equals("true")) {
						disp = "Sim";
					} else {
						disp = "Não";
					}
					Cliente cli = new Cliente();
					ClienteDAO daoCli = null;
					try {
						daoCli = new ClienteDAO(ConnectionFactory
								.getConnection());
						cli = daoCli.getPorCod(vetHosp.get(i).getCod_cliente());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					SimpleDateFormat formatIso = new SimpleDateFormat(
							"yyyy-MM-dd");
					SimpleDateFormat formatBra = new SimpleDateFormat(
							"dd/MM/yyyy");
					String dataE = null;
					String dataS = null;
					{
						Date d = new java.sql.Date(vetHosp.get(i)
								.getDataEntrada().getTimeInMillis());
						try {
							java.util.Date date = formatIso.parse(d + "");
							dataE = formatBra.format(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					{
						Date d2 = new java.sql.Date(vetHosp.get(i)
								.getDataSaida().getTimeInMillis());

						try {
							java.util.Date date2 = formatIso.parse(d2 + "");
							dataS = formatBra.format(date2);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if (vetHosp.get(i).getCheckout().equals("false")) {
						dataS = "----";
					}

					modelo.addRow(new Object[] { cli.getNome(), dataE, dataS,
							disp });
				}
			}
			if (cbCli.getSelectedIndex() == 1) {
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				HospedagemDAO daoHosp = null;
				ArrayList<Hospedagem> vetHosp = new ArrayList<Hospedagem>();
				try {
					Connection conexao = ConnectionFactory.getConnection();
					daoHosp = new HospedagemDAO(conexao);
					vetHosp.addAll(daoHosp.listaTodos());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String disp;
				for (int i = 0; i < vetHosp.size(); i++) {
					if (vetHosp.get(i).getCheckout().equals("true")) {
						disp = "Sim";
					} else {
						disp = "Não";
					}
					Cliente cli = new Cliente();
					ClienteDAO daoCli = null;
					try {
						daoCli = new ClienteDAO(ConnectionFactory
								.getConnection());
						cli = daoCli.getPorCod(vetHosp.get(i).getCod_cliente());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					SimpleDateFormat formatIso = new SimpleDateFormat(
							"yyyy-MM-dd");
					SimpleDateFormat formatBra = new SimpleDateFormat(
							"dd/MM/yyyy");
					String dataE = null;
					String dataS = null;
					{
						Date d = new java.sql.Date(vetHosp.get(i)
								.getDataEntrada().getTimeInMillis());
						try {
							java.util.Date date = formatIso.parse(d + "");
							dataE = formatBra.format(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					{
						Date d2 = new java.sql.Date(vetHosp.get(i)
								.getDataSaida().getTimeInMillis());

						try {
							java.util.Date date2 = formatIso.parse(d2 + "");
							dataS = formatBra.format(date2);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if (vetHosp.get(i).getCheckout().equals("false")) {
						dataS = "----";
					}
					if (vetHosp.get(i).getCheckout().equals("false")) {
						modelo.addRow(new Object[] { cli.getNome(), dataE,
								dataS, disp });
					}

				}
			}
			if (cbCli.getSelectedIndex() == 2) {
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				for (int i = 0; i < modelo.getRowCount(); i++) {
					modelo.removeRow(i);
				}
				HospedagemDAO daoHosp = null;
				ArrayList<Hospedagem> vetHosp = new ArrayList<Hospedagem>();
				try {
					Connection conexao = ConnectionFactory.getConnection();
					daoHosp = new HospedagemDAO(conexao);
					vetHosp.addAll(daoHosp.listaTodos());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String disp;
				for (int i = 0; i < vetHosp.size(); i++) {
					if (vetHosp.get(i).getCheckout().equals("true")) {
						disp = "Sim";
					} else {
						disp = "Não";
					}
					Cliente cli = new Cliente();
					ClienteDAO daoCli = null;
					try {
						daoCli = new ClienteDAO(ConnectionFactory
								.getConnection());
						cli = daoCli.getPorCod(vetHosp.get(i).getCod_cliente());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					SimpleDateFormat formatIso = new SimpleDateFormat(
							"yyyy-MM-dd");
					SimpleDateFormat formatBra = new SimpleDateFormat(
							"dd/MM/yyyy");
					String dataE = null;
					String dataS = null;
					{
						Date d = new java.sql.Date(vetHosp.get(i)
								.getDataEntrada().getTimeInMillis());
						try {
							java.util.Date date = formatIso.parse(d + "");
							dataE = formatBra.format(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					{
						Date d2 = new java.sql.Date(vetHosp.get(i)
								.getDataSaida().getTimeInMillis());

						try {
							java.util.Date date2 = formatIso.parse(d2 + "");
							dataS = formatBra.format(date2);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if (vetHosp.get(i).getCheckout().equals("false")) {
						dataS = "----";
					}
					if (vetHosp.get(i).getCheckout().equals("true")) {
						modelo.addRow(new Object[] { cli.getNome(), dataE,
								dataS, disp });
					}

				}
			}
		}
	}

}
