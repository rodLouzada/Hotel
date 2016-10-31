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
					 janCadCli = new JanelaDeCadastroCliente(janMenPrin);
					}
					System.out.println("OP: " + janCadCli.getOp());
					if (janCadCli.getOp() == 1){
						cli.setNome(janCadCli.getTfNome().getText());
						cli.setRua(janCadCli.getTfEndereco().getText());
						cli.setNumero(Integer.parseInt(janCadCli.getTfNum().getText()));
						cli.setCidade(janCadCli.getTfCidade().getText());
						cli.setUf((String) janCadCli.getTfUF().getSelectedItem());
						cli.setCep(janCadCli.getTfCEP().getText());
						cli.setCpf(janCadCli.getTfCPF().getText());
						cli.setIdentidade(janCadCli.getTfIden().getText());
						cli.setTelefone(janCadCli.getTfTel().getText());
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
				else if (op ==10){
					try{
					JanelaDeEditarCaract janEditCar = new JanelaDeEditarCaract();
					}catch(Exception e){
						
					}
				}
				else 	if (op ==11){
					JanelaDeCadastroProduto janCadPro = new JanelaDeCadastroProduto();
				}
				else if (op ==12){
					JanelaDeCadastroServico janCadSer = new JanelaDeCadastroServico();
				}
				else if (op ==13){
					JanelaDeExcluirProduto janCadSer = new JanelaDeExcluirProduto();
				}
				else if (op ==14){
					JanelaDeExcluirServico janCadSer = new JanelaDeExcluirServico();
				}
				else if (op ==15){
					JanelaDeEditarProduto janEdiPro = new JanelaDeEditarProduto();
				}
				else if (op ==16){
					try{
					JanelaDeEditarServico janEdiSer = new JanelaDeEditarServico();
					} catch(Exception e){
						
					}
				}
				else  if (op ==17){
					JanelaDeCheckIn janCheckIn = new JanelaDeCheckIn();
				}
				else  if (op ==18){
					JanelaDeCheckOutQua janCheckout = new JanelaDeCheckOutQua();
				}
				else  if (op ==19){
					JanelaDeConsumo janCons = new JanelaDeConsumo();
				}
				else  if (op ==20){
					JanelaDeRelatorioDespesas janRelDesp = new JanelaDeRelatorioDespesas();
				}
				else  if (op ==21){
					JanelaDeRelatorioQuartos janRelQua = new JanelaDeRelatorioQuartos();
				}
				else  if (op ==22){
					JanelaDeRelatorioHospedagem janRelHosp = new JanelaDeRelatorioHospedagem();
				}
				else  if (op ==23){
					JanelaDeCadastroDeUsuario janCadUsu = new JanelaDeCadastroDeUsuario();
					if (janCadUsu.getOp() == 1){
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
					JanelaDeEditarUsuario janEditUsu = new JanelaDeEditarUsuario();
				}
				else if (op == 25){
					JanelaDeExcluirUsuario janExcUsu = new JanelaDeExcluirUsuario();
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
		
	}

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
