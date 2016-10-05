import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JanelaDeQuantidade {
	private JLabel lbQtd;
	static JTextField tfQtd;
	private JButton btOk;
	private JPanel panel;
	private JDialog frame;
	private int op;
	
	
	public int getOp() {
		return op;
	}
	public JanelaDeQuantidade(){
		lbQtd = new JLabel("");
		lbQtd.setBounds(5, 5, 100, 20);
		tfQtd = new JTextField(20);
		tfQtd.setBounds(5,30,200,20);
		btOk = new JButton("Ok");
		btOk.setBounds(5, 56, 90, 20);
		btOk.addActionListener(new OkListener());
		JButton btSair = new JButton("Cancelar");
		btSair.setBounds(116, 56, 89, 20);
		btSair.addActionListener(new SairListener());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(lbQtd);
		lbQtd.setText("Quantidade:");
		panel.add(tfQtd);
		panel.add(btOk);
		panel.add(btSair);
		
		frame = new JDialog();
		frame.setTitle("Quantidade");
		frame.setModal(true);
		frame.add(panel);
		panel.setPreferredSize(new java.awt.Dimension(229, 116));
		frame.setSize(220, 115);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setPreferredSize(new java.awt.Dimension(230, 120));

	}
	public static void main(String[] args) {
		new JanelaDeQuantidade();
		
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