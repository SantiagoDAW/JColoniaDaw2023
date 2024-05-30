package jcolonia.daw2023.dosventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaDos extends JFrame {
	private static final long serialVersionUID = 1L;

	private String título;
	private ControladorTicket control;

	private JPanel jpanelExterior;
	private JPanel jpanelBorde;
	private JPanel jpanelPrincipal;
	private JScrollPane jpanelDeslizante;
	private JTextArea jtexto;

	/**
	 * Create the frame.
	 * 
	 * 
	 * 
	 */
	public VentanaDos(ControladorTicket control, String nombreComercio, VentanaUno ventanaUno) {
		this.control = control;
		título = nombreComercio + " –2–";

		Point pos0 = ventanaUno.getLocation();
		pos0.translate(80, 80);

		initialize(pos0);
	}

	private void initialize(Point pos0) {
		setTitle(título);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		if (!Objects.isNull(pos0)) {
			setLocation(pos0);
		}

		jpanelExterior = new JPanel();
		jpanelExterior.setName("jpanelExterior");
		jpanelExterior.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().setLayout(new BorderLayout());
		setContentPane(jpanelExterior);
		jpanelExterior.setLayout(new BorderLayout(10, 10));
		jpanelExterior.add(getJpanelBorde(), BorderLayout.CENTER);
	}

	private JPanel getJpanelBorde() {
		if (jpanelBorde == null) {
			jpanelBorde = new JPanel();
			jpanelBorde
					.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jpanelBorde.setName("jpanelBorde");
			jpanelBorde.setLayout(new BorderLayout(10, 10));
			jpanelBorde.add(getJpanelPrincipal(), BorderLayout.CENTER);
		}
		return jpanelBorde;
	}

	private JPanel getJpanelPrincipal() {
		if (jpanelPrincipal == null) {
			jpanelPrincipal = new JPanel();
			jpanelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
			jpanelPrincipal.setName("jpanelPrincipal");
			jpanelPrincipal.setLayout(new BorderLayout(0, 0));
			jpanelPrincipal.add(getJpanelDeslizante(), BorderLayout.CENTER);
		}
		return jpanelPrincipal;
	}

	private JScrollPane getJpanelDeslizante() {
		if (jpanelDeslizante == null) {
			jpanelDeslizante = new JScrollPane();
			jpanelDeslizante.setName("jpanelDeslizante");
			jpanelDeslizante.setViewportView(getJtexto());
		}
		return jpanelDeslizante;
	}

	private JTextArea getJtexto() {
		if (jtexto == null) {
			jtexto = new JTextArea();
			jtexto.setName("jtexto");
		}
		return jtexto;
	}

	public void mostrarTexto(String texto) {
		JTextArea zonaTexto;
		zonaTexto = getJtexto();
		zonaTexto.setText(texto);
		zonaTexto.setCaretPosition(0);
	}
}
