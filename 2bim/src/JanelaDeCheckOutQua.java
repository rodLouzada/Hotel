import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Date;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.SQLException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


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


public class JanelaDeCheckOutQua {
	private JPanel panel;
	private JButton btSalvar;
	private JTextField tfIDHosp;
	private JLabel lbCodHosp;
	private JTextField tfDataSaida;
	private JLabel lbDataSaida;
	private JTextField tfDataEntrada;
	private JLabel lbDataEntrada;
	private JComboBox cbCliente;
	private JLabel lbCliente;
	private JDialog frame;
	private int op;
	private Connection conexao;
	
	public boolean fechar = false;
	private JanelaMenuPrincipal JM;
	
	public int getOp() {
		return op;
	}
	int contador=0;
	public JanelaDeCheckOutQua(JanelaMenuPrincipal janMenPrin){
		ArrayList<Quarto> vetor = new ArrayList<Quarto>();
		JM = janMenPrin;

		Connection conexao = null;
		QuartoDAO quarto = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			quarto = new QuartoDAO(conexao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			vetor = quarto.listaTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < vetor.size(); i++) {
			if (vetor.get(i).getOcupado().equals("true")){
				contador++;
			}
		}
		if(contador == 0){
			JOptionPane.showMessageDialog(frame, "Nenhuma hospedagem disponível!", "Hotel Vazio", JOptionPane.ERROR_MESSAGE);
		}else{

		ClienteDAO daoCli = null;
		QuartoDAO daoQua = null;
		{
			panel = new JPanel();
			

			{
				JButton btSair = new JButton("Cancelar");
				panel.add(btSair);
				btSair.setBounds(161, 134, 89, 23);
				btSair.addActionListener(new SairListener());
			}
			String list[] ;
			
			HospedagemDAO daoHosp = null;
			{
				
				try {
					conexao = ConnectionFactory.getConnection();
					daoHosp = new HospedagemDAO(conexao);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ArrayList<Hospedagem> vetHosp = new ArrayList<Hospedagem>();
				try {
					vetHosp = daoHosp.listaTodos();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//
//				ArrayList<Quarto_Hospedagem> vetQuaHo = new ArrayList<Quarto_Hospedagem>();
//				Quarto_HospedagemDAO daoQuaHo = null;
//				try {
//					vetQuaHo = daoQuaHo.listaTodos();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				//
				try {
					conexao = ConnectionFactory.getConnection();
					daoCli = new ClienteDAO(conexao);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ArrayList<Cliente> vetCli = new ArrayList<Cliente>();
				for (int i=0; i<vetHosp.size(); i++){
					try {
						vetCli.addAll((daoCli.listaPorCod(vetHosp.get(i).getCod_cliente())));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				try {
					conexao = ConnectionFactory.getConnection();
					daoQua = new QuartoDAO(conexao);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ArrayList<Quarto> vetQua = new ArrayList<Quarto>();
				
				try {
					vetQua = daoQua.listaTodos();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				ArrayList<Quarto_Hospedagem> vetQH = new ArrayList<Quarto_Hospedagem>();
				Quarto_HospedagemDAO daoQH = null;
				try{
					conexao = ConnectionFactory.getConnection();
					daoQH = new Quarto_HospedagemDAO(conexao);
					vetQH.addAll(daoQH.listaTodos());
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				
				int j=0;
				list = new String[vetQH.size()];
//				for (int i = 0; i < vetQua.size(); i++){
//					if (vetQua.get(i).getOcupado().equals("true")){
//						list[j] = ""+vetQua.get(i).getNumero();
//						j++;
//					}
//				}
				ComboBoxModel cbClienteModel = 
					new DefaultComboBoxModel(list);
				lbCliente = new JLabel();
				panel.add(lbCliente);
				lbCliente.setText("Quarto");
				lbCliente.setBounds(12, 12, 56, 16);
				lbCliente.setFont(new java.awt.Font("Tahoma",1,12));
			}
			{
				ComboBoxModel cbClienteModel = 
					new DefaultComboBoxModel(list);
				cbCliente = new JComboBox();
				panel.add(cbCliente);
				cbCliente.setModel(cbClienteModel);
				cbCliente.setBounds(12, 34, 271, 23);
				cbCliente.addActionListener(new comboListener());
			}
			String numQua = "-";
						
			int codQua=0;
			try{
				codQua = daoQua.getCodPorNum(numQua);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(codQua);
			Quarto_HospedagemDAO daoQua_hos = null;
			int codHosp=0;
			try{
				conexao = ConnectionFactory.getConnection();
				daoQua_hos = new Quarto_HospedagemDAO(conexao);
				codHosp = daoQua_hos.getCodPorCodQua(codQua);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Hospedagem hosp = new Hospedagem();
			try{
				hosp = daoHosp.listaPorCod(codHosp);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			{
				lbDataEntrada = new JLabel();
				panel.add(lbDataEntrada);
				lbDataEntrada.setText("Data Entrada");
				lbDataEntrada.setBounds(63, 69, 104, 16);
				lbDataEntrada.setFont(new java.awt.Font("Tahoma",1,12));
			}
			{
				tfDataEntrada = new JTextField();
				panel.add(tfDataEntrada);
				tfDataEntrada.setBounds(61, 92, 108, 23);
				tfDataEntrada.setEditable(false);
				Date d = new java.sql.Date(hosp.getDataEntrada().getTimeInMillis());
				SimpleDateFormat formatIso = new SimpleDateFormat("yyyy-MM-dd");  
			    SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy"); 
			    try{  
			         Date date = formatIso.parse(d+"");  
			         tfDataEntrada.setText(""+formatBra.format(date));
			      }  catch(ParseException e) {  
			         e.printStackTrace();   
			      }
			}
			{
				lbDataSaida = new JLabel();
				panel.add(getLbDataSaida());
				lbDataSaida.setText("Data Saída");
				lbDataSaida.setBounds(179, 69, 92, 16);
				lbDataSaida.setFont(new java.awt.Font("Tahoma",1,12));
			}
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Date date = new Date();
		    String d = dateFormat.format(date);
			{
				MaskFormatter mascara = null;
				try {
					mascara = new MaskFormatter("##/##/####");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				tfDataSaida = new JFormattedTextField(mascara);
				panel.add(tfDataSaida);
				tfDataSaida.setBounds(175, 92, 108, 23);
				tfDataSaida.setText(d);
			}
			{
				lbCodHosp = new JLabel();
				panel.add(lbCodHosp);
				lbCodHosp.setText("ID Hosp");
				lbCodHosp.setBounds(12, 70, 64, 16);
				lbCodHosp.setFont(new java.awt.Font("Tahoma",1,11));
			}
			{
				tfIDHosp = new JTextField();
				panel.add(tfIDHosp);
				tfIDHosp.setBounds(12, 92, 43, 23);
				tfIDHosp.setEditable(false);
				tfIDHosp.setText(""+codHosp);
			}
			{
				btSalvar = new JButton();
				panel.add(btSalvar);
				btSalvar.setText("OK");
				btSalvar.setBounds(55, 134, 89, 23);
				btSalvar.addActionListener(new OkListener());
			}
			panel.setLayout(null);
			panel.setPreferredSize(new java.awt.Dimension(295, 169));
			
			janMenPrin.frameConteudo.setTitle("Check Out - Hotel");
			janMenPrin.frameConteudo.getContentPane().add(BorderLayout.CENTER, panel);
			janMenPrin.frameConteudo.pack(); // ajusta o tamanho da janela (frame)
			janMenPrin.frameConteudo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Sair do
			janMenPrin.frameConteudo.setVisible(true); // torna a janela visÃ­vel.ss	
		}
		}
		
	}
	public static void main(String[] args) {
		
	}
	
	public JLabel getLbDataSaida() {
		return lbDataSaida;
	}
	private class OkListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Calendar data = new GregorianCalendar();
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			try {
				data.setTime(f.parse(tfDataSaida.getText()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			HospedagemDAO daoHosp = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoHosp = new HospedagemDAO(conexao);
				daoHosp.setDataSaida(data);
				daoHosp.setCheckout();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			fechar = true;
			JanelaDeCheckOutQua2 jan = new JanelaDeCheckOutQua2(Integer.parseInt(tfIDHosp.getText()), JM);
		}
	}
	private class SairListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			fechar = true;
		}
	}
	public class comboListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			String numQua = cbCliente.getSelectedItem().toString();
			
			int codQua=0;
			QuartoDAO daoQua = null;
			HospedagemDAO daoHosp = null;
			try{
				conexao = ConnectionFactory.getConnection();
				daoQua = new QuartoDAO(conexao);
				codQua = daoQua.getCodPorNum(numQua);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(codQua);
			Quarto_HospedagemDAO daoQua_hos = null;
			int codHosp=0;
			try{
				conexao = ConnectionFactory.getConnection();
				daoQua_hos = new Quarto_HospedagemDAO(conexao);
				codHosp = daoQua_hos.getCodPorCodQua(codQua);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Hospedagem hosp = new Hospedagem();
			try{
				conexao = ConnectionFactory.getConnection();
				daoHosp = new HospedagemDAO(conexao);
				hosp = daoHosp.listaPorCod(codHosp);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date d = new java.sql.Date(hosp.getDataEntrada().getTimeInMillis());
			//formatando data
			SimpleDateFormat formatIso = new SimpleDateFormat("yyyy-MM-dd");  
		    SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy"); 
		    try{  
		         Date date = formatIso.parse(d+"");  
		         tfDataEntrada.setText(""+formatBra.format(date));
		      }  catch(ParseException e) {  
		         e.printStackTrace();   
		      }
			
			
			tfIDHosp.setText(""+codHosp);
		}
	}
	
}