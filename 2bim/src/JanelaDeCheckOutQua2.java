import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;




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
public class JanelaDeCheckOutQua2 {
	private JButton btOk;
	private JPanel panel;
	private JTextField tfTotal;
	private JTextField tfCons;
	private JTextField tfDiaQua;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JTextField tfCliente;
	private JLabel lbCliente;
	private JTextField tfIdHosp;
	private JLabel lbIdHosp;
	private JDialog frame;
	private Connection conexao;
	private static JButton btSair;
	private static JanelaMenuPrincipal JM;
	
	public boolean fechar = false;
	double cons;
	double diaria;
	int codH;
	
	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btOk.doClick();
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


	public JanelaDeCheckOutQua2(int cod, JanelaMenuPrincipal janMenPrin){
		codH = cod;
		frame = new JDialog();
		JM = janMenPrin;
		
		{
			panel = new JPanel();
			frame.getContentPane().add(panel, BorderLayout.NORTH);
			{
				btOk = new JButton("Novo");
				btOk.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\new.png"));
				btOk.setForeground(new Color(0, 128, 0));
				btOk.setFont(new Font("Tahoma", Font.BOLD, 13));
				panel.add(btOk);
				btOk.setBounds(204, 200, 107, 39);
				btOk.addActionListener(new OkListener());
			}
			{
			    btSair = new JButton("Cancelar");
			    btSair.setForeground(new Color(255, 0, 0));
			    btSair.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
			    btSair.setFont(new Font("Tahoma", Font.BOLD, 13));
				panel.add(btSair);
				btSair.setBounds(68, 205, 124, 34);
				btSair.addActionListener(new SairListener());
			}
			{
				lbIdHosp = new JLabel();
				panel.add(lbIdHosp);
				lbIdHosp.setText("ID Hospede");
				lbIdHosp.setBounds(12, 12, 85, 16);
				lbIdHosp.setFont(new java.awt.Font("Tahoma",1,12));
			}
			{
				tfIdHosp = new JTextField();
				panel.add(tfIdHosp);
				tfIdHosp.setBounds(12, 34, 66, 23);
				tfIdHosp.setEditable(false);
				tfIdHosp.setText(""+cod);
			}
			
			{
				lbCliente = new JLabel();
				panel.add(lbCliente);
				lbCliente.setText("Cliente");
				lbCliente.setBounds(100, 12, 273, 16);
				lbCliente.setFont(new java.awt.Font("Tahoma",1,12));
			}
			Hospedagem hosp = new Hospedagem();
			HospedagemDAO daoHos = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoHos = new HospedagemDAO(conexao);
				hosp = daoHos.listaPorCod(cod);
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			Cliente cli = new Cliente();
			ClienteDAO daoCli = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoCli = new ClienteDAO(conexao);
				cli = daoCli.getPorCod(hosp.getCod_cliente());
			}catch(SQLException e){
				e.printStackTrace();
			}
			Conta conta = new Conta();
			ContaDAO daoConta = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoConta = new ContaDAO(conexao);
				conta = daoConta.listaPorCod(hosp.getCod());
			}catch(SQLException e){
				e.printStackTrace();
			}
			cons = conta.getValorTotal();
			
