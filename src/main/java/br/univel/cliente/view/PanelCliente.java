package br.univel.cliente.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.univel.jshare.comum.TipoFiltro;

public class PanelCliente extends JPanel {
	private JTable table;
	private JTextField tfBuscar;
	private JTextField tfValor;
	public PanelCliente() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{296, 271, 0};
		gridBagLayout.rowHeights = new int[]{57, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		gbl_panel.columnWidths = new int[]{55, 278, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{31, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
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
		
		JComboBox cbFiltro = new JComboBox(TipoFiltro.values());
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
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.anchor = GridBagConstraints.NORTH;
		gbc_btnBuscar.gridwidth = 2;
		gbc_btnBuscar.insets = new Insets(3, 5, 3, 5);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 1;
		add(btnBuscar, gbc_btnBuscar);
		
		JButton btnPublicar = new JButton("Publicar Minha Lista");
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
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton BtnBaixar = new JButton("Baixar");
		GridBagConstraints gbc_BtnBaixar = new GridBagConstraints();
		gbc_BtnBaixar.anchor = GridBagConstraints.SOUTH;
		gbc_BtnBaixar.fill = GridBagConstraints.HORIZONTAL;
		gbc_BtnBaixar.gridwidth = 2;
		gbc_BtnBaixar.insets = new Insets(3, 5, 3, 5);
		gbc_BtnBaixar.gridx = 0;
		gbc_BtnBaixar.gridy = 4;
		add(BtnBaixar, gbc_BtnBaixar);
	}

	public ActionListener actionBuscar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		};
	}
	
	public ActionListener actionPublicar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		};
	}
	
	public ActionListener actionBaixar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		};
	}
}
