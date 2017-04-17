package br.univel.cliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import br.univel.jshare.comum.Arquivo;
import br.univel.jshare.comum.Cliente;
import br.univel.jshare.comum.IServer;
import br.univel.jshare.comum.TipoFiltro;

public class PanelCliente extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table = new JTable();
	private JTextField tfBuscar;
	private JTextField tfValor;

	private Map<Cliente, List<Arquivo>> mapaArquivos = new HashMap<Cliente, List<Arquivo>>();
	private static List<Arquivo> listaArquivos = new ArrayList<Arquivo>();
	private JComboBox cbFiltro;

	public PanelCliente() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 296, 271, 0 };
		gridBagLayout.rowHeights = new int[] { 57, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(5, 5, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 55, 278, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 31, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Buscar:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		tfBuscar = new JTextField();
		GridBagConstraints gbc_tfBuscar = new GridBagConstraints();
		gbc_tfBuscar.gridwidth = 3;
		gbc_tfBuscar.insets = new Insets(5, 5, 5, 5);
		gbc_tfBuscar.fill = GridBagConstraints.BOTH;
		gbc_tfBuscar.gridx = 1;
		gbc_tfBuscar.gridy = 0;
		panel.add(tfBuscar, gbc_tfBuscar);
		tfBuscar.setColumns(10);

		JLabel lblFiltro = new JLabel("Filtro:");
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.fill = GridBagConstraints.VERTICAL;
		gbc_lblFiltro.anchor = GridBagConstraints.EAST;
		gbc_lblFiltro.insets = new Insets(5, 5, 5, 5);
		gbc_lblFiltro.gridx = 0;
		gbc_lblFiltro.gridy = 1;
		panel.add(lblFiltro, gbc_lblFiltro);

		cbFiltro = new JComboBox(TipoFiltro.values());
		GridBagConstraints gbc_cbFiltro = new GridBagConstraints();
		gbc_cbFiltro.insets = new Insets(5, 5, 5, 5);
		gbc_cbFiltro.anchor = GridBagConstraints.NORTH;
		gbc_cbFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFiltro.gridx = 1;
		gbc_cbFiltro.gridy = 1;
		panel.add(cbFiltro, gbc_cbFiltro);

		JLabel lblNewLabel_1 = new JLabel("Valor:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		tfValor = new JTextField();
		GridBagConstraints gbc_tfValor = new GridBagConstraints();
		gbc_tfValor.insets = new Insets(5, 5, 5, 5);
		gbc_tfValor.fill = GridBagConstraints.BOTH;
		gbc_tfValor.gridx = 3;
		gbc_tfValor.gridy = 1;
		panel.add(tfValor, gbc_tfValor);
		tfValor.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(actionBuscar());
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.anchor = GridBagConstraints.NORTH;
		gbc_btnBuscar.gridwidth = 2;
		gbc_btnBuscar.insets = new Insets(3, 5, 3, 5);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 1;
		add(btnBuscar, gbc_btnBuscar);

		JButton btnPublicar = new JButton("Publicar Minha Lista");
		btnPublicar.addActionListener(actionPublicar());
		GridBagConstraints gbc_btnPublicar = new GridBagConstraints();
		gbc_btnPublicar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPublicar.anchor = GridBagConstraints.NORTH;
		gbc_btnPublicar.gridwidth = 2;
		gbc_btnPublicar.insets = new Insets(3, 5, 3, 5);
		gbc_btnPublicar.gridx = 0;
		gbc_btnPublicar.gridy = 2;
		add(btnPublicar, gbc_btnPublicar);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		JButton btnBaixar = new JButton("Baixar");
		btnBaixar.addActionListener(actionBaixar());
		GridBagConstraints gbc_BtnBaixar = new GridBagConstraints();
		gbc_BtnBaixar.anchor = GridBagConstraints.SOUTH;
		gbc_BtnBaixar.fill = GridBagConstraints.HORIZONTAL;
		gbc_BtnBaixar.gridwidth = 2;
		gbc_BtnBaixar.insets = new Insets(3, 5, 3, 5);
		gbc_BtnBaixar.gridx = 0;
		gbc_BtnBaixar.gridy = 4;
		add(btnBaixar, gbc_BtnBaixar);

		scrollPane.setViewportView(table);
	}

	public ActionListener actionBuscar() {
		return new ActionListener() {
			private ResultadoModel modelo;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!cbFiltro.getSelectedItem().equals(TipoFiltro.NOME)) {
					if (tfValor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo de valor deve ser preenchido!");
						return;
					}

					if (!cbFiltro.getSelectedItem().equals(TipoFiltro.EXTENSAO)) {
						try {
							Integer.parseInt(tfValor.getText());
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Campo deve ser inteiro!");
							return;
						}
					}
				}

				try {
					mapaArquivos = MainApp.getServidor().procurarArquivo(tfBuscar.getText(),
							(TipoFiltro) cbFiltro.getSelectedItem(), tfValor.getText());
				} catch (Exception e) {
					PanelServidor.getTextArea().append("Erro ao buscar arquivo!\n" + e.getMessage());
				}

				modelo = new ResultadoModel(mapaArquivos);
				table.removeAll();
				table.setModel(modelo);
				configurarTabela();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		};
	}

	protected void configurarTabela() {
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setHeaderValue("Usuario");
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setHeaderValue("Arquivo");
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setHeaderValue("Extensao");
	}

	public ActionListener actionPublicar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Monitorador.getMonitorador(MainApp.PATH);
			}
		};
	}

	private ActionListener actionBaixar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado!");
				} else {
					Integer linha = table.getSelectedRow();
					Cliente cliente = (Cliente) table.getModel().getValueAt(linha, 5);
					Arquivo arquivo = (Arquivo) table.getModel().getValueAt(linha, 4);

					Registry registry;
					try {
						registry = LocateRegistry.getRegistry(cliente.getIp(), cliente.getPorta());
						IServer servidor = (IServer) registry.lookup(IServer.NOME_SERVICO);

						ler(arquivo, servidor.baixarArquivo(MainApp.getMeuCliente(), arquivo));
					} catch (RemoteException e) {
						PanelServidor.getTextArea().append("Erro ao baixar arquivo!\n" + e.getMessage());
					} catch (NotBoundException e) {
						PanelServidor.getTextArea().append("Erro ao baixar arquivo!\n" + e.getMessage());
					}
				}
			}
		};
	}

	public void ler(Arquivo arq, byte[] dados) {
		try {
			File arquivo = new File(MainApp.PATH + "Download " + arq.getNome() + "." + arq.getExtensao());
			Files.write(Paths.get(arquivo.getPath()), dados, StandardOpenOption.CREATE);

			String md5Novo = Md5Util.getMD5Checksum(arquivo.getAbsolutePath());
			if (!md5Novo.equals(arq.getMd5())) {
				JOptionPane.showMessageDialog(null, String.format(
						"Houve algum erro ao baixar o arquivo %s \n Ele podera estar corrompido!", arq.getNome()));
			} else {
				JOptionPane.showMessageDialog(null,
						String.format("O arquivo %s foi baixado com sucesso!", arq.getNome()));
			}
		} catch (IOException e) {
			PanelServidor.getTextArea().append("Erro ao baixar arquivo!\n" + e.getMessage());
		}
	}

	/**
	 * @return the listaArquivos
	 */
	public synchronized static List<Arquivo> getListaArquivos() {
		return listaArquivos;
	}

	/**
	 * @param listaArquivos the listaArquivos to set
	 */
	public synchronized static void setListaArquivos(List<Arquivo> listaArquivos) {
		PanelCliente.listaArquivos = listaArquivos;
	}
}
