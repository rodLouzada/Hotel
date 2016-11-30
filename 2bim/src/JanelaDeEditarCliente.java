import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;





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
public class JanelaDeEditarCliente implements ActionListener {

	private JDialog frame;
	private JPanel panelTable;

	private JPanel panelButton;
	private JButton btEditar;
	private JTextField tfCodCli;
	private JLabel lbCodCli;

	
	
	private JLabel lbNum;
	private JTextField tfNum;
	private JLabel lbNome;
	private JTextField tfNome;
	private JLabel lbEmail;
	private JTextField tfEmail;
	private JLabel lbData;
	private JTextField tfData;
	private JLabel lbIden;
	private JTextField tfIden;
	private JLabel lbCPF;
	private JTextField tfCPF;
	private JLabel lbTel;
	private JTextField tfTel;
	private JComboBox cbUF;

	private JTextField tfLogradouro;
	private JLabel lbNumero;
	private JTextField tfNumero;
	private JLabel lbEndereco;
	private JTextField tfEndereco;
	private JLabel lbCEP;
	private JTextField tfCEP;
	private JLabel lbCidade;
	private JTextField tfCidade;
	private JLabel lbUF;
	private JLabel lbDataCad;
	private JTextField tfDataCad;
	
	
	
	private JButton btMostrar;
	private JButton btSair;
	private JPanel panel;
	private JScrollPane jScrollPane1;
	//private JDialog frame;
	
	private int op;
	private int co_;


	private JScrollPane scrollTable;

	private JTable table;
	private DefaultTableModel modelo;
	private JButton buttonOk;
	ArrayList<Cliente> vetor;
	ClienteDAO daoCliente;
	
	public boolean fechar = false;

