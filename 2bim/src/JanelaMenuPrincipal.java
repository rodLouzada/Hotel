
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JPanel;

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
public class JanelaMenuPrincipal {
	public JFrame frame;

	public JMenuBar barraMenus;
	
	private JMenu menuCadastro;
	private JMenuItem editarCaracteristica;
	private JMenuItem menuHosp;
	private JMenuItem menuQua;
	private JMenuItem mnDespesas;
	private JMenuItem jMenuItem1;
	private JMenuItem editarServico;
	private JMenuItem editarUsuario;
	private JMenuItem editarProduto;
	private JMenuItem cadServico;
	private JMenuItem cadProduto;
	private JMenuItem editarQuarto;
	private JMenuItem editarCliente;
	private JMenu menuEditar;
	private JMenuItem checkOut;
	private JMenuItem cadCaract;
	private JMenuItem cadCli;
	private JMenuItem cadQuarto;
	private JMenuItem cadUsu;

	public int op;
	public JInternalFrame frameConteudo;
	private JMenu menuControle;
	private JMenuItem checkIn;
	
	private JMenu menuRelatorio;

	private JMenuItem sair;
	private String titulo;
	private JMenu mnInicio;

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {

				new JanelaMenuPrincipal();
				
			}
		});
	t.start();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaMenuPrincipal() {

		titulo = "Titulo teste";
		cadCli = new JMenuItem("Cliente");
		cadCli.setMnemonic(KeyEvent.VK_C);
		cadCli.addActionListener(new Lis1());

		menuCadastro = new JMenu("Cadastro");
		menuCadastro.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuCadastro.setIcon(new ImageIcon("..\\2bim\\icons\\insert.png"));
		menuCadastro.add(cadCli);
		{
			cadQuarto = new JMenuItem();
			menuCadastro.add(cadQuarto);
		}
		cadQuarto.setText("Quarto");
		cadQuarto.addActionListener(new Lis3());
		{
			cadCaract = new JMenuItem();
			menuCadastro.add(cadCaract);
			cadCaract.setText("Característica");
			cadCaract.addActionListener(new Lis2());
		}
		{
			cadProduto = new JMenuItem();
			menuCadastro.add(cadProduto);
			cadProduto.setText("Produto");
			cadProduto.addActionListener(new Lis11());
		}
		{
			cadServico = new JMenuItem();
			menuCadastro.add(cadServico);
			cadServico.setText("Serviço");
			cadServico.addActionListener(new Lis12());
		}
		{
			cadUsu = new JMenuItem();
			menuCadastro.add(cadUsu);
			cadUsu.setText("Usuário");
			cadUsu.addActionListener(new Lis23());
		}
		// -------------------

		menuControle = new JMenu("Controle");
		menuControle.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuControle.setIcon(new ImageIcon("..\\2bim\\icons\\control.png"));
		{
			checkIn = new JMenuItem();
			menuControle.add(checkIn);
		}
		checkIn.setText("Check In");
		checkIn.addActionListener(new Lis17());
		{
			checkOut = new JMenuItem();
			menuControle.add(checkOut);
			checkOut.setText("Check Out");
			checkOut.addActionListener(new Lis18());
		}
		{
			jMenuItem1 = new JMenuItem();
			menuControle.add(jMenuItem1);
			jMenuItem1.setText("Registrar Consumo");
			jMenuItem1.addActionListener(new Lis19());
		}
		// ---------------------------

		menuRelatorio = new JMenu("Relatorio");
		menuRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuRelatorio.setIcon(new ImageIcon("..\\2bim\\icons\\rel.png"));
		// ----------------------------
		sair = new JMenuItem("Sair");
		sair.setFont(new Font("Segoe UI", Font.BOLD, 15));
		sair.setIcon(new ImageIcon("..\\2bim\\icons\\checkout.png"));
		sair.setMnemonic('S');
		sair.addActionListener(new Lis6());

		barraMenus = new JMenuBar();
		{
			mnInicio = new JMenu("Inicio");
			mnInicio.setIcon(new ImageIcon("..\\2bim\\icons\\Home.png"));
			mnInicio.setFont(new Font("Segoe UI", Font.BOLD, 15));
			barraMenus.add(mnInicio);
		}
		barraMenus.add(menuCadastro);
		menuCadastro.setText("Cadastro  ");
		{
			menuEditar = new JMenu();
			menuEditar.setFont(new Font("Segoe UI", Font.BOLD, 15));
			menuEditar.setIcon(new ImageIcon("..\\2bim\\icons\\editMenu.png"));
			barraMenus.add(menuEditar);
			menuEditar.setText("Editar  ");
			{
				editarCliente = new JMenuItem();
				menuEditar.add(editarCliente);
				editarCliente.setText("Cliente");
				editarCliente.addActionListener(new Lis8());
			}
			{
				editarQuarto = new JMenuItem();
				menuEditar.add(editarQuarto);
				editarQuarto.setText("Quarto");
				editarQuarto.addActionListener(new Lis9());
			}
			{
				editarCaracteristica = new JMenuItem();
				menuEditar.add(editarCaracteristica);
				editarCaracteristica.setText("Característica");
				editarCaracteristica.addActionListener(new Lis10());
			}
			{
				editarProduto = new JMenuItem();
				menuEditar.add(editarProduto);
				editarProduto.setText("Produto");
				editarProduto.addActionListener(new Lis15());
			}
			{
				editarServico = new JMenuItem();
				menuEditar.add(editarServico);
				editarServico.setText("Serviço");
				editarServico.addActionListener(new Lis16());
			}
			{
				editarUsuario = new JMenuItem();
				menuEditar.add(editarUsuario);
				editarUsuario.setText("Usuário");
				editarUsuario.addActionListener(new Lis24());
			}
		}
		barraMenus.add(menuControle);
		menuControle.setText("Controle  ");
		barraMenus.add(menuRelatorio);
		menuRelatorio.setText("Relatório  ");
		{
			mnDespesas = new JMenuItem();
			menuRelatorio.add(mnDespesas);
			mnDespesas.setText("Despesas Pessoais");
			mnDespesas.addActionListener(new Lis20());
		}
		{
			menuQua = new JMenuItem();
			menuRelatorio.add(menuQua);
			menuQua.setText("Quartos");
			menuQua.addActionListener(new Lis21());
		}
		{
			menuHosp = new JMenuItem();
			menuRelatorio.add(menuHosp);
			menuHosp.setText("Hospedagens");
			menuHosp.addActionListener(new Lis22());
		}
		barraMenus.add(sair);

		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("..\\2bim\\icons\\Hotel.png"));
		frame.setTitle("Hotel Casta - Sistema de Hotel");
		// frame.setModal(true);
		frame.setJMenuBar(barraMenus);
		// frame.pack();
		frame.setSize(680, 489);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(new java.awt.Dimension(800,600));
		JButton btnNovoCliente = new JButton("");
		btnNovoCliente.setIcon(new ImageIcon(
				"..\\2bim\\icons\\novocliente.png"));

		btnNovoCliente.addActionListener(new Lis1());

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(
				"..\\2bim\\icons\\consumo.png"));

		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(
				"..\\2bim\\icons\\checkin.png"));

		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(
				"..\\2bim\\icons\\checkout.png"));

		JLabel label = new JLabel("");

		JLabel lblNewLabel = new JLabel("");

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(
				"..\\2bim\\icons\\logo.gif"));
		frameConteudo = new JInternalFrame("");
		frameConteudo.setForeground(Color.DARK_GRAY);
		frameConteudo.setFrameIcon(null);
		frameConteudo.setBorder(null);
		frameConteudo.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(frameConteudo, GroupLayout.PREFERRED_SIZE, 795, GroupLayout.PREFERRED_SIZE)
					.addGap(410)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovoCliente)
							.addGap(290)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addComponent(label)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addGap(117))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(label_1)))
					.addGap(159)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNovoCliente, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
					.addContainerGap())
				.addComponent(frameConteudo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
		);
		frame.getContentPane().setLayout(groupLayout);
		//frameConteudo.setVisible(false);
	}
	public int getOp(){
		return op;
	}
	
	private class Lis1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Click Opção 1:" + arg0);
			op = 1;
			//frame.dispose();
		}
	}
	private class Lis2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			//frame.dispose();
		}
	}
	private class Lis3 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 3;
		//	frame.dispose();
		}
	}
	private class Lis6 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 6;
		//	frame.dispose();
		}
	}
	private class Lis8 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 8;
			//frame.dispose();
		}
	}
	private class Lis9 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 9;
			//frame.dispose();
		}
	}
	private class Lis10 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 10;
			//frame.dispose();
		}
	}
	private class Lis11 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 11;
			//frame.dispose();
		}
	}
	private class Lis12 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 12;
		//	frame.dispose();
		}
	}
	private class Lis15 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 15;
			//frame.dispose();
		}
	}
	private class Lis16 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 16;
			//frame.dispose();
		}
	}
	private class Lis17 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 17;
			//frame.dispose();
		}
	}
	private class Lis18 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 18;
			//frame.dispose();
		}
	}
	private class Lis19 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 19;
			//frame.dispose();
		}
	}
	private class Lis20 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 20;
			//frame.dispose();
		}
	}
	private class Lis21 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 21;
			//frame.dispose();
		}
	}
	private class Lis22 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 22;
			//frame.dispose();
		}
	}
	private class Lis23 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 23;
			//frame.dispose();
		}
	}
	private class Lis24 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 24;
			//frame.dispose();
		}
	}
}