package br.univel.cliente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import br.univel.jshare.comum.Cliente;
import br.univel.jshare.comum.IServer;

/**
 * Classe de execucao
 * 
 * @author EHDFREITAS
 *
 */
public class MainApp extends JFrame implements Remote {

	private JButton btnDesconectar;
	private JButton btnConectar;

	private static IServer servidor;
	private static Registry registry;

	private JPanel panelConexao;
	private JPanel panelPrincipal;
	private JTabbedPane panelGeral = new JTabbedPane();
	private static final long serialVersionUID = 1L;

	public static final int LARGURA = 600;
	public static final int ALTURA = 500;
	public static final String PATH = "C:\\Shared\\";

	private JPanel jpTelaServidor = new PanelServidor();
	private JPanel jpTelaCliente = new PanelCliente();

	private Dimension dimensaoTela = Toolkit.getDefaultToolkit().getScreenSize();
	private JNumberField nfPortaConexao;
	private JTextField tfIpConexao;

	private boolean souServidor = false;
	private Object registryCliente;
	private static Cliente meuCliente;
	private static String meuIp;

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

		try {
			InetAddress IP = InetAddress.getLocalHost();
			this.meuIp = IP.getHostAddress();
		} catch (UnknownHostException ex) {
			PanelServidor.getTextArea().append("Erro ao buscar o IP da maquina!\n" + ex.getMessage());
		}

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

		nfPortaConexao = new JNumberField();
		GridBagConstraints gbc_nfPortaConexao = new GridBagConstraints();
		gbc_nfPortaConexao.fill = GridBagConstraints.HORIZONTAL;
		gbc_nfPortaConexao.anchor = GridBagConstraints.NORTH;
		gbc_nfPortaConexao.gridx = 1;
		gbc_nfPortaConexao.gridy = 1;
		panelConexao.add(nfPortaConexao, gbc_nfPortaConexao);
		nfPortaConexao.setColumns(10);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(conectar());

		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.anchor = GridBagConstraints.NORTH;
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.insets = new Insets(0, 10, 10, 10);
		gbc_btnConectar.gridx = 0;
		gbc_btnConectar.gridy = 1;
		panelPrincipal.add(btnConectar, gbc_btnConectar);

		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setEnabled(false);
		btnDesconectar.addActionListener(desconectar());
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 10, 10, 10);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panelPrincipal.add(btnDesconectar, gbc_btnNewButton);

		GridBagConstraints gbc_panelGeral = new GridBagConstraints();
		gbc_panelGeral.gridwidth = 2;
		gbc_panelGeral.insets = new Insets(10, 10, 10, 10);
		gbc_panelGeral.fill = GridBagConstraints.BOTH;
		gbc_panelGeral.gridx = 0;
		gbc_panelGeral.gridy = 2;
		panelPrincipal.add(panelGeral, gbc_panelGeral);

		nfPortaConexao.setText("1818");
		tfIpConexao.setText(meuIp);
		panelGeral.addTab("Servidor", jpTelaServidor);
	}

	public ActionListener desconectar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (souServidor == false) {
					try {
						servidor.desconectar(meuCliente);
						resetarTela();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else {
					if (Servidor.desligar()) {
						resetarTela();
					}
				}
			}
		};
	}

	protected void resetarTela() {
		tfIpConexao.setText(meuIp);
		nfPortaConexao.setText("1818");

		tfIpConexao.setEnabled(true);
		nfPortaConexao.setEnabled(true);

		btnConectar.setEnabled(true);
		btnDesconectar.setEnabled(false);

		servidor = null;
		registry = null;

		panelGeral.remove(jpTelaCliente);

		PanelServidor.getTextArea().setText("");
	}

	public ActionListener conectar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nfPortaConexao.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Porta Invalida!");
					return;
				}
				if (tfIpConexao.getText().equals(meuIp)) {
					souServidor = true;
				}
				conectarServidor(tfIpConexao.getText(), nfPortaConexao.getNumber());
				jpTelaCliente.setEnabled(true);
			}
		};
	}

	private void conectarServidor(String text, Integer number) {
		if (nfPortaConexao.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Porta Invalida!");
			return;
		}

		tfIpConexao.setEnabled(false);
		nfPortaConexao.setEnabled(false);
		btnConectar.setEnabled(false);

		new Servidor(tfIpConexao.getText(), nfPortaConexao.getNumber());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			PanelServidor.getTextArea().append("Erro ao pausar thread atual!\n");
		}
		
		try {
			registry = LocateRegistry.getRegistry(tfIpConexao.getText(), nfPortaConexao.getNumber());

			servidor = (IServer) registry.lookup(IServer.NOME_SERVICO);

			meuCliente = new Cliente();

			meuCliente.setNome("Dread");
			meuCliente.setIp(meuIp);
			meuCliente.setPorta(nfPortaConexao.getNumber());

			servidor.registrarCliente(meuCliente);

			btnConectar.setEnabled(false);
			btnDesconectar.setEnabled(true);
			panelGeral.addTab("Cliente", jpTelaCliente);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel e conectar ao servidor, tente novamente!");
			PanelServidor.getTextArea().append("Erro ao se conectar ao servidor!\n" + e.getMessage());
			resetarTela();
		} catch (NotBoundException e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel e conectar ao servidorm, tente novamente!");
			PanelServidor.getTextArea().append("Erro ao se conectar ao servidor!\n" + e.getMessage());
			resetarTela();
		}
	}

	public static IServer getServidor() {
		return servidor;
	}

	public static Cliente getMeuCliente() {
		return meuCliente;
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
