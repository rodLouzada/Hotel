import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField.AbstractFormatter;

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
public class JanelaDeCadastroCliente extends javax.swing.JFrame{
	private JLabel lbNome;
	private JTextField tfNome;
	private JLabel lbEmail;
	private JTextField tfEmail;
	private JLabel lbData;
	private JFormattedTextField tfData;
	private JLabel lbCPF;
	private JTextField tfCPF;
	private JLabel lbTel;

	private JTextField tfLogradouro;
	private JLabel lbNumero;
	private JTextField tfNumero;
	private JTextField tfEndereco;
	private JLabel lbCEP;
	private JTextField tfCEP;
	private JLabel lbDataCad;
	private JTextField tfDataCad;
	
	private JButton btMostrar;
	private JButton btSair;
	private JPanel panel;
	private JLabel lbOpcional;
	private JDialog frame;
	
	private int op;
	
	
	MaskFormatter maskCPF;
	private JTextField txtProf;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
				  btMostrar.doClick();
		        } 
			  else if (e.getKeyCode()== 27){
				  btSair.doClick();
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
	
	public JanelaDeCadastroCliente() {
		
		lbNome = new JLabel("Nome Completo - Full Name*");
		tfNome = new JTextField(30);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfNome.setBackground(Color.white);
			}
		});
		

		
		lbEmail = new JLabel("Email");
		tfEmail = new JTextField(20);

		lbData = new JLabel("Data");
		try {
			MaskFormatter maskIden = new MaskFormatter("UU-##.###.###");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//tfIden = new JTextField(10);
		
		lbCPF = new JLabel("CPF *");
		try {
			maskCPF = new MaskFormatter("###.###.###-##");
			tfCPF = new JFormattedTextField(maskCPF);
			tfCPF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					tfCPF.setBackground(Color.white);
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//tfCPF = new JTextField(10);
		
		lbTel = new JLabel("Telefone - Phone *");
		try {
			MaskFormatter maskTel = new MaskFormatter("(##)####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//tfTel = new JTextField(10);

		lbData = new JLabel("Data de Nascimento - Birth Date*");
		
		lbCEP = new JLabel("CEP *");
		
		
		
		
		tfLogradouro = new JTextField(25);
		tfNumero = new JTextField(4);
		tfEndereco = new JTextField(30);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tfEndereco.setBackground(Color.white);
			}
		});
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
		ComboBoxModel cbUFModel = 
			new DefaultComboBoxModel(
					new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"});
		tfCEP.setColumns(1);

		
		
		
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			tfData = new JFormattedTextField(mascara);
			tfData.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tfData.setBackground(Color.white);
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tfData.setColumns(8);

		//btSair.setEnabled(false);
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL; // não redimensionar objeto inserido;
		cons.insets = new Insets(3,3, 3, 3); // distancia entre os objetos
		cons.anchor = GridBagConstraints.WEST;

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(900, 1100));

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		panel.add(lbNome, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbNome.setBounds(18, 58, 171, 16);

		cons.gridy = 1;
		cons.gridwidth = 4;
		panel.add(tfNome, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfNome.setBounds(18, 80, 458, 23);

		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth = 1;

		cons.gridy = 3;
		cons.gridwidth = 2;
		panel.add(tfEndereco, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfEndereco.setBounds(158, 390, 497, 23);

		cons.gridx = 2;
		cons.gridy = 2;

		cons.gridy = 3;
		cons.gridwidth = 1;

		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridwidth = 1;

		cons.gridy = 5;
		cons.gridwidth = 1;

		cons.gridx = 1;
		cons.gridy = 4;

		cons.gridy = 5;
		cons.gridwidth = 1;

		cons.gridx = 2;
		cons.gridy = 4;
		cons.gridwidth = 1;
		panel.add(lbCEP, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbCEP.setBounds(18, 368, 107, 16);

		cons.gridy = 5;
		cons.gridwidth = 2;
		panel.add(tfCEP, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfCEP.setBounds(18, 390, 116, 23);

		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridwidth = 1;
		panel.add(lbCPF, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbCPF.setBounds(18, 270, 171, 16);

		cons.gridy = 7;
		cons.gridwidth = 1;
		panel.add(tfCPF, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfCPF.setBounds(18, 293, 171, 23);

		cons.gridx = 1;
		cons.gridy = 6;
		cons.gridwidth = 1;

		cons.gridy = 7;
		cons.gridwidth = 1;

		cons.gridx = 2;
		cons.gridy = 6;
		cons.gridwidth = 1;
		panel.add(lbTel, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbTel.setBounds(369, 114, 107, 16);

		cons.gridy = 7;
		cons.gridwidth = 2;

		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridwidth = 1;
		panel.add(lbEmail, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbEmail.setBounds(18, 114, 47, 18);

		cons.gridy = 9;
		cons.gridwidth = 2;
		panel.add(tfEmail, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfEmail.setBounds(18, 138, 333, 23);

		cons.gridx = 2;
		cons.gridy = 8;
		cons.gridwidth = 1;
		panel.add(lbData, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		lbData.setBounds(475, 178, 171, 16);

		cons.gridy = 9;
		cons.gridwidth = 2;
		panel.add(tfData, new GridBagConstraints(2, 9, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
		tfData.setBounds(475, 201, 122, 23);
		{
			btMostrar = new JButton("Salvar");
			btMostrar.setForeground(new Color(100, 149, 237));
			btMostrar.setFont(new Font("Tahoma", Font.BOLD, 15));
			btMostrar.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\save.png"));
			panel.add(btMostrar);
			btMostrar.setBounds(759, 1044, 125, 37);
			btMostrar.addActionListener(new MostrarListener());
		}
		{
			btSair = new JButton("Cancelar");
			btSair.setForeground(new Color(255, 0, 0));
			btSair.setFont(new Font("Tahoma", Font.BOLD, 15));
			btSair.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
			panel.add(btSair);
			btSair.setBounds(616, 1048, 131, 29);
			btSair.addActionListener(new SairListener());
		}
		{
			lbOpcional = new JLabel();
			panel.add(lbOpcional);
			lbOpcional.setText("(opcional)");
			lbOpcional.setBounds(54, 116, 54, 16);
			lbOpcional.setFont(new java.awt.Font("Segoe UI",0,9));
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
		frame.setTitle("Cadastro de Cliente - Hotel");
		frame.setModal(true);
		//frame.add(panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(0);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 900, 42);
		panel.add(panel_1);
		
		JLabel lblHspede = new JLabel("H\u00F3spede");
		lblHspede.setFont(new Font("Nirmala UI", Font.BOLD, 24));
		panel_1.add(lblHspede);
		
		JLabel lblDdi = new JLabel("DDI");
		lblDdi.setBounds(371, 141, 26, 14);
		panel.add(lblDdi);
		
		JTextFieldSomenteNumeros textFieldSomenteNumeros = new JTextFieldSomenteNumeros();
		textFieldSomenteNumeros.setBounds(397, 138, 33, 23);
		panel.add(textFieldSomenteNumeros);
		
		JLabel lblDdd = new JLabel("DDD");
		lblDdd.setBounds(440, 141, 26, 14);
		panel.add(lblDdd);
		
		JTextFieldSomenteNumeros textFieldSomenteNumeros_1 = new JTextFieldSomenteNumeros();
		textFieldSomenteNumeros_1.setBounds(465, 138, 33, 23);
		panel.add(textFieldSomenteNumeros_1);
		
		JLabel lblN = new JLabel("N");
		lblN.setBounds(508, 142, 26, 14);
		panel.add(lblN);
		
		JTextFieldSomenteNumeros tfTel = new JTextFieldSomenteNumeros();
		tfTel.setBounds(525, 138, 88, 23);
		panel.add(tfTel);
		
		JLabel lblCelularCell = new JLabel("Celular - Cell Phone *");
		lblCelularCell.setBounds(629, 114, 107, 16);
		panel.add(lblCelularCell);
		
		JLabel label_1 = new JLabel("DDI");
		label_1.setBounds(629, 141, 26, 14);
		panel.add(label_1);
		
		JTextFieldSomenteNumeros textFieldSomenteNumeros_3 = new JTextFieldSomenteNumeros();
		textFieldSomenteNumeros_3.setBounds(655, 138, 33, 23);
		panel.add(textFieldSomenteNumeros_3);
		
		JLabel label_2 = new JLabel("DDD");
		label_2.setBounds(698, 141, 26, 14);
		panel.add(label_2);
		
		JTextFieldSomenteNumeros textFieldSomenteNumeros_4 = new JTextFieldSomenteNumeros();
		textFieldSomenteNumeros_4.setBounds(723, 138, 33, 23);
		panel.add(textFieldSomenteNumeros_4);
		
		JLabel label_3 = new JLabel("N");
		label_3.setBounds(766, 142, 26, 14);
		panel.add(label_3);
		
		JTextFieldSomenteNumeros textFieldSomenteNumeros_5 = new JTextFieldSomenteNumeros();
		textFieldSomenteNumeros_5.setBounds(783, 138, 88, 23);
		panel.add(textFieldSomenteNumeros_5);
		
		JLabel lblProfisso = new JLabel("Profiss\u00E3o - Occupation");
		lblProfisso.setBounds(18, 177, 134, 18);
		panel.add(lblProfisso);
		
		txtProf = new JTextField(15);
		txtProf.setBounds(18, 201, 259, 23);
		panel.add(txtProf);
		
		JLabel lblNacionalidadeCitizenship = new JLabel("Nacionalidade - Citizenship *");
		lblNacionalidadeCitizenship.setBounds(307, 177, 149, 18);
		panel.add(lblNacionalidadeCitizenship);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(307, 202, 153, 20);
		panel.add(comboBox);
		
		JLabel lblGneroGender = new JLabel("G\u00EAnero - Gender *");
		lblGneroGender.setBounds(656, 178, 149, 18);
		panel.add(lblGneroGender);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(656, 203, 153, 20);
		panel.add(comboBox_1);
		
		JLabel lblDocumentoDeIdentificao = new JLabel("Documento de Identifica\u00E7\u00E3o - Travel Document *");
		lblDocumentoDeIdentificao.setBounds(18, 243, 277, 16);
		panel.add(lblDocumentoDeIdentificao);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(201, 271, 54, 16);
		panel.add(lblTipo);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(352, 271, 54, 16);
		panel.add(lblNmero);
		
		textField_1 = new JTextField(15);
		textField_1.setBounds(350, 293, 141, 23);
		panel.add(textField_1);
		
		JLabel lblrgo = new JLabel("\u00D3rg\u00E3o");
		lblrgo.setBounds(503, 271, 54, 16);
		panel.add(lblrgo);
		
		textField_2 = new JTextField(15);
		textField_2.setBounds(501, 293, 154, 23);
		panel.add(textField_2);
		
		JLabel lblResidnciaPermanente = new JLabel("Resid\u00EAncia Permanente - Permanent Address");
		lblResidnciaPermanente.setBounds(158, 366, 239, 16);
		panel.add(lblResidnciaPermanente);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(198, 294, 134, 20);
		panel.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(18, 448, 259, 23);
		panel.add(comboBox_3);
		
		JLabel lblPasCountry = new JLabel("Pa\u00EDs - Country *");
		lblPasCountry.setBounds(18, 426, 90, 16);
		panel.add(lblPasCountry);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(302, 448, 196, 23);
		panel.add(comboBox_4);
		
		JLabel lblUfState = new JLabel("UF - State*");
		lblUfState.setBounds(302, 426, 66, 16);
		panel.add(lblUfState);
		
		JLabel lblCidadeCity = new JLabel("Cidade - City*");
		lblCidadeCity.setBounds(523, 426, 107, 16);
		panel.add(lblCidadeCity);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLocalizar.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\search.png"));
		btnLocalizar.setBounds(18, 1051, 134, 36);
		panel.add(btnLocalizar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setForeground(new Color(0, 128, 0));
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNovo.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\new.png"));
		btnNovo.setBounds(177, 1050, 122, 37);
		panel.add(btnNovo);
		
		textField = new JTextField(15);
		textField.setBounds(525, 449, 231, 23);
		panel.add(textField);
		
		JLabel lblltimaProcedncia = new JLabel("\u00DAltima Proced\u00EAncia - Arriving From *\r\n");
		lblltimaProcedncia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblltimaProcedncia.setBounds(18, 488, 222, 16);
		panel.add(lblltimaProcedncia);
		
		JLabel label = new JLabel("Pa\u00EDs - Country *");
		label.setBounds(18, 510, 90, 16);
		panel.add(label);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(18, 532, 259, 23);
		panel.add(comboBox_5);
		
		JLabel label_4 = new JLabel("UF - State*");
		label_4.setBounds(302, 510, 66, 16);
		panel.add(label_4);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(302, 532, 196, 23);
		panel.add(comboBox_6);
		
		JLabel label_5 = new JLabel("Cidade - City*");
		label_5.setBounds(523, 509, 107, 16);
		panel.add(label_5);
		
		textField_3 = new JTextField(15);
		textField_3.setBounds(525, 532, 231, 23);
		panel.add(textField_3);
		
		JLabel lblPrximoDestino = new JLabel("Pr\u00F3ximo Destino - Next Destination*\r\n");
		lblPrximoDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrximoDestino.setBounds(18, 566, 222, 16);
		panel.add(lblPrximoDestino);
		
		JLabel label_6 = new JLabel("Pa\u00EDs - Country *");
		label_6.setBounds(18, 590, 90, 16);
		panel.add(label_6);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(18, 612, 259, 23);
		panel.add(comboBox_7);
		
		JLabel label_7 = new JLabel("UF - State*");
		label_7.setBounds(302, 590, 66, 16);
		panel.add(label_7);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setBounds(302, 612, 196, 23);
		panel.add(comboBox_8);
		
		JLabel label_8 = new JLabel("Cidade - City*");
		label_8.setBounds(523, 589, 107, 16);
		panel.add(label_8);
		
		textField_4 = new JTextField(15);
		textField_4.setBounds(525, 612, 231, 23);
		panel.add(textField_4);
		
		JLabel lblMotivoDaViagem = new JLabel("Motivo da Viagem - Purpose of Trip*\r\n");
		lblMotivoDaViagem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMotivoDaViagem.setBounds(18, 657, 222, 16);
		panel.add(lblMotivoDaViagem);
		
		JLabel lblMeioDeTransporte = new JLabel("Meio de Transporte - Arriving By*\r\n");
		lblMeioDeTransporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMeioDeTransporte.setBounds(18, 748, 222, 16);
		panel.add(lblMeioDeTransporte);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Lazer - F\u00E9rias / Leisure - Vacation");
		chckbxNewCheckBox.setBounds(18, 678, 207, 23);
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNegciosBusiness = new JCheckBox("Neg\u00F3cios / Business");
		chckbxNegciosBusiness.setBounds(18, 707, 207, 23);
		panel.add(chckbxNegciosBusiness);
		
		JCheckBox chckbxCongressoFeira = new JCheckBox("Congresso - Feira / Convention - Fair");
		chckbxCongressoFeira.setBounds(227, 680, 207, 23);
		panel.add(chckbxCongressoFeira);
		
		JCheckBox chckbxParentesAmigos = new JCheckBox("Parentes - Amigos / Relatives - Friends");
		chckbxParentesAmigos.setBounds(227, 707, 216, 23);
		panel.add(chckbxParentesAmigos);
		
		JCheckBox chckbxEstudosCursos = new JCheckBox("Estudos - Cursos / Studies - Courses");
		chckbxEstudosCursos.setBounds(448, 678, 207, 23);
		panel.add(chckbxEstudosCursos);
		
		JCheckBox chckbxReligioReligion = new JCheckBox("Religi\u00E3o / Religion");
		chckbxReligioReligion.setBounds(448, 707, 207, 23);
		panel.add(chckbxReligioReligion);
		
		JCheckBox chckbxSadeHealth = new JCheckBox("Sa\u00FAde / Health");
		chckbxSadeHealth.setBounds(664, 678, 128, 23);
		panel.add(chckbxSadeHealth);
		
		JCheckBox chckbxComprasShopping = new JCheckBox("Compras / Shopping");
		chckbxComprasShopping.setBounds(664, 707, 128, 23);
		panel.add(chckbxComprasShopping);
		
		JCheckBox chckbxAvioPlane = new JCheckBox("Avi\u00E3o / Plane");
		chckbxAvioPlane.setBounds(18, 771, 128, 23);
		panel.add(chckbxAvioPlane);
		
		JCheckBox chckbxAutomvelCar = new JCheckBox("Autom\u00F3vel / Car");
		chckbxAutomvelCar.setBounds(18, 797, 128, 23);
		panel.add(chckbxAutomvelCar);
		
		JCheckBox chckbxnibusBus = new JCheckBox("\u00D4nibus / Bus");
		chckbxnibusBus.setBounds(158, 771, 119, 23);
		panel.add(chckbxnibusBus);
		
		JCheckBox chckbxMotoMotorcycle = new JCheckBox("Moto / Motorcycle");
		chckbxMotoMotorcycle.setBounds(158, 797, 119, 23);
		panel.add(chckbxMotoMotorcycle);
		
		JCheckBox chckbxNavioBarco = new JCheckBox("Navio - Barco / Ship - Ferry Boat");
		chckbxNavioBarco.setBounds(291, 771, 207, 23);
		panel.add(chckbxNavioBarco);
		
		JCheckBox chckbxTremTrain = new JCheckBox("Trem / Train");
		chckbxTremTrain.setBounds(290, 797, 207, 23);
		panel.add(chckbxTremTrain);
		
		JCheckBox chckbxOutroother = new JCheckBox("Outro/Other");
		chckbxOutroother.setBounds(799, 678, 85, 23);
		panel.add(chckbxOutroother);
		
		JCheckBox chckbxOutroOther = new JCheckBox("Outro / Other");
		chckbxOutroOther.setBounds(508, 771, 105, 23);
		panel.add(chckbxOutroOther);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es - Notes");
		lblObservaes.setBounds(16, 839, 239, 16);
		panel.add(lblObservaes);
		
		JLabel lblNmeroDeHospedes = new JLabel("N\u00FAmero de Hospedes / Number of Guests*");
		lblNmeroDeHospedes.setBounds(616, 866, 255, 16);
		panel.add(lblNmeroDeHospedes);
		
		JLabel lblUh = new JLabel("UH N *");
		lblUh.setBounds(616, 925, 120, 16);
		panel.add(lblUh);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBackground(UIManager.getColor("Button.background"));
		editorPane.setBounds(19, 866, 548, 96);
		panel.add(editorPane);
		
		textField_5 = new JTextField(15);
		textField_5.setBounds(616, 882, 116, 23);
		panel.add(textField_5);
		
		textField_6 = new JTextField(15);
		textField_6.setBounds(616, 939, 116, 23);
		panel.add(textField_6);
		
		JLabel lblPrevisoDeEntrada = new JLabel();
		lblPrevisoDeEntrada.setText("Previs\u00E3o de Entrada");
		lblPrevisoDeEntrada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrevisoDeEntrada.setBounds(18, 975, 130, 16);
		panel.add(lblPrevisoDeEntrada);
		
		JFormattedTextField formattedTextField = new JFormattedTextField((AbstractFormatter) null);
		formattedTextField.setText("  /  /    ");
		formattedTextField.setColumns(8);
		formattedTextField.setBounds(18, 1002, 122, 23);
		panel.add(formattedTextField);
		
		JLabel lblData = new JLabel();
		lblData.setText("Previs\u00E3o Sa\u00EDda");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(177, 975, 130, 16);
		panel.add(lblData);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField((Object) null);
		formattedTextField_1.setText("  /  /    ");
		formattedTextField_1.setColumns(8);
		formattedTextField_1.setBounds(177, 1002, 122, 23);
		panel.add(formattedTextField_1);
		
		JLabel lblDataCkeckin = new JLabel();
		lblDataCkeckin.setText("Data Checkin");
		lblDataCkeckin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataCkeckin.setBounds(332, 975, 130, 16);
		panel.add(lblDataCkeckin);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField((Object) null);
		formattedTextField_2.setText("  /  /    ");
		formattedTextField_2.setColumns(8);
		formattedTextField_2.setBounds(332, 1002, 122, 23);
		panel.add(formattedTextField_2);
		frame.addKeyListener(new OkKeyListener());
		panel.addKeyListener(new OkKeyListener());
		
		//frame.add(panelBotoes);
		//frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		//frame.setSize(300, 300);
		frame.pack(); // ajusta o tamanho da janela (frame)
		frame.setLocationRelativeTo(null); // coloca no meio
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Sair do
		frame.setResizable(false);														// programa
		frame.setVisible(true); // torna a janela visível.ss
	

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
			
/*			if(tfIden.getText().equals("  -  .   .   ")){
				tfIden.setBackground(Color.pink);
				erro = true;
			} */
				
			if(tfCPF.getText().equals("   .   .   -  ")){
				tfCPF.setBackground(Color.pink);
				erro = true;
			}
			
/*			if(tfTel.getText().equals("(  )    -    ")){
				tfTel.setBackground(Color.pink);
				erro = true;
			} */
			
			if(tfData.getText().equals("  /  /    ")){
				tfData.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfEndereco.getText() == null || tfEndereco.getText().isEmpty()){
				tfEndereco.setBackground(Color.pink);
				erro = true;
			}
			
/*			if(tfNum.getText() == null || tfNum.getText().isEmpty()){
				tfNum.setBackground(Color.pink);
				erro = true;
			}
			
			if(tfCidade.getText() == null || tfCidade.getText().isEmpty()){
				tfCidade.setBackground(Color.pink);
				erro = true;
			} */
			
			if(tfCEP.getText().equals("     -   ")){
				tfCEP.setBackground(Color.pink);
				erro = true;
			}
			
			
			if (!erro && !ValidaCPF.isCPF(cpf)){
					JOptionPane.showMessageDialog(frame, "Informe um CPF v�lido.", "Erro", JOptionPane.ERROR_MESSAGE);
					erro = true;
			}
			else if (!erro && tfEmail.getText() != null && !tfEmail.getText().isEmpty()){
					if(!EmailValidator.isEmailValid(tfEmail.getText())){
						JOptionPane.showMessageDialog(frame, "Informe um email v�lido.", "Erro", JOptionPane.ERROR_MESSAGE);
						erro = true;
					}else{
						op = 1;
						frame.dispose();
					}
				}
			else if(!erro){
				op = 1;
				frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigat�rios", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class SairListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			frame.dispose();
		}
	}


	public static void main(String[] args) {
		new JanelaDeCadastroCliente();
		//j.getPessoa();
	}

/*	public JTextField getTfNum() {
		return tfNum;
	} */

	public JTextField getTfNome() {
		return tfNome;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JFormattedTextField getTfData() {
		return tfData;
	}

/*	public JTextField getTfIden() {
		return tfIden;
	} */

	public JTextField getTfCPF() {
		return tfCPF;
	}

/*	public JTextField getTfTel() {
		return tfTel;
	} */

	public JTextField getTfLogradouro() {
		return tfLogradouro;
	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

	public JTextField getTfEndereco() {
		return tfEndereco;
	}

	public JTextField getTfCEP() {
		return tfCEP;
	}

/*	public JTextField getTfCidade() {
		return tfCidade;
	} */

/*	public JComboBox getTfUF() {
		return cbUF;
	} */

	
	public int getOp(){
		return op;
	}
}