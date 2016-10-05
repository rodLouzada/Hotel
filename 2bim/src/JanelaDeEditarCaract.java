import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class JanelaDeEditarCaract implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JButton btEditar;
	private JTextField tfCodCaract;
	private JLabel lbCodCaract;


	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;

	

	public static void main(String[] args) {
		new JanelaDeEditarCaract();
	}

	public JanelaDeEditarCaract() {

		String colunas[] = new String[] {"ID", "Nome", "Descrição" };
		modelo = new DefaultTableModel(colunas,0);

		ArrayList<Caract> vetor = new ArrayList<Caract>();

		Connection conexao = null;
		CaractDAO daoCaract = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoCaract = new CaractDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoCaract.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < vetor.size(); i++) {
			modelo.addRow(new Object[] {vetor.get(i).getCod(),vetor.get(i).getNome(), vetor.get(i).getDescricao()});
		}

		table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(182);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.addMouseListener(new MouseListener());

		// trocar as colunas de posiï¿½ï¿½o
		// Redimensionamento automï¿½tico
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new java.awt.Dimension(410, 299));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(4, 43, 400, 250);
			
		}
		{
			lbCodCaract = new JLabel();
			panelTable.add(lbCodCaract);
			lbCodCaract.setText("ID da Característica:");
			lbCodCaract.setBounds(22, 12, 134, 16);
		}
		{
			tfCodCaract = new JTextField();
			tfCodCaract.setEditable(false);
			panelTable.add(tfCodCaract);
			tfCodCaract.setBounds(174, 9, 57, 23);
		}
		{
			btEditar = new JButton();
			panelTable.add(btEditar);
			btEditar.setText("Editar");
			btEditar.setBounds(278, 9, 86, 23);
			btEditar.addActionListener(new ExcluirListener());
		}



		buttonOk = new JButton("Cancelar");
		buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.add(buttonOk);
		buttonOk.addActionListener(new OkListener());
		

		frame = new JDialog();
		frame.setTitle("Editar Características - Hotel");
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
			if(!tfCodCaract.getText().isEmpty() && tfCodCaract != null){
				int cod = Integer.parseInt(tfCodCaract.getText());
				JanelaDeEditarCaract2 janEdiCaract = new JanelaDeEditarCaract2(cod);
				frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Selecione uma característica para editar", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class MouseListener implements java.awt.event.MouseListener {
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = ""+table.getValueAt(r, 0);
			tfCodCaract.setText(c);
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
