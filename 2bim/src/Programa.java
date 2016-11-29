import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;



public class Programa {
	//Todas as telas
	public static JanelaDeCadastroCliente janCadCli = null;
	public static JanelaDeCadastroDeCaract janCadCaract = null;
	public static JanelaDeCadastroQuarto janCadQua = null;
	public static JanelaDeExcluirCliente janExCli = null;
	public static JanelaDeExcluirCaract janExCar = null;
	public static JanelaDeExcluirQuarto janExQua = null;
	public static JanelaDeEditarCliente janEditCli  = null;
	public static JanelaDeEditarQuarto janEditQua = null;
	public static JanelaDeEditarCaract janEditCar = null;
	public static JanelaDeCadastroProduto janCadPro = null;
	public static JanelaDeCadastroServico janCadSer = null;
	public static JanelaDeExcluirProduto janExcProd = null;
	public static JanelaDeExcluirServico janExcSer = null;
	public static JanelaDeEditarProduto janEdiPro = null;
	public static JanelaDeEditarServico janEdiSer = null;
	public static JanelaDeCheckIn janCheckIn = null;
	public static JanelaDeCheckOutQua janCheckout = null;
	public static JanelaDeConsumo janCons = null;
	public static JanelaDeRelatorioDespesas janRelDesp = null;
	public static JanelaDeRelatorioQuartos janRelQua = null;
	public static JanelaDeRelatorioHospedagem janRelHosp = null;
	public static JanelaDeCadastroDeUsuario janCadUsu = null;
	public static JanelaDeEditarUsuario janEditUsu = null;
	public static JanelaDeExcluirUsuario janExcUsu = null;
	public static boolean acesso = false;
	public static JanelaMenuPrincipal janMenPrin= null;
	public static int op=0;
    public static String senha;
	public static String usu;
	public static JanelaDeLogin j0 = new JanelaDeLogin();
	public static Cliente cli = new Cliente();
	public static Quarto quarto = new Quarto();
	public static Caract caract = new Caract();
	public static Usuario usuario = new Usuario();
	public static Connection conexao = null;
	public static UsuarioDAO daoUsuario = null;
	
	public static Robot r = null;
	
	public static void main(String[] args) {
		ArrayList<Usuario> vetorUsu = new ArrayList<Usuario>();
		try {
			r = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			conexao = ConnectionFactory.getConnection();
			daoUsuario = new UsuarioDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetorUsu = daoUsuario.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		senha = j0.getTfSenha().getText();
		usu = j0.getTfUsu().getText();
		
		
		for (int i = 0; i < vetorUsu.size(); i++) {
			if(usu.equals(vetorUsu.get(i).getLogin()) && senha.equals(vetorUsu.get(i).getSenha())){
				acesso = true;
			}
		}
		
		JButton button = new JButton();		 
		Action buttonAction = new AbstractAction("Refresh") {		 
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		        System.out.println("Refreshing...");
		    }
		};		 
		
		if (acesso){
			janMenPrin = new JanelaMenuPrincipal();
			atalhosTeclado();
			start();
		}
	
}
	
	public static void atalhosTeclado(){
		
		//Atalhos usando Ctrl
		  janMenPrin.frameConteudo.getActionMap().put("CtrlN", new AbstractAction() {
	             @Override
					public void actionPerformed(ActionEvent arg0) {				
						System.out.println("\nCtrl+N");
						janMenPrin.op = 1;
					}
	           });
			  
			  janMenPrin.frameConteudo.getActionMap().put("CtrlO", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+O");
							janMenPrin.op = 18;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("CtrlI", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+I");
							janMenPrin.op = 17;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("CtrlR", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+R");
							janMenPrin.op = 19;
						}
		           });
				
			  janMenPrin.frameConteudo.getActionMap().put("CtrlC", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+C");
							janMenPrin.op = 2;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("CtrlP", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+P");
							janMenPrin.op = 11;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("CtrlS", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+S");
							janMenPrin.op = 12;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("CtrlU", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+U");
							janMenPrin.op = 23;
						}
		           });
			  
			  janMenPrin.frameConteudo.getActionMap().put("CtrlD", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+D");
							janMenPrin.op = 20;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("CtrlQ", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+Q");
							janMenPrin.op = 21;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("CtrlH", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nCtrl+H");
							janMenPrin.op = 22;
						}
		           });
			  
			  janMenPrin.frameConteudo.getActionMap().put("AltZ", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nAlt+Z");
							janMenPrin.op =6;
						}
		           });
			  
