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
import java.awt.Color;
import java.awt.Font;



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
public class JanelaDeEditarServico implements ActionListener {

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

	public boolean fechar = false;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private JButton button_1;
	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
				  btEditar.doClick();
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
	private JanelaMenuPrincipal jM = null;
	public static void main(String[] args) {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeEditarServico(JanelaMenuPrincipal janMenPrin) {

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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new Dimension(800, 600));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable.setBounds(31, 111, 713, 298);
			
		}
		{
			lbCodCaract = new JLabel();
			lbCodCaract.setFont(new Font("Tahoma", Font.PLAIN, 17));
			panelTable.add(lbCodCaract);
			lbCodCaract.setText("ID do Serviço:");
			lbCodCaract.setBounds(31, 77, 113, 20);
		}
		{
			tfCodCaract = new JTextField();
			tfCodCaract.setEditable(false);
			panelTable.add(tfCodCaract);
			tfCodCaract.setBounds(142, 77, 57, 23);
		}
		{
			btEditar = new JButton();
			btEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
			btEditar.setForeground(new Color(30, 144, 255));
			btEditar.setIcon(new ImageIcon("..\\2bim\\icons\\edit.png"));
			panelTable.add(btEditar);
			btEditar.setText("Editar");
			btEditar.setBounds(391, 420, 124, 34);
			btEditar.addActionListener(new ExcluirListener());
		}



		buttonOk = new JButton("Cancelar");
		buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.add(buttonOk);
		buttonOk.addActionListener(new OkListener());
		
		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Editar Serviçose - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		{
			label = new JLabel("Pesquisar:");
			label.setFont(new Font("Tahoma", Font.PLAIN, 17));
			label.setBounds(31, 9, 108, 26);
			panelTable.add(label);
		}
		{
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textField.setColumns(10);
			textField.setBounds(31, 40, 329, 24);
			panelTable.add(textField);
		}
		{
			button = new JButton("Localizar");
			button.setIcon(new ImageIcon("..\\2bim\\icons\\search.png"));
			button.setFont(new Font("Tahoma", Font.BOLD, 13));
			button.setBounds(622, 31, 122, 33);
			panelTable.add(button);
		}
		{
			button_1 = new JButton("Excluir");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = "Deseja realmente excluir o servico?";
					String title = "Confirmação";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						if (!tfCodCaract.getText().isEmpty() && tfCodCaract != null) {
							Connection conexao;
							int codigo = Integer.parseInt(tfCodCaract.getText());
							try {
								conexao = ConnectionFactory.getConnection();
								ServicoDAO dao = new ServicoDAO(conexao);
								dao.excluir(codigo);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");
							fechar = true;
							JanelaDeExcluirServico j10 = new JanelaDeExcluirServico(jM);
						} else {
							JOptionPane.showMessageDialog(frame, "Selecione um serviço para excluir", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			button_1.setForeground(Color.RED);
			button_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			button_1.setBounds(246, 420, 124, 34);
			panelTable.add(button_1);
		}
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
	private class ExcluirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!tfCodCaract.getText().isEmpty() && tfCodCaract != null){
				int cod = Integer.parseInt(tfCodCaract.getText());
				JanelaDeEditarServico2 janEdiServico = new JanelaDeEditarServico2(cod);
				fechar = true;
			}else{
				JOptionPane.showMessageDialog(frame, "Selecione um serviço para editar", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class MouseListener implements java.awt.event.MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = ""+table.getValueAt(r, 0);
			tfCodCaract.setText(c);
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