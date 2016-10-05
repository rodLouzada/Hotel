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



public class JanelaDeExcluirServico implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JButton btExcluir;
	private JTextField tfCod;
	private JLabel lbCod;


	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;

	

	public static void main(String[] args) {
		new JanelaDeExcluirServico();
	}

	public JanelaDeExcluirServico() {

		String colunas[] = new String[] {"ID", "Nome", "Valor" };
		modelo = new DefaultTableModel(colunas,0);

		ArrayList<Servico> vetor = new ArrayList<Servico>();

		Connection conexao = null;
		ServicoDAO daoServico = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoServico = new ServicoDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoServico.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < vetor.size(); i++) {
			modelo.addRow(new Object[] {vetor.get(i).getCod(),vetor.get(i).getNome(), vetor.get(i).getValor()});
		}

		table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(182);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.addMouseListener(new MouseListener());

		// trocar as colunas de posiï¿½ï¿½o
		// Redimensionamento automï¿½tico
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new java.awt.Dimension(353, 299));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(4, 43, 349, 250);
			
		}
		{
			lbCod = new JLabel();
			panelTable.add(lbCod);
			lbCod.setText("ID do Produto:");
			lbCod.setBounds(49, 12, 91, 16);
		}
		{
			tfCod = new JTextField();
			tfCod.setEditable(false);
			panelTable.add(tfCod);
			tfCod.setBounds(152, 9, 57, 23);
		}
		{
			btExcluir = new JButton();
			panelTable.add(btExcluir);
			btExcluir.setText("Excluir");
			btExcluir.setBounds(234, 9, 86, 23);
			btExcluir.addActionListener(new ExcluirListener());
		}



		buttonOk = new JButton("Cancelar");
		buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.add(buttonOk);
		buttonOk.addActionListener(new OkListener());
		

		frame = new JDialog();
		frame.setTitle("Excluir Serviços - Hotel");
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
			if(!tfCod.getText().isEmpty() && tfCod != null){
				Connection conexao;
				int codigo = Integer.parseInt(tfCod.getText());
				try {
					conexao = ConnectionFactory.getConnection();
					ServicoDAO dao = new ServicoDAO(conexao);
					dao.excluir(codigo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");
				frame.dispose();
				JanelaDeExcluirServico j10 = new JanelaDeExcluirServico();
			}else{
				JOptionPane.showMessageDialog(frame, "Selecione um serviço para excluir", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class MouseListener implements java.awt.event.MouseListener {
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = ""+table.getValueAt(r, 0);
			tfCod.setText(c);
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