			  //Atalhos usando Shift
			  janMenPrin.frameConteudo.getActionMap().put("ShiftE", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nShift+E");
							janMenPrin.op = 8;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("ShiftQ", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nShift+Q");
							janMenPrin.op = 9;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("ShiftC", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nShift+C");
							janMenPrin.op = 10;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("ShiftP", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nShift+P");
							janMenPrin.op = 15;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("ShiftS", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nShift+S");
							janMenPrin.op = 16;
						}
		           });
			  janMenPrin.frameConteudo.getActionMap().put("ShiftU", new AbstractAction() {
		             @Override
						public void actionPerformed(ActionEvent arg0) {				
							System.out.println("\nShift+U");
							janMenPrin.op = 24;
						}
		           });
			  
			 //Atalhos do teclado 
			InputMap inputMap = janMenPrin.frameConteudo.getInputMap();
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), "CtrlN"); //cadastro de cliente
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK), "CtrlO"); //check out
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK), "CtrlI"); //check in
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK), "CtrlR"); //registro de consumo
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "CtrlC");	//cadastro de característica
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK), "CtrlP"); //cadastro de produto
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), "CtrlS"); //cadastro de serviço	
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK), "CtrlU"); //cadastro de usuários
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK), "CtrlD"); //despesas pessoais, relatório de consumo
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK), "CtrlQ"); //relatório de quartos
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK), "CtrlH"); //relatório de hospedagens
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK), "ShiftE"); //editar clientes
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.SHIFT_MASK), "ShiftQ"); //editar quartos
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK), "ShiftC"); //editar característica
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK), "ShiftP"); //editar produtos
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK), "ShiftS"); //editar serviços
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.SHIFT_MASK), "ShiftU"); //editar usuários
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.ALT_MASK), "AltZ"); //sair do sistema			
			
	}
	public static void start(){		
		Thread t = new Thread(new Runnable() {			
		@Override
		public void run() {			
			do{
				op = janMenPrin.getOp();		
				//System.out.println("\n"+op);
				if (op == 1){					
					if(janCadCli == null){
					 setAllToNull();
					 janMenPrin = restauraJanelaPrincipal(janMenPrin);
					 janMenPrin.frameConteudo.getContentPane().removeAll();
					 janCadCli = new JanelaDeCadastroCliente();
					}
					System.out.println("OP: " + janCadCli.getOp());
					if (janCadCli.getOp() == 1){
						cli.setNome(janCadCli.getTfNome().getText());
						cli.setRua(janCadCli.getTfEndereco().getText());
//						cli.setNumero(Integer.parseInt(janCadCli.getTfNum().getText()));
//						cli.setCidade(janCadCli.getTfCidade().getText());
//						cli.setUf((String) janCadCli.getTfUF().getSelectedItem());
						cli.setCep(janCadCli.getTfCEP().getText());
						cli.setCpf(janCadCli.getTfCPF().getText());
//						cli.setIdentidade(janCadCli.getTfIden().getText());
//						cli.setTelefone(janCadCli.getTfTel().getText());
						cli.setEmail(janCadCli.getTfEmail().getText());
						
						//DATA--
						Calendar calen = new GregorianCalendar();
						SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
						try {
							calen.setTime(f.parse(janCadCli.getTfData().getText()));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						cli.setDataNasc(calen);
						
						conexao = null;
						ClienteDAO daoContato = null;
						try {
							conexao = ConnectionFactory.getConnection();
							daoContato = new ClienteDAO(conexao);
							
							daoContato.adiciona(cli);
							
							conexao.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						janCadCli = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
						JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");					
					}
					else if (janCadCli.getOp() == 2){
						janCadCli = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else if (op == 2){
					if(janCadCaract == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
					   janCadCaract = new JanelaDeCadastroDeCaract(janMenPrin);
					}
					
					if (janCadCaract.getOp() == 1){
						caract.setNome(janCadCaract.getTfNome().getText());
						if (janCadCaract.getTfDescCaract().getText() != null){
							caract.setDescricao(janCadCaract.getTfDescCaract().getText());
						}
						conexao = null;
						CaractDAO daoCaracteristica = null;
						try {
							conexao = ConnectionFactory.getConnection();
							daoCaracteristica = new CaractDAO(conexao);
							
							daoCaracteristica.adiciona(caract);
							
							conexao.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}		
						janCadCaract = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
						JOptionPane.showMessageDialog(null, "Característica cadastrada com sucesso!");
					}
					else if (janCadCaract.fechar){
						janCadCaract = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else if (op ==3){
					if(janCadQua == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janCadQua = new JanelaDeCadastroQuarto(janMenPrin);
					}
					
					if (janCadQua.getOp() == 1){
						quarto.setNumero(Integer.parseInt(janCadQua.getTfNum().getText()));
						quarto.setValorDiaria(Double.parseDouble(janCadQua.getTfValorDiaria().getText()));
						int cont = janCadQua.getTable2().getRowCount();
						int cont2 = 0;
						
						//aki
						int vetCod[] = new int[cont];
						int vetQtd[] = new int[cont];
						
						for (int i=0; i<cont; i++){
							vetCod[i] = Integer.parseInt((String)janCadQua.getTable2().getValueAt(i, 0));
							vetQtd[i] = Integer.parseInt((String)janCadQua.getTable2().getValueAt(i, 2));
						}
						//ate aki
						
						conexao = null;
						QuartoDAO daoQuarto = null;
						//aki
						ArrayList<Quarto> vetor = new ArrayList<Quarto>();
						try {
							conexao = ConnectionFactory.getConnection();
							daoQuarto = new QuartoDAO(conexao);
						
							daoQuarto.adiciona(quarto);
							//aki
							vetor = daoQuarto.listaTodos();
						
							conexao.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						//aki
						for (int j = 0; j < vetor.size(); j++) {
							cont2 = vetor.get(j).getCod();
						}
						conexao = null;
						Quarto_CaractDAO daoQuarto_Caract = null;
						try {
							conexao = ConnectionFactory.getConnection();
							daoQuarto_Caract = new Quarto_CaractDAO(conexao);
							for (int i=0; i<cont; i++){
								daoQuarto_Caract.adiciona(cont2, vetCod[i], vetQtd[i]);
							}						
						
							conexao.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						janCadQua = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
						janMenPrin.op = 3;
						JOptionPane.showMessageDialog(null, "Quarto cadastrado com sucesso!");
					}
					else if (janCadQua.getOp() == 2){
						janCadQua = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else 	if (op ==4){
					if(janExCli == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janExCli = new JanelaDeExcluirCliente(janMenPrin);
					}
					if(janExCli.fechar){
						janExCli = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==5){ 
					if(janExCar == null){
						setAllToNull();	
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janExCar = new JanelaDeExcluirCaract(janMenPrin);
					}
					if(janExCar.fechar){
						janExCar = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
					
				}
				else  if (op ==7){
					if(janExQua == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janExQua = new JanelaDeExcluirQuarto(janMenPrin);
					}
					if(janExQua.fechar){
						janExQua = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==8){
					if(janEditCli == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janEditCli = new JanelaDeEditarCliente(janMenPrin);
					}
					if(janEditCli.fechar){
						janEditCli = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==9){ 
					if(janEditQua == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janEditQua = new JanelaDeEditarQuarto(janMenPrin);
					}
					if(janEditQua.fechar){
						janEditQua = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
					
				}
				//Falta alterar o conteúdo da classe para todos as telas abaixo
				else if (op ==10){
					try{
						if(janEditCar == null){
							setAllToNull();
							janMenPrin.frameConteudo.getContentPane().removeAll();
							janEditCar = new JanelaDeEditarCaract(janMenPrin);
						}						
						if(janEditCar.fechar){
							janEditCar = null;
							janMenPrin = restauraJanelaPrincipal(janMenPrin);
						}
						
					}catch(Exception e){
						
					}
				}
				else 	if (op ==11){
					if(janCadPro == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janCadPro = new JanelaDeCadastroProduto(janMenPrin);
					}
					if(janCadPro.fechar){
						janCadPro = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else if (op ==12){
					if(janCadSer == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janCadSer = new JanelaDeCadastroServico(janMenPrin);
					}
					if(janCadSer.fechar){
						janCadSer = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else if (op ==13){
					if(janExcProd == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janExcProd = new JanelaDeExcluirProduto(janMenPrin);
					}
					if(janExcProd.fechar){
						janExcProd = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else if (op ==14){
					if(janExcSer == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janExcSer = new JanelaDeExcluirServico(janMenPrin);
					}
					if(janExcSer.fechar){
						janExcSer = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				
				else if (op ==15){
					if(janEdiPro == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janEdiPro = new JanelaDeEditarProduto(janMenPrin);
					}
					if(janEdiPro.fechar){
						janEdiPro = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else if (op ==16){
					try{
						if(janEdiSer == null){
							setAllToNull();
							janMenPrin.frameConteudo.getContentPane().removeAll();
							janEdiSer = new JanelaDeEditarServico(janMenPrin);
						}
						if(janEdiSer.fechar){
							janEdiSer = null;
							janMenPrin = restauraJanelaPrincipal(janMenPrin);
						}
					} catch(Exception e){
						
					}
				}
				else  if (op ==17){
					if(janCheckIn == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janCheckIn = new JanelaDeCheckIn(janMenPrin);
					}
					if(janCheckIn.fechar){
						janCheckIn = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==18){
					if(janCheckout == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janCheckout = new JanelaDeCheckOutQua(janMenPrin);
					}
					if(janCheckout.fechar){
						janCheckout = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==19){
					if(janCons == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janCons = new JanelaDeConsumo(janMenPrin);
					}
					if(janCons.fechar){
						janCons = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==20){
					if(janRelDesp == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janRelDesp = new JanelaDeRelatorioDespesas(janMenPrin);
					}
					if(janRelDesp.fechar){
						janRelDesp = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==21){
					if(janRelQua == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janRelQua = new JanelaDeRelatorioQuartos(janMenPrin);
					}
					if(janRelQua.fechar){
						janRelQua = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else  if (op ==22){
					if(janRelHosp == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janRelHosp = new JanelaDeRelatorioHospedagem(janMenPrin);
					}
					if(janRelHosp.fechar){
						janRelHosp = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				// Opção 23 não foi finalizada
				else  if (op ==23){
					if(janCadUsu == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janCadUsu = new JanelaDeCadastroDeUsuario(janMenPrin);
					}
					if(janCadUsu.fechar){
						janCadUsu = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
					else if (janCadUsu.getOp() == 1){
						usuario.setNome(janCadUsu.getTfNome().getText());
						usuario.setLogin(janCadUsu.getTfLogin().getText());
						usuario.setSenha(janCadUsu.getTfSenha().getText());
						usuario.setPergunta(janCadUsu.getTfPergunta().getText());
						usuario.setResposta(janCadUsu.getTfResposta().getText());
						
						conexao = null;
						daoUsuario = null;
						try {
							conexao = ConnectionFactory.getConnection();
							daoUsuario = new UsuarioDAO(conexao);
							
							daoUsuario.adiciona(usuario);
							
							conexao.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
					}
				}
				else if (op == 24){
					if(janEditUsu == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janEditUsu = new JanelaDeEditarUsuario(janMenPrin);
					}
					if(janEditUsu.fechar){
						janEditUsu = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				else if (op == 25){
					if(janExcUsu == null){
						setAllToNull();
						janMenPrin.frameConteudo.getContentPane().removeAll();
						janExcUsu = new JanelaDeExcluirUsuario(janMenPrin);
					}
					if(janExcUsu.fechar){
						janExcUsu = null;
						janMenPrin = restauraJanelaPrincipal(janMenPrin);
					}
				}
				try{
				Thread.currentThread();
				Thread.sleep(150);
				}catch(Exception e){e.printStackTrace();}
			}while(op != 6);
			janMenPrin.frame.removeAll();
			janMenPrin.frameConteudo.removeAll();
			janMenPrin.frameConteudo.dispose();
			janMenPrin.frame.dispose();
			JOptionPane.showMessageDialog(null, "Fechando o sistema!");
		}
		});
		
		t.start();		
}

	private static void setAllToNull() {
		janCadCli = null;
		janCadCaract = null;
		janCadQua = null;
		janExCli = null;
		janExCar = null;
		janExQua = null;
		janEditCli  = null;
		janEditQua = null;
		janEditCar = null;
		janCadPro = null;
		janCadSer = null;
		janExcProd = null;
		janExcSer = null;
		janEdiPro = null;
		janEdiSer = null;
		janCheckIn = null;
		janCheckout = null;
		janCons = null;
		janRelDesp = null;
		janRelQua = null;
		janRelHosp = null;
		janCadUsu = null;
		janEditUsu = null;
		janExcUsu = null;
		
	}

	public static JanelaMenuPrincipal restauraJanelaPrincipal(JanelaMenuPrincipal janMenPrin){
		int x1 = janMenPrin.frame.getX();
		int y1 = janMenPrin.frame.getY();
		
		int x2 = janMenPrin.frameConteudo.getX();
		int y2 = janMenPrin.frameConteudo.getY();
		
		int x3 = janMenPrin.barraMenus.getX();
		int y3 = janMenPrin.barraMenus.getY();		
		
		 janMenPrin.frameConteudo.getContentPane().removeAll();
		 //janMenPrin.frame.dispose();
	     //janMenPrin = new JanelaMenuPrincipal();
	     janMenPrin.frame.setLocation(x1, y1);
	     janMenPrin.frameConteudo.setLocation(x2, y2);
	     janMenPrin.barraMenus.setLocation(x3, y3);
	     janMenPrin.op = 0;
	     janMenPrin.frameConteudo.setTitle("");
	     return janMenPrin;
	}
}
