package br.univel.cliente.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe de execucao
 * 
 * @author EHDFREITAS
 *
 */
public class MainApp extends JFrame {
//	private JPanel panelGeral;

	private JButton btnNewButton;
	private JButton btnConectar;
	
	private JPanel panelConexao;
	private JPanel panelPrincipal;
	private JTabbedPane panelGeral = new JTabbedPane();
	private static final long serialVersionUID = 1L;

	public static final int LARGURA = 600;
	public static final int ALTURA = 500;

	private JPanel jpTelaServidor = new PanelServidor();
	private JPanel jpTelaCliente = new PanelCliente();

	Dimension dimensaoTela = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField nfPortaConexao;
	private JTextField tfIpConexao;

	/**
	 * Classe construtora
	 */
	public MainApp() {
		setSize(LARGURA, ALTURA);
		setLocation((dimensaoTela.width - this.getSize().width) / 2, (dimensaoTela.height - this.getSize().height) / 2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(LARGURA, ALTURA);
		this.setResizable(false);
		this.setVisible(true);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		panelPrincipal = new JPanel();
		GridBagConstraints gbc_panelPrincipal = new GridBagConstraints();
		gbc_panelPrincipal.fill = GridBagConstraints.BOTH;
		gbc_panelPrincipal.gridx = 0;
		gbc_panelPrincipal.gridy = 0;
		getContentPane().add(panelPrincipal, gbc_panelPrincipal);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[] { 300, 0, 0 };
		gbl_panelPrincipal.rowHeights = new int[] { 48, 0, 0, 0 };
		gbl_panelPrincipal.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelPrincipal.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelPrincipal.setLayout(gbl_panelPrincipal);

		panelConexao = new JPanel();
		GridBagConstraints gbc_panelConexao = new GridBagConstraints();
		gbc_panelConexao.gridwidth = 2;
		gbc_panelConexao.anchor = GridBagConstraints.NORTH;
		gbc_panelConexao.insets = new Insets(10, 10, 10, 10);
		gbc_panelConexao.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelConexao.gridx = 0;
		gbc_panelConexao.gridy = 0;
		panelPrincipal.add(panelConexao, gbc_panelConexao);
		GridBagLayout gbl_panelConexao = new GridBagLayout();
		gbl_panelConexao.columnWidths = new int[] { 359, 0, 0 };
		gbl_panelConexao.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelConexao.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelConexao.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelConexao.setLayout(gbl_panelConexao);

		JLabel lblNewLabel_1 = new JLabel("IP");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panelConexao.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Porta");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panelConexao.add(lblNewLabel, gbc_lblNewLabel);

		tfIpConexao = new JTextField();
		GridBagConstraints gbc_tfIpConexao = new GridBagConstraints();
		gbc_tfIpConexao.anchor = GridBagConstraints.NORTH;
		gbc_tfIpConexao.insets = new Insets(0, 0, 0, 5);
		gbc_tfIpConexao.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfIpConexao.gridx = 0;
		gbc_tfIpConexao.gridy = 1;
		panelConexao.add(tfIpConexao, gbc_tfIpConexao);
		tfIpConexao.setColumns(10);

		nfPortaConexao = new JTextField();
		nfPortaConexao.setText("1818");
		GridBagConstraints gbc_nfPortaConexao = new GridBagConstraints();
		gbc_nfPortaConexao.fill = GridBagConstraints.HORIZONTAL;
		gbc_nfPortaConexao.anchor = GridBagConstraints.NORTH;
		gbc_nfPortaConexao.gridx = 1;
		gbc_nfPortaConexao.gridy = 1;
		panelConexao.add(nfPortaConexao, gbc_nfPortaConexao);
		nfPortaConexao.setColumns(10);

		btnConectar = new JButton("Conectar");
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.anchor = GridBagConstraints.NORTH;
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.insets = new Insets(0, 10, 10, 10);
		gbc_btnConectar.gridx = 0;
		gbc_btnConectar.gridy = 1;
		panelPrincipal.add(btnConectar, gbc_btnConectar);

		btnNewButton = new JButton("Desconectar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 10, 10, 10);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panelPrincipal.add(btnNewButton, gbc_btnNewButton);

		GridBagConstraints gbc_panelGeral = new GridBagConstraints();
		gbc_panelGeral.gridwidth = 2;
		gbc_panelGeral.insets = new Insets(10, 10, 10, 10);
		gbc_panelGeral.fill = GridBagConstraints.BOTH;
		gbc_panelGeral.gridx = 0;
		gbc_panelGeral.gridy = 2;
		panelPrincipal.add(panelGeral, gbc_panelGeral);

		panelGeral.addTab("Servidor", jpTelaServidor);
		panelGeral.addTab("Cliente", jpTelaCliente);
	}

	/**
	 * Metodo Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MainApp();
	}
}
