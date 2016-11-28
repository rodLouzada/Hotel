import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.logging.impl.ServletContextCleaner;



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
	public static JanelaDeCheckOutQua2 janCheckout2 = null;
	public static JanelaDeConsumo janCons = null;
	public static JanelaDeRelatorioDespesas janRelDesp = null;
	public static JanelaDeRelatorioQuartos janRelQua = null;
	public static JanelaDeRelatorioHospedagem janRelHosp = null;
	public static JanelaDeCadastroDeUsuario janCadUsu = null;
	public static JanelaDeEditarUsuario janEditUsu = null;
	public static JanelaDeExcluirUsuario janExcUsu = null;
    
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		String senha;
		String usu;
		JanelaDeLogin j0 = new JanelaDeLogin();
		senha = j0.getTfSenha().getText();
		usu = j0.getTfUsu().getText();
		Cliente cli = new Cliente();
		Quarto quarto = new Quarto();
		Caract caract = new Caract();
		Usuario usuario = new Usuario();
		
		ArrayList<Usuario> vetorUsu = new ArrayList<Usuario>();

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
			vetorUsu = daoUsuario.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean acesso = false;
		
		for (int i = 0; i < vetorUsu.size(); i++) {
			if(usu.equals(vetorUsu.get(i).getLogin()) && senha.equals(vetorUsu.get(i).getSenha())){
				acesso = true;
			}
		}
		
		
		int opOld = 0;
		int op = 0;
		if (acesso){
			JanelaMenuPrincipal janMenPrin = new JanelaMenuPrincipal();
			
			do{
				op = janMenPrin.getOp();
				if(opOld!=op){
					opOld = op;
					System.out.println("Op: " + op);
				}
				
				if (op == 1){					
					if(janCadCli == null){
					 setAllToNull();
					 janMenPrin.frameConteudo.getContentPane().removeAll();
					 //janCadCli = new JanelaDeCadastroCliente(janMenPrin);
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
					else if (janCadCaract.getOp() == 2){
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
						janCheckout2 = null;
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
			}while(op != 6);
			janMenPrin.frame.removeAll();
			janMenPrin.frameConteudo.removeAll();
			janMenPrin.frameConteudo.dispose();
			janMenPrin.frame.dispose();
		}
		
		JOptionPane.showMessageDialog(null, "Fechando o sistema!");
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

	/**
	 * @wbp.parser.entryPoint
	 */
	public static JanelaMenuPrincipal restauraJanelaPrincipal(JanelaMenuPrincipal janMenPrin){
		int x1 = janMenPrin.frame.getX();
		int y1 = janMenPrin.frame.getY();
		
		int x2 = janMenPrin.frameConteudo.getX();
		int y2 = janMenPrin.frameConteudo.getY();
		
		int x3 = janMenPrin.barraMenus.getX();
		int y3 = janMenPrin.barraMenus.getY();
		
		
		 janMenPrin.frameConteudo.dispose();
		 janMenPrin.frame.dispose();
	     janMenPrin = new JanelaMenuPrincipal();
	     janMenPrin.frame.setLocation(x1, y1);
	     janMenPrin.frameConteudo.setLocation(x2, y2);
	     janMenPrin.barraMenus.setLocation(x3, y3);
	     
	     return janMenPrin;
	}
}