	private JanelaMenuPrincipal jM;
	private JButton btnExcluir;
	private JLabel lblPesquisar;
	private JTextField tfBusca;
	private JButton btnBuscar;
	
	
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
	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeEditarCliente(final JanelaMenuPrincipal janMenPrin) {

		jM = janMenPrin;
		String colunas[] = new String[] {"ID", "Nome", "CPF"};
		modelo = new DefaultTableModel(colunas,0);

		vetor = new ArrayList<Cliente>();

		Connection conexao = null;
		daoCliente = null;

		
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


		// trocar as colunas de posi��o
		// Redimensionamento autom�tico
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
			lbCodCli = new JLabel();
			lbCodCli.setFont(new Font("Tahoma", Font.PLAIN, 17));
			panelTable.add(lbCodCli);
			lbCodCli.setText("ID do Cliente:");
			lbCodCli.setBounds(31, 77, 103, 16);
		}
		{
			DecimalFormat dFormat = new DecimalFormat ( "#######" ) ;
            NumberFormatter Formatter = new NumberFormatter ( dFormat ) ;
            Formatter.setFormat ( dFormat ) ;
            Formatter.setAllowsInvalid ( false ) ; 
            tfCodCli = new JFormattedTextField(Formatter);
            tfCodCli.setEditable(false);
            
           // JFormattedTextField textField = new JFormattedTextField ( ) ;
           // textField.setFormatterFactory ( new DefaultFormatterFactory ( Formatter ) ) ;
			
			
			
			
			//tfCodCli = new JTextField();
			panelTable.add(tfCodCli);
			tfCodCli.setBounds(144, 77, 57, 23);
		}
		{
			btEditar = new JButton();
			btEditar.setForeground(new Color(30, 144, 255));
			btEditar.setIcon(new ImageIcon("..\\2bim\\icons\\edit.png"));
			btEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
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
		

		janMenPrin.frameConteudo.setTitle("Editar Clientes - Hotel");
		janMenPrin.frameConteudo.getContentPane().add(BorderLayout.NORTH, panelTable);
		{
			btnExcluir = new JButton("Excluir");
			btnExcluir.setForeground(new Color(255, 0, 0));
			btnExcluir.setIcon(new ImageIcon("..\\2bim\\icons\\delete.png"));
			btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnExcluir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String message = "Deseja realmente excluir o cliente?";
					String title = "Confirma褯";
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

							JOptionPane.showMessageDialog(null, "Cliente exclu com sucesso!");
							fechar = true;
							// jMenuP.frameConteudo.dispose();
							// JanelaDeExcluirCliente j10 = new
							// JanelaDeExcluirCliente(jMenuP);
						} else {
							JOptionPane.showMessageDialog(janMenPrin.frameConteudo, "Selecione um cliente para excluir", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			btnExcluir.setBounds(246, 420, 124, 34);
			panelTable.add(btnExcluir);
		}
		{
			lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblPesquisar.setBounds(31, 9, 108, 26);
			panelTable.add(lblPesquisar);
		}
		{
			tfBusca = new JTextField();
			tfBusca.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfBusca.setBounds(31, 40, 329, 24);
			panelTable.add(tfBusca);
			tfBusca.setColumns(10);
		}
		{
			btnBuscar = new JButton("Localizar");
			btnBuscar.setIcon(new ImageIcon("..\\2bim\\icons\\search.png"));
			btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnBuscar.setBounds(622, 31, 122, 33);
			panelTable.add(btnBuscar);
		}
		panelTable.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfBusca, btnBuscar, btEditar, btnExcluir, scrollTable, table, tfCodCli, lbCodCli, lblPesquisar}));
		
		janMenPrin.frameConteudo.addKeyListener(new OkKeyListener());
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
			if(!tfCodCli.getText().isEmpty() && tfCodCli != null){
				int codigo = Integer.parseInt(tfCodCli.getText());
				JanelaDeEditarCliente2 JanEditar = new JanelaDeEditarCliente2(codigo);
				fechar = true;
			}else{
				JOptionPane.showMessageDialog(frame, "Selecione um cliente para editar", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class MouseListener implements java.awt.event.MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int r = table.getSelectedRow();
			String c = ""+table.getValueAt(r, 0);
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
	public void JanelaDeEditarCliente2(int co) {
		ArrayList<Cliente> vetor = new ArrayList<Cliente>();
		co_ = co;
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
			vetor = daoCliente.listaPorCod(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		op = vetor.get(0).getCod();
		lbNum = new JLabel("Numero *");
		tfNum = new JTextField(2);
		tfNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfNum.setBackground(Color.white);
			}
		});
		tfNum.setText(""+vetor.get(0).getNumero());
		
		lbNome = new JLabel("Nome *");
		tfNome = new JTextField(30);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				tfNome.setBackground(Color.white);
			}
		});
		tfNome.setText(vetor.get(0).getNome());
		
		lbEmail = new JLabel("E-mail");
		tfEmail = new JTextField(20);
		tfEmail.setText(vetor.get(0).getEmail());

		lbData = new JLabel("Data");
		
		lbIden = new JLabel("Identidade *");
		try {
			MaskFormatter maskIden = new MaskFormatter("UU-##.###.###");
			tfIden = new JFormattedTextField(maskIden);
			tfIden.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfIden.setBackground(Color.white);
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tfIden.setText(vetor.get(0).getIdentidade());
		//tfIden = new JTextField(10);
		
		lbCPF = new JLabel("CPF *");
		try {
			MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
			tfCPF = new JFormattedTextField(maskCPF);
			tfCPF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfCPF.setBackground(Color.white);
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tfCPF.setText(vetor.get(0).getCpf());
		//tfCPF = new JTextField(10);
		
		lbTel = new JLabel("Telefone *");
		try {
			MaskFormatter maskTel = new MaskFormatter("(##)####-####");
			tfTel = new JFormattedTextField(maskTel);
			tfTel.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfTel.setBackground(Color.white);
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//tfTel = new JTextField(10);
		tfTel.setText(vetor.get(0).getTelefone());

		lbData = new JLabel("Data de Nascimento *");
		
		lbEndereco = new JLabel("Endere\u00E7o *");
		
		lbCEP = new JLabel("CEP *");
		
		lbCidade = new JLabel("Cidade *");
		
		lbUF = new JLabel("UF *");
		
		
		
		tfLogradouro = new JTextField(25);
		tfNumero = new JTextField(4);
		tfNumero.setText(""+vetor.get(0).getNumero());
		tfEndereco = new JTextField(30);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfEndereco.setBackground(Color.white);
			}
		});
		tfEndereco.setText(vetor.get(0).getRua());
		try {
			MaskFormatter maskCEP = new MaskFormatter("#####-###");
			tfCEP = new JFormattedTextField(maskCEP);
			tfCEP.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfCEP.setBackground(Color.white);
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tfCEP.setColumns(6);
		tfCEP.setText(vetor.get(0).getCep());
		tfCidade = new JTextField(15);
		tfCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfCidade.setBackground(Color.white);
			}
		});
		tfCidade.setText(vetor.get(0).getCidade());
		ComboBoxModel cbUFModel = 
			new DefaultComboBoxModel(
					new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"});
		cbUF = new JComboBox();
		cbUF.setModel(cbUFModel);
		tfCEP.setColumns(1);
		cbUFModel.setSelectedItem(vetor.get(0).getUf());

		
		
		
//		try {
//			MaskFormatter mascara = new MaskFormatter("########");
//			tfData = new JFormattedTextField(mascara);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		tfData = new JTextField();
		tfData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfData.setBackground(Color.white);
			}
		});
		tfData.setColumns(8);
		Date d = new java.sql.Date(vetor.get(0).getDataNasc().getTimeInMillis());
		tfData.setText(""+d);
		

		//btSair.setEnabled(false);
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // não redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new java.awt.Dimension(496, 342));

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		panel.add(lbNome, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbNome.setBounds(16, 12, 171, 16);

		cons.gridy = 1;
		cons.gridwidth = 4;
		panel.add(tfNome, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfNome.setBounds(16, 34, 458, 23);

		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth = 1;
		panel.add(lbEndereco, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbEndereco.setBounds(18, 182, 171, 16);

		cons.gridy = 3;
		cons.gridwidth = 2;
		panel.add(tfEndereco, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfEndereco.setBounds(18, 206, 331, 23);

		cons.gridx = 2;
		cons.gridy = 2;
		panel.add(lbNum, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbNum.setBounds(355, 182, 116, 16);

		cons.gridy = 3;
		cons.gridwidth = 1;
		panel.add(tfNum, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfNum.setBounds(355, 206, 119, 23);

		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridwidth = 1;
		panel.add(lbCidade, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbCidade.setBounds(18, 238, 171, 16);

		cons.gridy = 5;
		cons.gridwidth = 1;
		panel.add(tfCidade, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfCidade.setBounds(18, 260, 259, 23);

		cons.gridx = 1;
		cons.gridy = 4;
		panel.add(lbUF, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbUF.setBounds(283, 238, 33, 16);

		cons.gridy = 5;
		cons.gridwidth = 1;
		panel.add(cbUF, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		cbUF.setBounds(283, 260, 66, 23);

		cons.gridx = 2;
		cons.gridy = 4;
		cons.gridwidth = 1;
		panel.add(lbCEP, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbCEP.setBounds(355, 238, 107, 16);

		cons.gridy = 5;
		cons.gridwidth = 2;
		panel.add(tfCEP, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfCEP.setBounds(355, 260, 116, 23);

		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridwidth = 1;
		panel.add(lbCPF, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbCPF.setBounds(16, 63, 171, 16);

		cons.gridy = 7;
		cons.gridwidth = 1;
		panel.add(tfCPF, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfCPF.setBounds(16, 85, 171, 23);

		cons.gridx = 1;
		cons.gridy = 6;
		cons.gridwidth = 1;
		panel.add(lbIden, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbIden.setBounds(193, 63, 159, 16);

		cons.gridy = 7;
		cons.gridwidth = 1;
		panel.add(tfIden, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfIden.setBounds(193, 85, 159, 23);

		cons.gridx = 2;
		cons.gridy = 6;
		cons.gridwidth = 1;
		panel.add(lbTel, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbTel.setBounds(358, 63, 107, 16);

		cons.gridy = 7;
		cons.gridwidth = 2;
		panel.add(tfTel, new GridBagConstraints(2, 7, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfTel.setBounds(358, 85, 116, 23);

		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridwidth = 1;
		panel.add(lbEmail, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbEmail.setBounds(16, 114, 47, 18);

		cons.gridy = 9;
		cons.gridwidth = 2;
		panel.add(tfEmail, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfEmail.setBounds(16, 138, 333, 23);

		cons.gridx = 2;
		cons.gridy = 8;
		cons.gridwidth = 1;
		panel.add(lbData, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbData.setBounds(355, 115, 129, 16);

		cons.gridy = 9;
		cons.gridwidth = 2;
		panel.add(tfData, new GridBagConstraints(2, 9, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfData.setBounds(355, 138, 122, 23);
		{
			btMostrar = new JButton("Salvar");
			panel.add(btMostrar);
			btMostrar.setBounds(277, 308, 88, 22);
			btMostrar.addActionListener(new MostrarListener());
		}
		{
			btSair = new JButton("Cancelar");
			panel.add(btSair);
			btSair.setBounds(376, 308, 89, 22);
			btSair.addActionListener(new SairListener());
		}
		{
			jScrollPane1 = new JScrollPane();
			panel.add(jScrollPane1);
			jScrollPane1.setBounds(14, 171, 463, 126);
		}

		cons.gridx = 0;
		cons.gridy = 12;
		cons.gridwidth = 1;

		cons.gridx = 0;
		cons.gridy = 13;
		cons.gridwidth = 1;

		cons.gridy = 14;
		cons.gridwidth = 4;

		cons.gridx = 0;
		cons.gridy = 15;
		cons.gridwidth = 3;

		cons.gridy = 16;
		cons.gridwidth = 4;

		cons.gridx = 0;
		cons.gridy = 18;
		cons.gridwidth = 3;

		cons.gridy = 19;
		cons.gridwidth = 4;

		frame = new JDialog();
		frame.setTitle("Edi褯 de Cliente - Hotel");
		frame.setModal(true);
		//frame.add(panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		frame.pack(); // ajusta o tamanho da janela (frame)
		frame.setLocationRelativeTo(null); // coloca no meio
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		frame.setResizable(false);														// programa
		frame.setVisible(true); // torna a janela visível

	}

	private class MostrarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean erro = false;

			//ValidaCPF valida = new ValidaCPF();
			String cpf = tfCPF.getText().substring(0, 3);
			cpf = cpf + tfCPF.getText().substring(4, 7);
			cpf = cpf + tfCPF.getText().substring(8, 11);
			cpf = cpf + tfCPF.getText().substring(12, 14);
			
			
			if(tfNome.getText() == null || tfNome.getText().isEmpty()){
				tfNome.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfIden.getText().equals("  -  .   .   ")){
				tfIden.setBackground(Color.pink);
				erro = true;
			}
				
			if(tfCPF.getText().equals("   .   .   -  ")){
				tfCPF.setBackground(Color.pink);
				erro = true;
			}
			/*
			else{
				if (!ValidaCPF.isCPF(cpf)){
					JOptionPane.showMessageDialog(frame, "Informe um CPF v⭩do.", "Erro", JOptionPane.ERROR_MESSAGE);
					erro = true;
				}
			}
			*/
			
			if(tfTel.getText().equals("(  )    -    ")){
				tfTel.setBackground(Color.pink);
				erro = true;
			}
			
			/*
			if (tfEmail.getText() != null && !tfEmail.getText().isEmpty()){
				if(!EmailValidator.isEmailValid(tfEmail.getText())){
					JOptionPane.showMessageDialog(frame, "Informe um email v⭩do.", "Erro", JOptionPane.ERROR_MESSAGE);
					erro = true;
				}
			}
			*/
			if(tfData.getText().equals("  /  /    ")){
				tfData.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfEndereco.getText() == null || tfEndereco.getText().isEmpty()){
				tfEndereco.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfNum.getText() == null || tfNum.getText().isEmpty()){
				tfNum.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfCidade.getText() == null || tfCidade.getText().isEmpty()){
				tfCidade.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfCEP.getText().equals("     -   ")){
				tfCEP.setBackground(Color.pink);
				erro = true;
			}
			
			
			if (!erro && !ValidaCPF.isCPF(cpf)){
				JOptionPane.showMessageDialog(frame, "Informe um CPF v⭩do.", "Erro", JOptionPane.ERROR_MESSAGE);
				erro = true;
			}
			else if (!erro && tfEmail.getText() != null && !tfEmail.getText().isEmpty() && !EmailValidator.isEmailValid(tfEmail.getText())){
				//if(!EmailValidator.isEmailValid(tfEmail.getText())){
					JOptionPane.showMessageDialog(frame, "Informe um email v⭩do.", "Erro", JOptionPane.ERROR_MESSAGE);
					erro = true;
				}
			else if(!erro){
				Cliente cli = new Cliente();
				cli.setNome(getTfNome().getText());
				cli.setRua(getTfEndereco().getText());
				cli.setNumero(Integer.parseInt(getTfNum().getText()));
				cli.setCidade(getTfCidade().getText());
				cli.setUf((String) getTfUF().getSelectedItem());
				cli.setCep(getTfCEP().getText());
				cli.setCpf(getTfCPF().getText());
				cli.setIdentidade(getTfIden().getText());
				cli.setTelefone(getTfTel().getText());
				cli.setEmail(getTfEmail().getText());
				
				//DATA--
				Calendar calen = new GregorianCalendar();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				try {
					calen.setTime(f.parse(getTfData().getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				cli.setDataNasc(calen);
				Connection conexao;
				int codigo = getOp();
				try {
					conexao = ConnectionFactory.getConnection();
					ClienteDAO dao = new ClienteDAO(conexao);
					dao.edita(cli, codigo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigat󲩯s", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			
			/*
			
			if(!erro){
				Cliente cli = new Cliente();
				cli.setNome(getTfNome().getText());
				cli.setRua(getTfEndereco().getText());
				cli.setNumero(Integer.parseInt(getTfNum().getText()));
				cli.setCidade(getTfCidade().getText());
				cli.setUf((String) getTfUF().getSelectedItem());
				cli.setCep(getTfCEP().getText());
				cli.setCpf(getTfCPF().getText());
				cli.setIdentidade(getTfIden().getText());
				cli.setTelefone(getTfTel().getText());
				cli.setEmail(getTfEmail().getText());
				
				//DATA--
				Calendar calen = new GregorianCalendar();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				try {
					calen.setTime(f.parse(getTfData().getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				cli.setDataNasc(calen);
				Connection conexao;
				int codigo = getOp();
				try {
					conexao = ConnectionFactory.getConnection();
					ClienteDAO dao = new ClienteDAO(conexao);
					dao.edita(cli, codigo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				frame.dispose();
			}else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigat󲩯s", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			*/
		}
	}

	private class SairListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			frame.dispose();
			//JanelaDeEditarCliente j = new JanelaDeEditarCliente(co_);
		}
	}


	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		//new teste2();
		//j.getPessoa();
	}

	public JTextField getTfNum() {
		return tfNum;
	}

	public JTextField getTfNome() {
		return tfNome;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JTextField getTfData() {
		return tfData;
	}

	public JTextField getTfIden() {
		return tfIden;
	}

	public JTextField getTfCPF() {
		return tfCPF;
	}

	public JTextField getTfTel() {
		return tfTel;
	}

//	public JTextField getTfLogradouro() {
//		return tfLogradouro;
//	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

	public JTextField getTfEndereco() {
		return tfEndereco;
	}

	public JTextField getTfCEP() {
		return tfCEP;
	}

	public JTextField getTfCidade() {
		return tfCidade;
	}

	public JComboBox getTfUF() {
		return cbUF;
	}

	
	public int getOp(){
		return op;
	}
}
