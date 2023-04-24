package provinciasymunicipiosJPA.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import provinciasymunicipiosJPA.controllers.ControladorMunicipio;
import provinciasymunicipiosJPA.controllers.ControladorProvincia;
import provinciasymunicipiosJPA.models.Municipio;
import provinciasymunicipiosJPA.models.Provincia;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	private JTextField jtfFiltro;
	private final JButton jbtnFiltrar = new JButton("Filtrar");
	private JTextField jtfMunicipio;
	JComboBox<Provincia> jcmbProvincias;
	JComboBox<Municipio> jcmbMunicipios;
	public Main() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{345, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		setTitle("Gestión de municipios por provincia");
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 0;
		gbc_jtfFiltro.gridy = 1;
		getContentPane().add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		GridBagConstraints gbc_jbtnFiltrar = new GridBagConstraints();
		gbc_jbtnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_jbtnFiltrar.gridx = 1;
		gbc_jbtnFiltrar.gridy = 1;
		
		jbtnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCBMunicipio();
			}
		});
		getContentPane().add(jbtnFiltrar, gbc_jbtnFiltrar);
		
		jcmbMunicipios = new JComboBox<Municipio>();
		GridBagConstraints gbc_jcmbMunicipios = new GridBagConstraints();
		gbc_jcmbMunicipios.insets = new Insets(0, 0, 5, 5);
		gbc_jcmbMunicipios.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcmbMunicipios.gridx = 0;
		gbc_jcmbMunicipios.gridy = 2;
		getContentPane().add(jcmbMunicipios, gbc_jcmbMunicipios);
		
		JButton jbtnSeleccionar = new JButton("Seleccionar");
		jbtnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosSegunMunicipioSeleccionado();
			}
		});
		GridBagConstraints gbc_jbtnSeleccionar = new GridBagConstraints();
		gbc_jbtnSeleccionar.insets = new Insets(0, 0, 5, 0);
		gbc_jbtnSeleccionar.gridx = 1;
		gbc_jbtnSeleccionar.gridy = 2;
		getContentPane().add(jbtnSeleccionar, gbc_jbtnSeleccionar);
		
		JLabel lblNewLabel = new JLabel("Municipio Seleccionado:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(183, 255, 255));
		panel.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del municipio:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfMunicipio = new JTextField();
		GridBagConstraints gbc_jtfMunicipio = new GridBagConstraints();
		gbc_jtfMunicipio.insets = new Insets(0, 0, 5, 0);
		gbc_jtfMunicipio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfMunicipio.gridx = 1;
		gbc_jtfMunicipio.gridy = 0;
		panel.add(jtfMunicipio, gbc_jtfMunicipio);
		jtfMunicipio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre de la provincia:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jcmbProvincias = new JComboBox<Provincia>();
		GridBagConstraints gbc_jcmbProvincia = new GridBagConstraints();
		gbc_jcmbProvincia.insets = new Insets(0, 0, 5, 0);
		gbc_jcmbProvincia.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcmbProvincia.gridx = 1;
		gbc_jcmbProvincia.gridy = 1;
		panel.add(jcmbProvincias, gbc_jcmbProvincia);
		
		JButton jbtnGuardar = new JButton("Guardar");
		jbtnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarMunicipio();
			}
		});
		GridBagConstraints gbc_jbtnGuardar = new GridBagConstraints();
		gbc_jbtnGuardar.anchor = GridBagConstraints.EAST;
		gbc_jbtnGuardar.gridx = 1;
		gbc_jbtnGuardar.gridy = 2;
		panel.add(jbtnGuardar, gbc_jbtnGuardar);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	
	/**
	 * 
	 */
	private void cargarCBMunicipio() {
		jcmbMunicipios.removeAllItems();
		List<Municipio> lista = ControladorMunicipio.findByDescription(jtfFiltro.getText());
		
		for (Municipio c : lista) {
			this.jcmbMunicipios.addItem(c);
		}
	}
	
	/**
	 * 
	 */
	private void cargarDatosSegunMunicipioSeleccionado() {
		Municipio municipioSeleccionado = (Municipio) jcmbMunicipios.getSelectedItem();

		jtfMunicipio.setText(municipioSeleccionado.getNombre());

		List<Provincia> provincias = (List<Provincia>) ControladorProvincia.findProvinciaByDescripcionMunicipio(jcmbMunicipios.getSelectedItem().toString());

		jcmbProvincias.removeAllItems();

		for (Provincia c : provincias) {
		    jcmbProvincias.addItem(c);
		}

	}
	
	/**
	 * 
	 */
	private void guardarMunicipio() {
		   Municipio municipio = new Municipio();
		   municipio.setNombre(jcmbMunicipios.getSelectedItem().toString());
		   municipio.setProvincia((Provincia) jcmbProvincias.getSelectedItem());

		   ControladorMunicipio.modificacionEntidad(jcmbMunicipios.getSelectedItem().toString(), jtfMunicipio.getText());
		}

}