			ArrayList<Quarto_Hospedagem> vetQH = new ArrayList<Quarto_Hospedagem>();
			Quarto_HospedagemDAO daoQH = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoQH = new Quarto_HospedagemDAO(conexao);
				vetQH.addAll(daoQH.listaPorCodHosp(hosp.getCod()));
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			System.out.println(vetQH.get(0).getQuantidade());
			Quarto qua = new Quarto();
			QuartoDAO daoQua = null;
			double aux=0;
			for(int i=0; i<vetQH.size(); i++){
				try{
					conexao = ConnectionFactory.getConnection();
					daoQua = new QuartoDAO(conexao);
					
					qua = daoQua.getPorCod(vetQH.get(i).getQuarto_cod());
					aux = qua.getValorDiaria() * vetQH.get(i).getQuantidade();
					diaria = diaria +aux;
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			{
				tfCliente = new JTextField();
				panel.add(tfCliente);
				tfCliente.setBounds(97, 34, 276, 23);
				tfCliente.setEditable(false);
				tfCliente.setText(cli.getNome());
				
			}
			{
				jLabel1 = new JLabel();
				panel.add(jLabel1);
				jLabel1.setText("Diárias dos Quartos   .............................      R$");
				jLabel1.setBounds(11, 82, 283, 16);
				jLabel1.setFont(new java.awt.Font("Tahoma",0,12));
			}
			{
				jLabel2 = new JLabel();
				panel.add(jLabel2);
				jLabel2.setText("Serviços e Produtos Consumidos ............      R$");
				jLabel2.setBounds(12, 114, 277, 16);
				jLabel2.setFont(new java.awt.Font("Tahoma",0,12));
			}
			{
				jLabel3 = new JLabel();
				panel.add(jLabel3);
				jLabel3.setText("Total a Pagar ......................................      R$");
				jLabel3.setBounds(12, 159, 277, 16);
				jLabel3.setFont(new java.awt.Font("Tahoma",0,12));
			}
			{
				tfDiaQua = new JTextField();
				panel.add(tfDiaQua);
				tfDiaQua.setBounds(289, 79, 84, 23);
				tfDiaQua.setEditable(false);
				tfDiaQua.setText(""+diaria);
			}
			{
				tfCons = new JTextField();
				panel.add(tfCons);
				tfCons.setBounds(289, 111, 85, 23);
				tfCons.setEditable(false);
				tfCons.setText(""+cons);
			}
			double total = diaria+cons;
			{
				tfTotal = new JTextField();
				panel.add(tfTotal);
				tfTotal.setBounds(289, 156, 85, 23);
				tfTotal.setEditable(false);
				tfTotal.setText(""+total);
			}
			panel.setLayout(null);
			panel.setPreferredSize(new java.awt.Dimension(380, 243));
			frame.setTitle("Check Out - Hotel");
			frame.setModal(true);
			frame.setSize(389, 281);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.setVisible(true);
			frame.setPreferredSize(new java.awt.Dimension(389, 281));
		}
		
	}
	public static void main(String[] args) {
		new JanelaDeCheckOutQua2(3, JM);
		
	}
	private class OkListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Conta conta = new Conta();
			ContaDAO daoConta = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoConta = new ContaDAO(conexao);
				conta = daoConta.listaPorCod(codH);
				daoConta.addValor(Double.parseDouble(tfDiaQua.getText()), codH);
				daoConta.tornaPago(codH);
				
				Hospedagem hosp = new Hospedagem();
				HospedagemDAO daoHos = null;
				try{
					conexao = ConnectionFactory.getConnection();
					daoHos = new HospedagemDAO(conexao);
					hosp = daoHos.listaPorCod(codH);
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				ClienteDAO daoCli = null;
				try{
					conexao = ConnectionFactory.getConnection();
					daoCli = new ClienteDAO(conexao);
					daoCli.tornaNaoHospedado(hosp.getCod_cliente());
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				
				
				
				
				ArrayList<Quarto_Hospedagem> vetQH = new ArrayList<Quarto_Hospedagem>();
				Quarto_HospedagemDAO daoQH = null;
				try{
					conexao = ConnectionFactory.getConnection();
					daoQH = new Quarto_HospedagemDAO(conexao);
					vetQH.addAll(daoQH.listaPorCodHosp(hosp.getCod()));
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				Quarto qua = new Quarto();
				QuartoDAO daoQua = null;
				double aux=0;
				for(int i=0; i<vetQH.size(); i++){
					try{
						conexao = ConnectionFactory.getConnection();
						daoQua = new QuartoDAO(conexao);
						
						qua = daoQua.getPorCod(vetQH.get(i).getQuarto_cod());
						daoQua.tornaDisponivel(qua.getCod());
						
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
				
				
				
				
				JOptionPane.showMessageDialog(null, "Check Out realizado com sucesso!");
				fechar = true;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	private class SairListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			fechar = true;
			JanelaDeCheckOutQua j = new JanelaDeCheckOutQua(JM);
		}
	}
	
	
}