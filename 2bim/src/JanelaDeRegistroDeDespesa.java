import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


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
public class JanelaDeRegistroDeDespesa extends javax.swing.JDialog {
	private JLabel lbNomeCli;
	private JTextField tfNomeCli;
	private JButton butCancelar;
	private JButton butSalvar;
	private JTextField tfValor;
	private JLabel lbValor;
	private JTextField tfServico;
	private JLabel lbServico;
	private JTextField tfCPFdoCli;
	private JLabel lbCPF;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				JanelaDeRegistroDeDespesa inst = new JanelaDeRegistroDeDespesa(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public JanelaDeRegistroDeDespesa(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("Registro de Despesa");
				{
					lbNomeCli = new JLabel();
					getContentPane().add(lbNomeCli);
					lbNomeCli.setText("Nome do Cliente");
					lbNomeCli.setBounds(12, 12, 107, 19);
					lbNomeCli.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					tfNomeCli = new JTextField();
					getContentPane().add(tfNomeCli);
					tfNomeCli.setBounds(12, 37, 231, 23);
				}
				{
					lbCPF = new JLabel();
					getContentPane().add(lbCPF);
					lbCPF.setBounds(251, 13, 94, 24);
					lbCPF.setText("CPF do Cliente");
					lbCPF.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					tfCPFdoCli = new JTextField();
					getContentPane().add(tfCPFdoCli);
					tfCPFdoCli.setBounds(249, 37, 123, 23);
				}
				{
					lbServico = new JLabel();
					getContentPane().add(lbServico);
					lbServico.setText("Serviço");
					lbServico.setBounds(12, 78, 54, 19);
					lbServico.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					tfServico = new JTextField();
					getContentPane().add(tfServico);
					tfServico.setBounds(12, 103, 231, 23);
				}
				{
					lbValor = new JLabel();
					getContentPane().add(lbValor);
					lbValor.setText("Valor do Serviço");
					lbValor.setBounds(249, 79, 102, 16);
					lbValor.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					tfValor = new JTextField();
					getContentPane().add(tfValor);
					tfValor.setBounds(249, 103, 123, 23);
					tfValor.setText("R$");
				}
				{
					butSalvar = new JButton();
					getContentPane().add(butSalvar);
					butSalvar.setText("Salvar");
					butSalvar.setBounds(190, 145, 89, 23);
				}
				{
					butCancelar = new JButton();
					getContentPane().add(butCancelar);
					butCancelar.setText("Cancelar");
					butCancelar.setBounds(284, 145, 89, 23);
				}
			}
			this.setSize(400, 213);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
