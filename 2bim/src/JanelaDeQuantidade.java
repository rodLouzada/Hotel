import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

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
		lbQtd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbQtd.setBounds(5, 5, 100, 20);
		tfQtd = new JTextField(20);
		tfQtd.setBounds(5,30,200,20);
		btOk = new JButton("Salvar");
		btOk.setForeground(new Color(30, 144, 255));
		btOk.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\save.png"));
		btOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		btOk.setBounds(158, 63, 107, 39);
		btOk.addActionListener(new OkListener());
		JButton btSair = new JButton("Cancelar");
		btSair.setIcon(new ImageIcon("C:\\Users\\Rhay\\Documents\\2016Cefet\\IHC\\VersaoSistema28\\Hotel_Atualizado\\2bim\\icons\\cancel.png"));
		btSair.setForeground(new Color(255, 0, 0));
		btSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btSair.setBounds(15, 68, 124, 34);
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
		frame.getContentPane().add(panel);
		panel.setPreferredSize(new java.awt.Dimension(229, 116));
		frame.setSize(283, 146);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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