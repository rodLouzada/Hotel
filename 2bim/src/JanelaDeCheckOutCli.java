import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.sql.Date;
import java.util.Date;
import java.sql.SQLException;

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
import javax.swing.WindowConstants;
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
public class JanelaDeCheckOutCli {
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
	
	private class OkKeyListener implements KeyListener{
		 @Override
		    public void keyPressed(KeyEvent e) {
			  if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btSalvar.doClick();
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


	public int getOp() {
		return op;
	}
	int contador=0;
	/**
	 * @wbp.parser.entryPoint
	 */
	public JanelaDeCheckOutCli(){
		ArrayList<Quarto> vetorQ = new ArrayList<Quarto>();

		Connection conexao = null;
		QuartoDAO quarto = null;

		
		try {
			conexao = ConnectionFactory.getConnection();
			quarto = new QuartoDAO(conexao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vetorQ = quarto.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < vetorQ.size(); i++) {
			if (vetorQ.get(i).getOcupado().equals("true")){
				contador++;
			}
		}
		if(contador == 0){
			JOptionPane.showMessageDialog(frame, "Nenhuma hospedagem disponível!", "Hotel Vazio", JOptionPane.ERROR_MESSAGE);
		}else{

		ClienteDAO daoCli = null;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<Hospedagem> vetor = new ArrayList<Hospedagem>();
				try {
					vetor = daoHosp.listaTodos();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					conexao = ConnectionFactory.getConnection();
					daoCli = new ClienteDAO(conexao);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<Cliente> vetor2 = new ArrayList<Cliente>();
				for (int i=0; i<vetor.size(); i++){
					try {
						vetor2.addAll((daoCli.listaPorCod(vetor.get(i).getCod_cliente())));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				list = new String[vetor.size()];
				for (int i = 0; i < vetor2.size(); i++){
					list[i] = vetor2.get(i).getNome();
				}
				ComboBoxModel cbClienteModel = 
					new DefaultComboBoxModel(list);
				lbCliente = new JLabel();
				panel.add(lbCliente);
				lbCliente.setText("Cliente");
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
			ArrayList<Cliente> cli = new ArrayList<Cliente>();
			try{
				cli.addAll(daoCli.listaPorNome(cbCliente.getSelectedItem().toString()));
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
			ArrayList<Hospedagem> vetHosp;
			{
				tfDataEntrada = new JTextField();
				panel.add(tfDataEntrada);
				tfDataEntrada.setBounds(61, 92, 108, 23);
				tfDataEntrada.setEditable(false);
				vetHosp = new ArrayList<Hospedagem>();
				try {
					vetHosp.addAll(daoHosp.listaPorCodCli(cli.get(0).getCod()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date d = new java.sql.Date(vetHosp.get(0).getDataEntrada().getTimeInMillis());
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
					// TODO Auto-generated catch block
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
				tfIDHosp.setText(""+vetHosp.get(0).getCod());
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
			frame = new JDialog();
			frame.setTitle("Check Out - Hotel");
			frame.setModal(true);
			frame.setSize(295, 200);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.getContentPane().add(panel, BorderLayout.NORTH);
			frame.setVisible(true);
			frame.setPreferredSize(new java.awt.Dimension(295, 200));
		}
		}
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		new JanelaDeCheckOutCli();
		
	}
	
	public JLabel getLbDataSaida() {
		return lbDataSaida;
	}
	private class OkListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 1;
			frame.dispose();
		}
	}
	private class SairListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			op = 2;
			frame.dispose();
		}
	}
	public class comboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String nome = cbCliente.getSelectedItem().toString();
			//Connection conexao = null;
			ClienteDAO daoCli2 = null;
			try {
				daoCli2 = new ClienteDAO(conexao);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			ArrayList<Cliente> cli2 = new ArrayList<Cliente>();
			try{
				cli2.addAll(daoCli2.listaPorNome(nome));
			}catch (SQLException e) {
				e.printStackTrace();
			}
			ArrayList<Hospedagem> vetHosp = new ArrayList<Hospedagem>();
			HospedagemDAO daoHosp = null;
			try {
				daoHosp = new HospedagemDAO(conexao);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				vetHosp.addAll(daoHosp.listaPorCodCli(cli2.get(0).getCod()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Date d = new java.sql.Date(vetHosp.get(0).getDataEntrada().getTimeInMillis());
			//formatando data
			SimpleDateFormat formatIso = new SimpleDateFormat("yyyy-MM-dd");  
		    SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy"); 
		    try{  
		         Date date = formatIso.parse(d+"");  
		         tfDataEntrada.setText(""+formatBra.format(date));
		      }  catch(ParseException e) {  
		         e.printStackTrace();   
		      }
			
			
			tfIDHosp.setText(""+vetHosp.get(0).getCod());
		}
	}
	
}