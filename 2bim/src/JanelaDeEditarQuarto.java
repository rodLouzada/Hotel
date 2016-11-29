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
public class JanelaDeEditarQuarto implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JButton btExcluir;
	private JTextField tfCodQua;
	private JLabel lbCodQua;



	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;


	public boolean fechar = false;
	private JanelaMenuPrincipal jM;

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
		//new JanelaDeEditarQuarto();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeEditarQuarto(JanelaMenuPrincipal janMenPrin) {
		jM = janMenPrin;
		
		String colunas[] = new String[] {"ID", "N�mero", "Valor Diaria"};
		modelo = new DefaultTableModel(colunas,0);

		ArrayList<Quarto> vetor = new ArrayList<Quarto>();

		Connection conexao = null;
		QuartoDAO daoQuarto = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			daoQuarto = new QuartoDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetor = daoQuarto.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < vetor.size(); i++) {
			modelo.addRow(new Object[] {vetor.get(i).getCod(), vetor.get(i).getNumero(), vetor.get(i).getValorDiaria()});
		}

		table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.addMouseListener(new MouseListener());
		


		// trocar as colunas de posi��o
		// Redimensionamento autom�tico
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTable = new JPanel();
		panelTable.setPreferredSize(new Dimension(304, 276));
		panelTable.setLayout(null);
		{
			scrollTable = new JScrollPane(table);
			panelTable.add(scrollTable);
			scrollTable
			.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTable
			.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollTable.setBounds(24, 63, 268, 200);
			
		}
		{
			lbCodQua = new JLabel();
			panelTable.add(lbCodQua);
			lbCodQua.setText("ID do Quarto:");
			lbCodQua.setBounds(12, 30, 81, 16);
		}
		{
			tfCodQua = new JTextField();
			tfCodQua.setEditable(false);
			panelTable.add(tfCodQua);
			tfCodQua.setBounds(93, 27, 57, 23);
		}
		{
			btExcluir = new JButton();
			btExcluir.setForeground(new Color(30, 144, 255));
			btExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
			btExcluir.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\edit.png"));
			panelTable.add(btExcluir);
			btExcluir.setText("Editar");
			btExcluir.setBounds(185, 19, 107, 39);
			btExcluir.addActionListener(new ExcluirListener());
		}



		buttonOk = new JButton("Cancelar");
		buttonOk.addActionListener(this);
		

		panelButton = new JPanel();
		panelButton.add(buttonOk);
		buttonOk.addActionListener(new OkListener());
		
		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
		janMenPrin.frameConteudo.setTitle("Editar Quartos - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.SOUTH, panelButton);
		
		janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
		janMenPrin.frameConteudo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		janMenPrin.frameConteudo.setVisible(true); // torna a janela visível.ss
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
			if(!tfCodQua.getText().isEmpty() && tfCodQua != null){
				int codigo = Integer.parseInt(tfCodQua.getText());
				JanelaDeEditarQuarto2 JanEditar = new JanelaDeEditarQuarto2(codigo);
				fechar = true;
			}else{
				JOptionPane.showMessageDialog(frame, "Selecione um quarto para editar", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class MouseListener implements java.awt.event.MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = ""+table.getValueAt(r, 0);
			tfCodQua.setText(c);
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
