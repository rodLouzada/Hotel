import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;



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
public class JanelaDeExcluirCliente implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JButton btExcluir;
	private JTextField tfCodCli;
	private JLabel lbCodCli;



	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;


	

	public static void main(String[] args) {
		new JanelaDeExcluirCliente();
	}

	public JanelaDeExcluirCliente() {

		String colunas[] = new String[] {"ID", "Nome", "CPF"};
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
		
		for (int i = 0; i < vetor.size(); i++) {
			modelo.addRow(new Object[] {vetor.get(i).getCod(), vetor.get(i).getNome(), vetor.get(i).getCpf()});
		}

		table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.addMouseListener(new MouseListener());


		// trocar as colunas de posiï¿½ï¿½o
		// Redimensionamento automï¿½tico
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new java.awt.Dimension(373, 291));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(4, 43, 369, 248);
			
		}
		{
			lbCodCli = new JLabel();
			panelTable.add(lbCodCli);
			lbCodCli.setText("ID do Cliente:");
			lbCodCli.setBounds(58, 15, 103, 16);
		}
		{
			tfCodCli = new JTextField();
			tfCodCli.setEditable(false);
			panelTable.add(tfCodCli);
			tfCodCli.setBounds(155, 12, 57, 23);
		}
		{
			btExcluir = new JButton();
			panelTable.add(btExcluir);
			btExcluir.setText("Excluir");
			btExcluir.setBounds(246, 12, 86, 23);
			btExcluir.addActionListener(new ExcluirListener());
		}



		buttonOk = new JButton("Cancelar");
		buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.add(buttonOk);
		buttonOk.addActionListener(new OkListener());
		

		frame = new JDialog();
		frame.setTitle("Excluir Clientes - Hotel");
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
	private class ExcluirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!tfCodCli.getText().isEmpty() && tfCodCli != null){
				Connection conexao;
				int codigo = Integer.parseInt(tfCodCli.getText());
				try {
					conexao = ConnectionFactory.getConnection();
					ClienteDAO dao = new ClienteDAO(conexao);
					dao.excluir(codigo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
				frame.dispose();
				JanelaDeExcluirCliente j10 = new JanelaDeExcluirCliente();
			}else{
				JOptionPane.showMessageDialog(frame, "Selecione um cliente para excluir", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class MouseListener implements java.awt.event.MouseListener {
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = ""+table.getValueAt(r, 0);
			tfCodCli.setText(c);
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
