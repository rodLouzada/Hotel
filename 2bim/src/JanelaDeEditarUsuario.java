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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;



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
public class JanelaDeEditarUsuario implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JButton btEditar;
	private JTextField tfCodUsu;
	private JLabel lbCodUsu;



	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;
	
	public boolean fechar = false;
	private JButton btnExcluir;
	private JLabel lblPesquisar;
	private JTextField textField;
	private JButton btnBuscar;
	
	public static void main(String[] args) {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeEditarUsuario(JanelaMenuPrincipal janMenPrin) {

		String colunas[] = new String[] {"ID", "Nome", "Login"};
		modelo = new DefaultTableModel(colunas,0);

		ArrayList<Usuario> vetor = new ArrayList<Usuario>();

		Connection conexao = null;
		UsuarioDAO daoUsuario = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoUsuario = new UsuarioDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoUsuario.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < vetor.size(); i++) {
			modelo.addRow(new Object[] {vetor.get(i).getCod(), vetor.get(i).getNome(), vetor.get(i).getLogin()});
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
		panelTable.setPreferredSize(new Dimension(411, 325));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(4, 75, 407, 248);
			
		}
		{
			lbCodUsu = new JLabel();
			panelTable.add(lbCodUsu);
			lbCodUsu.setText("ID do Usuário:");
			lbCodUsu.setBounds(14, 47, 103, 16);
		}
		{
			tfCodUsu = new JTextField();
			tfCodUsu.setEditable(false);
			panelTable.add(tfCodUsu);
			tfCodUsu.setBounds(93, 44, 57, 23);
		}
		{
			btEditar = new JButton();
			panelTable.add(btEditar);
			btEditar.setText("Editar");
			btEditar.setBounds(219, 44, 86, 23);
			btEditar.addActionListener(new ExcluirListener());
		}



		buttonOk = new JButton("Cancelar");
		buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.add(buttonOk);
		buttonOk.addActionListener(new OkListener());
		

		janMenPrin.frameConteudo.setTitle("Editar Usuários - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		{
			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = "Deseja realmente excluir o usuario?";
					String title = "Confirmação";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						if (!tfCodUsu.getText().isEmpty() && tfCodUsu != null) {
							Connection conexao;
							int codigo = Integer.parseInt(tfCodUsu.getText());
							try {
								conexao = ConnectionFactory.getConnection();
								UsuarioDAO dao = new UsuarioDAO(conexao);
								dao.excluir(codigo);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
							fechar = true;
							//JanelaDeExcluirUsuario j = new JanelaDeExcluirUsuario(jM);
						} else {
							JOptionPane.showMessageDialog(frame, "Selecione um usuário para excluir", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			btnExcluir.setBounds(314, 43, 89, 23);
			panelTable.add(btnExcluir);
		}
		{
			lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(14, 11, 64, 14);
			panelTable.add(lblPesquisar);
		}
		{
			textField = new JTextField();
			textField.setBounds(72, 8, 233, 20);
			panelTable.add(textField);
			textField.setColumns(10);
		}
		{
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(312, 7, 89, 23);
			panelTable.add(btnBuscar);
		}
		panelTable.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, btnBuscar, btEditar, btnExcluir, scrollTable, table, lbCodUsu, tfCodUsu, lblPesquisar}));
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
	private class ExcluirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!tfCodUsu.getText().isEmpty() && tfCodUsu != null){
				int codigo = Integer.parseInt(tfCodUsu.getText());
				JanelaDeEditarUsuario2 JanEditar = new JanelaDeEditarUsuario2(codigo);
				fechar = true;
			}else{
				JOptionPane.showMessageDialog(frame, "Selecione um usuário para editar", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class MouseListener implements java.awt.event.MouseListener {
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = ""+table.getValueAt(r, 0);
			tfCodUsu.setText(c);
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
