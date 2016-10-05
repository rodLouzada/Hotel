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

	public static void main(String[] args) {
		new JanelaDeRelatorioHospedagem();
	}

	public JanelaDeRelatorioHospedagem() {

		String colunas[] = new String[] { "Cliente", "Data Entrada",
				"Data Saída", "Check Out" };
		modelo = new DefaultTableModel(colunas, 0);

		table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);

		// trocar as colunas de posiï¿½ï¿½o
		// Redimensionamento automï¿½tico
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new java.awt.Dimension(478, 317));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(12, 69, 466, 248);

		}
		{
			lbCodCli = new JLabel();
			panelTable.add(lbCodCli);
			lbCodCli.setText("Relatório de:");
			lbCodCli.setBounds(12, 12, 103, 16);
			lbCodCli.setFont(new java.awt.Font("Tahoma", 1, 12));
		}
		{
			ComboBoxModel cbCliModel = new DefaultComboBoxModel(new String[] {
					"Todas as Hospedagens", "Hospedagens Pendentes",
					"Hospedagens Finalizadas" });
			cbCli = new JComboBox();
			panelTable.add(cbCli);
			cbCli.setModel(cbCliModel);
			cbCli.setBounds(12, 34, 223, 23);
			cbCli.addActionListener(new comboListener());
		}

		// buttonOk.addActionListener(this);

		panelButton = new JPanel();
		panelButton.setPreferredSize(new java.awt.Dimension(201, 33));
		panelButton.setLayout(null);
		panelButton.setSize(478, 33);
		{
			buttonOk = new JButton("OK");
			panelButton.add(buttonOk);
			buttonOk.setBounds(99, 4, 118, 23);
			buttonOk.addActionListener(new OkListener());
		}
		{
			btImp = new JButton();
			panelButton.add(btImp);
			btImp.setText("Gerar PDF");
			btImp.setBounds(259, 4, 118, 23);
		}

		frame = new JDialog();
		frame.setTitle("Relatório de Hospedagem - Hotel");
		frame.setModal(true);
		// frame.getContentPane().add(BorderLayout.EAST, endereco);
		frame.getContentPane().add(BorderLayout.NORTH, panelTable);
		frame.getContentPane().add(BorderLayout.SOUTH, panelButton);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame.dispose();

	}

	private class OkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();

		}
	}

	public class comboListener implements ActionListener {

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
