import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

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
	private JanelaMenuPrincipal jMenuP;

	public boolean fechar = false;

	public static void main(String[] args) {
		// new JanelaDeExcluirCliente();
	}
	
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

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeExcluirCliente(JanelaMenuPrincipal janMenPrin) {

		jMenuP = janMenPrin;
		String colunas[] = new String[] { "ID", "Nome", "CPF" };
		modelo = new DefaultTableModel(colunas, 0);

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
			modelo.addRow(new Object[] { vetor.get(i).getCod(), vetor.get(i).getNome(), vetor.get(i).getCpf() });
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
		panelTable.setPreferredSize(new Dimension(395, 320));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(12, 59, 369, 248);

		}
		{
			lbCodCli = new JLabel();
			panelTable.add(lbCodCli);
			lbCodCli.setText("ID do Cliente:");
			lbCodCli.setBounds(12, 26, 103, 16);
		}
		{
			tfCodCli = new JTextField();
			tfCodCli.setEditable(false);
			panelTable.add(tfCodCli);
			tfCodCli.setBounds(109, 23, 57, 23);
		}
		{
			btExcluir = new JButton();
			btExcluir.setForeground(new Color(255, 0, 0));
			btExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
			btExcluir.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\delete.png"));
			panelTable.add(btExcluir);
			btExcluir.setText("Excluir");
			btExcluir.setBounds(276, 15, 107, 39);
			btExcluir.addActionListener(new ExcluirListener());
		}

		buttonOk = new JButton("Cancelar");
		buttonOk.addActionListener(this);

		panelButton = new JPanel();
		panelButton.add(buttonOk);
		buttonOk.addActionListener(new OkListener());

		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Excluir Clientes - Hotel");

		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.SOUTH, panelButton);

		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair
																					// do
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

	private class ExcluirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "Deseja realmente excluir o cliente?";
			String title = "Confirmação";
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {

				if (!tfCodCli.getText().isEmpty() && tfCodCli != null) {
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
					fechar = true;
					// jMenuP.frameConteudo.dispose();
					// JanelaDeExcluirCliente j10 = new
					// JanelaDeExcluirCliente(jMenuP);
				} else {
					JOptionPane.showMessageDialog(jMenuP.frameConteudo, "Selecione um cliente para excluir", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private class MouseListener implements java.awt.event.MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = "" + table.getValueAt(r, 0);
			tfCodCli.setText(c);
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
}
