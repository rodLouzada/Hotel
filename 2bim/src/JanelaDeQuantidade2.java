import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
public class JanelaDeQuantidade2 {
	private JLabel lbQtd;
	static JTextField tfQtd;
	private JButton btOk;
	private JPanel panel;
	private JDialog frame;
	private int op;
	
	
	public int getOp() {
		return op;
	}
	public JanelaDeQuantidade2(){
		lbQtd = new JLabel("");
		lbQtd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbQtd.setBounds(5, 13, 231, 20);
		tfQtd = new JTextField(20);
		tfQtd.setBounds(5,38,200,20);
		btOk = new JButton("Salvar");
		btOk.setForeground(new Color(30, 144, 255));
		btOk.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\save.png"));
		btOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btOk.setBounds(149, 71, 107, 39);
		btOk.addActionListener(new OkListener());
		JButton btSair = new JButton("Cancelar");
		btSair.setForeground(new Color(255, 0, 0));
		btSair.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
		btSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btSair.setBounds(5, 76, 124, 34);
		btSair.addActionListener(new SairListener());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(lbQtd);
		lbQtd.setText("Quantidade de Pessoas por Quarto:");
		panel.add(tfQtd);
		panel.add(btOk);
		panel.add(btSair);
		
		frame = new JDialog();
		frame.setTitle("Quantidade de Pessoas");
		frame.setModal(true);
		frame.getContentPane().add(panel);
		panel.setPreferredSize(new java.awt.Dimension(229, 116));
		frame.setSize(274, 159);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setPreferredSize(new java.awt.Dimension(230, 120));

	}
	public static void main(String[] args) {
		new JanelaDeQuantidade2();
		
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
	
	public JTextField getTfQtd(){
		return tfQtd;
	}
	
}